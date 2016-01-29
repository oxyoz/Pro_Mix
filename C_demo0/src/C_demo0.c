/*
 ============================================================================
 Name        : C_demo0.c
 Author      : oz
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

#define A 13
#define B 7
#define AND(A,B) A+B

int
(*sum1)(int, int);

void
a();

void
b();

void
c();

int
sun(int s1, int s2);

typedef struct point
{

  int x, y;

} Point;

int
main(void)
{

  c();

  return EXIT_SUCCESS;
}

void
c()
{

  Point p = {3,2};

  Point *p0 = &p;

  sum1 = sun;

  printf("sun=%d", sum1(p0->x, p0->y));

}

void
b()
{
  sum1 = sun;

  int result = sum1(sum1(0, 1), sum1(1, 2));

  printf("sun=%d", result);

}

int
sun(int s1, int s2)
{
  return s1 + s2;
}

void
a()
{

  int a = 1;

  float b = 0.4f;

  double c = 0.05;

  char e = 'a';

  char *p = &e;

  int size = sizeof(a);

  int pSize = sizeof(p);

  printf("%d\n", size);

  printf("%d\n", pSize);

  printf("%d", AND(A,B));

}
