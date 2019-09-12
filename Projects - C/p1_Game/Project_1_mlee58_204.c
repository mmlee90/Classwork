// Michelle Lee G00585387
// CS 262, Lab Section 204
// Project 1


#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int playerKopek = 10;
int compKopek = 10;
int playerScore = 0;
int compScore = 0;//players total score
int firstThrow;//this value is to keep track of who went first and switch to other player based off of this


void EnterRandomSeed()
{
        unsigned int myRandomSeed;
        printf("Please enter a random number seed: ");
        scanf("%d", &myRandomSeed);
        srandom(myRandomSeed);
}
                        

void declareWinner(int playerScore, int compScore, int playerKopek, int compKopek)
{
        char response;
	char playerThrow;
	if(playerScore >= 132)//check to see which player won and deduct/add kopeks accordingly
        {
                printf("Player wins.");
                playerKopek += 1;
                compKopek -= 1;
        }
        else if(compScore >= 132)
        {
                printf("Computer wins.");
                playerKopek -= 1;
                compKopek += 1;
        }
	printf("Play again? ");
	scanf(" %c", &response);
        if(response  == 'y' || response  == 'Y')
        {
                printf("Would you like to throw first? ");
                scanf(" %c", &playerThrow);
                if(playerThrow == 'y' || playerThrow == 'Y')
                {
                        firstThrow = 1;//stores that player will go first
                        game(playerScore, compScore, playerKopek, compKopek, firstThrow);
                }
                else if(playerThrow ==  'n' || playerThrow == 'N')
                {
                        firstThrow = 2; //stores that player will go second
                        game(playerScore, compScore,  playerKopek, compKopek, firstThrow);
                }
                else
                {
                        printf("Invalid input. Exiting game.\n");
                        exit(0);
                }
        }	
	else if(response == 'n' || response == 'N')
	{	
		printf("Player Kopeks: %d\nComputer Kopeks: %d\n", playerKopek, compKopek);
		exit(0);
	}
}

char playerChoice()//get player choice of yes or no
{
        printf("Throw again? ");
        char choice;
        scanf(" %c", &choice);
        return choice;
}


void playerTurn(int playerScore,  int playerKopek)
{
	int points = 0;
	int *pointer;
	pointer  = &playerScore;
	char choice = 'y'; //player decision to throw again or not. default is 'y'
	while(choice != 'n' && choice != 'N' && (playerScore + points) < 132)
	{
		int throw = random()%100;//generate random number from 0-99(inclusive)

		if(0 <= throw && throw <= 16)//Dooshi Ever. Lose all points for the round. Horn is passed to other player
		{
			points = 0;
			choice = 'n';
			printf("Current score: %d\n", playerScore);
		}
		else if(17 <= throw && throw <=32)//khurtel ever, add all points to players score
		{
			*pointer = playerScore + points;
			points = 0;
			choice = 'n';
			printf("Current score: %d\n", playerScore);
			//return(0);
		}
		else if(33 <= throw && throw <=55)
		{	
			points += 2;
			printf("Current points: %d\n", points);
			choice = playerChoice();
			if(choice == 'y' || choice == 'Y')
				continue;
			else
			{
				*pointer = playerScore + points;

				points = 0;
				choice = 'n';
				printf("Current score: %d\n", playerScore);

				//return(0);
			}	
		}
		else if(56 <= throw && throw <= 68)
		{
			points += 3;
			printf("Current points: %d\n", points);
			choice = playerChoice();
			if(choice == 'y' || choice == 'Y')
				continue;
			else
			{
				*pointer = playerScore + points;

				points = 0;
				choice = 'n';
				printf("Current score: %d\n", playerScore);

				//return(0);
			}
		}
		else if(69 <= throw && throw <= 83)
		{
			points += 5;
			printf("Current points: %d\n", points);
			choice = playerChoice();
			if(choice == 'y' || choice == 'Y')
                                continue;
                        else
                        {
                                *pointer = playerScore + points;

                                points = 0;
				choice = 'n';
				printf("Current score: %d\n", playerScore);

				//return(0);
                        }
		}
		else if(84 <= throw && throw <= 92)
		{
			points += 10;
			printf("Current points: %d\n", points);
			choice = playerChoice();
			if(choice == 'y' || choice == 'Y')
                                continue;
                        else
                        {
                                *pointer = playerScore + points;

                                points = 0;
				choice = 'n';
				printf("Current score: %d\n", playerScore);

				//return(0);
                        }
		}
		else if(93 <= throw && throw <= 99)
		{
			points += 15;
			printf("Current points: %d\n", points);
			choice = playerChoice();
			if(choice == 'y' || choice == 'Y')
                                continue;
                        else
                        {
                                *pointer = playerScore + points;

                                points = 0;
				choice = 'n';
				printf("Current score: %d\n", playerScore);

				//return(0);
                        }
		}
	}
	if(playerScore + points > 132)
		declareWinner(playerScore, compScore, playerKopek, compKopek);
}

void compTurn(int compScore, int compKopek)
{
	int points;
	int *pointer;//pointer to store/change value of compScore
	pointer = &compScore;
        while(points < 25  && (compScore + points) < 132)
        {
                int throw = random()%100;//generate random number from 0-99(inclusive)

                if(0 <= throw && throw <= 16)//Dooshi Ever. Lose all points for the round. Horn is passed to other player
                {
                        points = 0;
                        break;
                }
                else if(17 <= throw && throw <=32)//khurtel ever, add all points to players score
                {
                        *pointer = compScore + points;//
                        points = 0;
                        break;
                }
                else if(33 <= throw && throw <=55)
                {
                        points += 2;
                }
                else if(56 <= throw && throw <= 68)
                {
                        points += 3;
                }
                else if(69 <= throw && throw <= 83)
                {
                        points += 5;
                }
                else if(84 <= throw && throw <= 92)
                {
                        points += 10;
                }
                else if(93 <= throw &&throw <= 99)
                {
                        points += 15;
                }
        }
	if(compScore + points > 132)
		declareWinner(playerScore, compScore, playerKopek, compKopek);

	else if(points > 25)
	{
		*pointer = compScore + points;
		points = 0;
	}
}

int game(int playerScore, int compScore, int playerKopek, int compKopek, int firstThrow)
{
	if(playerKopek ==  0 || compKopek == 0)
	{
		if(playerKopek ==0)
		{
			printf("Computer wins.");
			exit(0);
		}
		else
		{
			printf("Player wins.");
			exit(0);
		}
	}

        if(firstThrow == 1)
        {
                while(playerScore < 132 && compScore < 132)//keep repeating turns
                                {
                                        playerTurn(playerScore, playerKopek);
                                        compTurn(compScore, compKopek);
                                }
        }
        else if(firstThrow == 2)
        {
                while(playerScore < 132 && compScore < 132)//after each player goes, check to see if someone won
                                {
                                        compTurn(compScore, compKopek);
                                        playerTurn(playerScore, playerKopek);
                                }
        }
	return(0);
}



int main()
{
	int playerKopek = 10;
	int compKopek = 10;
	int playerScore = 0;
	int compScore = 0;//players total score
	int firstThrow;//this value is to keep track of who went first and switch to other player based off of this
     
	printf("Welcome.\n");
        EnterRandomSeed();
        printf("Do you want to play a game? ");
        char response;
        char playerThrow;
        scanf(" %c", &response);
        if(response  == 'y' || response  == 'Y')
        {
                printf("Would you like to throw first? ");
                scanf(" %c", &playerThrow);
                if(playerThrow == 'y' || playerThrow == 'Y')
                {
                        firstThrow = 1;//stores that player will go first
                        game(playerScore, compScore, playerKopek, compKopek, firstThrow);
                }
                else if(playerThrow ==  'n' || playerThrow == 'N')
                {
                        firstThrow = 2; //stores that player will go second
                        game(playerScore, compScore,  playerKopek, compKopek, firstThrow);
                }
                else
                {
                        printf("Invalid input. Exiting game.\n");
                        exit(0);
                }
        }
        else if(response  == 'n' || response =='N')
        {
                printf("Good-bye.\n");
                exit(0);
        }
        else
        {
                printf("Invalid input. Exiting game.\n");
                exit(0);
        }
	return(0);
}

