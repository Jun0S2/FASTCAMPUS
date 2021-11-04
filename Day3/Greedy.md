# Greedy

### Greedy Algorithms

탐욕 알고리즘은 쉬운 문제는 정렬을 이용, 보통 DP를 사용하여 문제를 푸는 것 같다. 본 강의에 나온 예제는 누가봐도 정렬로 푸는 문제였지만 보통 탐욕 알고리즘은 코테에서 만났을 때 탐욕 기법으로 풀 것이라 상상도 못하는 것들이 많다고 하니 전형적인 유형들만 몇개 공부하면 될 것 같다.

#### 문제 유형 : 

1\. 거스름돈 줄이기  
손님이 지불한 금액에서 물건값을 제한 차액을 지불하는 문재.   
동전을 최소한으로 주고 싶을 때  
\-> 주로, 500원 1000원이 아니라 400원 300원 이런식으로 나오기 때문에 정렬로 풀 수 없고 DP를 이용하여 풀어야한다

2\. 설탕배달   
[https://www.acmicpc.net/problem/2839](https://www.acmicpc.net/problem/2839)

 [2839번: 설탕 배달

상근이는 요즘 설탕공장에서 설탕을 배달하고 있다. 상근이는 지금 사탕가게에 설탕을 정확하게 N킬로그램을 배달해야 한다. 설탕공장에서 만드는 설탕은 봉지에 담겨져 있다. 봉지는 3킬로그

www.acmicpc.net](https://www.acmicpc.net/problem/2839)

위 문제도 대표적인 DP 문제이다. DP로 접근하지 않고 풀었던 경험이 있는데 후에 비교하니 DP로 푼 것 과 거의 비슷했다. DP로 풀었을 때 엄청 짧고 간편했기 때문에 DP 문제 유형들을 익숙하게 해두면 좋을 것 같다.

3\. Knapsack

( 1 ) Fractional Knapsack (부분 냅섹)은 정렬을 이용하여 풀 수 있는 탐욕 알고리즘 문제이다. 물건의 가성비가 가장 좋은 애들로 채워넣고 배낭에 다음 물건을 집어넣지 못하면 그 물건을 쪼개서 넣는다. 즉, 가성비순으로 정렬한 후 푸는 문제다

( 2 ) 0/1 Knapsack 은 정말 대표적인 DP 문제다. 

[https://www.acmicpc.net/problem/12865](https://www.acmicpc.net/problem/12865)

 [12865번: 평범한 배낭

첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)

www.acmicpc.net](https://www.acmicpc.net/problem/12865)

위 문제를 완전 탐색으로 풀었을 때에는 계속 틀렸습니다가 났다. K 값과 N 값을 고려해 봤을 때 당연한 결과다.... 하지만 DP를 사용하면 그 전 결과를 메모이제이션해놓기 때문에 오류가 날 일이 없다.

아래는 위 문제의 코드이다.

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
//dp로 접근. 순조부로 했더니 계속 실패
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//물건의 수
		int K = Integer.parseInt(st.nextToken());//배낭의 한도
		
		int[] D = new int[K+1];					//가방 DP 배열(최대 가치)
		int[] weights = new int[N+1];			//무게 배열
		int[] values = new int[N+1];			//물건의 가치
		
		for(int i= 1; i<=N ;i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken()); //각 물건의 무게
			values[i] = Integer.parseInt(st.nextToken());//각 물건의 가치
		}//end of input
		
		//Dp: 물건 가치의 최댓값
		for(int i =1; i<=N ; i++) {//모든 물건에 대하여
			for(int w = K ; w>=weights[i]; w--) {//거꾸로 순회 -> 현재 무게 남은만큼 보는거
				D[w] = Math.max(D[w], values[i]+D[w-weights[i]]);//담지 않는 상황 vs 담는 상황
				//담는 상황일 때: 현재 물건 가치 + 물건 담았을때 남는 가방 무겐
			}
			
		}
		//최대 가치 출력
		System.out.println(D[K]);
	}

}
```

코드도 매우 짧기 때문에 사실상 전부 input을 담는 코드고 solution 코드는 3줄밖에 없다.

따라서, 잘 익혀두면 비슷한 유형이 나올 때 정말 손쉽게 풀 수 있을 것 같다.

다음에, 0/1 Knapsack을 좀 더 자세하게 정리하고자 한다.

4\. 병뚜껑 개임

이진 검색을 하기 위해 정렬하여 써야한다.

### 정렬 알고리즘

앞서 말한, 그리디 문제를 정렬로 풀기 위해서는 Comparator나 Commparable을 사용한다.

```java
import java.util.Arrays;

class Burger{
	String name;
	int price;
	Burger(String name, int price){}
	
}
public class Test01 {
	public static void main(String[] args) {
		Burger[] burgers = new Burger[3];
		burgers[0] = new Burger("1957",6000);
		burgers[1] = new Burger("더블불고기",7000);
		burgers[2] = new Burger("한우버거", 8000);
		
		Arrays.sort(burgers); //Exception occurs
}
}
```

위 코드를 실행시키면 ClassCaseException이 발생한다.

이유는 Arrays.sort의 기준점인 comparable에 버거 객체 (String name , int price ) 를 정렬하는 기준점이 없기 때문이다. 따라서, Comparator나 Comparable을 사용하여 기준점을 심어주면 된다.

```java
Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];}});
		print(arr);
```

익명 클래스에서 Comparator를 활용한 방식이다.

```java
//람다식으로 처리하기
		Arrays.sort(arr, (o1, o2)-> o1[1] - o2[1]);
		print(arr);
```

람다식을 이용하면 훨씬 더 짧고 간단해진다. 


