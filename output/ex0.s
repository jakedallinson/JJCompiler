.text
	.align 4
.globl  god
god:
god_bb2:
	movl	%EDI, %ECX
god_bb4:
	movl	$0, %EAX
	cmpl	%EAX, %ESI
	jne	god_bb7
god_bb5:
	movl	%ECX, %EAX
god_bb1:
	ret
god_bb7:
	movl	%ESI, %EDI
	movl	$0, %EDX
	movl	%ECX, %EAX
	idivl	%ESI, %EAX
	imull	%ESI, %EAX
	movl	%EAX, %EDX
	movl	%ECX, %ESI
	subl	%EDX, %ESI
	call	god
	jmp	god_bb1
	jmp	god_bb1
.globl  main
main:
main_bb2:
main_bb3:
	call	god
main_bb1:
	ret
