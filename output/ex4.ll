(FUNCTION  main  [(int a) (int x)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 2)])
    (OPER 5 Mov [(r 1)]  [(r 4)])
    (OPER 6 Mov [(r 6)]  [(i 5)])
    (OPER 7 Mov [(r 3)]  [(r 6)])
    (OPER 8 Mov [(m RetReg)]  [(r 1)])
    (OPER 9 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
