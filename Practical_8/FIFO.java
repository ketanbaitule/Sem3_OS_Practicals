/**
 * Study and implementation of FIFO page replacement algorithm
 */

import java.util.LinkedList;

class Optimal {
    public static int contains(int search, int arr[]){
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == search)
                return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int referenceString[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 3};
        int no_of_frames = 4;
        int frames[] = new int[no_of_frames];

        for(int i=0; i<no_of_frames; i++)
            frames[i] = -1;
        
        int pageFault = 0;
        LinkedList<Integer> order = new LinkedList<Integer>();
        for(int page: referenceString){
            int index = contains(page, frames);
            if(index == -1){
                pageFault++;
                if(order.size() < no_of_frames){
                    frames[order.size()] = page;
                    order.add(order.size());
                    continue;
                }
                int current_replace_index = order.pollFirst();
                frames[current_replace_index] = page;
                order.add(current_replace_index);
            }
        }

        System.out.println("No of PageFault: "+pageFault);
        

    }
}
