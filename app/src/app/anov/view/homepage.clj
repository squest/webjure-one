(ns app.anov.view.homepage
  (:require
    [hiccup2.core :as h]
    [app.utils :refer :all]
    [app.anov.view.components :as com]))

;; Jadi homepagenya hellow world aja, tapi udah pake component yg ada di com ya

(defn homepage
  "Homepage"
  [db openai request]
  (try
    (str (h/html
           (com/head)
           [:body
            [:div.container
             [:div.row (com/header)]
             [:div.row
              [:div.col-md-3 (com/sidebar)]
              [:div.col-md-9 [:h1 "Hellow world"]]]
             [:div.row (com/footer)]]]))
    (catch Exception e
      (error e))))

(defn request-form
  "Request form"
  [db openai request]
  (try
    (str (h/html
           (com/head)
           [:body
            [:div.container
             [:div.row (com/header)]
             [:div.row
              [:div.col-md-3 (com/sidebar)]
              [:div.col-md-9
               [:form {:action "/anov/request" :method "post"}
                [:div.form-group
                 [:label {:for "title"} "Title"]
                 [:input.form-control {:type "text" :id "title" :name "title"}]]
                [:div.form-group
                 [:label {:for "n-sections"} "Number of sections"]
                 [:input.form-control {:type "number" :id "n-sections" :name "n-sections"}]]
                [:div.form-group
                 [:label {:for "prompt"} "Prompt"]
                 [:textarea.form-control {:id "prompt" :name "prompt"}]]
                [:button.btn.btn-primary {:type "submit"} "Submit"]]]]
             [:div.row (com/footer)]]]))
    (catch Exception e
      (error e))))

(defn process-done
  "Process done"
  [db openai request]
  (try
    (str (h/html
           (com/head)
           [:body
            [:div.container
             [:div.row (com/header)]
             [:div.row
              [:div.col-md-3 (com/sidebar)]
              [:div.col-md-9
               [:h1 "Request has been processed"]
               [:a {:href "/anov/articles"} "See generated articles"]]]
             [:div.row (com/footer)]]]))
    (catch Exception e
      (error e))))

(defn article-list
  "Article list"
  [db openai request]
  (try
    (str (h/html
           (com/head)
           [:body
            [:div.container
             [:div.row (com/header)]
             [:div.row
              [:div.col-md-3 (com/sidebar)]
              [:div.col-md-9
               [:h1 "List of generated articles"]
               [:ul
                [:li [:a {:href "/anov/article/1"} "Article 1"]]
                [:li [:a {:href "/anov/article/2"} "Article 2"]]
                [:li [:a {:href "/anov/article/3"} "Article 3"]]]]]
             [:div.row (com/footer)]]]))
    (catch Exception e
      (error e))))

(defn article-detail
  "Article detail"
  [db openai request]
  (try
    (str (h/html
           (com/head)
           [:body
            [:div.container
             [:div.row (com/header)]
             [:div.row
              [:div.col-md-3 (com/sidebar)]
              [:div.col-md-9
               [:h1 "Article 1"]
               [:p "This is the content of article 1"]]]
             [:div.row (com/footer)]]]))
    (catch Exception e
      (error e))))


