(DATA  c)
(FUNCTION  main  [(int a) (int x)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 2)])
    (OPER 5 Mov [(r 1)]  [(r 4)])
    (OPER 6 Load [(i 0)]  [(s 1)])
    (OPER 7 Mov [(r 7)]  [(i 5)])
    (OPER 8 Mov [(r 6)]  [(r 7)])
    (OPER 9 Mov [(r 9)]  [(i 5)])
    (OPER 10 Mov [(r 3)]  [(r 9)])
    (OPER 11 Mov [(m RetReg)]  [(r 1)])
    (OPER 12 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
