# 재귀

재귀 함수란 특정 함수 내에서 자기 자신을 다시 호출하여 문제를 해결해나가는 함수이다. 문제를 해결하기 위해 원래 범위의 문제에서 더 작은 범위의 하위 문제를 먼저 해결하여 원래 문제를 해결해 나간다.

#### Factorial 예제

그 유명한 팩토리얼 예제...

스택 오버플로우에서 그림과 코드를 가져와봤다.

```java
int factorial(int n) {
      if (n <= 1)
            return 1;
      else
            return n * factorial(n - 1);
}
```

강의에서도 위와 같은 방식으로 풀었는데, 아래 그림을 보면 재귀가 어떻게 이루어지는지 쉽게 이해할 수 있어서 가져와보았다.
![image](https://user-images.githubusercontent.com/36508552/141611764-1c94e0c1-c19c-4f0f-967e-294c950c70b9.png)

강의에서는 조건을 (n>1)로 주었지만, 대학에서 배울 때 Base Case를 콕 잡아서 코드 쓰는 게 더 편해졌기때문인지 몰라도, n==1일 때를 base case로 잡는 게 더 보기가 좋은 것 같다.

```java
public class Factorial{
	public int factorial(int n ){
    	//Base case
        if(n==1){return 1;}
        return n* factorial(n-1);
    }
}
```

#### 시간 복잡도

O(n-1) = O(n)

#### 공간 복잡도

O(n-1) = O(n)

시간 복잡도와 공간 복잡도를 보면 알겠지만, n 이 커지면 시간 초과나 스택오버플로우가 나기 일상이다. 따라서, 보통 n이 큰 경우는 재귀가 아니라 DP로 풀 수 있는지 의심해 봐야 한다. 만약, 재귀를 푸는 과정에서 중복되는 과정이 많다면 DP로 한번 풀어보는 것을 추천한다.

Reference : [https://stackoverflow.com/questions/8183426/factorial-using-recursion-in-java](https://stackoverflow.com/questions/8183426/factorial-using-recursion-in-java)
