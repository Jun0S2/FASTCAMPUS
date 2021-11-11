# **정렬**

정렬은 조건이 필요하다.

배열의 정렬은 Arrays.sort(arrName)로 구현할 수 있다.

하지만, 만약 정렬의 조건이 integer나 String 이 아닌 경우는 새로운 조건이 필요한데 당연히 자바는 우리가 이 조건을 직접 지정해주지 않으면 정렬해주지 않는다.

따라서, comparator를 implement 해서 compareTo 메서드를 재정의하여, 우리가 새로운 조건을 지정해주면 새 기준에 의해 정렬해준다.

```java
@override
public int compareTo(Object o ) {
	return num-o.num ; //small to big - ASC
    //return o.num - num; //big to small - DESC
}
```

위 코드를 살펴보면,

return 값이 num-o.num일 때는 ascending 반대는 descending 순서로 정렬해준다.

Return 값에 따라 정렬 순서가 정해지게 되는데 ,

| Return |  |
| --- | --- |
| 음수 (-) | 본인이 먼저 |
| 0 | 같다 |
| 양수 (+) | 비교 대상이 먼저 |

위 표를 살펴보면 return statement가 음수일 때는 본인이 더 작기 때문에 더 앞에 위치하게 되고 양수일 때에는 본인이 더 크기 때문에 뒤에 위치하게 된다.

정렬 문제를 연습하기 위해서,

[https://www.acmicpc.net/problem/10825](https://www.acmicpc.net/problem/10825)

 [10825번: 국영수

첫째 줄에 도현이네 반의 학생의 수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 한 줄에 하나씩 각 학생의 이름, 국어, 영어, 수학 점수가 공백으로 구분해 주어진다. 점수는 1보다 크거나 같고, 1

www.acmicpc.net](https://www.acmicpc.net/problem/10825)

위의 백준 문제를 풀어봤다.

이름 정렬에서 좀 헤매었었는데 수정 후에, 시간 차이나 용량 차이는 크게 없었다. 다만 system.out.println대신 bufferedWriter를 사용했을 때, 시간이 반으로 줄어든 것을 볼 수 있었다. 

#### Record Class

백준의 문제 description을 보면 아래와 같다.

> 도현이네 반 학생 N명의 이름과 국어, 영어, 수학 점수가 주어진다. 이때, 다음과 같은 조건으로 학생의 성적을 정렬하는 프로그램을 작성하시오.  
>   
>   1. 국어 점수가 감소하는 순서로  
>   2. 국어 점수가 같으면 영어 점수가 증가하는 순서로  
>   3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로  
>   4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키코드에서 대문자는 소문자보다 작으므로 사전 순으로 앞에 온다.)

따라서, 입력을 받을 때 리스트로 받고 Record라는 클래스를 만들어서 이름, 국어, 영어, 수학 점수를 저장하기로 했다.

```java
public static class Record implements Comparable<Record>{
	String name;
	int Korean;
	int English;
	int Math;
	Record(String name , int Korean, int English, int Math){
		this.name = name;
		this.Korean = Korean;
		this.English = English;
		this.Math = Math;
	}
    //override compareTo
}
```

#### 정렬 기준

문제에서 제시한

>   1. 국어 점수가 감소하는 순서로  
>   2. 국어 점수가 같으면 영어 점수가 증가하는 순서로  
>   3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로  
>   4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전 순으로 앞에 온다.)

은 다시 풀어서 설명하면

>   1. 국어를 큰 수부터 (DESC)  
>   2. 국어 점수가 같다면 영어가 작은 수부터 (ASC)  
>   3. 영어 점수가 같다면 수학이 큰 수 부터 (DESC)  
>   4. 모든 점수가 같다면 이름이 작은 수부터 (ASC)

위 조건으로 정렬 알고리즘을 표현하면,

1\. 국어 점수 : Descending order

```java
if(Korean-o.Korean!=0) { return o.Korean- Korean;}
```

2\. 영어 점수 : Ascending order

```java
if(English-o.English!=0) { return English-o.English;}
```

3\. 수학 점수 : Descending order

```java
if(Math-o.Math!=0) {return o.Math - Math;}
```

4\. 이름 : Ascending order

처음에는, for loop를 사용하여, 글자의 앞 캐릭터의 ascii 코드 번호가 비교대상보다 작으면 -1, 크면 1을 리턴했다.

```java
for(int i = 0 ; i <o.name.length() ; i ++) {
	if(o.name.charAt(i)!= name.charAt(i)) {
		if(o.name.charAt(i)-'0'> name.charAt(i)-'0') {//쟤가 나보다 클때
			return -1;
			}
		else {return 1;}
	}
}
```

하지만, 강의를 듣고 나니 for loop를 사용할 필요 없이

```java
return name.compareTo(o.name);
```

그냥 name을 compareTo하면 된다는 것을 깨달았다. 원래 Collections.sort()를 통해 alphabetically ordered list로 정렬할 수 있듯이 String도 그냥 비교해도 된다는 것을 잊고 있었다.

for loop를 사용한 코드와 return name.compareTo(o.name)의 메모리나 실행 속도는 크게 차이가 없었다. 앞서 게시

![image](https://user-images.githubusercontent.com/36508552/141240971-579b74a8-92be-4240-b642-8d272c283977.png)

물론 코드 길이에 큰 차이가 있기 때문에 String 형태도 바로 비교할 수 있다는 것을 잊지 말아야겠다...

#### 전체 소스 코드

```java
package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_10825_국영수 {
/**
 * @author June Park
 * @date 11/11/2021
 * 국영수 점수 정렬 문제
 * 1. 국어 점수가 감소하는 순으로
 * 2. 국어 점수가 같으면 영어 점수가 증가하는 순
 * 3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순
 * 4. 모든 점수가 같으면 이름이 사전순으로 증가하는 순으로 (대문자가 소문자보다 작음 -'0'하면 될듯)
 * */
	static int N;
	static ArrayList<Record>list;
	public static class Record implements Comparable<Record>{
		String name;
		int Korean;
		int English;
		int Math;
		Record(String name , int Korean, int English, int Math){
			this.name = name;
			this.Korean = Korean;
			this.English = English;
			this.Math = Math;
		}
		/**국어 점수가 감소하는 순으로 정렬 : 국어가 높은거부터
		 * if same : 영어가 작은순		
		 * if same : 수학이 큰 순
		 * if same : 이름이 작은순
		 */
		@Override
		public int compareTo(Record o) {
			//국어가 큰 순
			if(Korean-o.Korean!=0) { return o.Korean- Korean;}
			//Else : 영어가 작은순
			if(English-o.English!=0) { return English-o.English;}
			//Else : 수학이 큰 순
			if(Math-o.Math!=0) {return o.Math - Math;}
			//Else : 이름이 작은 순
			
			/*for(int i = 0 ; i <o.name.length() ; i ++) {
				if(o.name.charAt(i)!= name.charAt(i)) {
					if(o.name.charAt(i)-'0'> name.charAt(i)-'0') {
						//쟤가 나보다 클때
						return -1;
					}
					else {return 1;}
				}
			} */
			return name.compareTo(o.name);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //학생의 수
		list = new ArrayList<>();
		String name = "";
		int kor= 0, eng = 0, math = 0; 
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			name = st.nextToken();
			kor = Integer.parseInt(st.nextToken());
			eng = Integer.parseInt(st.nextToken());
			math = Integer.parseInt(st.nextToken());
			
			list.add(new Record(name, kor, eng, math));
		}
		br.close();
		//sort
		Collections.sort(list);
		
		//print
		for(Record r : list) {
			//System.out.println(r.name);
			bw.write(r.name);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

}
```
