;;  OK, let the board be a hashmap with vector keys and following values:
;;       :empty -- cell empty
;;       :ship-alive -- here is alive part of a ship
;;       :ship-dead -- here is dead part of ship
;;       :busy -- cell is near a ship, so another ship can't be placed here
;;
;;  Also, let the board be actually 12x12 with :busy values on the borders,
;;  just to save program's logic from decisions abut existence of the cells
;;
;;
;;  Also, let's keep a value of ships parts already hit, if this value reaches 4 + 3 + 3 + 2 + 2 + 2 + 1 + 1 + 1 + 1 = 20
;;  game is over
;;
;;

(ns seabattle.core
  (:gen-class))

(defn make-empty-board
  "Makes empty board as hashmap, returns board"
  [ ]
  (loop [n 11
             m 11
             board {}]

        (if ( < n 0)
          board
          (if (> m 0)
            (recur n (dec m)  (assoc board [n m] :empty))
            (recur (dec n) 11 (assoc board [n m] :empty))))))

(defn make-border-for-board
  "Makes :busy border for the board. Returns new board"
  [board]
  (loop [x 0
              new-board board]
    (if (> x 11)
      new-board
      (recur (inc x) (assoc new-board (vector x 0 ) :busy (vector 0 x ) :busy  (vector x  11) :busy  (vector  11 x)  :busy)))))

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

(defn is-cell-alive?
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
   (loop [c leftcol
                d 1
               ans true]
      (if (> d size)
          ans
          (recur (inc r) (inc d) (and ans (or (is-cell-empty? board r col ) (is-cell-busy? board r col)))))))

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

(defn show-board
  "Shows the board state. Used for debug purposes only"
  [board]
  (loop [n 0
              m 0]
    (if (> n 11)
      true
      (do
        (cond
          (is-cell-empty? board n m) (print ".")
          (is-cell-busy? board n m) (print "#")
          :default (print "*")
          )
        (if (= m 11)
          (do
            (println)
            (recur (inc n) 0)
          )
          (recur n (inc m))
      )))))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
