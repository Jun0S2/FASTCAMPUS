package com.fastcamp.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BOJ_11652_카드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Map<Long,Integer> numbers = new HashMap<>(); //리스트 형태로 가져오기 위해 Map 사용

		for(int n = 0 ; n< N ; n++) {
			long num = Long.parseLong(br.readLine());
			if(numbers.containsKey(num)) {
				numbers.put(num, numbers.get(num)+1);//override
			}
			else {numbers.put(num, 1);}
		}
		br.close();
		//sort
		//리스트 형태로 가져옴
		List<Map.Entry<Long,Integer>>entry = new LinkedList<>(numbers.entrySet());
		
		//sort
		entry.sort(new Comparator< Map.Entry<Long, Integer>>(){
			@Override
			public int compare(Entry<Long, Integer> o1, Entry<Long, Integer> o2) {
				if(o1.getValue().intValue()==o2.getValue().intValue()) //같을 때
					return Long.compare(o1.getKey().longValue(), o2.getKey().longValue());
		
				return o2.getValue()-o1.getValue();
			}
			
		});
		System.out.println(entry.get(0).getKey());
	}

	}
