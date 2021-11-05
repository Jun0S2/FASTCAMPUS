import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
/***
* @date : 11/05/2021
* @author : June Park
* Class to find shortest path using Kruskal algorithm
*/
public class KruskalPath {
 /**
 *Node Class
 *weight, v, u와 sorting 메서드를 오버라이드 한다
 */
  public static class Node implements Comparable<Node>{
	int weight, V, U;
    //setter method 구현
    @Override
    public int compareTo(Node node){
    	return this.weight - node.weight;
    }
}
    HashMap<String, String> parent = new HashMap<String, String>(); //부모 노드를 담는다
    HashMap<String, Integer> rank = new HashMap<String, Integer>(); //자신의 랭크를 담는다
    
  
    /**
    * Find Method : 해당 노드의 루트를 찾는 메서드
    * Path Compression 기법을 활용하여 시간복잡도를 단축
    */
    public String find (String node) {
	      if(parent.get(node)!=node){	//자기 부모가 루트가 아니라면
    	  parent.put(node.this.find(this.parent.get(node))); //부모의 부모 찾기
        }
	    return this.parent.get(node);//최종 루트 반환
     }
  
     /**
     * Union Method : 트리를 합치는 메서드
     * Union by Rank 기법을 활용하여 두 간선을 연결하여 시간 복잡도를 단축
     */
      public void union(String u, String v){ //노드 u와 노드 v
	        String root1 = find(u); //노드 u의 루트를 찾는다
          String root2 = find(v); //노드 v의 루트를 찾는다
          //union by rank 기법
          if(rank.get(root1) > rank.get(root2)){ //u의 랭크가 v의 랭크보다 크다면 v를 u에 더한다
    	      parent.put(root2, root1);
          }
          else{
    	      parent.put(root1, root2); //반대인 상황
            if(rank.get(root1) == rank.get(root2){ //만약 랭크가 같으면 하나를 조정한다
        	      rank.put(root2, rank.get(root2)+1 ); //루트 2의 랭크를 조정시킴
            }
          }
       }
               
       /**
       * MakeSet Method : 분리된 set으로 만드는 메서드
       * 전체 노드를 하나씩 받아 자신을 루트 노드로 만들고 랭크를 0으로 만들어서 초기화 하는 작업을 수행한다
       */
        public void makeSet(String node) {
          parent.put(node, node) ;//자기 자신 연결
          rank.put(node, 0);//랭크 0으로 초기화
        } 
        
        /** Kruskal Method : 앞서 구현한 기능들을 활용하여 최소 신장 트리를 리턴한다 (최단경로)
        * @nodes : 각 노드를 담은 리스트 [ex : "a", "b", "c", "d"]
        * @infoList : 각 노드 정보들을 담은 리스트 [ex : u = "a", v = "b", weight = "3"]
        */
        public ArrayList<Node> Kruskal (ArrayList<String> nodes, ArrayList<Node> infoList){
          ArrayList<Node> mst = new ArrayList<>();//최단 경로를 담을 최소신장리스트
          //초기화
          for(int i = 0 ; i< nodes.size() ; i++){ 
            this.makeSet(nodes.get(i);
          }
          //간선을 기반으로 sort
          Collections.sort(infoList);
          //최단경로 
          for(int i = 0 ; i < infoList.size() ; i++){
            if(find(infoList.get(i).u) != find(infoList.get(i).v){ //from-to의 루트가 다를때 : 추가가능
               union(infoList.get(i).u , infoList.get(i).v); //트리 합체
               mst.add(infoList.get(i)); //최소 신장 트리에 현재 경로 추가
              }
          }

        return mst ; //최소 신장 트리(최단경로)를 반환
   }
}
               
}
