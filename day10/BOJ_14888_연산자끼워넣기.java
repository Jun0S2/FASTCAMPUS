package Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author June Park
 * @date 11/10/2021
 * dfs 를 이용하여 연산자 +-* /에 대해 남은 연산자가 있는 경우 해당연산처리 후 dfs로 보낸다
 * */
public class BOJ_14888_연산자끼워넣기 {
static int N , nums[],operator[];
static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N];
		
		operator = new int[4];//+ - * /
		for(int i = 0 ; i < N ; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++) {
			operator[i]=Integer.parseInt(st.nextToken());
		}//end of input
		
		dfs(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
	private static void dfs(int depth , int calculated) {
		if(depth == N) {
			//System.out.println(calculated);
			min = Math.min(calculated, min );
			max = Math.max(calculated, max);
			return;
		}
		for(int i = 0 ; i <4 ; i++) {//연산자에 대해 +,-,*,/ 확인
			if(operator[i]>0) {		 //연산자가 남아 있을 경우에만
			operator[i]--;			 //사용된 연산자 pop off
			if(i==0) dfs(depth+1 , nums[depth]+calculated);
			else if(i==1) dfs(depth+1 , calculated - nums[depth]);
			else if(i==2) dfs(depth+1 , calculated * nums[depth]);
			else if(i==3) dfs(depth+1 , calculated / nums[depth]);
			operator[i]++;
			}
		}
		
		
	}

	
	

}
