/**
 * Study and implementation of Round Robin(RR) CPU scheduling Algorithm
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

class Prac5{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        Process processes[] = new Process[n];

        for(int i=0; i<n; i++){
            processes[i] = new Process();
            processes[i].id = i;
            System.out.print("at["+i+"]: ");
            processes[i].at = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            System.out.print("bt["+i+"]: ");
            processes[i].bt = sc.nextInt();
        }

        RR r = new RR(processes, quantum);
        r.run();
        r.printData();
    }
}
class RR{
    private Process processes[];
    private int quantum;
    RR(Process p[], int q){
        quantum = q;
        processes = p;
        Arrays.sort(processes, (Process p1, Process p2)->p1.at - p2.at);
    }
    public void run(){
        Queue<Process> rq = new LinkedList<Process>();
        int total_process_executed = 0;
        int process_to_be_executed = 0;
        Process add = null;
        int time = 0;
        while(total_process_executed < processes.length){
            // Add With Respect To Time
            for(;process_to_be_executed < processes.length; process_to_be_executed++){
                if(processes[process_to_be_executed].at <= time)
                    rq.add(processes[process_to_be_executed]);
                else
                    break;
            }

            if(add != null){
                rq.add(add);
            }

            if(rq.size() == 0){
                // Add With Respect To Time
                if(process_to_be_executed < processes.length){
                    time = processes[process_to_be_executed].at;
                }
            }

            Process curr = rq.remove();
            int execution_time = Math.min(quantum, curr.bt - curr.trt);
            curr.trt += execution_time;
            time += execution_time;
            
            // Add Curr Process
            if(curr.trt < curr.bt)
                add = curr;
            else{
                add = null;
                curr.tt = time - curr.at;
                curr.wt = curr.tt - curr.bt;
                total_process_executed++;
            }
        }
    }

    public void printData(){
        System.out.println("Id\tAT\tBT\tWT\tTT");
        Arrays.sort(processes, (Process p1, Process p2)->p1.id - p2.id);
        for(Process p: processes){
            System.out.println(p.id + "\t"+p.at+"\t"+p.bt+"\t"+p.wt+"\t"+p.tt);
        }
    }
}
class Process{
    int id;
    int at;
    int bt;
    int wt;
    int tt;
    int trt; // Total Running Time
}