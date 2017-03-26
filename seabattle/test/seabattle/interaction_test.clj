(ns seabattle.interaction-test
  (:require [clojure.test :refer :all]
            [seabattle.logicals :refer :all]
            [seabattle.interaction :refer :all]))

(deftest letter-convertion
  (testing "Letter conversion works good"
    (is (= (letter-to-num "#") nil) "Wrong char is not nil")
    (is (= (letter-to-num "a") 1) "Letter 'a' is not 1")))
