(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 5)])
    (OPER 5 Mov [(r 3)]  [(i 6)])
    (OPER 6 Add_I [(r 4)]  [(r 2)(r 3)])
    (OPER 7 Mov [(r 5)]  [(i 0)])
    (OPER 8 Div_I [(r 6)]  [(r 4)(r 5)])
    (OPER 9 Mov [(r 1)]  [(r 6)])
    (OPER 10 Mov [(m RetReg)]  [(r 1)])
    (OPER 11 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
