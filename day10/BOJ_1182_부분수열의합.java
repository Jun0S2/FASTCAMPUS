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
