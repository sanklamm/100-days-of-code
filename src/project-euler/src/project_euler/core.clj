(ns project-euler.core)

;; PE001
;; Derivative of Fizz-Buzz

(defn pe001
  "Add all numbers divisible by 3 or 5."
  [x]
  (reduce +
          (filter #(or
                    (zero? (mod % 3))
                    (zero? (mod % 5)))
                  (range x))))

(defn pe001-2
  "Add all numbers divisible by 3 or 5."
  [x]
  (reduce +
          (filter
           (fn [y]
             (or
              (zero? (mod y 3))
              (zero? (mod y 5))))
           (range x))))

(pe001-2 1000)

;; PE002
;; Sum up even Fibonacci numbers under a certain value

;; I'm investigating multiple methods of implementation to learn about recursion in Clojure

;; Recursive with stack usage and memoize

(defn fib
   "Fibonacci - using stack"
   [n]
  (if (> n 1)  ; if n=0 or n=1 just return n
    (+ (fib(dec n)) (fib (- n 2)))
    n))

(time (fib 36))

(def fib-m
  (memoize  ; caches already computed results
   (fn [n]
     (if (> n 1)
       (+ (fib-m (dec n)) (fib-m (- n 2)))
       n))))

(time (fib-m 36))  ; for n=1000 blows the stack b/c Clojure has no tail call optimization by default

;; Recursive with tail call

(defn fib-recursive
  "Fibonacci with tail call"
  [n]
  (if (> n 1)
    (loop [x 1 f0 0 f1 1]
      (if (< x n)  ; if it reaches n return f1
        (recur (inc x) f1 (+' f0 f1)) ; +' automatically uses BigInt if necessary
        f1))
    n))

(time (fib-recursive 100000))

;; With reduce function

(defn fib-reduce
  "Fibonacci with reduce function"
  [n]
  (if (> n 1)
    (first (reduce (fn [coll _]
                     [(+' (first coll) (second coll))  ; save total and the previous
                      (first coll)])                   ; in a vector
                   [1 0]  ; initial values
                   (range 1 n)))
    n))

(time (fib-reduce 10000))  ; super fast
