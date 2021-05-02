(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 5)]  [(i 0)])
    (OPER 5 Mov [(r 1)]  [(r 5)])
    (OPER 6 Mov [(r 6)]  [(i 0)])
    (OPER 7 Mov [(r 2)]  [(r 6)])
    (OPER 8 Mov [(r 7)]  [(i 0)])
    (OPER 9 Mov [(r 3)]  [(r 7)])
    (OPER 10 Mov [(r 8)]  [(i 0)])
    (OPER 11 Mov [(r 4)]  [(r 8)])
  )
  (BB 4
    (OPER 12 LT [(r 9)]  [(r 1)(r 2)])
    (OPER 13 BEQ []  [(r 9)(i 0)(bb 6)])
  )
  (BB 5
  )
  (BB 7
    (OPER 14 Mov [(r 10)]  [(i 0)])
    (OPER 15 GTE [(r 11)]  [(r 3)(r 10)])
    (OPER 16 BEQ []  [(r 11)(i 0)(bb 9)])
  )
  (BB 8
    (OPER 17 Mov [(r 12)]  [(i 3)])
    (OPER 18 Mov [(r 4)]  [(r 12)])
  )
  (BB 9
    (OPER 19 Mov [(r 13)]  [(i 4)])
    (OPER 20 Mov [(r 4)]  [(r 13)])
    (OPER 21 Mov [(r 14)]  [(i 1)])
    (OPER 22 Add_I [(r 15)]  [(r 1)(r 14)])
    (OPER 23 Mov [(r 1)]  [(r 15)])
    (OPER 24 Jmp []  [(bb 4)])
  )
  (BB 6
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
