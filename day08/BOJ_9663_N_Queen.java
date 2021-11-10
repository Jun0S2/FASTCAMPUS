package com.fastcamp.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * @author June Park
 * @date 11/08/2021
 * N-Queen Problem
 * on NxN chessboard, find #sols of n queen
 * */
public class BOJ_9663_N_Queen {

	static int [] board; //각 row 마다 퀸을 놓은 인덱스 저장
	static int N , answer;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		N = Integer.parseInt(br.readLine());
		board = new int [N]; //board[row] = col -> row 마다 하나의 퀸을 놓을 수 있음. board[1] = 1번째 row의 퀸을 몇col에 두었는지 저장한다 
		answer = 0;
		dfs(0);
		System.out.println(answer);
	}
	/**
	 * */
	private static void dfs(int row) {
		if(row == N) {//끝까지 도착
			answer++;
			return;
		}
		for(int i = 0 ; i<N ;i ++) {
			board[row] = i; //row 가 1이라면, 퀸을 i 번째 column에 위치시켜보고 둘 수 있는지 없는지 판단
			if(check(row)) {//해당 row에 놓은 col 정보가 괜찮은가..
				dfs(row+1); //다음 row에 놓음
			}
			//else : 다시 col 선정후 반복
		}
	}
	/**
	 * 현재 위치에 queen 이 갈 수 있는지 없는지 확인
	 * 1. 퀸이 같은 col에 있는 경우 : board[row] = col 에서 좌표는 row, col
	 * board[row] 와 board[i] 를 비교하면 동일한 칼럼에 위치하는지 알 수 있음
	 * 
	 * 2. 대각선에 존재 ? 
	 * abs(queen.col - curr.col) == abs(queen.row - curr.row) 면 대각선상에 있음
	 * */
	private static boolean check(int row) {
		// 같은 column인지 판단 : board[i] : dfs 메서드에서 board[row] = col 로 초기화 시켜줬음.
		// board[n] 은 column 정보를 담고 있음. board[i]==board[row] 같은 column
		for(int i = 0 ; i< row ;i ++) {
			if(board[i] == board[row])
				{
				return false;}
			//대각선 :row, col
			if(Math.abs(i-row)== Math.abs(board[row]-board[i])) return false;
		}
		return true;
	}

}
