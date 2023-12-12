/**
 * Study and implementation First Fit memory allocation methods
 */
class FirstFit{
    public static void main(String[] args) {
        int no_of_blocks = 5;
        int no_of_process = 4;
        int blocks[]   = {100, 500, 200, 300, 600};
        int processes[] = {212, 417, 112, 426};
        boolean blocksUsed[] = new boolean[no_of_blocks];
        int assignedProcess[] = new int[no_of_process];

        for (int i=0; i<no_of_process; i++)
            assignedProcess[i] = -1;

        for (int i=0; i<no_of_process; i++) {
            for(int j=0; j<no_of_blocks; j++){
                if(!blocksUsed[j] && processes[i] <= blocks[j]){
                    blocksUsed[j] = true;
                    assignedProcess[i] = j;
                    break;
                }
            }
        }

        System.out.println("Process No\tProcess Size\tBlock No");
        for (int i=0; i<no_of_process; i++){
            System.out.println(i+1 + "\t" + processes[i] + "\t" + (assignedProcess[i] == -1 ? "Not Allocated" : (assignedProcess[i] + 1)));
        }
    }
}