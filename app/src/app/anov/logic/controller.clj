(ns app.anov.logic.controller
  (:require [app.anov.logic.db :as db]
            [app.anov.logic.generator :as gen]
            [app.utils :refer :all]
            [app.anov.view.homepage :as home-page]))

(defn request-article
  "Request article based on user input"
  [db openai request]
  (pres request)
  (let [{:keys [title n-sections prompt]} request
        gened-sections (try (gen/generate-sections openai request)
                            (catch Exception e (error e)))]
    gened-sections))

(defn api-req-article
  "API version for requesting article"
  [db openai request]
  (let [data (get-in request [:body])]
    (pres data)
    (if (= #{:title :n-sections :prompt} (set (keys data)))
      (let [result (request-article db openai data)]
        (info "Hasil dari request article controller")
        (pres result)
        {:status 200
         :body result})
      {:status 400
       :body   "Bad request"})))

(defn process-request
  "Process request from a form submission"
  [db openai request]
  (pres request)
  (let [params (-> (get-in request [:params])
                   (update-in [:n-sections] #(Integer/parseInt %)))
        result (gen/generate-sections openai params)]
    (home-page/process-done db openai request)))


























