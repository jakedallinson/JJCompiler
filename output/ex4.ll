(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
    (OPER 4 Mov [(r 2)]  [(i 3)])
    (OPER 5 Mov [(r 1)]  [(r 2)])
    (OPER 6 Mov [(m RetReg)]  [(r 1)])
    (OPER 7 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
