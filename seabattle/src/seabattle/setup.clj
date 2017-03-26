(ns seabattle.setup
  (:require [seabattle.logicals :refer :all]))

(defn make-empty-board
  "Makes empty board as hashmap, returns board"
  []
  (loop [n 11
         m 11
        board {}]
    (if ( < n 0)
      board
      (if (> m 0)
          (recur n (dec m)  (assoc board [n m] :empty))
          (recur (dec n) 11 (assoc board [n m] :empty))))))

(defn make-border
  "Makes :busy border for the board. Returns new board"
  [board]
  (loop [x 0
         new-board board]
    (if (> x 11)
      new-board
      (recur (inc x) (assoc new-board (vector x 0 )  :busy
                                      (vector 0 x )  :busy
                                      (vector x 11)  :busy
                                      (vector 11 x)  :busy)))))

(defn surround-ship
  "Surround the given ship with :busy cells.
  Returns new board."
  [board ship-type ship-size start other]
  (loop [new-board board
         cellnum 1
         n (dec start)]
    (if (> cellnum (+ ship-size 2))
      (if (= ship-type :hor)
          (assoc new-board (vector other (dec start))         :busy
                           (vector other (+ start ship-size)) :busy)
          (assoc new-board (vector (dec start) other)         :busy
                           (vector (+ start ship-size) other) :busy))
      (recur (if (= ship-type :hor)
                 (assoc new-board (vector (dec other) n) :busy
                                  (vector (inc other) n) :busy)
                 (assoc new-board (vector n (dec other)) :busy
                                  (vector n (inc other)) :busy))
             (inc cellnum)
             (inc n)))))

(defn place-ship-vertical
  "Places ship vertically, assumed that it's possible
  Returs new board."
  [board ship-size start-row ncol]
  (loop [new-board board
         cellnum 1
         r start-row]
    (if (> cellnum ship-size)
      (surround-ship new-board :vert ship-size start-row ncol)
      (recur (assoc new-board (vector r ncol) :ship-alive)
             (inc cellnum)
             (inc r)))))

(defn place-ship-horizontal
  "Places 4-cell ship horizontally, assumed that it's possible
  Returs new board."
  [board ship-size start-col nrow]
  (loop [new-board board
         cellnum 1
         c start-col]
    (if (> cellnum ship-size)
      (surround-ship new-board :hor ship-size start-col nrow)
      (recur (assoc new-board (vector nrow c) :ship-alive)
             (inc cellnum)
             (inc c)))))

(defn can-place-ship-vertical?
  "Tests if ship with given size can be placed vertically with given top point"
  [board toprow ncol ship-size]
   (loop [r toprow
          cellnum 1
          ans true]
      (if (> cellnum ship-size)
          ans
          (recur (inc r)
                 (inc cellnum)
                 (and ans (is-cell-empty? board r ncol))))))

(defn can-place-ship-horizontal?
  "Tests if ship with given size can be placed horizontally with given left point"
  [board nrow leftcol ship-size]
   (loop [c leftcol
          cellnum 1
          ans true]
      (if (> cellnum ship-size)
          ans
          (recur (inc c)
                 (inc cellnum)
                 (and ans (is-cell-empty? board nrow c))))))

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


(defn setup-board
  "Takes no parameters, returns border with all the ships on"
  []
  (->  (make-empty-board)
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
       (place-ship 1)))

