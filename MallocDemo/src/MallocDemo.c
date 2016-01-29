/*
 ============================================================================
 Name        : MallocDemo.c
 Author      : oz
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>



void f(int**p);

int
main(void)
{

  int *i;

  f(&i);

  printf("i=%i", *i);


  return EXIT_SUCCESS;
}

void f(int **p)
{

  int *k = (int) malloc(sizeof(int));

  *k = 4;

  printf("i=%i\n", *k);

  *p = k;

}
