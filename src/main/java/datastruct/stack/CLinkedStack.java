package datastruct.stack;

public class CLinkedStack {
    /**
     * #include <stdio.h>
     * #include <stdlib.h>
     * typedef struct StackNode {
     *   int data;
     *   struct StackNode *next;
     * } StackNode;
     * typedef struct LinkedStack {
     *     int size;
     *     struct StackNode *head;
     * } LinkedStack;
     *
     *  int push(LinkedStack *stack,int value){
     *     StackNode *newNode = (StackNode *)malloc(sizeof(StackNode));
     *     if(NULL == newNode){
     *         printf("malloc newNode fail\n");
     *         return 0;
     *     }
     *     newNode->data = value;
     *     newNode->next = stack->head;
     *     stack->head = newNode;
     *    // printf("push newNode %d \n",value);
     *     stack->size ++;
     *     return 1;
     * };
     *
     * int pop(LinkedStack *stack){
     *     StackNode *currentNode = stack->head;
     *
     *     if(currentNode->next == NULL){
     *         printf("*************\n");
     *         return NULL;
     *     }
     *     printf("currentNode %d \n",currentNode->data);
     *     int value = currentNode->data;
     *     StackNode *nextNode = currentNode->next;
     *     stack->head = nextNode;
     *     stack->size --;
     *     free(currentNode);
     *     return value;
     * }
     *
     *
     * int main()
     * {
     *      LinkedStack *linkedStack = (LinkedStack *)malloc(sizeof(LinkedStack));
     *     if(linkedStack == NULL){
     *         printf("malloc LinkedStack fail\n");
     *     }
     *     linkedStack->size = 0;
     *     StackNode *head = (StackNode *) malloc(sizeof(StackNode));
     *     head->next = NULL;
     *     linkedStack->head = head;
     *     if(linkedStack == NULL){
     *         printf("malloc LinkedStack headNode fail\n");
     *     }
     *
     *     for(int x = 0;x<12;x++){
     *         int status = push(linkedStack,x);
     *         if(status == 0){
     *             printf("push linkedStack value：%d fail\n",x);
     *             continue;
     *         }
     *         printf("push linkedStack value：%d success\n",x);
     *     }
     *
     *     for(int x = 0;x<14;x++){
     *         int value = pop(linkedStack);
     *         printf("value = %d \n",value);
     *         if(NULL == value){
     *             printf("pop linkedStack fail\n");
     *             continue;
     *         }
     *          printf("pop linkedStack value %d success\n",value);
     *     }
     *     printf("size = %d \n",linkedStack->size);
     *
     *     printf("Hello world!\n");
     *     return 0;
     * }
     *
     *
     *
     * */
}
