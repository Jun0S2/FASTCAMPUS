# 완전탐색

모든 경우를 전부 탐색

4가지 문제 유형으로 나누어 볼 수 있음.

> **\[문제유형\]**  
> 1\. N개의 원소 중 중복 없이  M 개를 순서있게 나열  
> 2\. N개의 원소 중 중복 없이  M 개를 고르기  
> 3\. N개의 원소 중 중복 허용하여 M 개를 순서있게 나열  
> 4\. N개의 원소 중 중복 허용하여 M 개를 고르기

완전탐색으로 문제를 풀 때, 백트래킹이 자주 사용되는데 dfs는 백트래킹 문제 방법 중 하나임

따라서, 완전탐색으로 문제를 풀이하는 경우에,

```
public static void recurrsive(int depth){
	//재귀
}

public static void main(String[] args){
	recurrsive(0);
}
```

위의 형태가 자주 쓰인다.

나는 완탐은 보통 순열, 조합, 부분집합을 많이 사용하는데 dfs를 활용하면 런타임 에러가 좀 덜 날것같아서 앞으로 되도록 dfs를 사용하고자 한다.

앞서 제시한 4가지 문제 유형을 아래 문제들로 연습해 볼 수 있다.

| N과 M (3) | [https://www.acmicpc.net/problem/15651](https://www.acmicpc.net/problem/15651) |
| --- | --- |
| N과 M (1) | [https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649) |
| N과 M (4) | [https://www.acmicpc.net/problem/15652](https://www.acmicpc.net/problem/15652) |
| N과 M (2) | [https://www.acmicpc.net/problem/15650](https://www.acmicpc.net/problem/15650) |
