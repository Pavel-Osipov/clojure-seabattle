(ns seabattle.core
  (:require [seabattle.setup :refer :all])
  (:require [seabattle.logicals :refer :all])
  (:gen-class))




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


(defn -main
  "Run that game :)"
  [& args]
  (let [board (setup-board)]
      (println (make-board-debug-text board))
      (println (make-board-user-text board))
    ))
