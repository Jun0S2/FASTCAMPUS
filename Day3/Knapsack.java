package com.fastcamp.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author June Park
 * @date 11/03/2021
 *  0/1 Knapsack 
 *  DP 를 사용하여 구함
 *  백준 12865
 * */
public class Knapsack {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //물품의 수
		int K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게의 수
		
		int [] weight = new int [N+1];					//무게를 담을 배열
		int [] value = new int [N+1];					//가치를 담을 배열
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		//DP 로 풀이 : D [물건] [최대 무게 연산]
		//D로 구하려는 것 : 최대 가치
		int [][] D = new int [N+1][K+1];
		for(int i = 1 ; i<= N ; i++) {				//for all objs
			for(int w = 1 ; w <=K ; w++) {			//fill until maximum weight
				if(weight[i]<=w) {					//해당 물건을 가방에 넣을 수 있다: 현재물건의 무게가 무게연산을 초과x
					D[i][w] = Math.max(D[i-1][w] ,  //이전 가치가 더 많은가 ?
					value[i]+D[i-1][w-weight[i]]);  //현재물건의가치 + 이전물건의가치 & 물건무게 track

				}
				else {
					D[i][w] = D[i-1][w];			//아니라면, 이전 가치와 무게 유지
				}
			}
			
		}
		System.out.println(D[N][K]);
	}
}
