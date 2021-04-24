.data
.comm	x,4,4

.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$3, %EAX
	movl	%EAX, x(%RIP)
main_bb1:
	ret
