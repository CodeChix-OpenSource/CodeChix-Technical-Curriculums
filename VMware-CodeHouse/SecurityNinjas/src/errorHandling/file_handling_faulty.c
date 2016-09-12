#include<stdio.h>
#include<fcntl.h>


int main(int argc, char* argv[]){
	FILE *filp;
	int a,b;
    if (argv[2] && argv[3]) {
        a = strtol(argv[2], NULL, 10);
        b = strtol(argv[3], NULL, 10);
    } else {
		return -1;
	}

	filp = fopen(argv[1], "ab+");
	if (!filp) {
		return -1;
	}

	if (b != 0) {
		fprintf (filp,"%d divided by %d is %d",a,b,a/b);
		return 0;
	}
	else {
		printf ("Divide by zero. Exiting");
		return -1;
	}

	return 0;
}
