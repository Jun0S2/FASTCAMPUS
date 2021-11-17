# 이분 탐색 (Binary Search)

**정렬이 보장되는 배열**에서 기준 x를 가지고 범위를 "이분"하면서 탐색

### Search for...

1.  x 가 존재하는가
2.  x이하, 미만, 초과의 원소는 몇 개가 있는가
3.  x 랑 가장 가까운 원소는 무엇인가

### 오름 차순 정렬된 배열의 특성

1.  임의의 M번 인덱스에 있는 `A [M]` 이 X 보다 크다면, `A [M... N]` 은 전부 x 보다 **크다**
2.  임의의 M번 인덱스에 있는 `A [M]` 이 X 보다 작다면, `A [M... N]` 은 전부 x 보다 **작다**
3.  위 조건은 정렬이 된 상태에서만 사용 가능

### Example

x = 38

| 10 | 15 | 20 | 35 | 40 |
| --- | --- | --- | --- | --- |

1.  L = 1, R = 5로 잡음

-   L : 탐색할 가치가 있는 범위의 왼쪽 끝 인덱스
-   R : 탐색할 가치가 있는 범위의 오른쪽 인덱스

2.  x와 L R 비교 후 범위 재설정  
    L = 4 R = 5
3.  x 와 L R 비교 후 범위 재설정  
    L = 5, R = 5

\-> 존재하지 않는다!

### 정리

`A [M]과` x를 한번 비교할 때마다 `[L, R]` 구간이 절반씩 좁아진다  
즉, **총 비교 횟수**는 **구간의 변화 횟수**인 `O(logN)`만에 원하는 값을 탐색한다  
만약, N = 10만 이면, log(10만) == 16 이 된다. 따라서 굉장히 효율적인 탐색이다.

### 자주 하는 실수

1.  **정렬을 하지 않은 경우**
2.  L, R, M, Result 변수의 정의를 헷갈려서 부등호를 잘못 사용
3.  L, R범위를 잘못 설정하거나 Result의 초기값을 잘못 설정

#### 연습문제

| 먹을 것인가 먹힐 것인가 | BOJ 7795 |
| --- | --- |
| 두 용액 | BOJ 2470 |
