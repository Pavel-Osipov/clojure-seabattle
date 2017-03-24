(ns seabattle.core-test
  (:require [clojure.test :refer :all]
            [seabattle.core :refer :all]))


(def empty-board
"............
............
............
............
............
............
............
............
............
............
............
............
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
    (is (= (count (make-board-text board))  (count empty-board)) "Wrong board text count")
    (is (= (make-board-text board) empty-board) "Wrong board text by itself")))

  (testing "Board has good border"
    (let [board (make-border (make-empty-board))]
    (is (= (count (make-board-text board))  (count board-with-border)) "Wrong board text count")
    (is (= (make-board-text board) board-with-border) "Wrong board text by itself"))))
