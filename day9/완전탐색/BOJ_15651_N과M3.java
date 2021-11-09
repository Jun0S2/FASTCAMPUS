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
 * N과 M (3) : 중복 허용
 * bw 를 써서 시간 초과를 해결했다.
 * */
public class BOJ_15651_N과M3 {
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
		if(depth == M ) {
			for(int i : num)bw.write(i+" ");// System.out.print(i+" "); 
			bw.write("\n");
			return;
		}
		for(int i = 1 ;i <=N ;i ++) {
			//visited 필요 없음
			num[depth] = i;
			dfs(depth+1);
		}
		
	}
	
}
