(ns try-2-creact.core
  (:require [reagent.core :as r] ))

(enable-console-print!)

(def initial-state {:skills [{:text "Soccer"}
                             {:text "Programming"}
                             {:text "Something Else"}
                             ]})

(defonce app-state (r/atom initial-state))

(defonce value (r/atom ""))

(defn skill-view [skill]
  [:div
    [:h2 (:text skill)]
    [:button#edit "edit"]
    [:button#delete "delete"]]
  )

(defn skill-list []
  [:ul
   (for [item (get @app-state :skills)]
         ^{:key item } [:li [skill-view item]])])

(defn new-skill [] {:text (str @value)})

(defn save-skill []
  (swap! app-state update-in [:skills] conj (new-skill)))



(defn my-app []
  [:div [:h1 "Creact - But with ClojureScript and Reagent"]
        [:h1 "Skills"]
        [:input {:type "text"
                 :value @value
                 :on-change #(reset! value (-> % .-target .-value))}]
        [:input {:type "button" 
                 :value "New Skill"
                 :on-click save-skill }]
        [skill-list]])



(defn mountit []
    (r/render-component [my-app] (js/document.getElementById "app"))) 
(mountit)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
