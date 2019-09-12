// Michelle Lee G00585387
// CS 262, Lab Section 204
// Project 2

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

#define X_SEMIRANGE 3
#define Y_SEMIRANGE 2


int numRound(double x);
char newGraph(int row, int col);
double f(char graph[X_SEMIRANGE*20 + 1][Y_SEMIRANGE*20 + 1]);
void printGraph(char *graph);

int main()
{
	//initialize blank graph
	// graph[X_SEMIRANGE*20 + 1][Y_SEMIRANGE*20 + 1];
	char graph = newGraph(X_SEMIRANGE*20 + 1, Y_SEMIRANGE*20 + 1);
	//printGraph(graph);
	//then call f() to draw graph with 'o'
	
	return 1;
}

//holds function to be graphed
double f(char graph[X_SEMIRANGE*20 + 1][Y_SEMIRANGE*20 + 1])
{	
	double x,y;	
	int i,j;

	for(i=0; i<X_SEMIRANGE*20 +1;i++)
	{
		for(j=0; j<Y_SEMIRANGE*20+1;j++)
		{
			for(x=-X_SEMIRANGE; x<=X_SEMIRANGE;x+=0.1)
			{
				y = (double)x*x; //y = x^2
				y = numRound(y);
				graph[i][j] = 'o';
			}
		}
	}
}

int numRound(double x)
{
	int i;
	if (x >= 0)
		i = (int)(x + 0.5);
	else /* x < 0 */
		i = (int)(x - 0.5);
	return i;
}

/* create a new graph */
char newGraph(int row, int col)
{
		int i, j;
		char **graph;
		graph = (char*)malloc(row*sizeof(char));
		
		for(i=0; i< row;i++)
			graph[i]=(char*)malloc(col*sizeof(char));
		
		//populate graph 
        for(i=0; i < row; i++)
		{
            for(j = 0; j < col; j++)
            {
                graph[i][j]= ' ';
            }
		}

        for(i=row/2, j = 0; j < col; j++)
        {
            graph[i][j]='-';
        }
		
        for(i = 0, j = col/2; i < row; i++)
        {
            graph[i][j] = '|';
        }
		//origin
		graph[row/2][col/2] = '+';

		return graph;
}
void printGraph(char *graph)
{
	int i,j;
	for(i = 0; i < X_SEMIRANGE*20 + 1; i++)
	{
		for(j = 0; j < Y_SEMIRANGE*20 + 1; j++)
		{
			printf("%c", graph[i][j]);
			
		}
		printf("\n");
	}
}























