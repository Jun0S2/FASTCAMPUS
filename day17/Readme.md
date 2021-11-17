# Stack
데이터를 제한적으로 접근할 수 있는 구조 -> 한쪽 끝에서만 자료를 넣거나 뺄 수 있는 구조
스택도 큐와 마찬가지로 https://visualgo.net/en/list 에서 시뮬레이션 해볼 수 있음.

## LIFO (Last in First Out)
* 가장 나중에 쌓은 데이터를 가장 먼저 뺄 수 있는 데이터 구조
* FILO 라고도 함 
* 대표적인 스택의 활용 : 컴퓨터 내부 프로세스 구조의 함수 동작 방식

## 용어
* push() : 데이터를 스택에 넣기
* pop() : 데이터를 스택에서 꺼내기
* peek() : 스택에 가장 마지막의 데이터 확인

## 장점
* 구조가 단순해서 구현이 쉽다
* 데이터 저장/읽기 속도가 빠름

## 단점
* **일반적인 스택 구현 시, 데이터 최대 갯수를 미리 정해야함**
* 따라서, 미리 최대 개수 만큼 공간을 만들어 놔야해서 저장 공간의 낭비가 생길 수 있음

## Java의 스택
* java에서 기본적으로 java.util.Stack 패키지에서 스택 클래스를 제공함
* push(item) method : 아이템을 stack 에 추가
* pop(item) method : stack에 마지막에 넣은 아이템을 리턴하고 해당 아이탬은 스텍에서 삭제

### import 문
```java
import java.util.Stack;
```

### 스택 생성
```java
Stack<Integer> stack = new Stack<Integer>();
```
큐와는 달리, LinkedLlist 클래스를 사용할 필요가 없다

### 데이터 추가
```java
atack.push(1);
stack.push(1201);
```

### 데이터 제거
```java
stack.pop(); //1201을 리턴하고 제거함
```
마지막 값을 리턴하고 제거한다

## ArrayList 클래스로 push 와 pop 구현해보기
* pop 호출 시, 데이터가 없을 경우 null을 리턴
* 다양한 데이터 타입을 다루기 위해 Generic 타입으로 구현
```java
public class MyStack<T>{
	private ArrayList<T> stack = new ArrayList<T>();
    public void push(T item){
    	stack.add(item);
    }
    public T pop(){
    	if(stack.istEmpty()) return null;
        return stack.remove(stack.size()-1);
    }
    public boolean isEmpty(){
    	if(stack.size()==0)return true;
        return false;
    }
}
```
https://junebee.tistory.com/23 에서 구현한 큐 자료구조와 대부분 동일하다. pop 실행 시, 큐에서는 가장 처음 원소를 제거하고 스택에서는 가장 마지막 원소를 제거하는 부분만 다르다.
