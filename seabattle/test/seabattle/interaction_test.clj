(ns seabattle.interaction-test
  (:require [clojure.test :refer :all]
            [seabattle.logicals :refer :all]
            [seabattle.interaction :refer :all]))

(deftest letter-convertion
  (testing "Letter conversion works good"
    (is (nil? (letter-to-num \#)) "Wrong char is not nil")
    (is (= 1 (letter-to-num \a)) "Letter 'a' is not 1")))
