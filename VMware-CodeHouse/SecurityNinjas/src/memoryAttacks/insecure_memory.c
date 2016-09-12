#include<stdio.h>
#include<string.h>

int attack1()
{
  char buffer[10], input[20];
  printf("\nEnter the string to be stored : ");
  scanf("%s",input);
  strcpy(buffer, input);
  return -1; 
}

int attack2()
{
    unsigned int l1, l2;
  char in1[150], in2[150];
  printf("\nEnter a string whose length is less than 128: ");
  scanf("%s",in1);
  printf("\nEnter a string whose length is less than 128: ");
  scanf("%s",in2);
  printf("\nEnter the lengths of the 2 strings whose sum should be less than 256 (in hex): ");
  scanf("%x %x", &l1, &l2);
	char concatstring[256];
	if((l1+l2) > 256)
	 return -1;
	memcpy(concatstring, in1, l1);
	memcpy(concatstring+l1, in2, l2);
	return 0;
}
void main()
{
  int ret1, ret2;
  ret1 = attack1();
  printf("\nReturned from attack 1 %d", ret1);
  ret2 = attack2();
  printf("\nReturned from attack 2 %d", ret2);
}
