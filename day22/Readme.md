## Big O Notation

입력의 크기 N에 대해서 시간이 얼마나 걸릴지 나타내는 방법. 최악의 경우를 구한다.

1부터 N까지의 숫자의 합을 구하는 프로그램도, 코드에 따라 시간 복잡도가 크게 차이 난다.

1.  O(N)

```java
int sum = 0;
for (int i =1; i<= N ; i++) {sum+=i;}
```

1.  O(N^2)

```java
int sum = 0; 
for(int i =1; i<=N; i++){ for (int j =1; j<=N; j++) {if (i==j}sum+= j;}
```

1.  O (1)

```java
int sum = 0; sum = N * (N+1) /2
```

### 입력의 크기에 따른 시간 복잡도

1초가 걸리는 입력의 크기:

O(1), O(lgN), O(N): 1억, O(NlgN): 5백만, O(N^2): 1만, O(N^3): 500, O(2^N) : 20, O(N!) 10

이를 염두에 두고 N의 크기를 보고 시간 복잡도를 따져봐야 한다. 또한, 팩토리얼은 최악의 방식이기 때문에 기피하는 게 좋고 보통 우리가 문제를 풀 때 O(N^2)으로 풀게 되는데, O(NlgN)으로 변환할 수 있다면 매우 효율적으로 짠 프로그램이다.

[##_Image|kage@7hTnO/btrlGIrTeTu/tFiEQzCmuKYGB6A5NAMG0K/img.png|CDM|1.3|{"originWidth":1223,"originHeight":590,"style":"alignCenter"}_##]

# 메모리

문제 제한이 보통 1초/ 512MB이다

### 나를 위한 팁...

<aside> 🤦🏻‍♀️ 배열의 크기가 크면 시간 초과를 받는 경우가 많다 불필요한 공간이 없으면 메모리는 알아서 지켜진다

</aside>

# 입출력

1.  Scanner
    -   사용이 편하다 (나한테..)
    -   **입력이 많은 경우에 속도가 느려서 BufferedReader를 사용**해야 한다
2.  BufferedReader
    
    -   **BufferedReader br = new BufferedReader ( new InputStreamReader ([System.in](http://System.in)));**
    -   출력이 많은 경우에는 StringBuilder를 사용하여 한 문자열로 만들어서 출력을 한 번만 사용하거나 Buffered Writer을 사용한다
    
    BufferedReader 사용 방법
    1.  Input 이 int 인 경우: Integer.parseInt()로 타입 변환
    2.  Input 이 int 배열의 요소들일 때: StringTokenizer와 split()을 사용한다 
        ```java
        import java.io.BuffredReader; 
        import java.io.IOException; 
        import java.io.InputStreamReader; 
        import java.util.StringTokenizer; 
        public class Main{ public static void main(String[] args) throws IOException{ 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            int N = Integer.parseInt(br.readLine()); //한개의 상수 형변환
            int[] arr = new int[N] ; //배열 input을 담을 배열 생성
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            for(int i =0; i<N; i++){ 
                 arr[i] = Integer.parseInt(st.nextToken());
                } //토크나이저를 다시 숫자로 parse br.close(); 
             } 
        } 
        ```

### BufferedWriter 사용 방법

```java
import java.io.BufferedWritier;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main{
public static void main(String[] args) throws IOException{
BuffredWriter bw = new BufferedWritier (new OutputStreamWriter(System.out));
bw.write("Java");
bw.flush();
br.close();
}
}
```

### StringBuilder 사용 방법

```java
StringBuilder sb = new StringBuilder("Hey");
sb.append("add elements");
sb.setSize(sb.length()-1); //이렇게하면 바로 전까지 잘라내서 콤마를 없애버릴수있다
System.out.print(sb);
int indexStart = 0, indexEnds = 3;
sb.deleted(0,3);
```
