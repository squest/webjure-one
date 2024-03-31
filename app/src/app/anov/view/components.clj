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

;; dude gue mau bikin basic components buat website gue, ada header, footer, sidebar, sama main content
;; nah gue butuh elo bikinin component header, footer, sama sidebar aja (isinya links)
;; tapi udah masuk ke bagian html body ya, bukan head ya

;; nah skrg gue butuh untuk bagian head html yg include bootstrap js and css di atas, pake hiccup whatever

(defn head
  "Head component"
  []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:title "Starter Template for Bootstrap"]
   boostrap-css])

(defn header
  "Header component"
  []
  [:nav.navbar.navbar-expand-lg.navbar-light.bg-light
   [:div.container-fluid
    [:a.navbar-brand {:href "#"} "Navbar"]
    [:button.navbar-toggler {:type        "button"
                             :data-bs-toggle "collapse"
                             :data-bs-target "#navbarNav"
                             :aria-controls "navbarNav"
                             :aria-expanded "false"
                             :aria-label    "Toggle navigation"}
     [:span.navbar-toggler-icon]]]])

(defn footer
  "Footer component"
  []
  [:footer.footer.bg-light
   [:div.container-fluid
    [:span.text-muted "Place sticky footer content here."]]
   boostrap-js])

(defn sidebar
  "Sidebar component"
  []
  [:div.sidebar
   [:ul.nav.flex-column
    [:li.nav-item
     [:a.nav-link {:href "#"} "Active"]]
    [:li.nav-item
     [:a.nav-link {:href "#"} "Link"]]
    [:li.nav-item
     [:a.nav-link {:href "#"} "Link"]]
    [:li.nav-item
     [:a.nav-link {:href "#"} "Disabled" {:tabindex "-1" :aria-disabled "true"}]]]])