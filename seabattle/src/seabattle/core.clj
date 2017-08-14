(ns seabattle.core
  (:require [seabattle.setup :refer :all])
  (:require [seabattle.logicals :refer :all])
  (:require [seabattle.interaction :refer :all])
  (:gen-class))


(defn -main
  "Run that game :)"
  [& args]
  (game-loop))
