.global readstring

# %rdi	            %rsi	      %rdx
# unsigned int fd	  char *buf	  size_t count

readstring:
movq $0, %rdi       # In
movq 8(%rsp), %rsi  # Ens arriba el punter per parámetre
movq $256, %rdx     # Els nostres strings tendran mida fixa
movq $0, %rax
syscall
movb $0, -1(%rsi, %rax) #Posam un zero al final de l'string. %rax té la logitud de l'string
ret
