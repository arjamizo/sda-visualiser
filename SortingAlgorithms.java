package sda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SortingAlgorithms {
	static {
		int[] ar = new int[]{1, 6, 32, 65, 123, 12333, 542};
		bubbleSort(ar);
		int[] ret = new int[]{1, 6, 32, 65, 123, 542, 12333};
		List<int[]> asList = Arrays.asList(ar);
		assert (asList).equals(ret);
	}
	
	public static int[] bubbleSort(int[] comparable) {
	    boolean changed = false;
	    do {
	        changed = false;
	        for (int a = 0; a < comparable.length - 1; a++) {
	            if (comparable[a]<comparable[a + 1]) {
	                int tmp = comparable[a];
	                comparable[a] = comparable[a + 1];
	                comparable[a + 1] = tmp;
	                changed = true;
	            }
	        }
	    } while (changed);
		return comparable;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Printing the file passed in:");
        List<Integer> arr = new ArrayList<Integer>();
        while(sc.hasNextLine()) {
        	try {
        		int i = Integer.parseInt(sc.nextLine());				
        		arr.add(i);
			} catch (Exception e) {
				break;
			}
        }
		System.out.println(Arrays.toString(arr.toArray()));
	}
}
