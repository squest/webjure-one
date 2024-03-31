(ns app.plumbing.db
  (:require
    [com.stuartsierra.component :as component]
    [app.utils :refer :all]
    [monger.core :as mg]))

(declare clear-db)

(defrecord Dbase [db-mongo-config other-config]
  component/Lifecycle
  (start [this]
    (info "Starting the database component")
    (pres db-mongo-config)
    (let [conn (mg/connect db-mongo-config)
          db-instance (mg/get-db conn (:db db-mongo-config))]
      ;; just in case need some clearance
      ;; (clear-db db-instance)
      (info "Starting the database")
      (assoc this :db db-instance :conn conn)))
  (stop [this]
    (when-let [conn (:conn this)]
      (mg/disconnect conn))
    (info "Database stopped")
    (info "Scheduler stopped")
    (dissoc this :conn)))

(defn create-database-component [db-mongo-config other-config]
  (map->Dbase {:db-mongo-config db-mongo-config
               :other-config    other-config}))

;; read the app.schema so you know the structure







