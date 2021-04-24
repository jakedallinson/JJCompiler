(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 1)])
    (OPER 5 Mov [(r 1)]  [(r 2)])
  )
  (BB 4
    (OPER 6 Mov [(r 3)]  [(i 1)])
    (OPER 7 EQ [(r 4)]  [(r 1)(r 3)])
    (OPER 8 BEQ []  [(r 4)(i 0)(bb 7)])
  )
  (BB 5
    (OPER 9 Mov [(r 5)]  [(i 5)])
    (OPER 10 Mov [(r 1)]  [(r 5)])
  )
  (BB 6
    (OPER 14 Mov [(r 7)]  [(i 3)])
    (OPER 15 Mov [(r 1)]  [(r 7)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
  (BB 7
    (OPER 11 Mov [(r 6)]  [(i 2)])
    (OPER 12 Mov [(r 1)]  [(r 6)])
    (OPER 13 Jmp []  [(bb 6)])
  )
)
