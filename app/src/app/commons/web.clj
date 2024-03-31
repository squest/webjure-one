(ns app.commons.web
  (:require
    [app.utils :refer :all]
    [java-time :as t]))

(defn frontware-view
  "Midware for frontend"
  [fun db openai request]
  (info "=======================================================================")
  (info "URI : " (:uri request))
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (fun db openai request)})

