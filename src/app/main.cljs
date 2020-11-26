(ns app.main
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

;; Main application ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def error (r/atom nil))
(def loaded? (r/atom false))
(def items (r/atom []))
(def query (r/atom ""))

(defn run-query []
  (println (js/Date.) "run-query")
  (clj->js [{:index 0 :title "first dummy" :pageid 100} {:index 1 :title "second dummy" :pageid 202}])
  )

(defn queryChanged [evt]
  (let [new-value (evt.target.value)]
    (reset! query new-value)
    (run-query)))

(defn main-app []
  (cond
    @error [:div (str "Error: " @error)]
    (false? @loaded?) [:div "Loading..."]
    :else
    [:<>
     [:input {:type "text" :placeholder "Search terms" :value @query :onChange queryChanged}]
     [:h3 "Search results"]
     [:ul
      (mapv (fn [item] [:li {:key (.-index item)} (str (.-title item) (.-pageid item))]) @items)
      ]]
    ))

;; App initialization ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn mount-root []
  (rdom/render [main-app] (.getElementById js/document "app"))
  (run-query))

(defn init-ui []
  (mount-root))

(defn main! []
  (uifabric/initializeIcons)
  (init-ui))

(defn ^:dev/after-load reload! []
  (init-ui))

