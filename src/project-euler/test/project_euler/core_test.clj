(ns project-euler.core-test
  (:require [clojure.test :refer :all]
            [project-euler.core :refer :all]))

(deftest pe001-test
  (testing "PE001"
    (is (= (pe001 10) 23))))
