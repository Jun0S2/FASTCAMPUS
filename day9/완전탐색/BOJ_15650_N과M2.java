package com.fastcamp.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * @author June Park
 * @date 11/09/2021
 * 중복 없이 M 개를 고름
 * 오름차순
 * */
public class BOJ_15650_N과M2 {
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
		if(depth == M) {
			for(int i:num)System.out.print(i+" ");
			System.out.println();
			return;
		}
		
		for(int i = 1; i<= N ;i ++) {
			if(visited[i]) continue; //중복 -> out
			if(depth>0 && num[depth-1]>i)continue; //원소가 하나 이상일때, 저번 원소보다 i 가 작으면 x
			//Else:
			visited[i] = true;
			num[depth] = i;
			dfs(depth+1);
			visited[i] = false;
		}
		
	}
	

}
