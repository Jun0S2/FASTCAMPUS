#Queue

> 시청 날짜 : 11/16/2021  
> 시청 강의 : 큐

프로그래밍 중 가장 많이 쓰이는 자료구조 중 하나 : Data in and Data out 만 가능  
[https://visualgo.net/en/list](https://visualgo.net/en/list) 의 queue탭에서 시뮬레이션 가능

## FIFO (First in, First out)

![image](https://user-images.githubusercontent.com/36508552/141977380-1266e0cf-a86a-438c-b91b-692e75bea841.png)
이미지 출처 : https://www.programiz.com/java-programming/queue

-   LILO 라고도 불림 (last in last out)
-   가장 먼저 넣은 데이터를 가장 먼저 꺼낼 수 있는 구조 (스택과 반대)
-   front (앞)에선 무조건 삭제가 일어나고, rear(뒤, tail이라고도 함) 에선 무조건 삽입이 일어난다
-   "줄서기" 와 동일

## 용어

-   enqueue : 큐에 데이터를 넣는 기능
-   dequeue : 큐에서 데이터를 꺼내는 기능
-   peek : 맨 앞의 데이터가 무엇인지 훔쳐보는 기능 (literarlly peek..)

## JAVA의 큐

-   java에서 기본적으로 java.util 패키지에 Queue 클래스를 제공함
-   add(value), poll(), peek(), remove() 메서드 가능
-   Queue 클래스에서 데이터 생성을 위해서는 LinkedList클래스를 사용해야함

### import 문

```
import java.util.LinkedList<>();
import java.util.Queue
```

### 큐 생성

```
Queue<Integer> q = new LinkedList<>();
Queue<Node> q2 = new LinkedList<>(); //BFS에서 자주 쓰임. Node 클래스에 x,y,distance를 set& get
Queue<Integer> q3 = new Queue<>(); //불가능 !
```

앞서, 큐 클래스를 생성하기 위해서는 링크드 리스트를 사용해야 한다고 했는데, 3번째줄의 코드처럼 링크드리스트를 사용하지 않은 코드로는 큐 생성이 불가능 하다!

### 데이터 추가

> add(value) 또는 offer(value)를 사용

```
q.add(1);
q2.offer(new Node(2,3));
```

### 데이터 삭제

> poll()과 remove()를 사용

큐는 무조건 데이터를 앞에 삽입하고, 맨 뒤의 데이터를 삭제한다.  
따라서, 특정 엘리먼트를 지정할 필요 없이 poll() 또는 remove()를 사용하면 맨 뒤의 원소를 제거할 수 있다

```
q.remove();
Node info = q2.poll(); //이런식으로 info 에 맨 마지막 원소를 담아서 활용 가능
```

## ArrayList 클래스로 enqueue와 dequeue 구현해보기

-   dequeue : 큐 데이터가 없으면 null 리넡

```java
public class MyQueue<T>{ 
	private ArrayList<T> queue = new ArrayList<T>(); 
    public void enqueue(T item){ 
    	queue.add(item);
    }
    
    
    public T dequeue(){ 
    	if(q.isEmpty()){ return null; } 
    	//else: 
    	return queue.remove(0); //맨 앞 원소를 삭제 
    } 
    
    public boolean isEmpty(){ 
    	if(queue.size()==0){ return true; } 
    	return false; 
    }
    
}
```
