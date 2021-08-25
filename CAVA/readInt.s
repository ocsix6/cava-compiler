.global readint

# %rdi	            %rsi	      %rdx
# unsigned int fd	  char *buf	  size_t count

readint:
subq $256, %rsp # Recordem que els strings tendran una mida de 256
movq %rsp, %rsi # @ on comença l'string

#Llegim un string
movq $0, %rdi       # In
movq $256, %rdx     # Els nostres strings tendran mida fixa
movq $0, %rax
syscall
movb $0, -1(%rsi, %rax) # Posam un zero al final de l'string. %rax té la logitud de l'string

subq $2, %rax    #Ens botam el primer zero
# L'string acaba %rax
movq %rax, %r10 #Copiam el valor de %rax dins %r10
movq $1, %r9
movq $0, %r11  #Acumulador. Contidrá el número que ha inserit l'usuari

bucle:
movb (%rsi, %r10), %r8b  # Carregam el caracter
cmpb $'-', %r8b
je nega
subq $'0', %r8          # Caracter amb acsii
movq %r8, %rax
imul %r9                # Multiplicam el carácter amb la seva posició (1, 10, 100...)
addq %rax, %r11         # Sumam el resultat dins l'acumulador

movq %r9, %rax
movq $10, %r9
imul %r9          # Incrementam amb 10 la posició (1,10,100,...)
movq %rax, %r9    # Ho guardam per a la próxima iteració
decq %r10
cmpq $-1, %r10
jne bucle
jmp end

nega:
negq %r11

end:
addq $256, %rsp      # Borram els 256 bytes reservats per l'string
movq %r11, 8(%rsp)        # Ens botam l'adreça de retorn i ho posam dins l'espai reservat
ret


# Ex: Tenim el número 123.
# 123 = 3 * 1 + 2 * 10 + 1 * 100
