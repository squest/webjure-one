(ns app.anov.routes
  (:require [app.utils :refer :all]
            [hiccup2.core :as h]
            [ring.util.response :as resp]
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
   ["/" {:get (partial health-check db openai)}]
   ["" {:get (partial midware home/homepage db openai)}]])


