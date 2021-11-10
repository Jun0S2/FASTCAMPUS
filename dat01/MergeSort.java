import java.util.Arrays;

/**
 * @author June Park
 * @date 11/01/2021
 * Merge Sort 구현
 * 재귀로 구현
 * */
public class MergeSort {
	static int N = 10;
	static int [] sorted = new int[N];
	
	public static void main(String[] args) {
		//Create an random array
		int[] given = new int [N];
		for(int i = 0 ; i < N ; i ++) {
			given[i] = (int) (Math.random()*100)+1;
		}
		
		split(given,0,N-1); //0~N-1 (총 N개) 까지 쪼갤 것
		for(int i : given) {System.out.print(i+ " ");}
	}
	
	/**
	 * Split Method 
	 * Splits arrays to half (start ~ mid, mid ~ end ) whereas mid = start+end / 2
	 * Splits until start < end -> splits everyeach elements
	 * Merge the split array
	 * 
	 * */
	private static void split(int[] given, int start, int end) {
		if(start < end ) {							//stops the recursion when its all split
			int mid = (start + end ) /2 ;
			
			//Recursive:
			split(given, start , mid );//0~half
			split(given, mid+1, end); //half ~ end
		
			//merge the split array
			merge(given, start, mid, end);
		}
		
	}
	
	/** Case 1 : left side 와 right side array 가 둘 다 준재할 때 :
	 *  given[startpointer] 와 given[endpointer] 를 비교하여 sort 한 후, 사용한 포인터는 ++하여 다음으로 넘겨줌
	 *  
	 *  Case 2 : left 만 남음 
	 *  sorted array에 left를 붙임
	 *  
	 *  Case 3 : right 만 남음
	 *  sorted array에 right 붙임
	 * */
	private static void merge(int[] given, int start, int mid, int end) {
		int startPointer = start;
		int index = start;
		int endPointer = mid+1;
		
		//두 배열 중 하나를 다 옮길 때 까지 반복
		//Case 1 : 
		while(startPointer <= mid &&  endPointer<= end) {
			if(given[startPointer] < given[endPointer]){ 	//startPointer가 더 작으면, startpointer++
				sorted[index] = given[startPointer];
				startPointer ++;
			}
			else {
				sorted[index] = given[endPointer];
				endPointer ++;
			}
			index++; 										//sorted array의 인덱스를 한 칸 옮김
			}
		//Case 2 and 3 : Case 1이 끝나고, left 나 right 에만 남음
		if(startPointer > mid ) {									//right 만 남음			
			for(int i = endPointer ; i <= end ; i ++) { 	//endpointer ~ end 까지 sorted 에 더함
				sorted[index] = given[i];
				index++;
			}
		}
		else {												//left 만 남음
			for(int i = startPointer ; i<= mid ; i++) {		//startpointer ~ mid 까지 sorted에 더함
				sorted[index] = given[i];
				index++;
			}
		}
		
		//정렬된 배열을 삽입
		for(int i = start ; i <= end ;i ++) {
			given[i] = sorted[i];							//given 에 현재 sorted process를 해놓아야만, split이 다음 merge를 불렀을 때 current process가 유지
		}
		System.out.println(Arrays.toString(given));
	}

	
	


}
