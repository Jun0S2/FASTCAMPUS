# **백트래킹**

제약 조건 만족 문제에서 해를 찾기 위한 전략  
해가 될 수 있는 다양한 후보근을 트리 형태로 표현 (State Space Tree)  
진짜 트리는 아니고, dfs 로 풀 수 있다.

### **How it works...**

1\. 루트 노드를 하나 잡아서, 해가 될 수 있는지 판단

2\. 해가 될 수 있다면 해당 해의 child 가 해가 될 수 있는지 판단

3\. 해가 될 수 없다면, 다시 그 전 조건으로 돌아간다.

이때, 해가 될 수 없는 노드의 자식들은 확인해보지 않아도 해가 될 수 없기 때문에 해당 가지들은 확인할 필요가 없어지는데 이를 가지치기라고 한다.

4\. 위 과정을 최종 해를 찾을 때 까지 반복
![image](https://user-images.githubusercontent.com/36508552/140935614-19b7699e-21fe-4b24-b9a3-12c183725f20.png)
### **대표 문제 : N Queen**

![image](https://user-images.githubusercontent.com/36508552/140935631-1ed80266-a1ed-47c6-8eea-4d63da57a33f.png)
N-Queen 문제는 백 트래킹 전략의 대표적인 예시 문제이다.

NxN 체스판에 N 개의 퀸을 놓았을 때 서로 공격하지 못하도록 배치하는 방법을 찾는 문제이다.

제약 조건 : 퀸은 대각선, 수평, 수직으로 이동할 수 있다.

#### **Approach :** 

백 트래킹으로 해당 제약 조건들을 가지치기 하면서 접근한다.

> 수평으로 이동 할 수 있음 : 한 row에 한개의 퀸만 존재할 수 있다고 해석할 수 있다.

맨위에 퀸을 배치하고 해당 퀸이 이동할 수 없는 위치에 다음 퀸을 배치하면 된다. 만약 퀸을 배치할 수 없다면, 그 전단계로 돌아가서 재배치한다

![image](https://user-images.githubusercontent.com/36508552/140935644-ccb4578c-c2f1-4273-9ec7-06e18ab6954d.png)
#### **조건 체크**

**1\. 수직 체크**

퀸의 좌표가 (1,2) 라고 가정하면 이 퀸은 모든 (x,2)로 이동할 수 있다.

즉, 해당 column (y좌표) 에는 더이상 퀸을 배치할 수 없다! 한 column에는 하나의 퀸만 존재할 수 있다.

**2\. 수평 체크**

퀸은 수평으로 이동할 수 있다. 즉, 한 row에는 하나의 퀸만 존재할 수 있다.

**3\. 대각선 체크**

[##_Image|kage@m5sDe/btrkaP09V7A/cEBP8aYOkKHKWfvuzhs0u0/img.png|floatLeft|data-origin-width="511" data-origin-height="481" width="285" data-ke-mobilestyle="widthOrigin"|||_##]

옆에 ~조잡하게~ 그림을 그려봤는데, 퀸의 대각선 위치에 있다는 것은 옆과 같이 이등변 삼각형이 그려질 수 있는 위치에 퀸이 배치된다.

**즉, |배치될 퀸의 x 좌표 - 현재 퀸의 x 좌표| == |배치될 퀸의 y 좌표 - 현재 퀸의 y 좌표| 일때 이 퀸들은 대각선상에 있다고 볼 수 있다.**

따라서, 이를 수도 코드로 구현해보면 아래와 같은 조건식을 도출할 수 있다.

```
if ( abs(queen.col - curr_queen.col) == abs(queen.row - curr_queen.col) ) continue; //대각선상
else {
	//other code
}
```

위에 제시된 조건들로 가지치기를 하고 dfs 방식으로 풀어본다면 n 퀸 문제를 해결할 수 있다.

#### 백준 9663 N-Queen

백준 문제를 풀고 왔다..

2차원 배열로 풀 수 있을 줄 알았는데 생각처럼 잘 안됐다. 구글에 검색해보니 아니 이럴수가..다들 1차원 배열로 해결하다니 이래서 어릴때 싱크빅을 했어야..

#### 1 차원 배열로 표현

```
//1차원 배열
int[] board = new int[N];
```

1차원 배열로 어떻게 표현하냐 하면, 각 인덱스가 row 로 치는거다.

앞서 말했듯이, N queen의 제약 조건에서 한 row에 하나의 퀸만 존재할 수 있다. 반대로 말하자면 모든 row에는 하나의 퀸이 존재하기 때문에 1차원 배열로 표현하면 index가 퀸의 row고, 내용물이 column의 위치라고 말할 수 있겠다.

| index (row) | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| column | y0 | y1 | y2 | y3 | y4 | y5 | y6 |

위 테이블을 보면 쉽게 이해할 수 있다.

예를들어, board\[2\] = 3이라면, x = 2, y = 3 인 (2,3) 에 퀸이 존재하고 있다는 소리다!

#### DFS

```
private static void dfs(int row) {
	if(row == N) {//끝까지 도착
		answer++;
		return;
	}
	for(int i = 0 ; i<N ;i ++) {
		board[row] = i; //row 가 1이라면, 퀸을 i 번째 column에 위치시켜보고 둘 수 있는지 없는지 판단
		if(check(row)) {//해당 row에 놓은 col 정보가 괜찮은가..
			dfs(row+1); //다음 row에 놓음
		}
		//else : 다시 col 선정후 반복
	}
}
```

위 코드는 dfs코드이다.

먼저, for loop를 사용하여, column 위치를 정한다

```
for(int i = 0 ; i<N ;i ++){
	board[row]=i;
}
```

위 코드가 column을 설정하게 해준다.

```
if(check(row)) {//해당 row에 놓은 col 정보가 괜찮은가..
	dfs(row+1); //다음 row에 놓음
}
```

위 코드는, 해당 row에 방금 대입한 column값이 괜찮은지 아닌지 확인하는 메서드로 호출한 후 만일 괜찮다면 다음 row로 넘어가는 코드이다.

즉, (0,0) 이 validation check에서 패스했다면, 다음 (1,y) 좌표를 구할 것이다.

참고로, check method는 앞서 말한 대각선과 column을 확인하는 작업을 한다.

```
private static boolean check(int row) {
	for(int i = 0 ; i< row ;i ++) {
		if(board[i] == board[row])return false;
		//대각선 :row, col
		if(Math.abs(i-row)== Math.abs(board[row]-board[i])) return false;
	}
	return true;
}
```

if(board\[i\] == board\[row\]) 는 해당 열 전까지만 탐색하는데 (해당 열까지만 column을 정했기 때문에), 같은 행에 이미 퀸이 존재하면 퀸을 배치할 수 없다.

대각선 코드는 위에서 설명한 것 과 같이 이등변 삼각형 조건을 사용하여 true인지 false인지 리턴해주었다.

위 check 코드에서 true가 나오면, 재귀를 통해 다음 row로 가게 된다. 

이렇게 row가 N 까지 도착하게 되면 answer 가 증가하고 다른 좌표로 재탐색 시작한다.

#### 전체 소스 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * @author June Park
 * @date 11/08/2021
 * N-Queen Problem
 * on NxN chessboard, find #sols of n queen
 * */
public class BOJ_9663_N_Queen {

	static int [] board; //각 row 마다 퀸을 놓은 인덱스 저장
	static int N , answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		N = Integer.parseInt(br.readLine());
		board = new int [N]; //board[row] = col -> row 마다 하나의 퀸을 놓을 수 있음. board[1] = 1번째 row의 퀸을 몇col에 두었는지 저장한다 
		answer = 0;
		dfs(0);
		System.out.println(answer);
	}
	/**
	 * */
	private static void dfs(int row) {
		if(row == N) {//끝까지 도착
			answer++;
			return;
		}
		for(int i = 0 ; i<N ;i ++) {
			board[row] = i; //row 가 1이라면, 퀸을 i 번째 column에 위치시켜보고 둘 수 있는지 없는지 판단
			if(check(row)) {//해당 row에 놓은 col 정보가 괜찮은가..
				dfs(row+1); //다음 row에 놓음
			}
			//else : 다시 col 선정후 반복
		}
	}
	/**
	 * 현재 위치에 queen 이 갈 수 있는지 없는지 확인
	 * 1. 퀸이 같은 col에 있는 경우 : board[row] = col 에서 좌표는 row, col
	 * board[row] 와 board[i] 를 비교하면 동일한 칼럼에 위치하는지 알 수 있음
	 * 
	 * 2. 대각선에 존재 ? 
	 * abs(queen.col - curr.col) == abs(queen.row - curr.row) 면 대각선상에 있음
	 * */
	private static boolean check(int row) {
		// 같은 column인지 판단 : board[i] : dfs 메서드에서 board[row] = col 로 초기화 시켜줬음.
		// board[n] 은 column 정보를 담고 있음. board[i]==board[row] 같은 column
		for(int i = 0 ; i< row ;i ++) {
			if(board[i] == board[row])return false;
			//대각선 :row, col
			if(Math.abs(i-row)== Math.abs(board[row]-board[i])) return false;
		}
		return true;
	}

}
