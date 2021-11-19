​
## Doubly LinkedList (이중 연결리스트)
이중 연결리스트는 하나의 노드에 두개의 링크가 존재한다.
​
1. 각 노드는 이전 노드의 참조 링크와, 다음 노드의 참조링크를 가지고 있다
2. 전체 노드를 순회해야 이전 노드를 찾을수 있는 단일 연결리스트와는 다르게, **이중 연결리스트는 이전 노드의 링크를 통해 O(1)의 시간 복잡도로 이전 노드를 찾을 수 있다**
3. 노드의 추가와 제거가 단일 연결리스트보다 많은 작업을 해야한다
4. 작업이 더 단순하고 잠재적으로 더 효율적이다
​
## 구현
아래 코드는 위의 연결 리스트의 기본 구조 - head, next, previous links 를 표현한 코드이다
​
### 노드 표현
​
```java
public class DLL {
    Node head; // head of list
 
    /* Doubly Linked list Node*/
    class Node {
        int data;
        Node prev;
        Node next;
        Node(int d) { data = d; } //next 와 prev 은 null로 초기화된다
    }
}
```
​
### 맨 앞에 노드 추가
​
```java
// 맨 앞에 노드를 추가하는 코드
public void push(int new_data)
{
    Node new_Node = new Node(new_data);
​
     /*새로 추가하려는 노드의 다음 포인터를 기존의 head를 참조하게 만들고
      *이전 포인터는 Null 로 만든다-> 즉 자기자신을 head로 만드는 것*/
    new_Node.next = head;
    new_Node.prev = null;
 
    /* 기존 Head의 이전 참조값이 새로 생성한 노드를 가리키도록 한다*/ 
    if (head != null)
        head.prev = new_Node;
 
    /* Head가 새로 생성된 노드를 가리키게 한다 */
    head = new_Node;
}
​
```
​
### 특정 노드 뒤에 새로운 노드 추가
​
```java
//주어진 노드 뒤에 새 노드를 추가하는 구현이다
//이전 노드가 비어있으면 추가할 수 없다
public void InsertAfter(Node prev_Node, int new_data)
{
        if (prev_Node == null) {
        System.out.println("The given previous node cannot be NULL ");
        return;
    }
 //생성 가능한 위치
    Node new_node = new Node(new_data);
 
    /* 추가되기 전의 노드가 가리키던 다음 값을 추가하려는 노드의 다음값으로 세팅한다
         * ex: 1  2  3  ___  4 : 빈칸에 노드를 넣으려면, 원래는 3이 4를 가리키고 있었지만
     * 빈칸에 들어갈 노드가 4를 가리키도록 지정해야 한다 */
​
    new_node.next = prev_Node.next; 
 
    /* 주어진 노드의 다음 참조 포인터가 새로 추가할 노드를 가리키도록 한다
         * ex: 1  2  3  ___ 4 : 3은 빈칸(새로추가하려는노드)을 가르켜야한다*/
    prev_Node.next = new_node;
 
    /* 추가한 노드의 이전 참조 포인터는 앞의 노드를 가리키도록 한다
         *  ex: 1  2  3  ___ 4: 새로 추가한 노드의 이전참조포인터는 3을 가리켜야한다 */
    new_node.prev = prev_Node;
 
    /*추가한 노드의 다음 노드의 이전 참조 포인터가 새로 추가된 노드를 가리키게한다 */
    if (new_node.next != null)
        new_node.next.prev = new_node;
}
​
```
​
### 맨 뒤에 새로운 노드 추가
```java
//맨 뒤에 노드 생성
void append(int new_data)
{
    Node new_node = new Node(new_data); //새 노드
 
    Node last = head; //while 루프 안에서 쓰임
 
    // 새 노드는 마지막에 추가하기 때문에 다음 참조값은 null을 가진다
    new_node.next = null;
 
    // 리스트가 비어있다면, 새로 추가하려는 노드는 헤드가 된다
    if (head == null) {
        new_node.prev = null;
        head = new_node;
        return;
    }
 
    // 마지막 노드까지 순회한다
    while (last.next != null)  //원래 해드의 다음 참조값이 존재하면
        last = last.next;    //그 다음 노드를 확인한다 -> 원래 리스트의 가장 마지막 원소 확인
 
    // 기존 리스트의 가장 마지막 노드의 next pointer 를 새로 추가한 노드를 가리키게한다
    last.next = new_node;
 
    // 새로 추가한 노드의 이전값은 기존 배열의 마지막을 가리킨다
    new_node.prev = last;
​
```
​
###노드 삭제
```java
//노드 삭제
void deleteNode(Node del)
 {       // Base case: head 나 삭제하려는 노드가 null이면 멈춤
        if (head == null || del == null) {
            return;
        }
 
        // 지우려는 노드가 head 노드일 때:
        if (head == del) {
            head = del.next; //새로운 해드를 기존의 지우려는 헤드가 가리키는 다음 노드로 지정
        }
                //ex: 3  2  1(del)  5 -> 1은 2를 가리키고 있는데, 5가 1을 가리키도록 해준다
        if (del.next != null) {//삭제하려는 노드가 마지막 노드가 아니라면
            del.next.prev = del.prev; //삭제하려는 노드의 이전 참조값이 삭제하려는 노드의 이전 탐색값을 가리키게 한다
        }
 
        //ex : 3  2  1(del)  5 -> 2가 5를 가리키게 해눈다
        if (del.prev != null) { //삭제하려는 노드가 헤드가 아니라면
            del.prev.next = del.next; //삭제하려는 노드의 이전노드가 삭제하려는 노드의 다음노드를 가리키게 한다
        }
 
        return; //free the memory
    }
```
​
code reference : Geeks for Geeks
​
