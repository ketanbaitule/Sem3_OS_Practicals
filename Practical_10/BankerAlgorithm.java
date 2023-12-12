/**
 * Study and implementation of Banker’s algorithm for Deadlock Avoidance
 */

import java.util.ArrayList;

class BankerAlgorithm {
    public static void main(String[] args) {
        int process_allocation[] = { 010, 200, 302, 211, 002 };
        int max_allocation[] = { 753, 322, 902, 222, 433 };
        int need[] = new int[process_allocation.length];
        boolean hasAllocated[] = new boolean[process_allocation.length];
        int available_work = 332;

        for(int i = 0; i < process_allocation.length; i++)
            need[i] = max_allocation[i] - process_allocation[i];

        ArrayList<Integer> sequence = new ArrayList<Integer>();
        
        for (int i=0; sequence.size() != process_allocation.length; i = (i+1) % process_allocation.length) {
            if(need[i] <= available_work && !hasAllocated[i]){
                available_work = available_work + process_allocation[i];
                sequence.add(i); 
                hasAllocated[i] = true;
            }
        }

        System.out.print("Sequence: ");
        for(int i=0; i<sequence.size(); i++)
            System.out.print(sequence.get(i)+"\t");


    }    
}
