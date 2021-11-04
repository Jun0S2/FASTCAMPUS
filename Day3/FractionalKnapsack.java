package com.fastcamp.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author June Park
 * @date 11/02/2021
 * 
 * Fractional Knapsack을 구현하는 클래스
 * 그리디 방식으로 정렬하여 풀이
 * input : w - 10 15 20 25 30
 * 		   v - 10 12 10 8 5
 * 5 13
10 10
15 12
20 10
25 8
30 5
 */
public class FractionalKanpsack {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //물품의 수
		int K = Integer.parseInt(st.nextToken()); //버틸 수 있는 무게의 수
		
		Integer[][] bag = new Integer [N][2];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i][0] = Integer.parseInt(st.nextToken());//물건의 무게
			bag[i][1] = Integer.parseInt(st.nextToken());//물건의 가치
		}
		
		//정렬-> fractional을 count 할 수 있으므로 최적의 방법은  가치/무게 가 큰 순으로 채워넣는것
	    Arrays.sort(bag, new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				return (o2[1] / o2[0]) - (o1[1] / o1[0]);			
			}} );
		
	    int weightSum = 0;
	    double value = 0;//fraction이기떄문에 double 사용
	    int index = 0;	//fraction만큼 더할 인덱스
	    //for loop를 돌면서 maximum 무게 (K) 까지 채워넣고, index를 기억해준 후 못들어간 물건의 fraction을 넣어준다
	      for(int i = 0; i <N ; i ++) {
	    	//  System.out.println(Arrays.deepToString(bag));
	    	  if(weightSum + bag[i][0] >= 0) { //이전 물건들 무게의 합과 현재 물건의 무게의 합이 0보다 크면 넣을 수 있다
	    		 value+=bag[i][1];
	    		 weightSum += bag[i][0];
	    	  }
	    	  else {index = i ; break;}
	      }
	      //마지막 담을 수 있는 물건
	      
	      System.out.println(value);
	}
}
