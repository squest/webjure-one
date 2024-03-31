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
            (com/header)
            [:div.container
             [:h1 "Hellow world"]]
            (com/footer)]))
    (catch Exception e
      (error e))))
