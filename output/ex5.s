.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$5, %EAX
	movl	$6, %EDI
	addl	%EDI, %EAX
	movl	$0, %EDI
	movl	$0, %EDX
	idivl	%EDI, %EAX
main_bb1:
	ret
