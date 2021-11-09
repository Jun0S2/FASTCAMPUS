package com.fastcamp.backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author June Park
 * @date 11/09/2021
 * N과 M (4) : 중복 허용
 * 비내림차순 : 중복은 허용(2 2 가능)되나 2 1 은 불가
 * */
public class BOJ_15652_N과M4 {
	static int N,M ,num[];
	static BufferedWriter bw;
	//static boolean visited[];
	public static void main(String[] args) throws Exception {
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //N개 중
		M = Integer.parseInt(st.nextToken()); //M개를 뽑는다
		num = new int [M];				  	  //방문한 번호를 넣을 배열
		//visited = new boolean[N+1];			  //방문처리
		br.close();
		dfs(0);
		bw.flush();
		bw.close();
	}
	private static void dfs(int depth) throws IOException {
		if(depth == M) {
			for(int i : num)bw.write(i+" ");
			bw.write("\n");
			return;
		}
		for(int i = 1 ; i<= N ;i ++) {
			//방문조건 필요없음
			if(depth!=0 && num[depth-1]>i)continue; //이전 원소가 지금보다크면 skip
			num[depth]= i;
			dfs(depth+1);
			
		}
		
	}
}
