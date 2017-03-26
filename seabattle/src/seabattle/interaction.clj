(ns seabattle.interaction
  (:require [seabattle.logicals :refer :all])
  (:require [clojure.core :refer :all]))

(defn letter-to-num
  "Converts letter to number, if parameter is not a letter returns nil"
  [letter]
  (let [ch (int (first letter))]
    (if (<= (int \a) ch (int \z))
      (inc (- (int ch) (int \a)))
      nil)))

(defn read-move
  "Loops until move is QQ -> quit or move is valid.
  Returns vector [n m]"
  []
  (let [cmd (read-line)
        letter (first cmd)
        number (rest cmd)]
    (vector (Integer. number) (letter-to-num letter))))


