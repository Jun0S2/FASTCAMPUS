package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author June Park
 * @date 11/12/2021
 * 수열 정렬
 * input
 * line 1 - 배열의 크기 N
 * line 2 - 배열의 원소
 * Problem 
 * 비내림차순이되는 수열을 찾는다 (오름차순, ascending) - print index not elements
 * */
public class BOJ_1015_수열정렬 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] compare = new int[N];
		int[] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		br.close();
		for(int i = 0 ; i < N ; i++) {
			int num = Integer.parseInt(st.nextToken());
			list[i] =compare[i] = num;
		}
		//sort list array and keep the input array as unsorted
		Arrays.sort(list);

		for(int i = 0 ; i < N ; i++) {
			for(int j =0 ; j<N ; j ++) {
				if(compare[i]== list[j]) { //if you find original element in sorted array 
					bw.write(j+" ");	   //print out the index from sorted array
					list[j] = -20;		   //put negative number to sorted array to avoid searching for same index when there is same number	
					break;				   //escape from the for loop and search for next element
				}
			}
		}//end of for
		bw.flush();
		bw.close();
	
	}
}
