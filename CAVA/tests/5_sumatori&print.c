#include <stdio.h>
//gcc sumatori\&print.c -o sumatori

char* func(){
	char array[] = "Hola";
	return array;
}

int sumatori(int n){
	if(n==0){
		return 0;
	}else{
		return n + sumatori(n-1);
	}
}

int main(int argc, char **argv){
	char *c = func();
	printf("%s", c);
	sumatori(3);
	return 0;
}
