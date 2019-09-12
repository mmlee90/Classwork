// Michelle Lee G00585387
// CS 262, Lab Section 204
// Lab 8
#include <stdio.h>
#include <stdlib.h>

typedef struct Location{
	int LocationNum;
	char ID[15];
	char Desc[50];
	double Lat;
	double Long;
	struct Location* next;
}location;

void options();
location *ResizeArray(location* LocationArray, int *num);
void Lookup(int num, location* LocationArray);

int main()
{
	//request array size from user, allocate memory from heap for an array called LocationArray
	int num;
	printf("Size of Array: ");
	scanf("%d", &num);	
	location* LocationArray = malloc(num * sizeof(struct Location));
	if(LocationArray == NULL)
	{
		printf("Error allocationg memory for LocationArray");
		exit(0);
	}
	options();
	char choice;
	scanf(" %c", &choice);
	int count = 0; //counts how many spaces in LocationArray is used
	while(choice != 'q')
	{
		//take input, create new struct location, populate it, and add to LocationArray
		if(choice == 'a')
		{
			if(count < num)
			{
				char identity[15];
				char description[50];
				float latitude;
				float longitude;
				printf("Enter ID: \n");
				scanf("%s", identity);
				printf("Enter Description: \n");
				scanf("%s", description);
				printf("Enter Latitude: \n");
				scanf("%f", &latitude);
				printf("Enter Longitude: \n");
				scanf("%f", &longitude);

				location newLocation;

				strcpy(newLocation.ID, identity);
				strcpy(newLocation.Desc, description);
				newLocation.Lat = latitude;
				newLocation.Long= longitude;
				LocationArray[count] = newLocation;
				count++;
			}
			else if (count == num)
			{
				ResizeArray(LocationArray,&num);
				char identity[15];
				char description[50];
				float latitude;
				float longitude;
				printf("Enter ID: \n");
				scanf("%s", identity);
				printf("Enter Description: \n");
				scanf("%s", description);
				printf("Enter Latitude: \n");
				scanf("%f", &latitude);
				printf("Enter Longitude: \n");
				scanf("%f", &longitude);

				location newLocation;

				strcpy(newLocation.ID, identity);
				strcpy(newLocation.Desc, description);
				newLocation.Lat = latitude;
				newLocation.Long= longitude;
				LocationArray[count] = newLocation;
				count++;
			}	
		}
		else if(choice == 'p')
		{
			int i;
			for(i=0;i<count;i++)
			{
				printf("ID: %s\n", LocationArray[i].ID);
				printf("Description: %s\n", LocationArray[i].Desc);
				printf("Latitude: %f\n", LocationArray[i].Lat);
				printf("Longitude: %f\n\n", LocationArray[i].Long);
			}
		}
		else if(choice == 'l')
		{
			Lookup(num, LocationArray);
		}	
		options();
		scanf(" %c", &choice);
	}
	exit(0); //when user selects quit
}

void options()
{
	printf("Choose from the following options:\n");
	printf("Add(a)\n");
	printf("Print(p)\n");
	printf("Lookup(l)\n");
	printf("Quit(q)\n");
}
location *ResizeArray(location* LocationArray, int *num)
{
	*num *= 2;
	//create temp location and allocate the current size to it
	location *temp = NULL;
	temp = (location *)malloc(*num * 2 * sizeof(struct Location));
	//copy LocationArray to temp
	memcpy(temp, LocationArray, sizeof *LocationArray * *num);
	
	
	free(LocationArray);
	return temp;
}
void Lookup(int num, location* LocationArray)
{
	int x;
	printf("Please enter Location ID\n");
	scanf("%d", &x);
	if(x < num)
	{
		printf("ID: %s\n", LocationArray[x].ID);
		printf("Description: %s\n", LocationArray[x].Desc);
		printf("Latitude: %f\n", LocationArray[x].Lat);
		printf("Longitude: %f\n\n", LocationArray[x].Long);
		
	}
	else
	{
		printf("Location ID %d not found\n", x);
		exit(0);
	}
}


















