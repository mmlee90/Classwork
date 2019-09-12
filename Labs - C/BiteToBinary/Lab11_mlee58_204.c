//Michelle Lee G00585387
//CS 262, Lab 204
//Lab 11

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define BYTETOBINARYPATTERN "%d%d%d%d%d%d%d%d"

#define BYTETOBINARY(byte)  \
  (byte & 0x80 ? 1 : 0), \
  (byte & 0x40 ? 1 : 0), \
  (byte & 0x20 ? 1 : 0), \
  (byte & 0x10 ? 1 : 0), \
  (byte & 0x08 ? 1 : 0), \
  (byte & 0x04 ? 1 : 0), \
  (byte & 0x02 ? 1 : 0), \
  (byte & 0x01 ? 1 : 0) 

#define PRINTBIN(x) printf(BYTETOBINARYPATTERN, BYTETOBINARY(x));

void setlsbs(unsigned char *p, unsigned char b0)
{
	int i;
	for(i = 0; i < 8; i++)
	{
		(p[i] & ~1) | b0;
	}


}
unsigned char getlsbs(unsigned char *p)
{
	int lsb, i, j;
	unsigned char byte[9] = "";
	for(i = 0; i < 8; i++)
	{
		for(j = 0; j < 8; j++)
		{
			lsb = j & 1; //rightmost bit will be assigned to lsb
		}
		byte[9-(i+1)] = lsb;
	}
	
	return byte;

}

int main(int argc, char *argv)
{
	srandom(argv[1]);//first argument is random number seed
	unsigned char *p[8];
	int i;
	for(i = 0; i < 8; i++)
	{
		p[i] = random()%256;
	}
	unsigned char byte0 = random();
	for(i = 0; i < 8; i++)//printing values found in array p in decimal format
	{
		printf("%s\n",p[i]);
	}
	for(i = 0; i < 8; i++)//printing array p in binary
        {
                PRINTBIN(*p[i]);
		printf("\n");
        }

	printf("%c", byte0);//printing byte0 in decimal the binary
	PRINTBIN(byte0);
	printf("\n");

	setlsbs(*p, byte0);
	for(i = 0; i < 8; i++)
        {
                printf(p[i]);
        }
	unsigned char result = getlsbs(*p);
	printf("%c\n", result);//print result of call to getlsbs
	PRINTBIN(result);
	printf("\n");
}
