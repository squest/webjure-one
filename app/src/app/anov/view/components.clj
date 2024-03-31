(ns app.anov.view.components
  (:require [app.utils :refer :all]
            [hiccup2.core :as h]
            [hiccup.page :as page]
            [hiccup.form :as form]))

;; Create a component for including bootstrap, this is the html, translate it into hiccup
(def boostrap-css
  [:link {:href        "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          :rel         "stylesheet"
          :integrity   "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          :crossorigin "anonymous"}])

(def boostrap-js
  [:script {:src         "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            :integrity   "sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            :crossorigin "anonymous"}])

;; My side mostly the navigation is on the left side, with each link having an icon
;; The sidebar consists of the following links:
;; Home, Boog ideas, Book Generator, Manage books, Author profile

(defn sidebar [userId]
  [:div {:class "d-flex flex-column p-3 text-white bg-dark"}
   [:a {:href "#"}
    [:img {:src "/img/logo.png" :alt "Logo" :class "d-block mx-auto mb-4"}]]
   [:ul {:class "nav nav-pills flex-column mb-auto"}
    [:li {:class "nav-item"} [:a {:href "#" :class "nav-link active"} [:i {:class "bi bi-house-door"}] " Home"]]
    [:li {:class "nav-item"} [:a {:href "#" :class "nav-link"} [:i {:class "bi bi-lightbulb"}] " Book Ideas"]]
    [:li {:class "nav-item"} [:a {:href "#" :class "nav-link"} [:i {:class "bi bi-book"}] " Book Generator"]]
    [:li {:class "nav-item"} [:a {:href "#" :class "nav-link"} [:i {:class "bi bi-book-half"}] " Manage Books"]]
    [:li {:class "nav-item"} [:a {:href "#" :class "nav-link"} [:i {:class "bi bi-person"}] " Author Profile"]]]])

;; The main content of the page is on the right side, this is where the user will see the content
;; The main content consists of the following:
;; The title of the page, the content of the page, and the footer of the page

(defn footer []
  [:footer {:class "footer mt-auto py-3 bg-light"}
   [:div {:class "container"} [:span {:class "text-muted text-center"} "Place sticky footer content here."]]])

;; The footer of the page is at the bottom of the page, this is where the user will see the footer
;; The footer consists of the following:
;; The footer of the page

(defn main-content []
  [:div {:class "col"}
   [:h1 "Hello, world!"]
   [:p "This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information."]
   [:p "It uses utility classes for typography and spacing to space content out within the larger container."]
   (footer)])


