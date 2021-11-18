# 링크드리스트

## 정의

![image](https://user-images.githubusercontent.com/36508552/142427249-270492e2-f64d-42ab-a892-56c52abb2cfe.png)

연결 리스트 (Linked List)로 각 노드가 데이터와 포인터를 가지고 한줄로 연결되어있는 방식의 자료 구조.

1.  링크드리스트는 인덱스가 존재하지 않기 때문에 검색 및 수정 시 첫번째 노드부터 순차적으로 모든 노드를 탐색해야 한다 → 시간복잡도 : O(1)
2.  **특정 위치(index)로** 삽입/삭제/탐색해야 하는 경우는 O(n) 이 걸린다. 따라서, 탐색을 자주해야 한다면 배열을 사용하고, 데이터의 추가/삭제가 많은 경우에 연결 리스트를 사용하는 것이 좋다.


연결리스트는 단일 연결리스트, 이중 연결리스트, 원형 연결리스트가 있다.

## 단일 연결리스트 (Singly Linked List)

![image](https://user-images.githubusercontent.com/36508552/142427255-1a6113a6-f6ed-41e2-bec3-49ad2307ed89.png)

단일 연결리스트는 데이터의 요소를 선형적으로 연결한 것이다.

단일 연결리스트: 하나의 노드에 한개의 포인터와 데이터값이 저장된다

1.  메모리와 디스크에 연속적으로 데이터를 할당해야하는 배열과는 다르게, 전체 구조를 재할당하거나 재구성하지 않고도 특정 지점에 요소를 추가하거나 제거할 수 있어서 유리하다.
2.  포인터로 다음 연결 리스트를 지정한다. 즉, 배열보다 더 많은 메모리를 사용하게 된다.
3.  단일 연결리스트는 특정 리스트값을 확인하기 위해서 리스트 안의 항목을 순차적으로 순회해야한다.

## 구현
### 기본 구조

![image](https://user-images.githubusercontent.com/36508552/142427320-40641df3-a611-4eff-882f-542a69f7add8.png)

```java
class LinkedList {
    Node head; 

    class Node {
        int data;
        Node next;

        Node(int d) { data = d; } //next 는 null 로 초기화
    }
}
```
### 맨 앞에 노드 추가
```java
// 맨 앞에 노드를 추가하는 코드 : 새로운 해드 생성
public void push(int new_data)
{
    Node new_node = new Node(new_data);
 
    new_node.next = head; //새 노드가 기존의 헤드를 가리키도록 해준다
 
    head = new_node;      //추가한 노드를 헤드로 지정한다
}

```
![image](https://user-images.githubusercontent.com/36508552/142427428-af8000a1-6ab9-475d-879f-5df3851cbebc.png)


### 특정 노드 뒤에 새로운 노드 추가
```java
//주어진 노드 뒤에 새 노드를 추가하는 구현이다
//이전 노드가 비어있으면 추가할 수 없다
public void insertAfter(Node prev_node, int new_data)
{
    if (prev_node == null) //비어있으면 추가 불가능
    {
        System.out.println("The given previous node cannot be null");
        return;
    }
 
    Node new_node = new Node(new_data);
 
    //ex: 1 _4(추가)_ 2 3 : 1이 2를 가리키고 있었다면, 4가 2를 가리키도록 해준다
    new_node.next = prev_node.next; //노드의 포인터가 이전노드가 가리키던 노드를 가리키게한다

		//ex: 1 _4(추가)_2 3 : 1은 기존에 2를 가리키고 있었지만, 4를 가리키도록 변경한다 
	  prev_node.next = new_node;      //이전 노드의 포인터가 새 노드를 가리키게 한다
}
```

![image](https://user-images.githubusercontent.com/36508552/142427547-93fc0006-c223-40bd-925f-d6ef85e9c978.png)
![image](https://user-images.githubusercontent.com/36508552/142427562-6ef08db9-5b59-418d-a698-dac375914193.png)


### 맨 뒤에 새로운 노드 추가
```java
//맨 뒤에 추가
public void append(int new_data)
{
    Node new_node = new Node(new_data);
    Node last = head; //while 에서 쓰임 : 현재의 head를 지정

    if (head == null)  //리스트가 비어있다면 추가하려는 노드가 헤드가 된다
    {
        head = new Node(new_data);
        return;
    }
 
    new_node.next = null; //노드를 맨 마지막에 추가하려고 하기 때문에 포인터는 null을 가리키게 해준다
    //else 문 부분   
    while (last.next != null)  //헤드가 다음 노드를 가리킨다면
        last = last.next;      //last(처음에 head로 초기화)를 다음 노드로 설정->iterate 한다
 
    last.next = new_node; //마지막 노드가 새 노드를 가리키게 해준다
    return; //free the memory
}
```
![image](https://user-images.githubusercontent.com/36508552/142427631-0f37dae3-800d-4126-943b-772fba5b57ee.png)


### 노드 삭제
```java
//주어진 key를 삭제
void deleteNode(int key)
    {
        Node temp = head, prev = null; //head 저장
 
        if (temp != null && temp.data == key) { //노드가 head고 head 다음값이 존재할때
            head = temp.next; // 헤드를 다음 값으로 교체한다
            return;
        }
 
        while (temp != null && temp.data != key) { //iterate
            prev = temp;
            temp = temp.next;
        }
 
        if (temp == null) //리스트에 주어진 key가 없다 -> escape
            return;
 
        // ex: 1 2 3(삭제) 4 : 2의 포인터를 3이 기존에 가리키던 4를 가리키게한다
        prev.next = temp.next; //이전 노드의 포인터가 삭제할 포인터의 다음값을 가리키게한다
    }
```
위 코드에서 삭제할 노드가 헤드일 경우 설명:
![image](https://user-images.githubusercontent.com/36508552/142427682-e1590f50-bc3a-4524-85d6-c69e574768ad.png)

```java
 if (temp != null && temp.data == key) { //노드가 head고 head 다음값이 존재할때
            head = temp.next; // 헤드를 다음 값으로 교체한다
            return;
        }
```

헤드가 아닌 부분 - while loop  구현 설명 : 
![image](https://user-images.githubusercontent.com/36508552/142427823-08d578c0-ad09-4757-8fef-ebf0e308517e.png)

```java
 while (temp != null && temp.data != key) { //iterate
            prev = temp;
            temp = temp.next;
        }
 
        if (temp == null) //리스트에 주어진 key가 없다 -> escape
            return;
 
        // ex: 1 2 3(삭제) 4 : 2의 포인터를 3이 기존에 가리키던 4를 가리키게한다
        prev.next = temp.next;
```
source code reference: Geeks for Geeks - Linkedlist
