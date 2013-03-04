(ns tboyt.arenaplayback.connect4
  (:use 
    [jayq.core :only [$ append]]
    [clojure.string :only [replace split]]))

(defn render-board [game-state element] 
  (.bind game-state :current-move-index "#current-move")
  (doseq [i (range 6)]
    (let [row-element ($ "<div class='row'></div>")]
         (append element row-element)
         (doseq [j (range 7)]
           (let [name (str i "x" j)] 
                (append row-element ($ (str "<div class='cell' id='cell" name "'></div>")))
                (.set game-state name "empty")
                (.bind game-state name (str "#cell" name) (clj->js {:type "class"})))))))

(defn parse-moves [moves]
  ; replace various characters with nothing
  (.log js/console moves)
  (map js/parseInt
        (-> moves
          (replace #"[\s|\[|\]]" "")
          (split ","))))

; immutable-game-state, int -> immutable-game-state
(defn play-move [game-state move]
  )