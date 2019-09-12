// Michelle Lee G00585387
// CS 262, Lab Section 204
// Project 2

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

#define X_SEMIRANGE 3
#define Y_SEMIRANGE 1

double f(double x)
{
        double y;
        double i;

        //y = x*x;
        //y = cos(x*x);
        y = exp(-1/(x*x));
        //y = sin(3*x);
	float nearest = roundf(y * 100)/100; 
	y = nearest;
	if (y > 0)
                i = (y + 0.05);
        else /* y < 0 */
                i = (y - 0.05);
        return i;
	
}

int main()
{
	int row = Y_SEMIRANGE*20+1;
	int col = X_SEMIRANGE*20+1;
	char graph[row][col];

	
	//print axis
	int i,j;
	for(i = 0; i < row; i++)
	{
		for(j = 0; j < col; j++)
			graph[i][j] = ' ';
	}
	
	for(j = 0; j < col; j++)
	{
		graph[row/2][j] = '-';
	}
	
	for(i = 0; i < row;i++)
	{
		
		graph[i][col/2] = '|';
	}
	//origin
	graph[row/2][col/2] = '+';
	
	
	for(i = 0; i < col; i++)
	{
		if(i==col/2)//if x ==0
		{
			graph[row/2][col/2]='o';
			continue;
		}		

		double x = -X_SEMIRANGE + (i * 0.1);//get correct x value to plug into function
		double y = f(x);
		//convert cartesian value obtained by function into graph[][] coordinates
		// cartesian (0,0) should be graph[20][30]
		double yval = 10 * (Y_SEMIRANGE - y);
		double xval = 10 * (i * 0.1);	
		//if in range
		graph[(int)yval][(int)xval] = 'o';

	}
				
	for(i = 0; i < row; i++)
        {
                for(j = 0; j< col;j++)
                        printf("%c", graph[i][j]);
                printf("\n");
        }
	
	
}

