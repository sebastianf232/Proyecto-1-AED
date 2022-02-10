(defun factorial (n)
  (cond
    ((= n 1) 1)
    (t (* n (fatorial (- n 1))))))
