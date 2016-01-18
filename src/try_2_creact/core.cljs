(ns try-2-creact.core
  (:require [reagent.core :as r] ))

(enable-console-print!)

(def initial-state {:skills [{:text "Soccer"}
                                   {:text "Programming"}
                                   {:text "Something Else"}
                                   ]})

(def app-state (atom initial-state))

(defn skill-view [skill]
  [:h2 (:text skill)])

(defn skill-list []
  [:ul
   (for [item (get @app-state :skills)]
         ^{:key item } [:li (:text item)])])


(println (first (get @app-state :skills)))

(println "hellooo")

(defn my-app []
  [:div [:h1 "Creact - But with ClojureScript and Reagent"]
        [:h1 "Skills"]
        [skill-list]])


(defn mountit []
    (r/render-component [my-app] (js/document.getElementById "app"))) 

(mountit)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)