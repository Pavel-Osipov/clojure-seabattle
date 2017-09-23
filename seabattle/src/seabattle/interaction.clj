(ns seabattle.interaction
  (:require [seabattle.logicals :refer :all])
  (:require [clojure.core :refer :all])
  (:require [seabattle.setup :refer :all])
  (:require [clojure.string :as cstr]))

(defn make-board-debug-text
  "Prepares the board as single strings and return that string"
  [board]
  (loop [n 0
         m 0
         board-s ""]
    (if (> n 11)
      board-s
      (if (<= m 11)
        (recur n (inc m) (cond
                           (is-cell-busy? board n m)  (str board-s "#")
                           (is-cell-empty? board n m) (str board-s ".")
                           (is-cell-alive? board n m) (str board-s "@")
                           (is-cell-dead? board n m)  (str board-s "*")))
        (recur (inc n) 0 (str board-s "\n"))))))

(defn make-board-user-text
  "Prepares the board as single strings and return that string"
  [board]
  (loop [n 1
         m 1
         board-s ""]
    (if (> n 10)
      board-s
      (if (<= m 10)
        (recur n (inc m) (cond
                           (is-cell-busy? board n m)  (str board-s ".")
                           (is-cell-empty? board n m) (str board-s ".")
                           (is-cell-alive? board n m) (str board-s ".")
                           (is-cell-fired? board n m) (str board-s "*")
                           (is-cell-dead? board n m)  (str board-s "#")))
        (recur (inc n) 1 (str board-s "\n"))))))

(defn letter-to-num
  "Converts letter to number, if parameter is not a letter returns nil"
  [letter]
  (let [ch (int letter)]
    (when (<= (int \a) ch (int \z))
      (inc (- (int ch) (int \a))))))

(defn read-move
  "Loops until move is Q! -> quit or move is valid.
  Returns vector [n m]"
  []
  (let [cmd (read-line)
        data (seq cmd)
        letter (first data)
        number (cstr/join "" (rest data))]
    (when-not (= cmd "Q!")
      (vector (Integer. number) (letter-to-num letter)))))

(defn show-progress
  "Shows game progress"
  [board]
  (println "For now scores are:")
  (println (str "Moves left: " (:moves-left board)))
  (println (str "Ships alive:  " (:alive-ships board)))
  (flush))

(defn show-results
  "Shows game outcome"
  [board]
  (println "**** The game is over!")
  (if (zero? (:alive-ships board))
    (println "You win, gratz!")
    (println "You lost or gave up!")))

(defn handle-move
  "Tries to handle move, which is given as vector"
  [board move]
  (let [moves (:moves-left board)
        n (first move)
        m (second move)
        new-board (assoc board :moves-left (dec moves))]
    (if (is-cell-alive? new-board n m)
      (-> new-board
          (assoc move :ship-dead)
          (assoc :alive-ships (dec (:alive-ships new-board))))
      (assoc new-board move :fired))))

(defn game-loop
  "Performs main game loop"
  []
  (let [board (setup-board)]
    (loop [current-board board]
      (println (make-board-user-text current-board))
      ;;(println (make-board-debug-text current-board))
      (flush)
      (show-progress current-board)
      (println "Enter your move as letter and number:")
      (flush)
      (let [move (read-move)]
        (flush)
        (if (or (nil? move) (zero? (:moves-left current-board)) (zero? (:alive-ships current-board)))
          (show-results current-board)
          (recur (handle-move current-board move)))))))
