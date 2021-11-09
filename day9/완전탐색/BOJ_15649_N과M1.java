package com.fastcamp.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author June Park
 * @date 11/09/2021
 * N과 M (1) 문제 : N 개 중 M 개를 중복 없이 뽑음
 * */
public class BOJ_15649_N과M1 {
	static int N,M ,num[];
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N개 중
		M = Integer.parseInt(st.nextToken()); //M개를 뽑는다
		num = new int [M];				  	  //방문한 번호를 넣을 배열
		visited = new boolean[N+1];			  //방문처리
		dfs(0);
	}
	
	
	private static void dfs(int depth) {
		if(depth == M) {					 //M개를 뽑음
			for(int i =0 ; i < M ; i++) {
				System.out.print(num[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i = 1 ; i <= N ;i ++) {
			//조건
			if(visited[i]) continue;
				visited[i] = true; //방문처리
				num[depth] = i;//현재 
				dfs(depth+1);//재귀
				visited[i] = false;//release
			
		}
		
	}

}
