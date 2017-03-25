(ns seabattle.setup)

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
      (recur (inc x) (assoc new-board (vector x 0 ) :busy
                                      (vector 0 x ) :busy
                                      (vector x  11) :busy
                                      (vector  11 x)  :busy)))))

(defn surround-ship
  "Surround the given ship with :busy cells.
  Returns new board."
  [board ship-type ship-size start other]
  (loop [new-board board
         d 1
         n (dec start)
         m other]
    (if (> d (+ ship-size 2))
      (if (= ship-type :hor)
          (assoc new-board (vector m (dec start)) :busy
                           (vector m (+ start ship-size)) :busy)
          (assoc new-board (vector (dec start) m) :busy
                           (vector (+ start ship-size) m) :busy))
      (recur (if (= ship-type :hor)
                 (assoc new-board (vector (dec m) n) :busy
                                  (vector (inc m) n) :busy)
                 (assoc new-board (vector n (dec m)) :busy
                                  (vector n (inc m)) :busy))
             (inc d)
             (inc n)
             m))))

(defn place-ship-vertical
  "Places ship vertically, assumed that it's possible
  Returs new board."
  [board ship-size start-row ncol]
  (loop [new-board board
         d 1
         r start-row
         c ncol]
    (if (> d ship-size)
      (surround-ship new-board :vert ship-size start-row ncol)
      (recur (assoc new-board (vector r c) :ship-alive)
             (inc d)
             (inc r)
             c))))


(defn place-ship-horizontal
  "Places 4-cell ship horizontally, assumed that it's possible
  Returs new board."
  [board ship-size start-col nrow]
  (loop [new-board board
         d 1
         r nrow
         c start-col]
    (if (> d ship-size)
      (surround-ship new-board :hor ship-size start-col nrow)
      (recur (assoc new-board (vector r c) :ship-alive)
             (inc d)
             r
             (inc c)))))
