.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$1, %EAX
	movl	%EAX, %EDI
main_bb4:
	movl	$1, %EAX
	cmpl	%EAX, %EDI
	jne	main_bb7
main_bb5:
	movl	$5, %EAX
main_bb6:
	movl	$3, %EAX
main_bb1:
	ret
main_bb7:
	movl	$2, %EAX
	jmp	main_bb6
