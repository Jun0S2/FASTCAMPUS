# 최단경로

### 최단 경로 - 다익스트라

가중치 그래프에서 간선의 가중치 합이 최소가 되도록 하는 경로를 찾는 것

유형

1\. 단일 출발 (다익스트라)

 특정 노드 -> 모든 그래프 돌아다니면서 가장 짧은 경로 찾기

2\. 단일 도착

 모든 노드에서 출발 -> 특정 노드로 도착하는 가장 짧은 경로

3\. 단일쌍

 u~ V까지의 최소 경로 (1 개)

4\. 전체쌍

 모든 쌍에 대한 최단 경로

### 다익스트라 알고리즘

> 다익스트라 알고리즘은 우선 순위 큐를 사용한다  
> 우선순위 큐를 활용하여 풀이하는 방식은 BFS 방식과 매우 유사한데, 이는 자식놔 연결된 노드를 체크하여 탐색하기 때문이다.

#### [https://www.cs.usfca.edu/~galles/visualization/Dijkstra.html](https://www.cs.usfca.edu/~galles/visualization/Dijkstra.html)

 [Dijkstra Visualzation

www.cs.usfca.edu](https://www.cs.usfca.edu/~galles/visualization/Dijkstra.html)

위 사이트를 통해 다익스트라 구현 방식을 한번 보는 것을 추천한다. 

다익스트라는, 노드와 본인 노드가 연결되지 않았다면 ∞ 를, 그렇지 않다면 본인과의 거리 (본인이면 0) 을 그래프에 넣어서 inf 가 아닌 노드들을 탐색하며 최단 거리를 탐색하는 방식이다.

[##_Image|kage@bSdHgB/btrjV9dlT7n/6ZutWk7kZcmdCmiBxDK8K0/img.png|alignCenter|data-origin-width="436" data-origin-height="455" data-ke-mobilestyle="widthOrigin"|||_##]

위 예시를 살펴보자.

노드 "0" 은 어떠한 노드와도 연결되어 있지 않다 (1과 2는 0으로 가는 방향일 뿐, 0에선 갈 수 없다).

이 관계를 다익스트라로 표현해 보자면,

| 노드 0 | 노드 1 | 노드 2 | 노드 3 | 노드 4 | 노드 5 | 노드 6 | 노드 7 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 0 | ∞  | ∞  | ∞  | ∞  | ∞  | ∞  | ∞  |

노드 "1" 을 살펴보면, 노드 5와 6으로 연결되어 있음을  확인해 볼 수 있다.

이는 아래와 같이 표현한다.

| 노드 0 | 노드 1 | 노드 2 | 노드 3 | 노드 4 | 노드 5 | 노드 6 | 노드 7 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 5 | 0 | ∞  | ∞  | ∞  | 5 | 3 | ∞  |

따라서, 다익스트라 방식으로 위 경로를 배열 형태로 나타내 보면

[##_Image|kage@z2qVb/btrjYimREq8/VRvp5ODCihOMTTl1E4Faa0/img.png|alignCenter|data-origin-width="298" data-origin-height="275" data-ke-mobilestyle="widthOrigin"|빈 공간은 &infin; 이다.||_##]

또, 리스트 형태로 나타내면 

[##_Image|kage@bsX1nc/btrjR7Veszi/YTkY69vr2NBfMdK9FPrQIK/img.png|alignCenter|data-origin-width="301" data-origin-height="319" data-ke-mobilestyle="widthOrigin"|||_##]

위와 같이 표현해 볼 수 있다.

노드 0, 1, 2를 다익스트라 방식으로 구현하면

[##_Image|kage@bfHdSv/btrjWSvloF4/dbnkTPiSgmgczMRoJsSpF0/img.gif|alignCenter|data-origin-width="600" data-origin-height="263" data-filename="dijkstra.gif" data-ke-mobilestyle="widthOrigin"|다익스트라 0~2 구현 애니메이션||_##]

### 자바 코드

#### 구현 과정

> **외부 클래스 생성**  
> 1\. 노드 클래스 생성  
> 2\. Comparator() 오버라이드  
> 3\. toString() 오버라이드  
>   
> **PriorityQueue 사용**  
>   
> **다익스트라 알고리즘 구현**

1\. 노드 클래스를 생성한다

BFS에서 하는 방식과 동일한 방식으로, constructor를 생성한다.

```
public class Node implements Comparable <Node> {
	int distance ; //가중치 (w) 혹은 거리
    String vertex;
    public Edge(int distance, String vertex){
    	this.distnace;
        this.vertex;
    }
    //Overrides goes here
}
```

2\. Comparator( )

```
@override
public int compareTo(Node node){
	return this.distance - node.distance;
}
```

PriorityQueue를 사용하기 위해서, compareTo를 override 해주어 거리순으로 정렬되게 해준다.

3\. toString( )

```
@Override
public String toString(){
	return "node : " + this.node + ", distance : " + this.distance;
}
```

4\. PriorityQueue

```
import java.util.PriorityQueue;
```

위 import 문을 통해 import해준다.

PriorityQueue의 사용 방법은 일반 큐와 비슷하다. 다른 점이 있다면, 최소힙처럼 가장 priority (현재는 compareTo에서 거리가 가장 짧은 순이 priority가 된다) 이 큐의 맨 위로 쌓이는 구조이다.

```
PriorityQueue <Node> q = new PriorityQueue<Node> (); //q 생성
q.offer(new Node(2, "A")); //add
q.poll();//poll out
q.peek(); //맨 위에값을 본다
```

위의 코드처럼 우선순위큐를 생성, 추가 및 삭제할 수 있다.

나는 q.add(new ~~) 보다는 q.offer()을 선호한다. ~취향존중~

5\. HashMap

다익스트라 알고리즘을 구현하기 앞서, 현재 구하려는 그래프를 HashMap을 통하여 입력해야 한다.

```
HashMap<String , ArrayList<Node>> graph = new HashMap<>(); //해쉬맵 생성
 //"A"노드에 연결된 노드들과 거리를 리스트 형태로 입력
graph.put("A", new ArrayList<Node> (Arrays.asList(new Node (8, "B"), 
		new Node(1 , "C"), new Node (1 , "D"));
```

위 해쉬맵 구조에서, 모든 노드의 연결 관계를 보고싶다면,

해쉬.keySet() 를 이용하여 iterate 하며, get(key) 하면 관계를 프린트하여 볼 수 있다.

```
for(String key : graph.keySet()){
	System.out.print("Node " + key + " : ");
    System.out.println(graph.get(key));
}
```

6\. 전체 구조

```
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

public class DijkstraPath {
	//노드 클래스
    public static class Node{
    	int distance, String node ; 
        //constructor 생성하기
        //toString 생성하기
        //compareTo 오버라이드하기
    }
    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Node>> graph, String start) {
		//input 받기
        
        /*거리 해쉬맵을 INF 로 초기화 시킨다*/
        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        for (String key : graph.keySet()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        /*시작 노드에 거리 0 을 준다*/
        distances.put(start, 0);
        
        /*큐 생성 및 시작 노드 삽입*/
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        priorityQueue.offer(new Node(distances.get(start), start));
        
        /*알고리즘 작성 : BFS와 비슷한 구조*/
        while (!priorityQueue.isEmpty()) {
        	/*큐에 있는 최소값 poll*/
            edgeNode = priorityQueue.poll();
       		/* 최소가 아니라면 continue 해주자..*/
            if (distances.get(currentNode) < edgeNode.distance) {
                continue;
            }
            /* 노드 리스트에 현재 노드와 연결된 노드들을 전부 더해준다 */
            nodeList = graph.get(edgeNode.node);
            /* 인접 노드 */
            for (int index = 0; index < nodeList.size(); index++) {
                adjacentNode = nodeList.get(index); //인접 노드 get
                adjacent = adjacentNode.node;		//인접 노드의 노드get
                weight = adjacentNode.distance;		//인접 노드에 저장된 distance get
                distance = edgeNode.distance + weight;// 새 거리 = 현재 거리와 인접 노드의 거리
                
                if (distance < distances.get(adjacent)) { //새 거리가 현재 거리보다 작으면 (최소)거리를 구하는 중이므로 큐에 들어갈 자격이 있다
                    distances.put(adjacent, distance);
                    priorityQueue.offer(new Node(distance, adjacent));
                }
                //새 거리가 더 크면 최소 거리를 구하는 중이므로 넣지 않는다..
            }
        }
        return distances;
    }
}
```
