package gitCopiado;

import java.util.ArrayList;

public class Subset { //Generate all subsets by generating all binary numbers
    public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {

        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << set.size();             //there are 2 power n different subsets

        for (int i = 0; i < max; i++) {
            ArrayList<Integer> subset = new ArrayList<Integer>();
            for (int j = 0; j < set.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subset.add(set.get(j));
                }
            }
            allsubsets.add(subset);
        }
        return allsubsets;
    }

    public static void main(String[] args) {
        ArrayList<Integer> set = new ArrayList<Integer>(); //Create an ArrayList
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);


        ArrayList<ArrayList<Integer>> list = getSubsets2(set);
        int i = 0;
        for(ArrayList<Integer> aux : list) {
        	System.out.print("List " + i + ": ");
        	for(int j = 0; j < aux.size(); j++) {
        		System.out.print(" " + aux.get(j) + " ");
        	}
        	System.out.println();
        	i++;
        }
    }
}