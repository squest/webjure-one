(ns app.system
  (:require
    [com.stuartsierra.component :as component]
    [app.plumbing.db :as db]
    [app.utils :refer :all]
    [app.plumbing.server :as immut]
    [app.plumbing.handler :as http]
    [app.plumbing.openai :as openai]))

(defn create-system
  "It creates a system, and return the system, but not started yet"
  []
  (let [{:keys [server-path
                server-port
                server-host
                db-mongo-uri
                db-mongo-port
                db-mongo-name
                db-mongo-quiet
                db-mongo-debug
                openai-url
                openai-token]} (read-config-true-flat)
        server {:port server-port :path server-path :host server-host}
        db-mongo {:uri   db-mongo-uri
                  :port  db-mongo-port
                  :db    db-mongo-name
                  :quiet db-mongo-quiet
                  :debug db-mongo-debug}
        other-config {:openai-url   openai-url
                      :openai-token openai-token}]
    (info "Preparing the system")
    (pres db-mongo)
    (pres other-config)
    (pres server)
    (component/system-map
      :dbase (db/create-database-component db-mongo other-config)
      :openai (openai/create-openai-component openai-url openai-token)
      :handler (component/using (http/create-handler-component) [:dbase :openai])
      :server (component/using (immut/create-server-component server) [:handler]))))
