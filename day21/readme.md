# 원형 링크드리스트 (2)

### 맨 뒤에 새로운 노드 추가
```java
static Node addEnd(Node last, int data)
{
    if (last == null)
        return addToEmpty(last, data);
     
    Node temp = new Node();
 
    temp.data = data;
    temp.next = last.next;
    last.next = temp;
    last = temp;
 
    return last;
}
```
### 특정 노드 뒤에 추가
```java
static Node addAfter(Node last, int data, int item)
{
    if (last == null)
        return null;
 
    Node temp, p;
    p = last.next;
    do
    {
        if (p.data == item)
        {
            temp = new Node();
            temp.data = data;
            temp.next = p.next;
            p.next = temp;
 
            if (p == last)
                last = temp;
            return last;
        }
        p = p.next;
    } while(p != last.next);
 
    System.out.println(item + " not present in the list.");
    return last;
 
}
```

### 노드 삭제
```java
//주어진 키 노드 삭제
static Node deleteNode(Node head, int key)
    {
        if (head == null) //리스트가 비어있는 경우
            return null;
 
        Node curr = head, prev = new Node();
        while (curr.data != key) {
            if (curr.next == head) { //못찾은 경우
                System.out.printf("Key does not exist int this list");
                break;
            }
 
            prev = curr;
            curr = curr.next;
        }
    }
```

Code Reference : Geeks for Geeks
