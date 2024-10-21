/*--------------------------------------------------------------------------------
PRAJWAL AJIT RAJPUT
TE B 28
Write a program to simulate Page replacement algorithm. 
--------------------------------------------------------------------------------*/
import java.util.*;
public class page{
    static void fifo(Scanner sc){
        int noofpages, capacity, ptr = 0, hit = 0, fault = 0;
        boolean isFull = false;
        double hitRatio, faultRatio;
        System.out.print("Enter the number of pages you want to enter: ");
        noofpages = sc.nextInt();
        int pages[] = new int[noofpages];
        for (int i = 0; i < noofpages; i++) {
            pages[i] = sc.nextInt();
        }
        System.out.print("Enter the capacity of frame: ");
        capacity = sc.nextInt();
        int frame[] = new int[capacity];
        int table[][] = new int[noofpages][capacity];
        for (int i = 0; i < capacity; i++) {
            frame[i] = -1;
        }
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < noofpages; i++) {
            int search = -1;
            for (int j = 0; j < capacity; j++) {
                if (frame[j] == pages[i]) {
                    search = j;
                    hit++;
                    System.out.printf("%4s", "H");
                    break;
                }
            }
            if (search == -1) {
                if (isFull) {
                    int[] index = new int[capacity];
                    boolean[] index_flag = new boolean[capacity];
                    for (int j = i + 1; j < noofpages; j++) {
                        for (int k = 0; k < capacity; k++) {
                            if ((pages[j] == frame[k]) &&
                                    (!index_flag[k])) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    ptr = 0;
                    if (max == 0)
                        max = 200;
                    for (int j = 0; j < capacity; j++) {
                        if (index[j] == 0)
                            index[j] = 200;
                        if (index[j] > max) {
                            max = index[j];
                            ptr = j;
                        }
                    }
                }
                frame[ptr] = pages[i];
                fault++;
                System.out.printf("%4s", "F");
                if (!isFull) {
                    ptr++;
                    if (ptr == capacity) {
                        ptr = 0;
                        isFull = true;
                    }
                }
            }
            System.arraycopy(frame, 0, table[i], 0, capacity);
        }
        System.out.println("\n----------------------------------------------------------------------");
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < noofpages; j++)
                System.out.printf("%3d ", table[j][i]);
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------");
        hitRatio = ((double) hit / noofpages) * 100;
        faultRatio = ((double) fault / noofpages) * 100;
        System.out.println("Page Fault: " + fault + "\nPage Hit: " + hit);
        System.out.printf("Hit Ratio:%.2f \nFault Ratio:%.2f ", hitRatio, faultRatio);
    }
    static void lru(Scanner sc){
		ArrayList<Integer> arr = new ArrayList<>();
		int noofpages, capacity, hit = 0, fault = 0, index = 0;
		boolean isFull = false;
		double hitRatio, faultRatio;
		System.out.print("Enter the number of pages you want to enter: ");
		noofpages = sc.nextInt();
		int pages[] = new int[noofpages];
		for (int i = 0; i < noofpages; i++) {
			pages[i] = sc.nextInt();
		}
		System.out.print("Enter the capacity of frame: ");
		capacity = sc.nextInt();
		int frame[] = new int[capacity];
		int table[][] = new int[noofpages][capacity];
		for (int i = 0; i < capacity; i++) {
			frame[i] = -1;
		}
		System.out.println("----------------------------------------------------------------------");
		for (int i = 0; i < noofpages; i++) {
			if (arr.contains(pages[i])) {
				arr.remove((Integer) pages[i]);
			}
			arr.add(pages[i]);
			int search = -1;
			for (int j = 0; j < capacity; j++) {
				if (frame[j] == pages[i]) {
					search = j;
					hit++;
					System.out.printf("%4s", "H");
					break;
				}
			}
			if (search == -1) {
				if (isFull) {
					int min_loc = noofpages;
					for (int j = 0; j < capacity; j++) {
						if (arr.contains(frame[j])) {
							int temp = arr.indexOf(frame[j]);
							if (temp < min_loc) {
								min_loc = temp;
								index = j;
							}
						}
					}
				}
				frame[index] = pages[i];
				fault++;
				System.out.printf("%4s", "F");
				index++;
				if (index == capacity) {
					index = 0;
					isFull = true;
				}
			}
			System.arraycopy(frame, 0, table[i], 0, capacity);
		}
		System.out.println("\n----------------------------------------------------------------------");
		for (int i = 0; i < capacity; i++) {
			for (int j = 0; j < noofpages; j++)
				System.out.printf("%3d ", table[j][i]);
			System.out.println();
		}

		System.out.println("----------------------------------------------------------------------");
		hitRatio = ((double) hit / noofpages) * 100;
		faultRatio = ((double) fault / noofpages) * 100;
		System.out.println("Page Fault: " + fault + "\nPage Hit: " + hit);
		System.out.printf("Hit Ratio:%.2f \nFault Ratio:%.2f ", hitRatio, faultRatio);

    }
    static  void optimal(Scanner sc){
        int noofpages, capacity, ptr = 0, hit = 0, fault = 0;
        boolean isFull = false;
        double hitRatio, faultRatio;
        System.out.print("Enter the number of pages you want to enter: ");
        noofpages = sc.nextInt();
        int pages[] = new int[noofpages];
        for (int i = 0; i < noofpages; i++) {
            pages[i] = sc.nextInt();
        }
        System.out.print("Enter the capacity of frame: ");
        capacity = sc.nextInt();
        int frame[] = new int[capacity];
        int table[][] = new int[noofpages][capacity];
        for (int i = 0; i < capacity; i++) {
            frame[i] = -1;
        }
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < noofpages; i++) {
            int search = -1;
            for (int j = 0; j < capacity; j++) {
                if (frame[j] == pages[i]) {
                    search = j;
                    hit++;
                    System.out.printf("%4s", "H");
                    break;
                }
            }
            if (search == -1) {
                if (isFull) {
                    int[] index = new int[capacity];
                    boolean[] index_flag = new boolean[capacity];
                    for (int j = i + 1; j < noofpages; j++) {
                        for (int k = 0; k < capacity; k++) {
                            if ((pages[j] == frame[k]) &&
                                    (!index_flag[k])) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    ptr = 0;
                    if (max == 0)
                        max = 200;
                    for (int j = 0; j < capacity; j++) {
                        if (index[j] == 0)
                            index[j] = 200;
                        if (index[j] > max) {
                            max = index[j];
                            ptr = j;
                        }
                    }
                }
                frame[ptr] = pages[i];
                fault++;
                System.out.printf("%4s", "F");
                if (!isFull) {
                    ptr++;
                    if (ptr == capacity) {
                        ptr = 0;
                        isFull = true;
                    }
                }
            }
            System.arraycopy(frame, 0, table[i], 0, capacity);
        }
        System.out.println("\n----------------------------------------------------------------------");
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < noofpages; j++)
                System.out.printf("%3d ", table[j][i]);
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------");
        hitRatio = ((double) hit / noofpages) * 100;
        faultRatio = ((double) fault / noofpages) * 100;
        System.out.println("Page Fault: " + fault + "\nPage Hit: " + hit);
        System.out.printf("Hit Ratio:%.2f \nFault Ratio:%.2f ", hitRatio, faultRatio);
    }
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int ch;
        do { 
            System.out.println("\n\n-------------------------------\n1.FCFS\n2.LRU\n3.Optimal\n4.Exit\nEnter your choice : ");
            ch = sc.nextInt();
            switch (ch) {
                case 1:fifo(sc);
                    break;
                case 2:lru(sc);
                    break;
                case 3:optimal(sc);
                    break;
                case 4:System.out.println("\n\n EXit.....");
                    break;
                default:System.out.println("Enter valid");
                
            }
            
        } while (ch!=4);
        sc.close();

    }
}


/*OUTPUT
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ javac page.java
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$ java page      


-------------------------------
1.FCFS
2.LRU
3.Optimal
4.Exit
Enter your choice :
1
Enter the number of pages you want to enter: 10
1               
2  
5
7
3
4
9
6
7
5
Enter the capacity of frame: 3
----------------------------------------------------------------------
   F   F   F   F   F   F   F   F   H   H
----------------------------------------------------------------------
  1   1   1   7   7   7   7   7   7   7 
 -1   2   2   2   3   4   9   6   6   6 
 -1  -1   5   5   5   5   5   5   5   5
----------------------------------------------------------------------
Page Fault: 8
Page Hit: 2
Hit Ratio:20.00
Fault Ratio:80.00

-------------------------------
1.FCFS
2.LRU
3.Optimal
4.Exit
Enter your choice :
2
Enter the number of pages you want to enter: 10
7
8
9
5
5

14
7
2
1
0
Enter the capacity of frame: 3
----------------------------------------------------------------------
   F   F   F   F   H   F   F   F   F   F
----------------------------------------------------------------------
  7   7   7   5   5   5   5   2   2   2 
 -1   8   8   8   8  14  14  14   1   1 
 -1  -1   9   9   9   9   7   7   7   0 
----------------------------------------------------------------------
Page Fault: 9
Page Hit: 1
Hit Ratio:10.00
Fault Ratio:90.00

-------------------------------
1.FCFS
2.LRU
3.Optimal
4.Exit
Enter your choice :
3
Enter the number of pages you want to enter: 10
7
5

6
2
1
0
5
7
6
2
Enter the capacity of frame: 3
----------------------------------------------------------------------
   F   F   F   F   F   F   H   H   F   F
----------------------------------------------------------------------
  7   7   7   7   7   7   7   7   6   2 
 -1   5   5   5   5   5   5   5   5   5 
 -1  -1   6   2   1   0   0   0   0   0
----------------------------------------------------------------------
Page Fault: 8
Page Hit: 2
Hit Ratio:20.00
Fault Ratio:80.00

-------------------------------
1.FCFS
2.LRU
3.Optimal
4.Exit
Enter your choice :
4


EXit.....
gescoe@gescoe-OptiPlex-3010:~/Desktop/TEB28$  */