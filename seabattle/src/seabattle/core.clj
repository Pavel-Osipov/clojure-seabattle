(ns seabattle.core
  (:require [seabattle.setup :refer :all])
  (:require [seabattle.logicals :refer :all])
  (:require [seabattle.interaction :refer :all])
  (:gen-class))




(defn -main
  "Run that game :)"
  [& args]
  (let [board (setup-board)]
      (println (make-board-debug-text board))
      (println (make-board-user-text board))
    ))
