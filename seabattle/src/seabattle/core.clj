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

(defn can-place-ship-vertical
  "Tests if ship with given size can be placed vertically with given top point"
  [board toprow col size]
   (loop [r toprow
                d 1
               ans true]
      (if (> d size)
          ans
          (recur (inc r) (inc d) (and ans (or (is-cell-empty? board r col ) (is-cell-busy? board r col)))))))

(defn can-place-ship-horizontal
  "Tests if ship with given size can be placed horizontally with given left point"
  [board row leftcol size]
;;   (loop [c leftcol
;;                d 1
;;               ans true]
;;      (if (> d size)
;;         ans
;;          (recur (inc r) (inc d) (and ans (or (is-cell-empty? board r col ) (is-cell-busy? board r col))))))
  )

(defn place-4-ship-vertical
  "Places 4-cell ship vertically, it's assumed this ship will be first, so function is to be called on empty board. Returs new board."
  [board]
  (let [row (inc (rand-int 7))
           col (inc (rand-int 10))]

    ))


(defn place-4-ship-horizontal
  "Places 4-cell ship horizontally, it's assumed this ship will be first, so function is to be called on empty board. Returs new board."
  [board]
  (let [row (inc (rand-int 10))
           col (inc (rand-int 7))]

    ))


(defn place-4-ship
   "Places 4-cell ship on  the board, and returns new board"
  [board]
  (let [direction  (rand-int 2)]
    (cond
      (= 0 direction) (place-4-ship-vertical board)
      (= 1 direction) (place-4-ship-horizontal board)
      :default (println "\n Some error happened")
      )))

(defn place-3-ship
   "Places 3-cell ship on  the board, and returns new board"
  [board]

  )

(defn place-2-ship
   "Places 3-cell ship on  the board, and returns new board"
  [board]

  )


(defn place-1-ship
   "Places 3-cell ship on  the board, and returns new board"
  [board]


  )


(defn make-board-text
  "Prepares the board as single strings and return that string"
  [board]
  (loop [n 0
         m 0
         board-s ""]
    (if (> n 11)
      board-s
        (if (<= m 11)
          (recur n (inc m) (if (is-cell-busy? board n m)
                             (str board-s "#")
                             (str board-s ".")))
          (recur (inc n) 0 (str board-s "\n"))))))


(defn show-board
  "Shows the board state. Used for debug purposes only"
  [board]
  (println (make-board-text board)))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (make-board-text (make-border (make-empty-board)))))
