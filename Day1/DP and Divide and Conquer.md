![11012021](https://user-images.githubusercontent.com/36508552/139672480-7b7e283d-94cc-4780-a590-962ec504b16e.jpg)

> ### 날짜 : 2021 년 11월 1 일
> 
> ### 시청 강의 : 동적 계획법과 분할 정복, 병합 정렬 (1), 병합 정렬 (2)

# 동적 계획법과 분할 정복

## 동적 계획법 (DP)

1.  입력 크기가 작은 부분을 해결하여 큰 문제 해결에 적용하는 방식
2.  상향식 (sub problems -> main problem )
3.  **Memoization 기법 사용**

`Memoization` 이란, 이전 값을 기억하여 runtime이나 memory 문제를 해결해준다. -> 백준 문제에서 유용하게 사용

## 분할 정복 (Divide and Conquer)

1.  문제를 나눌 수 없을때까지 나누어 푼 후, 해답을 합친다
2.  하향식 (main problem -> sub problems)

## DP vs 분할 정복 요약

|   | DP | 분할 정복 |
| --- | --- | --- |
| common | 문제를 쪼개어(나누어) 풀이한다. |   |
| differences | sub-problems: 중복되며, 이 sub-problems로 상위 문제를 풀 수 있다. | 부분 문제들은 중복되지 않는다 |
|   | **Memoization을 통해 중복되는 연산 기피** | 따라서, Memo를 할 필요가 없다 |

## DP Example

DP 의 가장 대표적인 예시로는 팩토리얼, 피보나치 수열, 넵섹 등이 있다.  
이중 비교적 간단한 팩토리얼로 예시를 들고자 한다.

> ### Previously,
> 
> 재귀를 통해서 팩토리얼을 푸는 방식이 보편적이다  
> 재귀를 사용하여 팩토리얼을 연산하게 되면, 매번 값을 계산해야 해서 중복되는 계산들이 많아진다.

```
    /**
     * Recursive 사용
     * 매번, 값이 계산된다.

     * */
    public static int recursive(int number) {
        if(number == 1) return 1;
        return recursive(number-1)*number;
    }
```

> ### With DP,
> 
> DP를 사용하여 풀면, 이전 값들이 저장되기 때문에 중복 연산이 없어진다

```
    /**
     * Dynamic Programming 
     * Memoization을 사용한다
     * 한번 계산한 값은, D[current_index]에 저장되기 때문에 다시 계산될 필요 없다
     * */
    public static int dynamic(int number) {
        //Step 1 : D[] 배열 생성
        Integer[] D = new Integer[number+1];
        //Step 2 : Initialize init_values
        D[0] = 0;
        D[1] = 1;
        //Step 3 : for loop를 돌면서 각 배열에 값을 업데이트 (현재값은 이전값을 참고하여 업데이트한다)
        for(int i = 2 ; i<= number ; i ++) {
            D[i] = D[i-1] * i;//이전값 * 현재값
        }
        return D[number]; //맨 마지막 배열의 원소가 최종값임
    }
```

## Example - 분할 정복

병합 정렬이 가장 대표적인 분할 정복의 예로 들 수 있다.

### 병합 정렬 (Merge Sort)

병합 정렬은 재귀 용법을 이용한 알고리즘으로,

| 6 | 5 | 1 | 2 | 7 | 4 | 5 |
| --- | --- | --- | --- | --- | --- | --- |

위와 같은 배열이 있을 때 최소의 단위로 나누어질 때 까지 반으로 계속 나누고 (divide)

재조립 될 때 정렬(sort)하여 합치는(merge) 방식이다.

**Step 1: Divide**

mid = 7/2 = 3

| 6 | 5 | 1 | 2 |
| --- | --- | --- | --- |

| 7 | 4 | 5 |
| --- | --- | --- |

**Step 2 : Divide**

mid = 4/2 = 2, 3/2 = 1

| 6 | 5 |
| --- | --- |

| 1 | 2 |
| --- | --- |

| 7 | 4 |
| --- | --- |

| 5 |
| --- |

**Step 3: Divide (Completed)**

| 6 |
| --- |

| 5 |
| --- |

| 1 |
| --- |

| 2 |
| --- |

| 7 |
| --- |

| 4 |
| --- |

| 5 |
| --- |

**Step 4 : Merge - index 0과 0끼리 비교**

left array:

| 5 | 6 |
| --- | --- |

right array:

| 1 | 2 |
| --- | --- |

left array 2:

| 4 | 7 |
| --- | --- |

right array 2:

| 5 |
| --- |

**Step 5 : Merge - index 01 ~ 01 끼리 비교**

Array 1끼리 비교

index 0 vs 0 : 5 > 1

| 1 |   |   |   |
| --- | --- | --- | --- |

index 0 vs 1 : 5 > 2

| 1 | 2 |   |   |
| --- | --- | --- | --- |

left Array만 남았기 때문에 left Array를 sorted array 뒤에 붙힌다

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

Array 2 끼리 비교

index 0 vs 0 : 4 < 5

| 4 |   |   |
| --- | --- | --- |

index 1 vs 0 : 7 > 5

| 4 | 5 |   |
| --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**Step 5 :**

**Array 1 vs Array 2**

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 |   |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 | 4 |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 | 4 | 5 |   |   |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 | 4 | 5 | 5 |   |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 | 4 | 5 | 5 | 6 |   |
| --- | --- | --- | --- | --- | --- | --- |

---

| 1 | 2 | 5 | 6 |
| --- | --- | --- | --- |

| 4 | 5 | 7 |
| --- | --- | --- |

**sorted array :**

| 1 | 2 | 4 | 5 | 5 | 6 | 7 |
| --- | --- | --- | --- | --- | --- | --- |

> ### 위에서 MergeSort 알고리즘을 살펴보았다.\*\*
> 이제, 재귀를 통해 Merge Sort를 구현해보면,\*\*



```
import java.util.Arrays;

/**
 * @author June Park
 * @date 11/01/2021
 * Merge Sort 구현
 * 재귀로 구현
 * */
public class MergeSort {
    static int N = 10;
    static int [] sorted = new int[N];

    public static void main(String[] args) {
        //Create an random array
        int[] given = new int [N];
        for(int i = 0 ; i < N ; i ++) {
            given[i] = (int) (Math.random()*100)+1;
        }

        split(given,0,N-1); //0~N-1 (총 N개) 까지 쪼갤 것
        for(int i : given) {System.out.print(i+ " ");}
    }

    /**
     * Split Method 
     * Splits arrays to half (start ~ mid, mid ~ end ) whereas mid = start+end / 2
     * Splits until start < end -> splits everyeach elements
     * Merge the split array
     * 
     * */
    private static void split(int[] given, int start, int end) {
        if(start < end ) {                            //stops the recursion when its all split
            int mid = (start + end ) /2 ;

            //Recursive:
            split(given, start , mid );//0~half
            split(given, mid+1, end); //half ~ end

            //merge the split array
            merge(given, start, mid, end);
        }

    }

    /** Case 1 : left side 와 right side array 가 둘 다 준재할 때 :
     *  given[startpointer] 와 given[endpointer] 를 비교하여 sort 한 후, 사용한 포인터는 ++하여 다음으로 넘겨줌
     *  
     *  Case 2 : left 만 남음 
     *  sorted array에 left를 붙임
     *  
     *  Case 3 : right 만 남음
     *  sorted array에 right 붙임
     * */
    private static void merge(int[] given, int start, int mid, int end) {
        int startPointer = start;
        int index = start;
        int endPointer = mid+1;

        //두 배열 중 하나를 다 옮길 때 까지 반복
        //Case 1 : 
        while(startPointer <= mid &&  endPointer<= end) {
            if(given[startPointer] < given[endPointer]){     //startPointer가 더 작으면, startpointer++
                sorted[index] = given[startPointer];
                startPointer ++;
            }
            else {
                sorted[index] = given[endPointer];
                endPointer ++;
            }
            index++;                                         //sorted array의 인덱스를 한 칸 옮김
            }
        //Case 2 and 3 : Case 1이 끝나고, left 나 right 에만 남음
        if(startPointer > mid ) {                                    //right 만 남음            
            for(int i = endPointer ; i <= end ; i ++) {     //endpointer ~ end 까지 sorted 에 더함
                sorted[index] = given[i];
                index++;
            }
        }
        else {                                                //left 만 남음
            for(int i = startPointer ; i<= mid ; i++) {        //startpointer ~ mid 까지 sorted에 더함
                sorted[index] = given[i];
                index++;
            }
        }

        //정렬된 배열을 삽입
        for(int i = start ; i <= end ;i ++) {
            given[i] = sorted[i];                            //given 에 현재 sorted process를 해놓아야만, split이 다음 merge를 불렀을 때 current process가 유지
        }
        System.out.println(Arrays.toString(given));
    }
}
```

재귀를 통해 배열을 반으로 분할 한 후, sort 시키는 과정을 반복하여 구현해 볼 수 있다.

