as printInt.s -o PrintInt.o
as printString.s -o PrintString.o
as readString.s -o ReadString.o
as readInt.s -o ReadInt.o
as CODI.s -o CODI.o
ld CODI.o PrintInt.o PrintString.o ReadString.o ReadInt.o -o EXEC
./EXEC
