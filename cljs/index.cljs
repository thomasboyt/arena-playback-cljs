(ns tboyt.arenaplayback
  (:require [tboyt.arenaplayback.connect4 :as connect4])
  (:use [jayq.core :only [$ css val toggle-class bind]]))

(def $board ($ :#board))
(def $game-view ($ :#game-view))
(def game-state (js/DomBindable.))

(defn toggle-game-view []
  (toggle-class $game-view "hidden"))

(bind ($ :#load-game) "click" (fn [e] 
  (.set game-state "moves" (connect4/parse-moves (val ($ :textarea))))
  (.set game-state :current-move-index 0)
  (connect4/render-board game-state $board)
  (toggle-game-view)))

(bind ($ :#forward-step) "click" (fn [e]
  (connect4/play-move )))