# 완전탐색

| 연산자 끼워넣기 | [https://www.acmicpc.net/problem/14888](https://www.acmicpc.net/problem/14888) |
| --- | --- |
| 부분수열의 합 | [https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/14888) |
| 암호 만들기 | [https://www.acmicpc.net/problem/1759](https://www.acmicpc.net/problem/14888) |

위의 연습문제를 모두 dfs를 사용하여 풀었다.

### 연산자 끼워넣기


#### DFS

```java
private static void dfs(int depth , int calculated){ ... }
//main 함수:
dfs(1,nums[0]);
```

dfs 함수에 depth 와 현재 계산중인 int variable을 파라미터에 넘겨 재귀로 호출하였다.

depth==N에서 멈추기 때문에 처음 depth를 0 이아니라 1로 주었고, 입력받은 배열의 맨 처음 숫자를 넘겨주었다. (만약, 4 라면 4 "+" "3" 이 되어야함 -> 맨 처음 숫자는 필요!)

Return 조건

```java
if(depth == N) {
	min = Math.min(calculated, min );
	max = Math.max(calculated, max);
	return;
}
```

#### for loop

처음에는 연산자가 아니라, N 만큼 돌려서 엄청난 디버깅을 했다.

```java
for(int i = 0 ; i <4 ; i++) {//연산자에 대해 +,-,*,/ 확인
	if(operator[i]>0) { //연산자가 남아 있을 경우에만
		operator[i]--;//사용된 연산자 pop off
		//dfs 호출
		operator[i]++;
	}
}
```

하지만, operator가 사이즈가 4로 잡혀있고,

operator \[0\] = +의 개수 , \[1\] = -의 개수 , \[2\] = \*의 개수, \[3\] = /의 개수

로 지정해놨다.

따라서, 루프를 4만큼 돌려야 하고, 해당 연산이 남아있는 경우에만 dfs를 호출해주었다. 이때, operator\[i\]--를 처리해주어야 dfs로 함수를 호출했을 때 방금 사용한 연산자를 중복 사용하는 경우가 없어지고 재귀에서 돌아오면 다시 operator\[i\] ++ 를 해줘서 초기화를 시켜야한다.

#### dfs 조건

```java
if(i==0) dfs(depth+1 , nums[depth]+calculated);
else if(i==1) dfs(depth+1 , calculated - nums[depth]);
else if(i==2) dfs(depth+1 , calculated * nums[depth]);
else if(i==3) dfs(depth+1 , calculated / nums[depth]);
```

i = 0일때는 + 연산, 1일때는 - 연산 , 2 일때는 \* 연산, 3 일때는 / 연산처리를 한다. (operator\[i\] 의 i 임\*)

dfs를 재귀호출할 때, depth는 1 더하고, calculated에 방금 연산한 결과를 파라미터에 같이 보내서 다음번 계산때에도 쓸 수 있게 했다.

#### 전체 소스 코드

```java
package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author June Park
 * @date 11/10/2021
 * dfs 를 이용하여 연산자 +-* /에 대해 남은 연산자가 있는 경우 해당연산처리 후 dfs로 보낸다
 * */
public class BOJ_14888_연산자끼워넣기 {
static int N , nums[],operator[];
static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N];
		
		operator = new int[4];//+ - * /
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++) {
			operator[i]=Integer.parseInt(st.nextToken());
		}//end of input
		
		dfs(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void dfs(int depth , int calculated) {
		if(depth == N) {
			//System.out.println(calculated);
			min = Math.min(calculated, min );
			max = Math.max(calculated, max);
			return;
		}
		for(int i = 0 ; i <4 ; i++) {//연산자에 대해 +,-,*,/ 확인
			if(operator[i]>0) {		 //연산자가 남아 있을 경우에만
			operator[i]--;			 //사용된 연산자 pop off
			if(i==0) dfs(depth+1 , nums[depth]+calculated);
			else if(i==1) dfs(depth+1 , calculated - nums[depth]);
			else if(i==2) dfs(depth+1 , calculated * nums[depth]);
			else if(i==3) dfs(depth+1 , calculated / nums[depth]);
			operator[i]++;
			}
		}
		
		
	}

	
	

}
```

### 부분 수열의 합

#### DFS 생성

```java
private static void dfs(int depth, int sum){...}
//main 함수:
dfs(0,0); //depth 와 sum
```

depth 와 현재 dfs연산의 합을 저장할 변수를 파라미터에 주었다.

#### Return 조건

```java
if(depth == N) { //끝까지 찾았다면,
	if(tempSum==S) {count++;}
	tempSum = 0;//초기화
	return;
}
```

만약, depth 가 N 와 같다면 -> 모든 원소를 둘러보았단 소리다.

따라서, 여태까지의 합이 입력으로 받은 target Sum (S)와 같은지 확인하고 같다면, count 를 더해준다.

#### DFS 조건

```java
//부분수열. 지금 위치의 원소를 선택하거나 하지 않는다
dfs(depth+1 , nums[depth]+tempSum);//선택함	
dfs(depth+1 , tempSum);	//선택안함
```

부분집합은 선택하거나 선택하지 않는 경우로 트리를 이룬다.

![image](https://user-images.githubusercontent.com/36508552/141126927-1b2c07ea-c087-43bb-824d-c3d0865d9edf.png)


위 그림을 보면, 좀 더 이해하기 쉬울 것 같아서 가져왔다.

현재 문제에서, 선택할 경우에는 해당 숫자를 더하고 선택하지 않으면 기존 숫자를 파라미터에 넘긴다.

#### Count 처리

```java
count = S==0? -1 : 0;
```

위 dfs 함수를 실행하기전, 위 코드를 처리해주어야 한다. 내 디버깅 시간을 잡아먹은 코드이다...

만약, 주어진 S가 0 인 경우는 공집합인 경우의 합도 0 이기 때문에 count가 하나 더 늘게 된다. ~허위매물..~

따라서, 만약 S가 0이라면 -1부터 시작, 아니라면 0부터 시작해 주면 정상적으로 공집합을 제외하고 카운트하게 된다.

#### 전체 소스 코드

```java
package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author June Park
 * @date 11/10/2021
 * 부분 수열의 합 :dfs 를 사용하여 먼저 부분 집합을 구한 후, 
 * return statement 에서 합이 같은지 확인하는 처리를 해주었다.
*/
public class BOJ_1182_부분수열의합 {
	static int N, S , nums[], count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		nums= new int[N];

		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {nums[i]=Integer.parseInt(st.nextToken());}
		
		count = S==0? -1 : 0; //합이 sum 이면 count ++해주고, 만약 S =-0 이면 공집합의 합도 0이기 때문에 1빼준다
		dfs(0,0); //depth 와 sum
		System.out.println(count);

	}
	
	/**
	 * 부분 집합 : 선택하거나, 선택하지 않거나 
	 * 위 형태의 트리를 이룬다.
	 * 즉, dfs 를 사용할 때, 현재 원소를 선택한(더한) 재귀호출과 선택하지 않은(더하지 않은) 재귀 호출을 한다.
	 * */
	private static void dfs(int depth, int tempSum) {
		if(depth == N) { //끝까지 찾았다면,
			if(tempSum==S) {
				count++;
			}
			tempSum = 0;
			return;
		}
		//부분수열. 지금 위치의 원소를 선택하거나 하지 않는다
		dfs(depth+1 , nums[depth]+tempSum);//선택함
		dfs(depth+1 , tempSum);	//선택안함

	}

}
```

### 암호만들기

놀랍게도 몇달 전에 풀어본적이 있는 문제다. 그때는 dfs 가 뭔지도 모르고 풀었는데 다시 코드를 보니 지금보다 훨씬 잘했던것같다...

이번에는 전형적인 dfs로 풀어보았다.

#### Arrays.sort

```java
Arrays.sort(input); //sort 하면, 현재가 이전보다 작은거 고민할 필요 x
```

이부분 때문에 한참 해맸다.. 과거의 나는 놀랍게도 이 문제를 보자마자 이 생각을 했나보다.

비밀번호는 오름차순만 가능하다고 문제에서 주어젔기 때문에 ex : ABCD 가능, XASD 불가능

Arrays.sort를 사용하여 미리 알파벳 순으로 탐색할 문자 배열을 정렬해두면 별도의 조건 없이도 탐색할 수 있다.

(ex : if(depth>0 && 이전고른문자<입력받은문자배열\[현재인덱스\])) 

#### DFS 생성

```java
private static void dfs(int depth, int index, char[] passwords){...}
```

#### Return 조건

```java
if(depth == L ) {
	int vowels=0 , consonants=0;
	for(char c : passwords) {
		if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {vowels++;}
		else {consonants++;}
	}
	if(vowels>=1 & consonants>=2) {
		for(char c:passwords) {
			bw.write(c);
		}
		bw.write("\n");					
	}
	return;
}
```

깊이 == length of password 면,

1\. 조건에서 제시한 모음 1개이상과 자음 2개이상 을 만족하는지 확인하고 

2\. 만족하면 정답 문자열에 추가한다 (속도개선을 위해 bw를 썼지만, System.out.print를 써도 시간 초과는 나지 않는다)

#### DFS 조건

```java
//중복 선택 허용 x - visited 배열 사용
for(int i = index ; i < C ; i++) { //index활용 책갈피
	if(visited[i])continue;
	visited[i] = true;
	passwords[depth] = input[i];
	dfs(depth+1 , i+1 , passwords);
	visited[i] = false;
}
```

패스워드 생성 시, 중복된 문자는 허용되지 않기 때문에 visited 배열을 사용하였다.

만약, 이미 사용된 문자라면 continue, 그렇지 않다면 일단 생성중인 패스워드에 문자를 담고 재귀를 돌려서 validation 체크를 하도록 한다. 재귀를 탄 후에 return statement를 만나면 다시 dfs(depth+1,i+1,passwords);지점에 돌아오게 되는데, 따라서 방문처리를 해제시켜줘야 다음번 패스워드를 정상적으로 담을 수 있다.

#### 전체 소스 코드

```java
package Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *@ author June Park
 *@ date 11/10/2021
 *1. 오름 차순 : Arrays.sort()를 사용하면, dfs에서 추가 연산 안해도 된다. (greedy)
 *2. return 조건에서 aeiou 개수 판단 
 * */
public class BOJ_1759_암호만들기 {
	static int L,C;
	static char[] input;
	static boolean[]visited;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		L = Integer.parseInt(st.nextToken()); //length of passwords
		C = Integer.parseInt(st.nextToken()); //number of given characters
		visited = new boolean[C];
		//password : 최소 한개의 모음과 두개의 자음으로 구성 . alphabetically ordered 증가순으로 구성
		input = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(input); //sort 하면, 현재가 이전보다 작은거 고민할 필요 x

		char[] passwords = new char[L];
		dfs(0,0 , passwords);//depth, index
		bw.flush();
	}
	//count 로 길이 센다
	private static void dfs(int depth, int index, char[] passwords) throws IOException {
		if(depth == L ) {
			int vowels=0 , consonants=0;
			for(char c : passwords) {
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {vowels++;}
				else {consonants++;}
			}
			if(vowels>=1 & consonants>=2) {
				for(char c:passwords) {
					bw.write(c);
				}
				bw.write("\n");					
			}
			return;

		}
		//중복 선택 허용 x - visited 배열 사용
		for(int i = index ; i < C ; i++) { //index활용 책갈피
			if(visited[i])continue;
			visited[i] = true;
			passwords[depth] = input[i];
			dfs(depth+1 , i+1 , passwords);
			visited[i] = false;
		}
	}
}
```
