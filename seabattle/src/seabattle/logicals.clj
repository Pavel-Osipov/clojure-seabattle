(ns seabattle.logicals)

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

