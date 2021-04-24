.text
	.align 4
.globl  fact
fact:
fact_bb2:
	pushq	%R15
	movl	%EDI, %R15D
fact_bb4:
	movl	$1, %EAX
	cmpl	%EAX, %R15D
	jle	fact_bb7
fact_bb5:
	movl	$1, %ESI
	movl	%R15D, %EDI
	subl	%ESI, %EDI
	call	fact
	movl	%EAX, %EDI
	movl	%R15D, %EAX
	imull	%EDI, %EAX
fact_bb1:
	popq	%R15
	ret
fact_bb7:
	movl	$1, %EAX
	jmp	fact_bb1
	jmp	fact_bb1
.globl  main
main:
main_bb2:
main_bb3:
	call	input
	movl	%EAX, %EDI
main_bb4:
	movl	$0, %EAX
	cmpl	%EAX, %EDI
	jle	main_bb1
main_bb5:
	call	fact
	movl	%EAX, %EDI
	call	output
main_bb1:
	ret
