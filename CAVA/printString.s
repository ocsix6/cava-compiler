.global printstring

printstring:
	#%rdi			        %rsi			        %rdx
	#unsigned int fd	const char *buf	 	size_t count

	movq $1, %rdi   #Out
	movq 8(%rsp), %rsi 	# Inici de l'adreça del buffer. Ens arriba per paràmetre
  movq $0, %rdx # Contador
  movq $0, %r8

# Llegim byte per byte fins trobar final de string
bucle:
  movb (%rsi, %r8), %r9b # Llegim un caracter
  cmpb $0, %r9b
  je halt
  incq %r8
  incq %rdx
  jmp bucle

halt:
	movq $1, %rax # Indicam que volem fer un print
	syscall
	ret
