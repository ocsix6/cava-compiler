as printInt.s -o PrintInt.o
as printString.s -o PrintString.o
as readString.s -o ReadString.o
as readInt.s -o ReadInt.o
as CODIOPT.s -o CODIOPT.o
ld CODIOPT.o PrintInt.o PrintString.o ReadString.o ReadInt.o -o EXECOPT
./EXECOPT
