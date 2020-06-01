/*Name           - Chanuka Nimsara Mathagadeera
IIT Student Id - 2017388
UOW Id         - w1698507*/

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Tester {
    private static int capacity;
    private static int nodes;
    private static int [][] arr2;
    static Scanner sc1 = new Scanner(System.in);
    private static int select;

    public static void main (String[] args) throws java.lang.Exception {
        do{
            System.out.println("1 - Enter nodes manually \n" +
                    "2 - Calculate nodes in randomly ");

            select = sc1.nextInt();

            if (select == 1) {
                System.out.println("Enter nodes");
                nodes = sc1.nextInt();
                calculate();
                break;
            }

            if (select == 2) {
                int minNodes = 6;
                int maxNodes = 12;
                nodes = (int) (Math.random() * ((maxNodes - minNodes) + 1)) + minNodes;
                calculate();
                break;

            } else{
                System.err.println("Invalid Input...!!");
            }
        }while (!(select==1) || !(select==2));
    }

    public static int[][] getArr2() {
        return arr2;
    }

    public static int getNodes() {
        return nodes;
    }

    public static void calculate(){
        System.out.println( " Nodes :" +nodes);
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] arr1 = new int[nodes];
        arr2 = new int[nodes][nodes];
        int maxCapacity = 20;
        int minCapacity = 5;

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                capacity = (int) (Math.random() * ((maxCapacity - minCapacity) + 1)) + minCapacity;
                if (j == 0) {
                    capacity = 0;
                }
                if (i == nodes - 1) {
                    capacity = 0;
                }
                if (i == j) {
                    capacity = 0;
                }
                arr1[j] = capacity;
            }
            arr2[i] = arr1.clone();
        }

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                System.out.printf("%6d", arr2[i][j]);
            }
            System.out.println();
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        long startTime = System.nanoTime();
        MaxFlow m1 = new MaxFlow();
        System.out.println("The Possible Max Flow is :"+m1.fordFulkerson(arr2, 0, nodes - 1));
        long endTime = System.nanoTime();
        long timeElapsed = endTime-startTime;
        System.out.println("Execution time in nanoseconds : " + +timeElapsed);
        System.out.println("Execution time in milliseconds : " + +timeElapsed / 1000000);
        JGraphXAdapter.myGraph();
    }
}

