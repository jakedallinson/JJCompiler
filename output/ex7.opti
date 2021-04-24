(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 3)]  [(i 0)])
    (OPER 5 Mov [(r 2)]  [(r 3)])
    (OPER 6 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 7 JSR []  [(s expo)] [(numParams 1)])
    (OPER 8 Mov [(r 4)]  [(m RetReg)])
    (OPER 9 Mov [(r 1)]  [(r 4)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)
