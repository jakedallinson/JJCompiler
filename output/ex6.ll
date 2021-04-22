(FUNCTION  main  [(void null)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 2)]  [(i 1)])
    (OPER 5 Mov [(r 1)]  [(r 2)])
  )
  (BB 4
    (OPER 6 Mov [(r 4)]  [(i 1)])
    (OPER 7 EQ [(r 5)]  [(r 1)(r 4)])
    (OPER 8 BEQ []  [(r 5)(i 0)(bb 6)])
  )
  (BB 5
    (OPER 9 Jmp []  [(bb 6)])
  )
  (BB 6
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
