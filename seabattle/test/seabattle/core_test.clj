(ns seabattle.core-test
  (:require [clojure.test :refer :all]
            [seabattle.core :refer :all]
            [seabattle.setup :refer :all]
            [seabattle.interaction :refer :all]))


(def empty-board
"############
############
############
############
############
############
############
############
############
############
############
############
")


(def board-with-border
"############
#..........#
#..........#
#..........#
#..........#
#..........#
#..........#
#..........#
#..........#
#..........#
#..........#
############
")



(deftest is-board-good
  (testing "Board is made correct"
    (let [board (make-empty-board)]
    (is (= (count (make-board-debug-text board))  (count empty-board)) "Wrong board text count")
    (is (= (make-board-debug-text board) empty-board) "Wrong board text by itself")))

  (testing "Board has good border"
    (let [board (make-border (make-empty-board))]
    (is (= (count (make-board-debug-text board))  (count board-with-border)) "Wrong board text count")
    (is (= (make-board-debug-text board) board-with-border) "Wrong board text by itself")))

  (testing "Alive ships and mpves count"
    (let [board (setup-board)]
    (is (= 20 (:alive-ships board)))
    (is (= 30 (:moves-left board))))))


