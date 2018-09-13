(ns project-euler.core)

(defn pe001
  "Add all numbers divisible by 3 or 5."
  [x]
  (reduce +
          (filter #(or
                    (= 0 (mod % 3))
                    (= 0 (mod % 5)))
                  (range x))))

(pe001 1000)
