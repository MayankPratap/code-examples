;; by Sean McKenna
;; custom list functions

;; Default lists; I would suggest A for most testing
(define(A) '(10 30 -20 -100 60 80 110 -34 -40 86))
(define(B) '(0 1 2 3 4 5 6 7 8 9))

;; Returns the length of a list
(define(len l)
  (if(null? l)
     0
     (+ (len (cdr l)) 1)))

;; Returns the sum of integers in a list
(define(sum l)
  (if(null? l)
     0
     (+ (sum (cdr l)) (car l))))

;; Returns the minimum integer in a list
(define(mini l)
  (if(null? (cdr l))
     (car l)
     (if(< (car l) (car (cdr l)))
        (mini (cons (car l) (cdr (cdr l))))
        (mini (cdr l)))))

;; Returns the maximum integer in a list
(define(maxi l)
  (if(null? (cdr l))
     (car l)
     (if(> (car l) (car (cdr l)))
        (maxi (cons (car l) (cdr (cdr l))))
        (maxi (cdr l)))))

;; Returns a min (<) or max (>) of a list
(define(minmax f l)
  (if(eq? f <)
     (mini l)
     (maxi l)))

;; Returns a list that has been reversed
(define(rev l)
  (if(null? l)
     '()
     (append (rev (cdr l)) (list (car l)))))

;; Combines list 1 and list 2
(define(comb l l2)
  (append l l2))
