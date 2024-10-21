/*--------------------------------------------------------------------------------
PRAJWAL AJIT RAJPUT
TE B 28
Write a program to simulate Memory placement strategies – best fit, first fit, next fit and worst fit.
--------------------------------------------------------------------------------*/

import java.util.Scanner;

public class mem {

    // First Fit strategy
    public static void firstFit(int[] blocks, int[] processes) {
        int[] allocation = initializeAllocation(processes.length);

        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    allocation[i] = j;
                    blocks[j] -= processes[i];
                    break;
                }
            }
        }
        printAllocation("First Fit", allocation);
    }

    // Next Fit strategy
    public static void nextFit(int[] blocks, int[] processes) {
        int[] allocation = initializeAllocation(processes.length);
        int j = 0;

        for (int i = 0; i < processes.length; i++) {
            
            while (j<=processes.length) {
                if (blocks[j] >= processes[i]) {
                    allocation[i] = j;
                    blocks[j] -= processes[i];
                    break;
                }
                
                if (j == processes.length) {
                    break; // No suitable block found
                }
				j=j+1;
            }
        }
        printAllocation("Next Fit", allocation);
    }

    // Best Fit strategy
    public static void bestFit(int[] blocks, int[] processes) {
        int[] allocation = initializeAllocation(processes.length);

        for (int i = 0; i < processes.length; i++) {
            int bestIdx = -1;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    if (bestIdx == -1 || blocks[j] < blocks[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blocks[bestIdx] -= processes[i];
            }
        }
        printAllocation("Best Fit", allocation);
    }

    // Worst Fit strategy
    public static void worstFit(int[] blocks, int[] processes) {
        int[] allocation = initializeAllocation(processes.length);

        for (int i = 0; i < processes.length; i++) {
            int worstIdx = -1;
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    if (worstIdx == -1 || blocks[j] > blocks[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blocks[worstIdx] -= processes[i];
            }
        }
        printAllocation("Worst Fit", allocation);
    }

    // Helper method to initialize the allocation array with -1
    public static int[] initializeAllocation(int size) {
        int[] allocation = new int[size];
        for (int i = 0; i < size; i++) {
            allocation[i] = -1; // Not allocated
        }
        return allocation;
    }

    // Helper method to print allocation results
    public static void printAllocation(String strategy, int[] allocation) {
        System.out.println("\n" + strategy + " Allocation:");
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] != -1) {
                System.out.println("Process " + (i + 1) + " -> Block " + (allocation[i] + 1));
            } else {
                System.out.println("Process " + (i + 1) + " -> Not allocated");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of blocks and their sizes
        System.out.print("Enter the number of blocks: ");
        int blockCount = scanner.nextInt();
        int[] blocks = new int[blockCount];
        System.out.println("Enter the sizes of the blocks:");
        for (int i = 0; i < blockCount; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blocks[i] = scanner.nextInt();
        }

        // Input number of processes and their sizes
        System.out.print("\nEnter the number of processes: ");
        int processCount = scanner.nextInt();
        int[] processes = new int[processCount];
        System.out.println("Enter the sizes of the processes:");
        for (int i = 0; i < processCount; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            processes[i] = scanner.nextInt();
        }

        int choice;
        do {
            System.out.println("\nMemory Allocation Strategies Menu:");
            System.out.println("1. First Fit");
            System.out.println("2. Next Fit");
            System.out.println("3. Best Fit");
            System.out.println("4. Worst Fit");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    firstFit(blocks.clone(), processes);
                    break;
                case 2:
                    nextFit(blocks.clone(), processes);
                    break;
                case 3:
                    bestFit(blocks.clone(), processes);
                    break;
                case 4:
                    worstFit(blocks.clone(), processes);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}


/*OUTPUT
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ javac mem.java
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ java mem      
Enter the number of blocks: 5
Enter the sizes of the blocks:
Block 1: 100
Block 2: 500
Block 3: 200
Block 4: 300
Block 5: 600

Enter the number of processes: 4
Enter the sizes of the processes:
Process 1: 212
Process 2: 417
Process 3: 112
Process 4: 426

Memory Allocation Strategies Menu:
1. First Fit
2. Next Fit
3. Best Fit
4. Worst Fit
5. Exit
Enter your choice (1-5): 1

First Fit Allocation:
Process 1 -> Block 2
Process 2 -> Block 5
Process 3 -> Block 2
Process 4 -> Not allocated

Memory Allocation Strategies Menu:
1. First Fit
2. Next Fit
3. Best Fit
4. Worst Fit
5. Exit
Enter your choice (1-5): 2

Next Fit Allocation:
Process 1 -> Block 2
Process 2 -> Block 5
Process 3 -> Block 5
Process 4 -> Not allocated

Memory Allocation Strategies Menu:
1. First Fit
2. Next Fit
3. Best Fit
4. Worst Fit
5. Exit
Enter your choice (1-5): 3

Best Fit Allocation:
Process 1 -> Block 4
Process 2 -> Block 2
Process 3 -> Block 3
Process 4 -> Block 5

Memory Allocation Strategies Menu:
1. First Fit
2. Next Fit
3. Best Fit
4. Worst Fit
5. Exit
Enter your choice (1-5): 4

Worst Fit Allocation:
Process 1 -> Block 5
Process 2 -> Block 2
Process 3 -> Block 5
Process 4 -> Not allocated

Memory Allocation Strategies Menu:
1. First Fit
2. Next Fit
3. Best Fit
4. Worst Fit
5. Exit
Enter your choice (1-5): 5
Exiting...*/
