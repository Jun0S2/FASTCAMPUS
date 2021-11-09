
일단 개념만 이해하고 코드는 나중에 이해하기로 결심했다.

### 크루스칼 vs 프림 알고리즘

1\. 크루스칼 알고리즘 : 간선 정보를 정렬 -> 가장 가중치가 낮은 간선부터 사이클이 생기지 않도록 연결

2\. 프림 알고리즘 : 시작 정점을 선택 -> 정점의 인접 간선 중 가중치가 최소인 간선을 연결

### 프림 알고리즘

[https://www.cs.usfca.edu/~galles/visualization/Prim.html](https://www.cs.usfca.edu/~galles/visualization/Prim.html)

 [Prim MST Visualzation

www.cs.usfca.edu](https://www.cs.usfca.edu/~galles/visualization/Prim.html)

위 사이트에 프림 알고리즘을 실행해 보면 도움이 많이 되니 참고해 보면 좋을 것 같다.

[##_Image|kage@XKRxj/btrj87rVjXD/FG90f3YOqcIpJ1C916LJK0/img.png|alignCenter|data-origin-width="920" data-origin-height="772" data-ke-mobilestyle="widthOrigin"|이미지 출처 : PNGWing||_##]

구글로 검색해서 대충 아무 그래프 사진을 가져와봤다.

프림 알고리즘은, 임의의 정점을 선택하여 그와 연결된 간선 중 가중치가 작은 정점을 선택하기를 반복하는 알고리즘이다. 따라서 위 그래프에 프림 알고리즘을 적용해보면,

1\. "A" 선택 : A-B(12), A-D(5) -> D 노드를 선택

2\. "A- D " : A-B(12), D-B(10), D-E(15) , D-F(6) -> F 노드를 선택

3\. "A - D - F " : A-B (12), D-B (10), D-E(15) , F-E(1) , F-G (11) -> E노드 선택

4\. "A- D- F - E " : A-B (12), D-B (10), D-E(15) , F-G (11), E-B(7) , E-C(2),  E-G(9)  ->C노드 선택

5\. "A- D- F - E - C" : A-B (12), D-B (10), D-E(15) , F-G (11), E-B(7) ,  E-G(9)  , C-B(8) -> E와 연결된 B노드 선택

G만 연결하면 끝남 : 가중치가 작은 E와 연결

A - D - F - E- C- G 노드를 전부 연결 : 최종 가중치 : 5 + 6 + 1 + 2 + 7 + 9 = 30 이 된다

### 프림 알고리즘 구현

> 1\. 임의의 정점 선택 -> 연결된 노드 집합에 삽입  
> 2\. 선택 정점에 연결된 간선을 간선 리스트에 삽입 -> 가중치가 가장 작은 edge 추출  
>    if 간선에 연결된 인접 정점이 연결된 노드 집합에 있다면 사이클이 생기므로 선택 안함  
>    else 간선을 연결하고 mst에 간선 정보 삽입  
> 3\. 추출한 간선을 리스트에서 pop  
> 4\. 간선 리스트가 빌때까지 2-3 을 반복

### 구현에 필요한 기능

#### 노드 클래스

```
public static class Node implements Comparable<Node>{
	int weight, node1, node2;
    //setter method 구현
    @Override
    public int compareTo(Node node){
    	return this.weight - node.weight;
    }
}
```

#### Priority Queue사용

다익스트리즘 알고리즘때처럼, 프림 알고리즘은 우선순위 큐를 사용하여 최소힙을 구현한다

```
import java.util.PriorityQueue;
```

큐 생성 및 추가

```
PriorityQueue<Node> p = new PriorityQueue<>();
p.offer(new Node (2 , "a", "b")); //가중치 : 2, 연결된 두 노드
```

#### HashMap 사용

Hashmap의 containsKey() 를 사용하여, 간선에 연결된 인접 정점이 연결된 노드 집합에 이미 있는지를 판단할 수 있다.

```
HashMap<String, ArrayList<Node>> graph = new HashMap<>();
graph.put("a", new ArrayList<Node> ()); // a를 해쉬맵에 삽입
// 후에 a가 있는지 확인
graph.getOrDefault("a", new ArrayList<Node>());
```

### 자바 코드 구현

자바 코드를 구현하기 위해 필요한 리스트가 꽤 많아서 복잡하다..

먼저, 대략적인 코드의 흐름은 아래와 같다.

```
import java.util.HashMap;
import java.util.PriorityQueue;

public class PrimPath {
	//declare lists
    //Node class
    
    public ArrayList<Node> prime(String startNode, ArrayList<Node> ndoes){
    	//리스트 선언
        //priority 큐 선언
    
    	//초기화
        //각 노드마다 연결된 간선 리스트 추가
        //우선순위큐에 candiateEdgeList를 추가
        //우선순위큐가 빌때까지 2-3 알고리즘 반복
    }
}
```

강의 외에 다른 자료들을 찾아보고 좀 더 쉬운 방식이 있다면 다시 자바 코드 구현을 올리도록 하겠다.

강의 코드는 리스트가 너무 많이 쓰여서 복잡한것 같다.
