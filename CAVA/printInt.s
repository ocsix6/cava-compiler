.global printint

printint:
	#%rdi			%rsi			%rdx
	#unsigned int fd	const char *buf	 	size_t count

	movq $1, %rdi
	movq 8(%rsp), %rax 	# Valor del parametre
	subq $32 ,%rsp 		# Reservam memoria per el buffer
	movq %rsp, %rsi 	# inici del buffer. El nostre array va de esquerra a dreta
	movq $31 ,%r8 		# index de la memoria

	#Cas de ser un zero
	movq $0, %r14
	cmpq %rax, %r14
	je caszero
	testq %rax, %rax #Fa una and bit a bit
	js neg       #jmp is signed
	movq $0, %r9		#Cas de numero positiu
	jmp bucle

neg:
	movq $1, %r9
	negq %rax		#Cas de numero negatiu

bucle:
	movq $0, %rdx
	movq $10, %rcx
	idiv %rcx 		# rdx = residu, rax = quocient
	addq $'0',%rdx
	movb %dl, (%rsi, %r8)
	subq $1, %r8
	cmpq $0, %rax		# Comparacio del quocient
	jne bucle
	jmp end

caszero:
	movb $'0', (%rsi,%r8)
	decq %r8

end:
	cmpq $1, %r9
	je posasigne
	jmp halt

posasigne:
	movb $'-', (%rsi, %r8)
	decq %r8

halt:
	movq $1, %rax
	movq $31, %rdx
	subq %r8, %rdx
	addq %r8, %rsi
	incq %rsi
	syscall
	addq $32 ,%rsp   # Borram l'array
	ret


# Succesives divisions entre 10
# Mod de la primera divisió
# 43 : 10 = 4  mod =3
# 0003 A l'esquerra de la darrera posició escrita
# 4:10 = 0 mod = 4
# 0043
# quocient es zero? SI
