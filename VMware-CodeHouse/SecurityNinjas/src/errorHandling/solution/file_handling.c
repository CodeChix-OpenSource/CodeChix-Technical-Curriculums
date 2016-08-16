#include<stdio.h>
#include<fcntl.h>


int main(int argc, char* argv[]){
	FILE *filp;
	int a,b;
	filp = fopen(argv[1], "ab+");
	if (!filp) {
		goto out;
	}

	if (argv[2] && argv[3]) {
		a = strtol(argv[2], NULL, 10);
		b = strtol(argv[3], NULL, 10);
	}
	if (b != 0) {
		fprintf (filp,"%d divided by %d is %d",a,b,a/b);
		goto out_sucess;
	}
	else {
		printf ("Divide by zero. Exiting");

		goto out_error;
	}
	printf("opened file\n");

out_error:
	remove(argv[1]);
out_sucess:
	fclose(filp);
out:
	return 0;
}
