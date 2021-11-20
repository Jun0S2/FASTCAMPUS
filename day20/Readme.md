# 원형 링크드리스트(1)
![image](https://user-images.githubusercontent.com/36508552/142721073-a7d06ed3-78be-4625-b9a8-1cd4fb8db112.png)

원형 링크드리스트에서 가장 큰 핵심은 **마지막 노드가 처음 노드의 주소를 가리킨다**는 점이다.

### 기본 구조
```java
public class CicularLinkedList{
    Node head; // head of list
 
    class Node {
        int data;
        Node next;
        Node(int d) { data = d; } //next는 null로 초기화된다
    }
}
```

### 비어있는 리스트에 노드 추가
```java
static Node addToEmpty(Node last, int data)
{
    if (last != null) //last 가 null -> circular linked list는 null이 없기때문에 비었단 소리다
      return last;
 
    // Creating a node dynamically
    Node temp =
          (Node*)malloc(sizeof(Node));
 
    temp.data = data;
    last = temp;

    temp.next = last; ///리스트가 비어있어서 추가 후 노드가 하나뿐이다-> 포인터가 본인에게 향하도록 설정
 
    return last;
}
```
### 맨 앞 노드 추가
```java
static Node addBegin(Node last, int data)
{   //비어다면, 위에 작성한 <빈 노드에 노드추가>에게 넘겨준다 
		/*넘겨주는이유: circularlist는 포인터가 null값을 가지지 않기 때문에 
		 *비어있다면 자신에게 포인터를 향하게 해줘야한다 */
    if (last == null) 
        return addToEmpty(last, data); 
      
    Node temp = new Node();
      
    temp.data = data;
   
    temp.next = last.next; 
    last.next = temp;
  
    return last;
}
```

code reference: Geeks for Geeks
내일 마저 링크드리스트 강의를 끝내고 남은 원형 리스트를 정리해보도록 하겠다.
