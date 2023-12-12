class Optimal {
    public static int nextOccurence(int start_idx, int search, int arr[]){
        for (int i=start_idx; i<arr.length; i++) {
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
        
        for(int i=0; i < referenceString.length; i++){
            int page = referenceString[i];
            int index = nextOccurence(0, page, frames);
            if(index == -1){
                pageFault++;
                if(i<no_of_frames){
                    frames[i] = page;
                    continue;
                }
                int current_replace_index = 0;
                int maxDistance = (int)Double.NEGATIVE_INFINITY;
                for(int j=0; j<no_of_frames; j++){
                    int nextOccurance = nextOccurence(i+1, frames[j], referenceString);
                    if(nextOccurance == -1){
                        maxDistance = (int)Double.POSITIVE_INFINITY;
                        current_replace_index = j;
                    }else if(nextOccurance > maxDistance){
                        maxDistance = nextOccurance;
                        current_replace_index = j;
                    }
                }
                frames[current_replace_index] = page;
            }
        }

        System.out.println("No of PageFault: "+pageFault);
        

    }
}
