(ns seabattle.interaction
  (:require [seabattle.logicals :refer :all])
  (:require [clojure.core :refer :all])
  (:require [seabattle.setup :refer :all]))

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
  (loop [n 0
         m 0
         board-s ""]
    (if (> n 11)
      board-s
        (if (<= m 11)
          (recur n (inc m) (cond
                             (is-cell-busy? board n m)  (str board-s ".")
                             (is-cell-empty? board n m) (str board-s ".")
                             (is-cell-alive? board n m) (str board-s ".")
                             (is-cell-dead? board n m)  (str board-s "*")))
          (recur (inc n) 0 (str board-s "\n"))))))


(defn letter-to-num
  "Converts letter to number, if parameter is not a letter returns nil"
  [letter]
  (let [ch (int (first letter))]
    (if (<= (int \a) ch (int \z))
      (inc (- (int ch) (int \a)))
      nil)))

(defn read-move
  "Loops until move is QQ -> quit or move is valid.
  Returns vector [n m]"
  []
  (let [cmd (read-line)
        letter (first cmd)
        number (rest cmd)]
    (if (= cmd "QQ")
        nil
       (vector (Integer. number) (letter-to-num letter)))))

(defn show-results
  "Shows game outcome"
  []
  nil
  )

(defn handle-move
  "Tries to handle move, which is given as vector"
  [board [m n]]
  (when (is-cell-alive? m n)
      (-> board
      (assoc (vector m n) :ship-dead)
      (assoc :alive-ships (dec (:alive-ships board))))))


(defn game-loop
  "Performs main game loop"
  []
  (let [board (setup-board)]
    (loop [current-board board]
      (do
        (println (make-board-user-text current-board))
        (println "Enter your move as letter and number:")
        (let [move read-move]
          (if (= move nil)
              (show-results)
              (recur (handle-move current-board move))))))))


