.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$2, %EAX
	movl	%EAX, %EDI
	movl	$5, %EAX
	movl	%EDI, %EAX
main_bb1:
	ret
