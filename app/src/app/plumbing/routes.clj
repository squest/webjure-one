(ns app.plumbing.routes
  (:require
    [reitit.ring :as ring]
    [app.utils :refer :all]
    [app.commons.web :as web]
    [app.anov.routes :as anov]))

(defn api-check
  "Helper function for testing api"
  [db request]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    {:status  "ok"
             :message "API is running fine"}})

(defn anov-view
  "Routes for anov frontend"
  [db openai midware]
  (anov/view-routes db openai midware))

(defn health-check
  [db]
  ["/health" {:get (partial api-check db)}])

(defn create-routes
  "Creates the whole routes for the system"
  [db openai]
  (ring/router
    [["/" {:get (partial api-check db)}]
     (health-check db)
     (anov-view db openai web/frontware-view)]))


