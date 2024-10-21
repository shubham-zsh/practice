/*--------------------------------------------------------------------------------
PRAJWAL AJIT RAJPUT
TE B 28
Write a program to simulate CPU Scheduling Algorithms: FCFS, SJF (Preemptive), Priority (Non-Preemptive) and Round Robin (Preemptive)
--------------------------------------------------------------------------------*/

import java.util.*;

public class cpu {

    static void fcfs(Scanner sc) {
        System.out.println("*** Shortest Job First Scheduling (Non Preemptive) ***");
        System.out.print("Enter no of process:");
        int n = sc.nextInt();
        int process[] = new int[n];
        int arrivaltime[] = new int[n + 1];
        int burstTime[] = new int[n + 1];
        int completionTime[] = new int[n];
        int TAT[] = new int[n];
        int waitingTime[] = new int[n];
        int temp, k = 1, time = 0;
        int min = 0, sum = 0, compTotal = 0;
        float avgwt = 0, avgTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println(" ");
            process[i] = (i + 1);
            System.out.print("Enter Arrival Time for processor " + (i + 1) + ":");
            arrivaltime[i] = sc.nextInt();
            System.out.print("Enter Burst Time for processor " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrivaltime[i] < arrivaltime[j]) {
                    temp = process[j];
                    process[j] = process[i];
                    process[i] = temp;
                    temp = arrivaltime[j];
                    arrivaltime[j] = arrivaltime[i];
                    arrivaltime[i] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[i];
                    burstTime[i] = temp;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            time = time + burstTime[j];
            min = burstTime[k];
            for (int i = k; i < n; i++) {
                if (time >= arrivaltime[i] && burstTime[i] < min) {
                    temp = process[k];
                    process[k] = process[i];
                    process[i] = temp;
                    temp = arrivaltime[k];
                    arrivaltime[k] = arrivaltime[i];
                    arrivaltime[i] = temp;
                    temp = burstTime[k];
                    burstTime[k] = burstTime[i];
                    burstTime[i] = temp;
                }
            }
            k++;
        }
        waitingTime[0] = 0;
        for (int i = 1; i < n; i++) {
            sum = sum + burstTime[i - 1];
            waitingTime[i] = sum - arrivaltime[i];
            avgwt += waitingTime[i];
        }
        for (int i = 0; i < n; i++) {
            compTotal = compTotal + burstTime[i];
            completionTime[i] = compTotal;
            TAT[i] = compTotal - arrivaltime[i];
            avgTAT += TAT[i];
        }

        System.out.println("\n*** Shortest Job First Scheduling (Non Preemptive) ***");
        System.out.println("Processor\tArrival time\tBrust time\tCompletion Time\t\tTurn around time\tWaiting time");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" + arrivaltime[i] + "ms\t\t" + burstTime[i] + "ms\t\t"
                    + completionTime[i] + "ms\t\t\t" + TAT[i] + "ms\t\t\t" + waitingTime[i] + "ms");
        }
        avgTAT /= n;
        avgwt /= n;
        System.out.println("\nAverage turn around time is " + avgTAT);
        System.out.println("Average waiting time is " + avgwt);
    }

    static void sjf(Scanner sc) {
        System.out.println("*** Shortest Job First Scheduling (Preemptive) ***");
        System.out.print("Enter no of process:");
        int n = sc.nextInt();
        int process[] = new int[n];
        int arrivaltime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int TAT[] = new int[n];
        int waitingTime[] = new int[n];
        int visit[] = new int[n];
        int remburstTime[] = new int[n];
        int temp, start = 0, total = 0;
        float avgwt = 0, avgTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println(" ");
            process[i] = (i + 1);
            System.out.print("Enter Arrival Time for processor " + (i + 1) + ":");
            arrivaltime[i] = sc.nextInt();
            System.out.print("Enter Burst Time for processor " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remburstTime[i] = burstTime[i];
            visit[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arrivaltime[i] < arrivaltime[j]) {
                    temp = process[j];
                    process[j] = process[i];
                    process[i] = temp;
                    temp = arrivaltime[j];
                    arrivaltime[j] = arrivaltime[i];
                    arrivaltime[i] = temp;
                    temp = remburstTime[j];
                    remburstTime[j] = remburstTime[i];
                    remburstTime[i] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[i];
                    burstTime[i] = temp;
                }
            }
        }
        while (true) {
            int min = 99, c = n;
            if (total == n) {
                break;
            }
            for (int i = 0; i < n; i++) {
                if ((arrivaltime[i] <= start) && (visit[i] == 0) && (burstTime[i] < min)) {
                    min = burstTime[i];
                    c = i;
                }
            }

            if (c == n) {
                start++;
            } else {
                burstTime[c]--;
                start++;
                if (burstTime[c] == 0) {
                    completionTime[c] = start;
                    visit[c] = 1;
                    total++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            TAT[i] = completionTime[i] - arrivaltime[i];
            waitingTime[i] = TAT[i] - remburstTime[i];
            avgwt += waitingTime[i];
            avgTAT += TAT[i];
        }
        System.out.println("*** Shortest Job First Scheduling (Preemptive) ***");
        System.out.println("Processor\tArrival time\tBrust time\tCompletion Time\t\tTurn around time\tWaiting time");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" + arrivaltime[i] + "ms\t\t" + remburstTime[i] + "ms\t\t"
                    + completionTime[i] + "ms\t\t\t" + TAT[i] + "ms\t\t\t" + waitingTime[i] + "ms");
        }

        avgTAT /= n;
        avgwt /= n;

        System.out.println("\nAverage turn around time is " + avgTAT);
        System.out.println("Average waiting time is " + avgwt);

    }


    static void priority(Scanner sc) {
        System.out.println("*** Priority Scheduling (Preemptive) ***");

        System.out.print("Enter Number of Processes: ");
        int n = sc.nextInt();

        // Arrays to store process details
        int[] process = new int[n];
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] burstTimeCopy = new int[n];  // For original burst times
        int[] completionTime = new int[n];
        int[] priority = new int[n];
        int[] TAT = new int[n];  // Turnaround Time
        int[] waitingTime = new int[n];

        int completed = 0, time = 0, minPriorityIndex = -1;
        double avgWT = 0, avgTAT = 0;

        // Input: Arrival Time, Burst Time, and Priority for each process
        for (int i = 0; i < n; i++) {
            process[i] = i + 1;
            System.out.println("\nProcess P" + (i + 1) + ":");
            System.out.print("Enter Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Enter Burst Time: ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter Priority: ");
            priority[i] = sc.nextInt();
            burstTimeCopy[i] = burstTime[i];  // Store burst time for later use
        }

        // Scheduling process
        while (completed < n) {
            minPriorityIndex = -1;

            // Find the process with the highest priority (lowest priority value) that is ready
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= time && burstTime[i] > 0) {
                    if (minPriorityIndex == -1 || priority[i] < priority[minPriorityIndex]) {
                        minPriorityIndex = i;
                    }
                }
            }

            if (minPriorityIndex == -1) {
                // If no process is ready, increment time to avoid infinite loop
                time++;
                continue;
            }

            // Execute the selected process for 1 unit of time
            burstTime[minPriorityIndex]--;

            // If the process finishes execution
            if (burstTime[minPriorityIndex] == 0) {
                completed++;  // Increment completed process count
                int endTime = time + 1;  // Completion time for the current process
                completionTime[minPriorityIndex] = endTime;

                // Calculate Turnaround Time and Waiting Time
                TAT[minPriorityIndex] = endTime - arrivalTime[minPriorityIndex];
                waitingTime[minPriorityIndex] = TAT[minPriorityIndex] - burstTimeCopy[minPriorityIndex];
            }

            // Increment time after each execution unit
            time++;
        }

        // Calculate average Turnaround Time and Waiting Time
        for (int i = 0; i < n; i++) {
            avgTAT += TAT[i];
            avgWT += waitingTime[i];
        }
        avgTAT /= n;
        avgWT /= n;

        // Display results
        System.out.println("\n*** Priority Scheduling (Preemptive) Results ***");
        System.out.println("Process\tAT\tBT\tPri\tCT\tTAT\tWT");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\t%d\n", 
                process[i], arrivalTime[i], burstTimeCopy[i], priority[i], 
                completionTime[i], TAT[i], waitingTime[i]);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);
        System.out.printf("Average Turnaround Time: %.2f\n", avgTAT);
    }


    static void RR(Scanner sc) {
        System.out.println("*** Priority Scheduling (Preemptive) ***");
        System.out.print("Enter Number of Process: ");
        int n = sc.nextInt();
        int process[] = new int[n];
        int arrivaltime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int priority[] = new int[n + 1];
        int TAT[] = new int[n];
        int waitingTime[] = new int[n];
        int burstTimecopy[] = new int[n];
        int min = 0, count = 0;
        int temp, time = 0, end;
        double avgWT = 0, avgTAT = 0;
        for (int i = 0; i < n; i++) {
            process[i] = (i + 1);
            System.out.println("");
            System.out.print("Enter Arrival Time for processor " + (i + 1) + ":");
            arrivaltime[i] = sc.nextInt();
            System.out.print("Enter Burst Time for processor " + (i + 1) + " : ");
            burstTime[i] = sc.nextInt();
            System.out.print("Enter Priority for " + (i + 1) + " process: ");
            priority[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivaltime[i] > arrivaltime[j]) {
                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                    temp = arrivaltime[j];
                    arrivaltime[j] = arrivaltime[i];
                    arrivaltime[i] = temp;
                    temp = priority[j];
                    priority[j] = priority[i];
                    priority[i] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[i];
                    burstTime[i] = temp;
                }
                if (arrivaltime[i] == arrivaltime[j] && priority[j] > priority[i]) {
                    temp = process[i];
                    process[i] = process[j];
                    process[j] = temp;
                    temp = arrivaltime[j];
                    arrivaltime[j] = arrivaltime[i];
                    arrivaltime[i] = temp;
                    temp = priority[j];
                    priority[j] = priority[i];
                    priority[i] = temp;
                    temp = burstTime[j];
                    burstTime[j] = burstTime[i];
                    burstTime[i] = temp;
                }
            }
        }
        System.arraycopy(burstTime, 0, burstTimecopy, 0, n);
        priority[n] = 999;
        for (time = 0; count != n; time++) {
            min = n;
            for (int i = 0; i < n; i++) {
                if (arrivaltime[i] <= time && priority[i] < priority[min] && burstTime[i] > 0) {
                    min = i;
                }
            }
            burstTime[min]--;
            if (burstTime[min] == 0) {
                count++;
                end = time + 1;
                completionTime[min] = end;
                waitingTime[min] = end - arrivaltime[min] - burstTimecopy[min];
                TAT[min] = end - arrivaltime[min];
            }
        }

        for (int i = 0; i < n; i++) {
            avgTAT += TAT[i];
            avgWT += waitingTime[i];
        }
        System.out.println("\n*** Priority Scheduling (Preemptive) ***");
        System.out.println("Processor\tArrival time\tBrust time\tCompletion Time\t\tTurn around time\tWaiting time");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + process[i] + "\t\t" + arrivaltime[i] + "ms\t\t" + burstTimecopy[i] + "ms\t\t"
                    + completionTime[i] + "ms\t\t\t" + TAT[i] + "ms\t\t\t" + waitingTime[i] + "ms");

        }
        avgWT /= n;
        avgTAT /= n;
        System.out.println("\nAverage Wating Time: " + avgWT);
        System.out.println("Average Turn Around Time: " + avgTAT);

    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. FCFS");
            System.out.println("2. SJF");
            System.out.println("3. PRIORITY");
            System.out.println("4. ROUND ROBIN");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    fcfs(sc);
                    break;
                case 2:
                    sjf(sc);
                    break;
                case 3:
                    priority(sc);
                    break;
                case 4:
                    RR(sc);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        sc.close();
    
    }
}


/*OUTPUT
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ javac cpu.java
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ java cpu      
1. FCFS
2. SJF
3. PRIORITY
4. ROUND ROBIN
5. Exit
Enter your choice (1-5): 1
*** Shortest Job First Scheduling (Non Preemptive) ***
Enter no of process:4
 
Enter Arrival Time for processor 1:1
Enter Burst Time for processor 1: 9
 
Enter Arrival Time for processor 2:2
Enter Burst Time for processor 2: 4
 
Enter Arrival Time for processor 3:5
Enter Burst Time for processor 3: 56
 
Enter Arrival Time for processor 4:24
Enter Burst Time for processor 4: 7

*** Shortest Job First Scheduling (Non Preemptive) ***
Processor       Arrival time    Brust time      Completion Time         Turn around time        Waiting time
----------------------------------------------------------------------------------------------------------
P1              1ms             9ms             9ms                     8ms                     0ms
P2              2ms             4ms             13ms                    11ms                    7ms
P3              5ms             56ms            69ms                    64ms                    8ms
P4              24ms            7ms             76ms                    52ms                    45ms

Average turn around time is 33.75
Average waiting time is 15.0
1. FCFS
2. SJF
3. PRIORITY
4. ROUND ROBIN
5. Exit
Enter your choice (1-5): 2
*** Shortest Job First Scheduling (Preemptive) ***
Enter no of process:2
 
Enter Arrival Time for processor 1:24
Enter Burst Time for processor 1: 24
 
Enter Arrival Time for processor 2:0
Enter Burst Time for processor 2: 20
*** Shortest Job First Scheduling (Preemptive) ***
Processor       Arrival time    Brust time      Completion Time         Turn around time        Waiting time
----------------------------------------------------------------------------------------------------------
P2              0ms             20ms            20ms                    20ms                    0ms
P1              24ms            24ms            48ms                    24ms                    0ms

Average turn around time is 22.0
Average waiting time is 0.0
1. FCFS
2. SJF
3. PRIORITY
4. ROUND ROBIN
5. Exit
Enter your choice (1-5): 3
*** Priority Scheduling (Preemptive) ***
Enter Number of Processes: 4

Process P1:
Enter Arrival Time: 1
Enter Burst Time: 23
Enter Priority: 3

Process P2:
Enter Arrival Time: 0
Enter Burst Time: 5
Enter Priority: 7

Process P3:
Enter Arrival Time: 0
Enter Burst Time: 7
Enter Priority: 4

Process P4:
Enter Arrival Time: 8
Enter Burst Time: 6
Enter Priority: 1

*** Priority Scheduling (Preemptive) Results ***
Process AT      BT      Pri     CT      TAT     WT
--------------------------------------------------
P1      1       23      3       30      29      6
P2      0       5       7       41      41      36
P3      0       7       4       36      36      29
P4      8       6       1       14      6       0

Average Waiting Time: 17.75
Average Turnaround Time: 28.00
1. FCFS
2. SJF
3. PRIORITY
4. ROUND ROBIN
5. Exit
Enter your choice (1-5): 4
*** Priority Scheduling (Preemptive) ***
Enter Number of Process:
3

Enter Arrival Time for processor 1:5
Enter Burst Time for processor 1 : 8
Enter Priority for 1 process: 6

Enter Arrival Time for processor 2:2
Enter Burst Time for processor 2 : 56
Enter Priority for 2 process: 3

Enter Arrival Time for processor 3:0
Enter Burst Time for processor 3 : 50
Enter Priority for 3 process: 1

*** Priority Scheduling (Preemptive) ***
Processor       Arrival time    Brust time      Completion Time         Turn around time        Waiting time
----------------------------------------------------------------------------------------------------------
P3              0ms             50ms            50ms                    50ms                    0ms
P2              2ms             56ms            106ms                   104ms                   48ms
P1              5ms             8ms             114ms                   109ms                   101ms

Average Wating Time: 49.666666666666664
Average Turn Around Time: 87.66666666666667
1. FCFS
2. SJF
3. PRIORITY
4. ROUND ROBIN
5. Exit
Enter your choice (1-5): 5
Exiting...
*/
