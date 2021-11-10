package Backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *@ author June Park
 *@ date 11/10/2021
 *1. 오름 차순 : Arrays.sort()를 사용하면, dfs에서 추가 연산 안해도 된다. (greedy)
 *2. return 조건에서 aeiou 개수 판단 
 * */
public class BOJ_1759_암호만들기 {
	static int L,C;
	static char[] input;
	static boolean[]visited;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		L = Integer.parseInt(st.nextToken()); //length of passwords
		C = Integer.parseInt(st.nextToken()); //number of given characters
		visited = new boolean[C];
		//password : 최소 한개의 모음과 두개의 자음으로 구성 . alphabetically ordered 증가순으로 구성
		input = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(input); //sort 하면, 현재가 이전보다 작은거 고민할 필요 x

		char[] passwords = new char[L];
		dfs(0,0 , passwords);//depth, index
		bw.flush();
	}
	//count 로 길이 센다
	private static void dfs(int depth, int index, char[] passwords) throws IOException {
		if(depth == L ) {
			int vowels=0 , consonants=0;
			for(char c : passwords) {
				if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u') {vowels++;}
				else {consonants++;}
			}
			if(vowels>=1 & consonants>=2) {
				for(char c:passwords) {
					bw.write(c);
				}
				bw.write("\n");					
			}
			return;

		}
		//중복 선택 허용 x - visited 배열 사용
		for(int i = index ; i < C ; i++) { //index활용 책갈피
			if(visited[i])continue;
			visited[i] = true;
			passwords[depth] = input[i];
			dfs(depth+1 , i+1 , passwords);
			visited[i] = false;
		}
	}
}
