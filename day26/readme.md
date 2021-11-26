
> 시청 강의 : CH04\_02. 분기문(case-when), 집합연산(union), 집합연산(union all), 서브쿼리(column, from, where)  
> 시청 날짜 : 11/26/2021

강의의 문제 풀이를 위해 필요한 함수들을 정리해보았다.

## CASE-WHEN

CASE문은 프로그래밍 언어에서 스위치(switch)문과 비슷하지만,다수의 조건에 하나의 반환 값은 동작하지 않는다.

```
CASE WHEN 조건
THEN '반환값'
WHEN 조건
THEN '반환값'
ELSE 'WHEN 조건에 해당 안되는 경우 반환값'
END
```

-   WHEN 과 THEN은 한쌍
-   WHEN 과 THEN 은 다수가 존재할 수 있음
-   ELSE 가 존재하면 모든 조건에 해당하지 않는 경우에 반환 값을 설정
-   ELSE 가 존재하지 않고 조건에 맞지 않아서 반환값이 없으면 NULL 반환

### UNION 과 UNION ALL

Union은 두 쿼리의 결과를 합치는 것으로, 열의 개수가 일치하고 각 쌍의 데이터 타입들이 호환되는 두 쿼리에서만 작동한다'

```
SELEC 필요한 값 FROM 테이블1
UNION ALL
SELECT 필요한값 FROM 테이블2
```

-   필요한 값들은 같은 값이다 ex: name, score
-   테이블 1과 테이블 2는 열의 개수가 일치한다
-   테이블 1과 테이블 2는 데이터 타입이 호환된다  
    ex

```
SELECT name, score from JavaTest
UNION ALL
SELECT name, score from SQLTest
```

---

패스트캠퍼스 환급 챌린지 바로가기👉 [https://bit.ly/3FVdhDa](https://bit.ly/3FVdhDa)

 [수강료 100% 환급 챌린지 | 패스트캠퍼스

딱 5일간 진행되는 환급챌린지로 수강료 100% 환급받으세요! 더 늦기전에 자기계발 막차 탑승!

fastcampus.co.kr](https://bit.ly/3FVdhDa)

**본 포스팅은 패스트캠퍼스 환급 챌린지 참여를 위해 작성되었습니다**
