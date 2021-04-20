(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
    (OPER 4 Mov [(r 3)]  [(i 3)])
    (OPER 5 Mov [(r 1)]  [(r 3)])
    (OPER 6 Mov [(r 5)]  [(i 4)])
    (OPER 7 Mov [(r 2)]  [(r 5)])
    (OPER 8 Mov [(r 7)]  [(i 5)])
    (OPER 9 Mov [(r 1)]  [(r 7)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
