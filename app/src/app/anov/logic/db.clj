(ns app.anov.logic.db
  (:require [app.utils :refer :all]
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

