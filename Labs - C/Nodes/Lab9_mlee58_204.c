//Michelle G00585387
//CS 262, Lab Section 204
//Lab 9

#include <stdlib.h>
#include <stdio.h>
#include <time.h>

struct node{
	int data;
	struct node* next;
}node;

void insertNodeSorted(struct node** head, struct node* new);
void printList(struct node* head);
void deleteList(struct node* list);
struct node *makeNode(int newInt);

//accept command line arguements. argc should be 4. 
//order of arguements: program name, random number seed, number of random numbers to generate, maximum possible value of rand nums
int main(int argc, char *argv[])
{
	if(argc != 4)
		printf("Incorrect number of arguements");
	int seed = atoi(argv[1]);
	int size = atoi(argv[2]);
	int max = atoi(argv[3]);
	srandom(seed);
	int counter = 0, num;
	struct node* head = NULL;
	struct node *newInt;
	while(counter < size)
	{
		num = random()%(max+1);
        	newInt = makeNode(num);
		insertNodeSorted(&head, newInt);
		counter++;
	}
	printList(head);
	deleteList(head);
	exit(1);
	
}
struct node *makeNode(int newInt)
{
	struct node* p;
	p = (struct node*)malloc(sizeof(struct node));
	p->data = newInt;
	p->next = NULL;
	return p;
}

void insertNodeSorted(struct node** head, struct node* new)
{
	struct node *p;
	//when head points to first item in list	
	if(*head==NULL)
	{
		new->next = *head;
		*head = new;
	}
	else
	{	
		//p
		while(p->data < new->data)
		{
			p = p->next;
		}
		new->next = p->next;
		p->next = new;
	}
}

void printList(struct node *head)
{
	struct node *t = head ;
	while(t)//while t(temp) isn't null
	{
		printf("%d\n", t->data);
		t = t->next;
	}

}

void deleteList(struct node *list)
{
	struct node *p = list;
	struct node *q = p;//dummy
	struct node *killer = p;
	while(p)
	{
		p = p->next;
		free(killer);
		killer = p;
	}
	list = NULL;

}
