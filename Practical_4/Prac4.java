/**
 * Study and implementation of Shortest Job Firs(SJF) CPU scheduling Algorithm
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

class Prac4{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
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

        SJF s = new SJF(processes);
        s.run();
        s.printData();
    }
}

class SJF{
    private Process processes[];
    private int time = 0;
    SJF(Process p[]){
        processes = p;
        Arrays.sort(processes, (Process p1, Process p2)-> p1.at - p2.at);
    }

    public void run(){
        List<Process> rq = new ArrayList<Process>();
        int total_process = processes.length;
        int process_to_be_executed = 0;
        int total_process_executed = 0;
        while(total_process_executed < total_process){
            // Set Time
            if(rq.size() == 0 && processes[process_to_be_executed].at > time)
                time = processes[process_to_be_executed].at;
            // Add Index of New Element
            for(int i=process_to_be_executed; i<total_process; i++){
                if(processes[i].at <= time){
                    rq.add(processes[i]);
                }else{
                    break;
                }
                process_to_be_executed++;
            }
            // Sort based on lowest time
            Collections.sort(rq, (Process p1, Process p2)->p1.bt - p2.bt);
            Process curr_process = rq.remove(0);
            curr_process.wt = time - curr_process.at;
            time += curr_process.bt;
            curr_process.tt = time - curr_process.at;
            total_process_executed++;
        }
    }
    public void printData(){
        System.out.println("Id\tAT\tBT\tWT\tTT");
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
}