(DATA  x)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Load [(r 1)]  [(s x)])
    (OPER 5 Mov [(r 2)]  [(i 3)])
    (OPER 6 Store []  [(r 2)(s x)(i 0)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
