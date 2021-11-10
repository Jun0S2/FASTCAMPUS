# 크루스칼 알고리즘 정리

### 신장 트리 (Spanning Tree)

-   전체 노드가 연결되어 있다.
-   사이클이 존재하지 않는다.

![image](https://user-images.githubusercontent.com/36508552/140524550-4430fae5-39a6-4d7f-a7b9-e7dba508eff5.png)

위 그래프에서, 그래프 G 는 사이클이 존재한다. 

스패닝 트리는, 모든 노드가 연결되어있으면서 사이클이 존재하지 않는 그래프다

### 최소 신장 트리

-   가중치가 존재하는 신장 트리 중,  간선의 합(weight)이 가장 작은 트리

![image](https://user-images.githubusercontent.com/36508552/140524531-edc58a31-29d6-4c44-8e85-d8560616c016.png)

만약, 앞의 신장 트리가 다음과 같은 가중치를 가지고 있다고 가정해보자. 그렇다면, 최소 신장 트리는 가중치가 가장 작은 트리로 첫번째 스패닝 트리가 최소 신장 트리가 된다. 

현실 세계에서 이러한 알고리즘이 적용되는 예로 최단거리 길찾기 알고리즘 등이 있다.

### 크루스칼 알고리즘

크루스칼 알고리즘은 그리디 방식으로 최소 스패닝 트리를 찾아낸다

이 알고리즘을 실행하는 방식은 아래와 같다.

-   모든 정점을 독립적인 집합으로 만듦
-   비용을 기준으로 간선 정렬 (최소가 가장 위에오게한다)
-   두 정점의 루트를 확인하고, 루트가 서로 다를경우에만 두 정점을 연결한다 

만약, 두 정점의 루트가 같다면 사이클이 발생하게 된다. 아래 그림을 참고하면 쉽게 이해할 수 있다.

![image](https://user-images.githubusercontent.com/36508552/140524573-94a96dd6-de8b-4dac-9753-2acfcca19e61.png)

위와같은 그래프가 있다고 가정해보자.

B와 C는 루트가 같다 (A)

![image](https://user-images.githubusercontent.com/36508552/140524600-244f22ac-ac37-4951-a2f4-25d35035746c.png)

B 와 C를 연결하면 사이클이 생성되고, 더이상 신장 트리가 아니게 된다.

### 구현 알고리즘

그렇다면, 크루스칼 알고리즘을 구현하기 위해선 어떻게 해야할까.

앞서 말했듯이, 크루스칼 알고리즘은 아래 방식으로 구현된다.

-   모든 정점을 독립적인 집합으로 만듦
-   비용을 기준으로 간선 정렬 (최소가 가장 위에오게한다)
-   두 정점의 루트를 확인하고, 루트가 서로 다를경우에만 두 정점을 연결한다 

#### Union-Find 알고리즘

크루스칼 알고리즘의 핵심이라고 할 수 있는, 사이클의 유무를 판단하기 위해 유니온파인드 알고리즘을 사용한다.

이를 위해서는 먼저,

1\. 모든 정점을 독립적인 집합으로 초기화 시킨다.

| A | B | C | D | E | F | G | H |
| --- | --- | --- | --- | --- | --- | --- | --- |

각 노드들을 분리시키고, 본인 노드를 본인에만 연결시켜 초기화 한다.

2\. Union : 두개의 집합을 하나로 합친다

> tree1 + tree2 = newTree

트리들을 가중치가 낮은 트리를 골라서 합쳐야 하기 때문에 Union 알고리즘을 이용하여 트리를 합친다

3\. Find : 두 노드의 루트가 같은지 판단한다

두 노드의 루트가 같다면, 사이클이 생성되기 때문에 Find를 통하여 루트를 찾아낸다.

#### Union-by-rank 와 Path-Compression 알고리즘

크루스칼 알고리즘은, 최소 스패닝 트리로 구현하기 때문에 최악의 경우 링크드리스트같은 형태가 될 수 있다.

![image](https://user-images.githubusercontent.com/36508552/140524622-c520dd29-813a-4b73-ad79-b689943147cc.png)

예를 들어, 위 그래프를 확인해보자.

a 노드가 루트라고 가정했을 때, e 노드의 루트를 찾기 위해서 모든 노드를 거쳐가야한다.

이렇게, 링크드 리스트의 형태를 띄게 되면 시간 복잡도가 높아지게 되는데 (O(N)) , 이러한 문제를 효과적으로 해결하기 위해 Union-by-rank 와 Path - compression 기법을 사용한다.

#### Union-by-rank

rank 를 기억하고, union 시 두 트리의 랭크가 다르면 높이가 작은 트리를 큰 트리에 붙이고 높이가 같다면 한쪽을 크게 만들고 합체한다.


![image](https://user-images.githubusercontent.com/36508552/140524641-881b123d-776b-496a-a81e-b0ffb4e09350.png)

이 알고리즘을 적용하면 높이가 무한정 높아지는것을 방지할 수 있다.

#### Path Compression

1\. find를 실행한 노드에서 거쳐간 노드를 루트에 다이랙트로 연결시킴

2\. find를 실행한 노드는, 이후부터 루트 노드가 누구인지 한번에 알 수 있음

![image](https://user-images.githubusercontent.com/36508552/140524703-c8d5409a-4420-4a02-beb3-033b33b07f41.png)


위 그림에서, a의 루트를 찾기 위해 find 를 실행하게 되면 : a 부모는 b, b의 부모는 c, c의 부모는 d . d는 부모가 자기 자신이므로 루트 노드이다.

하지만, 계속 이런 방식으로 루트를 찾는것은 비효율적이다. 따라서, 이렇게 연결된 노드들의 루트 노드를 바로 불러올 수 있게 매번 부모를 업데이트 시켜서 일렬로 나열하는 방식이다.

a: 부모- b

b: 부모 - c -> a의 루트를 c로 업데이트

c: 부모 -d -> a,b의 루트를 d로 업데이트

### 코드 구현

위에서 말한 알고리즘을 자바 코드로 구현하기 위해서는 다음이 필요하다:

-   Node Class : 가중치, 정렬 오버라이드
-   Find method : 루트를 찾는다 (path compression 기법을 사용하여 시간 단축)
-   Union method : 간선을 연결한다 (union by rank 기법을 사용)
-   MakeSet method: 랭크를 초반에 초기화 시켜서 모든 정점을 독립적인 정점으로 만들고 시작할 메서드
-   Kruskal method: 위 메서드를 이용하여 크루스칼 알고리즘을 실질적으로 구현할 메서드

시작하기 앞서, 부모 노드를 기록할 해쉬맵과 본인 랭크를 기록할 해쉬맵을 설정해준다.

```
HashMap<String, String> parent = HashMap<>();
HashMap<String, Integer> rank = HashMap<>();
```

> 1\. 노드 클래스  
> 정렬 오버라이딩과 weight이 핵심

```
public static class Node implements Comparable<Node>{
	int weight, V, U;
    //setter method 구현
    @Override
    public int compareTo(Node node){
    	return this.weight - node.weight;
    }
}
```

> 2\. Find Method  
> Path Compression 기법을 사용하여 시간 복잡도 단축

```
public String find (String node) {
	if(parent.get(node)!=node){	//자기 부모가 루트가 아니라면
    	parent.put(node.this.find(this.parent.get(node))); //부모의 부모 찾기
    }
	retunr this.parent.get(node);//최종 루트 반환
}
```

> 3\. Union Method  
> Union by Rank 기법을 사용하여, 두 간선을 연결한다

```
public void union(String u, String v){ //노드 u와 노드 v
	String root1 = find(u); //노드 u의 루트를 찾는다
    String root2 = find(v); //노드 v의 루트를 찾는다
    //union by rank 기법
    if(rank.get(root1) > rank.get(root2)){ //u의 랭크가 v의 랭크보다 크다면 v를 u에 더한다
    	parent.put(root2, root1);
    }
    else{
    	parent.put(root1, root2); //반대인 상황
        if(rank.get(root1) == rank.get(root2){ //만약 랭크가 같으면 하나를 조정한다
        	rank.put(root2, rank.get(root2)+1 ); //루트 2의 랭크를 조정시킴
        }
    }
}
```

> 4\. 초기화  
> 전체 노드를 하나씩 받아 자신을 루트 노드로 만들고 랭크를 0으로 초기화 시켜서  
> 분리된 set 으로 만드는 작업

```
public void makeSet(String node) {
	parent.put(node, node) ;//자기 자신 연결
    rank.put(node, 0);//랭크 0으로 초기화
}
```

> 5\. 크루스칼 메서드  
> 앞서 구현한 기능들을 활용하여 최소 신장 트리를 리턴한다. (최단경로)

```
// nodes : 각 노드를 담은 리스트 [ex : "a", "b", "c", "d"]
// infoList : 각 노드 정보들을 담은 리스트 [ex : u = "a", v = "b", weight = "3"]
public ArrayList<Node> Kruskal (ArrayList<String> nodes, ArrayList<Node> infoList){
	ArrayList<Node> mst = new ArrayList<>();//최단 경로를 담을 최소신장리스트
    
    //초기화
    for(int i = 0 ; i< nodes.size() ; i++){ this.makeSet(nodes.get(i);}
    //간선을 기반으로 sort
    Collections.sort(infoList);
    
    //최단경로 
    for(int i = 0 ; i < infoList.size() ; i++){
    	if(find(infoList.get(i).u) != find(infoList.get(i).v){ //from-to의 루트가 다를때 : 추가가능
        	union(infoList.get(i).u , infoList.get(i).v); //트리 합체
        	mst.add(infoList.get(i)); //최소 신장 트리에 현재 경로 추가
        }
    }
    
   return mst ; //최소 신장 트리(최단경로)를 반환
   }
}
```

