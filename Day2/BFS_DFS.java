package com.fastcamp.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @author June Park
 * @date 11/02/2021
 * 
 * BFS와 DFS 방식을 구현하는 클래스
 * 1. HashMap 구조로 인접 리스트를 구현하는 방식
 * 2. BFS 방식
 * 3. DFS 방식 : 재귀를 사용하지 않고, BFS와 동일한 방식으로 구현 (단, queue대신 stack 사용)
 * */
public class BFS_DFS {
	
	public static void main(String[] args) {
		//HashMap 구조로 간선 그래프 표현
		HashMap<String , ArrayList<String>> graph = new HashMap<>();
		/*Tip 
		 * 	put 기능을 사용하여, 각 노드 당 연결된 노드를 저장 
		 *  Arrays.asList()*/
		graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
		graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
		graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
		graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
		graph.put("E", new ArrayList<String>(Arrays.asList("D")));
		graph.put("F", new ArrayList<String>(Arrays.asList("D")));
		graph.put("G", new ArrayList<String>(Arrays.asList("C")));
		graph.put("H", new ArrayList<String>(Arrays.asList("C")));
		graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
		graph.put("J", new ArrayList<String>(Arrays.asList("I")));
		
		System.out.println("________________________________________________________________________");
		System.out.println("BFS 방식 : ");
		bfs(graph , "A");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("DFS 방식 : ");
		dfs(graph,"A");
		System.out.println("________________________________________________________________________");
	
	}

	
	/**
	 * 2차원 배열인 경우 : 
	 * Queue<int[]> 를 사용해도 되지만, 개인적으로 클래스를 따로 빼는 것을 선호한다.
	 * */
	public static class Node{
		int x,y;
		Node(int x, int y ){
			this.x = x;
			this.y = y;
		}
	}
	/** 
	 * BFS는 큐 구조를 사용
	 * O( V + E )
	 * 1. 큐 생성
	 * 2. 방문 처리
	 * */
	private static void bfs(HashMap<String, ArrayList<String>> graph, String startNode) {
		StringBuilder sb = new StringBuilder();
			Queue<String> q = new LinkedList<>();
			ArrayList<String> visited = new ArrayList<>();
			
			q.offer(startNode);
			while(!q.isEmpty()) {
				String currentNode = q.poll(); //현재 방문중인 노드
				
				if(visited.contains(currentNode)) continue; 	//이미 방문중이라면 pass
				sb.append(currentNode + "-> ");
				visited.add(currentNode);						//방문처리
				q.addAll(graph.get(currentNode));				//현재 노드따까리 더하기
			
			}
			sb.setLength(sb.length()-3);
			System.out.println(sb.toString());
	}
	
	/**
	 * DFS는 스택 구조를 사용
	 * 스택 대신 ArrayList로 구현하는게 일반적
	 * BFS 는 큐를 구현했다면 DFS 는 스택을 구현 -> 즉 : 맨 앞을 빼는게 아니라 맨 뒤를 poll!
	 * O(V+E)
	 * */
	private static void dfs(HashMap<String, ArrayList<String>> graph, String startNode) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> visited = new ArrayList<>();
		ArrayList<String> stack = new ArrayList<>();
		
		stack.add(startNode);
		while(!stack.isEmpty()) {									
			String currentNode = stack.remove(stack.size()-1);//마지막 원소를 poll한다
			if(visited.contains(currentNode))continue;			//이미 방문중이라면 pass
			sb.append(currentNode + "-> ");
			visited.add(currentNode);							//방문처리
			stack.addAll(graph.get(currentNode));				//현재 노드 자식 다 더하기
		}
		sb.setLength(sb.length()-3);
		System.out.println(sb.toString());
	}
}
