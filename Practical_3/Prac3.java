/**
 * First come First Serve (FCFS) CPU scheduling Algorithm
 */
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

class Prac3{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int at[] = new int[n];
        int bt[] = new int[n];
        Process processes[] = new Process[n];
        for(int i=0; i<n; i++){
            processes[i] = new Process();
            processes[i].id = i;
            System.out.print("Enter at["+i+"]: ");
            processes[i].at = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            System.out.print("Enter bt["+i+"]: ");
            processes[i].bt = sc.nextInt();
        }

        FCFS F1 = new FCFS(processes);
        F1.runFCFS();
        F1.printData();
    }
}

class Process{
    int id;
    int at;
    int bt;
    int wt;
    int tt;
}

class FCFS{
    private Process processes[];
    private int time = 0;

    FCFS(Process p[]){
        processes = p;
        Arrays.sort(processes, (Process p1, Process p2)->p1.at - p2.at);

    }

    public void runFCFS(){
        time = 0;
        Queue<Process> rq = new LinkedList<Process>();
        for(int i=0; i< processes.length; i++){
            rq.add(processes[i]);
        }

        while(rq.size() > 0){
            if(rq.peek().at > time){
                time = rq.peek().at;
            }
            Process curr = rq.remove();
            curr.wt = time - curr.at;
            time+=curr.bt;
            curr.tt = time - curr.at;
        }
    }

    public void printData(){
        System.out.println("Id\tAT\tBT\tWT\tTT");
        for(int i=0; i<processes.length; i++){
            System.out.println(processes[i].id +"\t"+ processes[i].at + "\t"+processes[i].bt + "\t"+processes[i].wt + "\t"+processes[i].tt);
        }
    }

}