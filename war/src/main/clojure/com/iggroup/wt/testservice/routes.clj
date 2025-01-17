(ns com.iggroup.wt.testservice.routes
  "Contains all the http endpoints, to be splitted if it becomes too big"
  (:require
    [ring.util.http-response :refer :all]
    [compojure.api.sweet :refer :all]
    [compojure.route :as route]
    [com.iggroup.wt.testservice.core :as core]
    [com.iggroup.wt.testservice.db :as db]
    [schema.core :as s]))

(def counter (atom 0))

;;;
;;; Http API
;;;

(defapi http-api
        (swagger-ui)
  (swagger-docs
    {:info {:title "Sample api"}})

  (GET* "/ping" []
        :summary "ping pong"
        :query-params [who :- String]
        (ok {:who "me!"}))

  (route/not-found "<h1>Page not found</h1>"))


(comment
  (do
    (-> (slurp "http://localhost:8080/testservice/total?client=1")
        (cheshire.core/parse-string true)
        (try (catch Exception e e))
        >pprint
        future)
    nil))

(comment
  @counter
  (reset! counter 1020202)
  (user/reset))
