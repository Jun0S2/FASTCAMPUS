/**
 * @author June Park
 * @date 11/01/2021
 * Factorial을 DP와 일반 recursive로 구하는 클래스
 * 
 * Factorial Formula : n! = n*(n-1)*(n-2)...*1
 * */
public class Factorial {
	public static void main(String[] args) {
		int num = 5;
		System.out.println(3/2);
		System.out.println("recursive : " + recursive(num));
		System.out.println("dynamic : " + dynamic(num));
	}
	
	/**
	 * Recursive 사용
	 * 매번, 값이 계산된다.

	 * */
	public static int recursive(int number) {
		if(number == 1) return 1;
		return recursive(number-1)*number;
	}
	
	
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
}
