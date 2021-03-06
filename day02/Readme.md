# 그래프 이론과 그래프 탐색 (BFS , DFS)

> 날짜 : 2021 년 11 월 2 일  
> 시청 강의 : 그래프 이해와 자료 구조 , 너비 우선 탐색 (BFS) (1) , 너비 우선 탐색 (BFS) (2), 깊이 우선 탐색 (DFS)

## 그래프

### 기본 용어

-   그래프는 아이템들과 아이템 사이를 연결하는 관계를 표현한다
-   Vertex (정점) : 연결점
-   Edge (간선) : 연결 선
-   Degree (차수) : 정접에 연결된 간선 수
-   그래프는 vertex의 집합과 이들을 연결하는 edges의 집합으로 구성된 자료

### 유형

이미지 출처 :https://www.hackerearth.com/practice/algorithms/graphs/graph-representation/tutorial/

-   무향 그래프 (Undirected → 양방향)  

    ![image](https://user-images.githubusercontent.com/36508552/139849665-59c0a611-79c0-42be-b025-02cdd82f5f19.png)

-   유향 그래프(Directed)  

    ![image](https://user-images.githubusercontent.com/36508552/139849683-fad8fa36-c2a4-48d5-987d-490e322fde88.png)

-   가중치 그래프(Weighed)  

    ![image](https://user-images.githubusercontent.com/36508552/139849721-ab734e5a-0c8b-4201-a5a3-fd39d8776c09.png)

-   사이클이 없는 방향 그래프
-   완전 그래프
-   부분 그래프
-   **트리도 그래프의 한 종류임**
   
![image](https://user-images.githubusercontent.com/36508552/139849761-f67eca5d-c645-4dd6-a7f7-210a2a4ac92c.png)  

    |   | Tree | Graph |
    | --- | --- | --- |
    | Cycle | X | O |
    | Root | O | X |
    | Parent-Child | O | X |
    

### 그래프 종류

1\. 인접 행렬

인접 행렬은 두 정점을 연결하는 간선의 유무를 행렬로 표현한다.

하지만, 정수값을 가지는 가중치가 있고 N = 10000개라면 int\[10000\]\[10000\] 의 인접 행렬을 생성해야하는데 이는 1억개임. int형이 1억개면 메모리는 4억바이트가 된다. 메모리 조건이 400MB보다 크면 상관없지만 주로 이보다 작아서 인접 행렬을 사용할 수 없다. → 인접 리스트를 사용해야함

2\. 인접 리스트

각 정점에 대한 인접 정점들을 순차적으로 표현하여 하나의 정점에 대한 인접 정점들을 각각 노드로 연결하는 리스트로 저장한다.

정점도 어느정도 있고 간선도 많으면( 밀집 그래프), 인접 리스트는 하나씩 전부 들여다 봐야하기때문에 효율성이 좋지 않다. 또한, 입력이 2차원식으로 들어온다면 → 인접 행렬을 사용해야한다

3\. 간선 리스트

두 정점에 대한 간선 그 자체를 객체로 표현하여 리스트로 저장하며, 간선을 표현하는 두 정점의 정보를 나타낸다(시작 정점, 끝 정점)

간선(시작 정점, 끝 정점)의 정보를 객체로 표현하여 리스트에 저장. 즉, 간선 정보를 리스트로 유지

즉, 노드가 많으면 인접 리스트를 간선이 많으면 인접 행렬을 사용

### 그래프 표현 

자바에서 그래프를 표현하는 방식에는 여러가지가 있다. 나는 주로 2D 배열을 사용하여 인접 배열 형태로 나타내는데, 이번 패스트캠퍼스 강의에서 HashMap으로 표현하는 방법을 배웠는데 인상적이였다.

ArrayList를 잘 활용하는 편이 아니라, 인접리스트 구현이 많이 어려웠는데 강의를 듣고나니 이해가 많이 됬다. ~물론 문제에 접목 가능한 정도인지는 백준을 열어봐야..~

```java
		HashMap<String , ArrayList<String>> graph = new HashMap<>();
		graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
		graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
		graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
		//이하 생략
```

1\. HashMap 을 사용하여 그래프 표현

2\. put () 을 사용하여 각 노드 당 연결된 노드를 삽입

3\. Arrays.asList() 를 사용하면, 쉽게 리스트를 만들 수 있다.

### 너비 우선 탐색

본 강좌에서는 visited와 needVisit 리스트 두개를 사용하여 방문처리 리스트와 방문중인 큐를 리스트로 구현하였는데, 나는 큐 방식이 더 쉽다고 느끼기에 큐를 사용하여 BFS를 구현해 보겠다.

```java
	private static void bfs(HashMap<String, ArrayList<String>> graph, String startNode) {
		StringBuilder sb = new StringBuilder();
			Queue<String> q = new LinkedList<>();
			ArrayList<String> visited = new ArrayList<>();
			
			q.offer(startNode);
			while(!q.isEmpty()) {
				String currentNode = q.poll(); //현재 방문중인 노드
				
				if(visited.contains(currentNode)) continue; 	//이미 방문중이라면 pass
				sb.append(currentNode + "-> ");			//현재 방문중인 노드 sb에추가
				visited.add(currentNode);			//방문처리
				q.addAll(graph.get(currentNode));		//현재 노드의 하위 자식들을 큐에추가
			
			}
			sb.setLength(sb.length()-3);
			System.out.println(sb.toString());
	}
```

보통 visited 리스트는 boolean 형태의 배열로 표현하는데, 리스트를 사용하니 .contains()를 사용할 수 있어서 편했다.

설명을 주석처리 해놨지만, 큐와 방문처리를 할 리스트를 구현한 후 시작점을 큐에 놓고 큐가 빌때까지 방문하지 않은 노드들을 큐에 담아 반복한다.

만일 아래 그래프라면,

![image](https://user-images.githubusercontent.com/36508552/139849453-3a44d902-6156-42b3-b1e2-d7c1bea3f7e2.png)

1\. A 를 큐에 담는다 (start node)

2\. 반복문 시작:

3\. 큐의 맨 앞을 뺀다 -> A 

4\. 방문중인가? no -> A를 방문처리해주고, A와 연결된 B와 C를 큐에 넣는다

5\. 반복문으로 돌아가서 큐의 맨 앞을 뺀다 -> B

6\. 방문중인가? no -> B를 방문처리 해주고, B와 연결된 D,E,F를 큐에 넣는다

현재 큐 상태 :\[C, D, E, F\]

7\. 반복문으로 돌아가서 큐의 맨 앞을 뺀다 -> C

8\. 방문 중인가 ? no -> C의 자식인 H, I ,G를 큐에 삽입

현재 큐 상태 : \[ D, E, F, H, I, G\]

위 노드들은 자식들이 없기 때문에 순차적으로 빠지고 나면 최종 순서는

A -> B -> C -> D -> E -> F-> G -> H -> I 가 되는 것을 볼 수 있다 

위 프로그램을 돌려보면, 성공적으로 BFS 가 구현됨을 확인해 볼 수 있다.

![image](https://user-images.githubusercontent.com/36508552/139849379-a47560a6-fa08-4b89-b00f-edfcfdbc1782.png)

### 깊이 우선 탐색

주로 재귀 형태로 쓰인 깊이 우선 탐색을 많이 봤는데, BFS와 거의 동일한 방식으로 사용하는 방법을 본 강좌에서 가르쳐주었기 때문에 너무 편리했다. 

항상 너비 우선 탐색만 사용하다가 DFS가 생각이 안나서 못푼 문제가 있는데 이가 갈린다... BFS 구현과 한줄 빼고 같다고 보면 된다. BFS는 큐를 사용했지만, DFS는 스택 자료 구조를 사용한다. 즉, BFS에서 current Node를 poll 해서 맨 앞에 들어간 원소를 빼냈다면  DFS는 스택(리스트)의 가장 마지막 원소를 빼내면 된다 !

```java
	private static void dfs(HashMap<String, ArrayList<String>> graph, String startNode) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> visited = new ArrayList<>();
		ArrayList<String> stack = new ArrayList<>();
		
		stack.add(startNode);
		while(!stack.isEmpty()) {									
			String currentNode = stack.remove(stack.size()-1);//마지막 원소를 poll한다
			if(visited.contains(currentNode))continue;	//이미 방문중이라면 pass
			sb.append(currentNode + "-> ");			//현재 노드 프린트
			visited.add(currentNode);			//방문처리
			stack.addAll(graph.get(currentNode));		//현재 노드 자식 다 더하기
		}
		sb.setLength(sb.length()-3);
		System.out.println(sb.toString());
	}
```

BFS 와는 다르게, 형제 노드를 먼저 방문하는 것이 아니라 자식 노드부터 방문한다.

위에서 쓰인 예시로 DFS를 바라본다면,

![image](https://user-images.githubusercontent.com/36508552/139849438-2e0575b2-1d09-4514-ae28-e42c29bf0420.png)

1\. A 를 스택에 담는다 (코드에선 리스트로 구현)

2\. 반복문 시작: 

3\. 가장 최신에 더한 원소를 스택에서 뺀다  : A 

4\. 방문했는가 ? no -> 방문 처리 후, A 의 자식들을 스택에 넣는다 \[B, C\]

5\. 반복문으로 돌아가서, 가장 최신 원소를 뺀다 : C

6\. 방문했는가 ? no -> 방문 처리 후, C 의 자식들을 스택에 넣는다 \[B ,G, H ,I \]

7\. 반복문으로 돌아가서, 가장 최신 원소를 뺀다 : I

8\. 방문했는가 ? no -> 방문 처리 후 I 의 자식을 스택에 넣는다 -> 없음 

현재 스택 : \[B, G, H\]

9\. 가장 최신 원소 H 를 빼내고 자식이 없으니 다시 G를 빼고 마찬가지로 자식이 없으니 B를 빼낸다

10\. B는 자식이 있기 때문에 위의 과정을 반복한다

최종적으로,

A-> C-> I-> H-> G-> B-> F-> E-> D 순서로 그래프를 순회하게 된다.

위 코드를 돌려보면,

![image](https://user-images.githubusercontent.com/36508552/139849340-33ba51e5-4814-43fa-b156-051d4339df16.png)

정상적으로 프로그램이 작동 된 것을 확인 해 볼 수 있다.

