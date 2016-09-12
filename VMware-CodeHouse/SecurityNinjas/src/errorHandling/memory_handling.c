#include<stdio.h>
#include<stdlib.h>
int main (int argc, char* argv[]) {
	int i;
	int count = 10;
	int *ptr = NULL;
	ptr = malloc(count*sizeof(int));
	for (i = 0; i < count; i++) {
		ptr[i] = i;
	}
    for (i = 0; i < count; i++) {
        printf("%d",ptr[i]);
    }

	printf("Memory location starts at %d\n",ptr);

	return 0;
}
