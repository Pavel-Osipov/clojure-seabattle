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
