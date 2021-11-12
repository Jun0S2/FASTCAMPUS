# 정렬 알고리즘 응용
| 수열 정렬 | [https://www.acmicpc.net/problem/1015](https://www.acmicpc.net/problem/1015) |
| --- | --- |
| 카드 | [https://www.acmicpc.net/problem/11652](https://www.acmicpc.net/problem/1015) |
| 화살표 그리기 | [https://www.acmicpc.net/problem/15970](https://www.acmicpc.net/problem/1) |

위 문제를 풀이해봤다. 화살표 그리기는 시간이 없어서 아직 풀지 못했다. 후에 시간이 나면 추가적으로 업로드하겠다.

## 문제 풀이

### 수열 정렬

#### 문제 해석

주어진 배열과 해당 원소의 인덱스를 따로 저장한다.

\[Original Array\]

| index | 0 | 1 | 2 | 3 | 4 | 5 |
| --- | --- | --- | --- | --- | --- | --- |
| element | 5 | 4 | 1 | 3 | 5 | 7 |

위 배열을 오름차순으로 정렬하게 되면 아래와 같다,

\[Sorted Array\]

| index | 0 | 1 | 2 | 3 | 4 | 5 |
| --- | --- | --- | --- | --- | --- | --- |
| element | 1 | 3 | 4 | 5 | 5 | 7 |

이제, 원본 배열의 element가 정렬된 배열에서 몇 번째인지 출력하면 된다.

![image](https://user-images.githubusercontent.com/36508552/141465704-bae4e83e-f184-4c6a-bed3-8353194613e3.png)


위에 매칭 하는 방법을 그려보았다. 위 문제의 경우는 3 2 0 1 4 5를 출력하게 된다.

#### 문제 풀이

나는 배열을 두 개 이용하여 위 문제를 풀었다. 원본 배열과 정렬할 배열을 따로 만들었는데, 위에서 내가 해석한 과정을 그대로 코드로 짠 풀이다.

> 알고리즘  
> 1\. 원본 배열과 정렬할 배열에 입력값을 받음  
> 2\. 정렬할 배열을 정렬함  
> 3\. For loop를 사용하여, 원본 배열의 원소를 정렬된 배열에서 찾은 후에 정렬된 index를 프린트하고 다음 원소를 정렬된 배열에서 찾아서 index를 프린트한다.  
> 4\. 3번을 N번만큼 반복  
>   
> 주의사항  
> \- 만약, 정렬된 배열에서 현재 원소와 같은 값을 찾았다면, 그 값을 음수로 변경해 주었다 - 만약, 배열에 같은 값이 있을 경우 계속 앞에 정렬된 원소를 찾는 것을 방지하기 위함  
> \- 원소를 찾은 후에는 break 해줘야 다음 원소를 찾는다.

#### 구현

1\. 입력

```java
for(int i = 0 ; i < N ; i++) {
	int num = Integer.parseInt(st.nextToken());
	list[i] =compare[i] = num;
}
```

-   list : 원본 배열
-   compare : 정렬할 배열

2\. 정렬

```java
Arrays.sort(list);
```

3\. 원본 배열의 정렬된 인덱스 값 찾기

```java
for(int i = 0 ; i < N ; i++) { //원본 배열을 돌린다
	for(int j =0 ; j<N ; j ++) { //정렬된 배열을 돌린다
		if(compare[i]== list[j]) { //원본배열의 원소를 정렬된 배열에서 찾으면
		bw.write(j+" ");//정렬된 인덱스를 프린트
		list[j] = -20;//음수값을 넣어주어서 같은 원소값이 있을 때 중복된 인덱스를 주는 것을 방지
		break;//찾은 후 다음 원소로 
	}
}
```

#### 전체 소스 코드

```java
package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author June Park
 * @date 11/12/2021
 * 수열 정렬
 * input
 * line 1 - 배열의 크기 N
 * line 2 - 배열의 원소
 * Problem 
 * 비내림차순이되는 수열을 찾는다 (오름차순, ascending) - print index not elements
 * */
public class BOJ_1015_수열정렬 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] compare = new int[N];
		int[] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		for(int i = 0 ; i < N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			list[i] =compare[i] = num;
		}
		//sort list array and keep the input array as unsorted
		Arrays.sort(list);

		for(int i = 0 ; i < N ; i++) {
			for(int j =0 ; j<N ; j ++) {
				if(compare[i]== list[j]) { //if you find original element in sorted array 
					bw.write(j+" ");  //print out the index from sorted array
					list[j] = -20;  //put negative number to sorted array to avoid searching for same index when there is same number	
					break;  //escape from the for loop and search for next element
				}
			}
		}//end of for
		bw.flush();
		bw.close();
	
	}
}
```

### 카드

런타임 에러 때문에 정말 많은 풀이를 시도해보았다.

리스트도 써보고, 배열도 써보고 트리 맵도 써보았는데 해쉬 맵을 사용한 코드가 가장 짧고 추가적으로 구현할 부분이 없었다. 

#### 문제 해석

Mode를 구하는 문제다.

#### HashMap

HashMap 구조를 사용하면, 숫자를 key로 저장하고 value에 숫자가 사용된 횟수를 저장한다.

입력을 받을 때, 만약 이미 이전에 숫자가 나왔다면 value에 이전 값에 1을 더해주면 count 할 수 있다.

```java
Map<Long,Integer> numbers = new HashMap<>(); //리스트 형태로 가져오기 위해 Map 사용
for(int n = 0 ; n< N ; n++) {
	long num = Long.parseLong(br.readLine());
	if(numbers.containsKey(num)) {
		numbers.put(num, numbers.get(num)+1);//override
	}
	else {numbers.put(num, 1);}
}
```

만약 해쉬 맵에 현재 받은 숫자가 없다면 value에 1을 준다(count 값이므로)

**\[주의\] 숫자는 int형이 아닌 long 값으로 받아야 한다! 런타임 에러(Number format)의 주요 범인이다.. N (1 ≤ N ≤ 100,000)으로 주어 저 있기 때문에 long으로 받아야 한다.**

#### HashMap 정렬

1\. 선언

HashMap을 정렬하기 위해 온갖 구글을 뒤 저 보았다. 정리하자면, HashMap을 정렬하려면 먼저

```java
HashMap<자료구조,자료구조> map = new HashMap<자료구조, 자료구조>();
```

이 아닌

```java
Map<자료구조,자료구조> map = new HashMap<자료구조, 자료구조>();
```

을 사용해야 한다

이유는, 해쉬 맵을 정렬하기 위해서는 리스트 형태로 가져와야 하기 때문이다!

2\. 리스트로 전환

해쉬맵을 리스트로 저장하기 위해서 EntrySet을 사용한다. 

방법은 아래와 같다. 해쉬 맵의 key와 value를 리스트로 저장하는 것이다.

```
List<Map.Entry<Long,Integer>>entry = new LinkedList<>(numbers.entrySet());
```

3\. 정렬

```java
entry.sort(new Comparator< Map.Entry<Long, Integer>>(){
	@Override
	public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
		if(o1.getValue().intValue()==o2.getValue().intValue()) return Long.compare(o1.getKey().longValue(), o2.getKey().longValue());
		return o2.getValue()-o1.getValue();
	}
});
```

정렬은 Comparator을 사용했다. count값(value)이 많이 사용된 키값을 구해야 하기 때문이다.

또, 한참을 헤매다가 여러 사람들의 풀이를 봤는데, if 조건을 주지 않으면 틀린다.

테스트 케이스로 시험해봤을 때는 if문이 있으나 없으나 결과가 알맞게 나와서 생각을 안 했는데 값이 음수일 때를 반례로 줘보면, 

```
4
-5
-5
-2
-2
```

위 테스트 경우, -5가 -2보다 작기 때문에 -5가 나와야 하지만 if 문이 없다면 -2가 출력되는 것을 확인해볼 수 있다.

마지막으로,

```java
entry.get(0).getKey()
```

으로 count 가 내림차순으로 정렬된 entry리스트의 0번째 원소의 키값을 가져오면 가장 많이 사용된 숫자를 볼 수 있다. 

#### 전체 소스 코드

```java
package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BOJ_11652_카드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Map<Long,Integer> numbers = new HashMap<>(); //리스트 형태로 가져오기 위해 Map 사용

		for(int n = 0 ; n< N ; n++) {
			long num = Long.parseLong(br.readLine());
			if(numbers.containsKey(num)) {
				numbers.put(num, numbers.get(num)+1);//override
			}
			else {numbers.put(num, 1);}
		}
		br.close();
		//sort
		//리스트 형태로 가져옴
		List<Map.Entry<Long,Integer>>entry = new LinkedList<>(numbers.entrySet());
		
		//sort
		entry.sort(new Comparator< Map.Entry<Long, Integer>>(){
			@Override
			public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
				if(o1.getValue().intValue()==o2.getValue().intValue()) //같을 때
					return Long.compare(o1.getKey().longValue(), o2.getKey().longValue());
				
				return o2.getValue()-o1.getValue();
			}
			
		});
		System.out.println(entry.get(0).getKey());
	}

	}
```
