package datastruct.linked;

public class CLinked {
    /*
    * #include <stdio.h>
#include <stdlib.h>
//定义链表结构
struct list_node {
  int data; //节点中的成员
  struct list_node *next;//指向下一个节点的指针  ，
};
typedef struct list_node list_single;
typedef struct list_node list_multi;
//链表的创建，尾插法
list_multi * create_list_multi_node(int length){
    list_multi *head_node = NULL;
    head_node = (list_multi *)malloc(sizeof(list_multi));
    if(head_node == NULL){
        printf("malloc failure");
    }
    list_multi *tail_node = head_node;
    tail_node->next = NULL;
    for(int i = 0;i<length;i++){
        list_multi *node = (list_multi *)malloc(sizeof(list_multi));
        node->data = (100+i);
         node->next = NULL;
        tail_node->next = node;
        tail_node = node;
    }
    return head_node;
}
//取出链表的所有元素
void loop_list_multi(list_multi *head_node){
    list_multi *node = head_node->next;
    while(node != NULL){
        printf("%d\n",node->data);
        node = node->next;
    }
}

//链表的插入
void insert_list_multi(list_multi *head_node,int index,int data){
 int i = 0;
 list_multi *current_node = head_node;
 list_multi *swap_node = NULL;
 if(index < 1 && head_node != NULL){
        printf("insert failure \n");
        return ;
 }
 while (i < (index -1)){
    current_node = current_node->next;
    i++;
 }
 list_multi *new_node = (list_multi *)malloc(sizeof(list_multi));
 new_node->data = data;
 swap_node = current_node->next;
 current_node->next = new_node;
 new_node->next = swap_node;
  printf("insert success \n");
}

//链表的删除
void delete_list_multi(list_multi *head_node,int index){
    int i = 0;
    list_multi *current_node = head_node;
    list_multi *swap_node = NULL;
    if(index < 1 && head_node->next == NULL){
        printf("delete failure \n");
        return ;
    }
    while(i < (index -1)){
        current_node = current_node->next;
        i++;
    }
    swap_node = current_node->next;
    current_node->next = swap_node->next;
    free(swap_node);
    printf("delete success \n");
}

list_single * create_list_node(int data){
    list_single *node = NULL;
    node = (list_single *)malloc(sizeof(list_single));
    if(node == NULL){
        printf("malloc failure");
    }
    memset(node,0,sizeof(list_single));
    node->data = data;
    node->next = NULL;
    return node;
}

int main()
{
    list_multi *node_list = create_list_multi_node(10);
    loop_list_multi(node_list);
    insert_list_multi(node_list,3,333);
    loop_list_multi(node_list);
    delete_list_multi(node_list,3);
    loop_list_multi(node_list);
    free(node_list);
    /*int data = 100;
    list_single *node_ptr = create_list_node(data);
    printf("data = %d\n",node_ptr->data);
    printf("next = %d\n",node_ptr->next);
    free(node_ptr);
    */
    /*list_single *node = NULL;
    node = (list_single *)malloc(sizeof(list_single)); //malloc函数会返回开辟空间的首地址，加(list_single *)的目的是让计算机知道，如何去划分这个开辟的空间
    if(node == NULL){
        printf("malloc failure");
    }
    memset(node,0,sizeof(list_single));
    node->data = 100;  //->是一个整体，它是用于指向结构体子数据的指针，用来取子数据
    node->next = NULL; //5、将链表的指针域指向空
    printf("Hello world!\n");
    printf("%d\n",node->data);
    free(node);
    return 0;
}

    *
    * */

}
