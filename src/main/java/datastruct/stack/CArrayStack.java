package datastruct.stack;

public class CArrayStack {
    /*
    * #include <stdio.h>
#include <stdlib.h>

#define SIZE 10
typedef struct {
        int data[SIZE];
        int tail;
    } ArrayStack;

int push(ArrayStack *stack,int value){
    if(stack->tail == SIZE){
        return 0;
    }
   // printf("push stack tail = %d\n",stack->tail);
    int tail = stack->tail;
    stack->data[tail] = value;
    stack->tail++;
    return 1;
}
int pop(ArrayStack *stack){
    if(stack->tail == 0){
        return NULL;
    }
     stack->tail--;
    int index = stack->tail;
    int value = stack->data[index];
    printf("pop stack value = %d\n",value);
    return value;
}
int main()
{
    //定义存放栈元素的数组结构
    //定义存取栈元素的方法
    //编写测试的Main函数
    ArrayStack *arrayStack = (ArrayStack *)malloc(sizeof(ArrayStack));
    arrayStack->tail = 0;
    if(arrayStack == NULL){
        printf("malloc arrayStack fail\n");
    }
    for(int x = 0;x<12;x++){
        int status = push(arrayStack,x);
        if(status == 0){
            printf("push arrayStack value：%d fail\n",x);
            continue;
        }
        printf("push arrayStack value：%d success\n",x);
    }

    for(int x = 0;x<12;x++){
        int value = pop(arrayStack);
        if(NULL == value){
            printf("pop arrayStack fail\n");
            continue;
        }
         printf("pop arrayStack value %d success\n",value);
    }
    free(arrayStack);
    printf("Hello world!\n");
    return 0;
}
    *
    *
    * */
}
