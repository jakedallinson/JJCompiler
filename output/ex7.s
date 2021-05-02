.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$0, %EAX
	movl	%EAX, %ECX
	movl	$0, %EAX
	movl	%EAX, %EDX
	movl	$0, %EAX
	movl	%EAX, %ESI
	movl	$0, %EAX
main_bb4:
	cmpl	%EDX, %ECX
	jge	main_bb1
main_bb7:
	movl	$0, %EAX
	cmpl	%EAX, %ESI
	jl	main_bb9
main_bb8:
	movl	$3, %EAX
main_bb9:
	movl	$4, %EAX
	movl	$1, %EDI
	movl	%ECX, %EAX
	addl	%EDI, %EAX
	movl	%EAX, %ECX
	jmp	main_bb4
main_bb1:
	ret
