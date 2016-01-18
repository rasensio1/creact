(ns try-2-creact.core
  (:require [reagent.core :as r] ))

(enable-console-print!)

(def initial-state {:skills []})

(defonce app-state (r/atom initial-state))
(defonce value (r/atom ""))
(defonce counter (atom 0))

(defn new-skill [] {:id (swap! counter inc)
                    :text (str @value)})

(defn save-skill []
  (swap! app-state update-in [:skills] conj (new-skill)))

(defn new-list [id]
  (remove #(= id (:id % )) (get @app-state :skills)))

(defn delete-skill [id]
  (swap! app-state assoc :skills (new-list id)))

(defn skill-view [skill]
  [:div
    [:h2 (:text skill)]
    [:input {:type "button"
             :value "edit"
             :on-click #(println "edit!")}]
    [:input {:type "button"
             :value "delete"
             :on-click #(delete-skill (:id skill))}]
    ])

(defn skill-list []
  [:ul
   (for [item (get @app-state :skills)]
         ^{:key item } [:li [skill-view item]])])

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
