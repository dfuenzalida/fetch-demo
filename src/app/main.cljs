(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [cljs.core.async :refer [go]]
            [cljs.core.async.interop :refer-macros [<p!]]))

;; Main application ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def error (r/atom nil))
(def loaded? (r/atom true))
(def items (r/atom []))
(def query (r/atom ""))

(defn run-query []
  (let [url (str "https://en.wikipedia.org/w/api.php?action=query&origin=*&format=json"
                 "&generator=search&gsrnamespace=0&gsrlimit=5&gsrsearch=%27" @query "%27")]
    (go
      (try
        (let [response (<p! (js/fetch url))
              result   (<p! (.json response))
              pages    (-> (js->clj result :keywordize-keys true) :query :pages vals)]
          (reset! loaded? true)
          (reset! items pages))
        (catch js/Error err (reset! error (ex-cause err)))))
    ))

(defn query-changed [evt]
  (let [new-value (.-value (.-target evt))]
    (reset! query new-value)
    (run-query)))

(defn main-app []
  (cond
    @error [:div (str "Error: " @error)]
    (false? @loaded?) [:div "Loading..."]
    :else
    [:<>
     [:input {:type "text" :placeholder "Search terms" :value @query :onChange query-changed}]
     [:h3 "Search results"]
     [:ul
      (when (seq @items)
        (map (fn [item]
               [:li {:key (:index item)} (str (get item :title) " " (get item :pageid))]) @items))
      ]]
    ))

;; App initialization ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root []
  (rdom/render [main-app] (.getElementById js/document "app"))
  (run-query))

(defn init-ui []
  (mount-root))

(defn main! []
  (init-ui))

(defn ^:dev/after-load reload! []
  (init-ui))

