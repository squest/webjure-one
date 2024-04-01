(ns app.anov.routes
  (:require [app.utils :refer :all]
            [hiccup2.core :as h]
            [ring.util.response :as resp]
            [app.anov.logic.controller :as ctrl]
            [app.anov.view.homepage :as home]))

(defn health-check
  "Helper function for testing api"
  [db openai request]
  (pres (get-in request [:cookies]))
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (h/html [:center [:h1 "Helloooow World!"]]))
   :cookies {"email" {:value "sabdaps@gmail.com"}}})

(defn view-routes
  [db openai midware]
  ["/anov"
   ["" {:get (partial midware home/homepage db openai)}]
   ["/" {:get (partial health-check db openai)}]
   ["/request" {:get (partial midware home/request-form db openai)
                :post (partial midware ctrl/process-request db openai)}]
   ["/articles" {:get (partial midware home/article-list db openai)}]
   ["/article/:id" {:get (partial midware home/article-detail db openai)}]])

(defn api-routes
  [db openai midware]
  ["/api/anov"
   ["/request-article" {:post (partial midware ctrl/api-req-article db openai)}]])


