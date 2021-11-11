package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_10825_국영수 {
/**
 * @author June Park
 * @date 11/11/2021
 * 국영수 점수 정렬 문제
 * 1. 국어 점수가 감소하는 순으로
 * 2. 국어 점수가 같으면 영어 점수가 증가하는 순
 * 3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순
 * 4. 모든 점수가 같으면 이름이 사전순으로 증가하는 순으로 (대문자가 소문자보다 작음 -'0'하면 될듯)
 * */
	static int N;
	static ArrayList<Record>list;
	public static class Record implements Comparable<Record>{
		String name;
		int Korean;
		int English;
		int Math;
		Record(String name , int Korean, int English, int Math){
			this.name = name;
			this.Korean = Korean;
			this.English = English;
			this.Math = Math;
		}
		/**국어 점수가 감소하는 순으로 정렬 : 국어가 높은거부터
		 * if same : 영어가 작은순		
		 * if same : 수학이 큰 순
		 * if same : 이름이 작은순
		 */
		@Override
		public int compareTo(Record o) {
			//국어가 큰 순
			if(Korean-o.Korean!=0) { return o.Korean- Korean;}
			//Else : 영어가 작은순
			if(English-o.English!=0) { return English-o.English;}
			//Else : 수학이 큰 순
			if(Math-o.Math!=0) {return o.Math - Math;}
			//Else : 이름이 작은 순
			
			/*for(int i = 0 ; i <o.name.length() ; i ++) {
				if(o.name.charAt(i)!= name.charAt(i)) {
					if(o.name.charAt(i)-'0'> name.charAt(i)-'0') {
						//쟤가 나보다 클때
						return -1;
					}
					else {return 1;}
				}
			} */
			return name.compareTo(o.name);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //학생의 수
		list = new ArrayList<>();
		String name = "";
		int kor= 0, eng = 0, math = 0; 
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			name = st.nextToken();
			kor = Integer.parseInt(st.nextToken());
			eng = Integer.parseInt(st.nextToken());
			math = Integer.parseInt(st.nextToken());
			
			list.add(new Record(name, kor, eng, math));
		}
		br.close();
		//sort
		Collections.sort(list);
		
		//print
		for(Record r : list) {
			//System.out.println(r.name);
			bw.write(r.name);
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

}
