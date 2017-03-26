(ns seabattle.core
  (:require [seabattle.setup :refer :all])
  (:gen-class))


(defn is-cell-empty?
  "Tests if given cell on the board is empty"
  [board n m]
  (= :empty (get  board (vector n m ))))

(defn is-cell-busy?
  "Tests if given cell on the board is busy"
  [board n m]
  (= :busy (get  board (vector n m ))))

(defn is-cell-alive?
  "Tests if given cell on the board is alive part of the ship"
  [board n m]
  (= :ship-alive (get  board (vector n m ))))

(defn is-cell-dead?
  "Tests if given cell on the board is dead part of the ship"
  [board n m]
  (= :ship-dead (get  board (vector n m ))))

(defn can-place-ship-vertical?
  "Tests if ship with given size can be placed vertically with given top point"
  [board toprow ncol ship-size]
   (loop [r toprow
          d 1
          ans true]
      (if (> d ship-size)
          ans
          (recur (inc r) (inc d) (and ans (is-cell-empty? board r ncol))))))

(defn can-place-ship-horizontal?
  "Tests if ship with given size can be placed horizontally with given left point"
  [board nrow leftcol ship-size]
   (loop [c leftcol
          d 1
          ans true]
      (if (> d ship-size)
          ans
          (recur (inc c) (inc d) (and ans (is-cell-empty? board nrow c) )))))


(defn place-ship
   "Places ship on the board, in random place and returns new board"
  [board ship-size]
  (let [direction  (rand-int 2)
        other (inc (rand-int 10))
        start (inc (rand-int 7))]
    (cond
      (= 0 direction) (if (can-place-ship-vertical? board start other ship-size)
                          (place-ship-vertical board ship-size start other)
                          (place-ship board ship-size))
      (= 1 direction) (if (can-place-ship-horizontal? board other start ship-size)
                          (place-ship-horizontal board ship-size start other)
                          (place-ship board ship-size)))))


(defn make-board-text
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


(defn show-board
  "Shows the board state. Used for debug purposes only"
  [board]
  (println (make-board-text board)))


(defn -main
  "Run that game :)"
  [& args]
  (println (-> (make-empty-board)
               (make-border)
               (place-ship 4)
               (place-ship 3)
               (place-ship 3)
               (place-ship 2)
               (place-ship 2)
               (place-ship 2)
               (place-ship 1)
               (place-ship 1)
               (place-ship 1)
               (place-ship 1)
               (make-board-text))))
