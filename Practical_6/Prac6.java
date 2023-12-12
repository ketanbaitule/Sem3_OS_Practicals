/**
 * Study and stimulation of fork and kill system call
 */

import java.util.ArrayList;

class Process{
    private int id = -1;
    private Process parent_id;
    private ArrayList<Process> childProcesses = new ArrayList<>();

    Process(int _id){
        this(_id, null);
    }

    Process(int _id, Process _parent_id){
        id = _id;
        parent_id = _parent_id;
        System.out.println("Process "+id+" is created");
    }

    public int getId(){
        return id;
    }

    public Process fork(int _new_id){
        Process childProcess = new Process(_new_id, this);
        childProcesses.add(childProcess);
        return childProcess;
    }

    public void kill(boolean _cause_parent){
        // First Parent Is Killed
        if (_cause_parent) 
            System.out.println("Process "+this.id+" is killed as its parent process "+this.parent_id.getId()+" was killed");
        else
            System.out.println("Process "+this.id+" is killed.");

        // Then Child Process is Killed
        for (int i=0; i<childProcesses.size(); i++) {
            if(childProcesses.get(i).processExist())
                childProcesses.get(i).kill(true);
        }
        childProcesses.clear();
        id = -1;
    }

    public boolean processExist(){
        if (id < 0) {
            return false;
        }else{
            return true;
        }
    }

    public void listProcess(){
        if(!this.processExist())
            return ;
        if(this.parent_id == null)
            System.out.println(this.id);
        else
            System.out.println(this.id + "\t\t" + this.parent_id.getId());
        for (int i=0; i<childProcesses.size(); i++) {
            if(childProcesses.get(i).processExist())
                childProcesses.get(i).listProcess();
        }
    }
}

class ForkKill{
    public static void listProcess(Process main_processs){
        System.out.println("Listing Process: ");
        System.out.println("Process_Id\tParent_Process");
        main_processs.listProcess();
    }

    public static void main(String[] args) {
        int curr_process = 0;
        System.out.println("Creating Main Process");
        Process main_process = new Process(curr_process++);

        System.out.println("Creating 3 Child Process of Process 0.");
        Process main_child1 = main_process.fork(curr_process++);
        Process main_child2 = main_process.fork(curr_process++);
        Process main_child3 = main_process.fork(curr_process++);

        listProcess(main_process);

        System.out.println("Creating 2 Child Process of Process 1.");
        Process child1_child1 = main_child1.fork(curr_process++);
        Process child1_child2 = main_child1.fork(curr_process++);

        listProcess(main_process);

        System.out.println("Kill Process 1");
        main_child1.kill(false);
        listProcess(main_process);
    }
}