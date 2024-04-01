(ns app.anov.logic.db
  (:require [app.utils :refer :all]
            [clojure.string :as cs]
            [monger.collection :as mc]))

(defn add-article
  "Add article into the db, based on user input"
  [db article-data]
  ;; article-data should contain :_id, :title, :n-sections, :complete? and :prompt
  (->> {:sections (repeat (:n-sections article-data) {})}
       (merge article-data)
       (mc/insert-and-return (:db db) "articles")))

(defn add-section
  [db article-id section-data]
  (if-let [article (mc/find-map-by-id (:db db) "articles" article-id)]
    (let [order (:order section-data)
          updated-article (assoc-in article [:sections order] section-data)
          complete? (every? not-empty? (:sections updated-article))]
      (->> (assoc updated-article :complete? complete?)
           (mc/update-by-id (:db db) "articles" article-id)))
    (error "Article not found")))

(defn get-article
  [db article-id]
  (mc/find-map-by-id (:db db) "articles" article-id))

(defn all-articles
  "Get all articles from db, but only _id, complete?, and title"
  [db]
  (mc/find-maps (:db db) "articles" {} [:_id :complete? :title]))

;; &lt;/pre&gt;

(defn fix-db
  [db]
  (try (let [articles (mc/find-maps (:db db) "articles" {})]
         (doseq [article articles]
           (let [updated (let [sections (:sections article)]
                           (-> (for [section (->> (remove empty? sections)
                                                  (filter #(string? (:section-content %))))]
                                 {:section-title   (:section-title section)
                                  :section-content (let [res (-> (cs/replace (:section-content section) #"&lt;" "<")
                                                                 (cs/replace #"&gt;" ">"))]
                                                     (pres res)
                                                     res)
                                  :order           (:order section)})
                               vec))]
             (mc/update-by-id (:db db) "articles" (:_id article) (merge article
                                                                        {:n-sections (count updated)}
                                                                        {:sections updated})))))
       (catch Exception e
         (error e))))


