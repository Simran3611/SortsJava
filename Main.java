package com.company;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);

	System.out.println("Enter the file name: ");     
	String filename = kb.nextLine();


        LinkedList<String> list = new LinkedList<String>();
        readFromFile(filename, list);
        String[] array = list.toArray(new String[list.size()]);
        //printArray(array);

        //Tests Insertion Sort
//        printArray(insertionSort(array));

        //Tests Merge Sort
//        printArray(mergeSort(array));

//        Tests Heap Sort
//        printArray(heapSort(array));

        int number;
        String outFileInsertionSort;
        String outFileMergeSort;
        String outFileHeapSort;

        char k = 'K';
        if(filename.charAt(6) == k) {
            outFileInsertionSort = "IS" + filename.charAt(4) + filename.charAt(5) + "K.txt";
            outFileMergeSort = "MS" + filename.charAt(4) + filename.charAt(5) + "K.txt";
            outFileHeapSort = "HS" + filename.charAt(4) + filename.charAt(5) + "K.txt";
        }
        else{
            outFileInsertionSort = "IS" + filename.charAt(4) + filename.charAt(5) + filename.charAt(6) + "K.txt";
            outFileMergeSort = "MS" + filename.charAt(4) + filename.charAt(5) + filename.charAt(6) + "K.txt";
            outFileHeapSort = "HS" + filename.charAt(4) + filename.charAt(5) + filename.charAt(6) + "K.txt";
        }

        //asks user for input of which sort method to use
        System.out.println("Enter which sort you would like to perform");
        String sortMethod = kb.nextLine();

        //goes through and checks which sort method requested
        if(sortMethod.compareToIgnoreCase("Insertion Sort") == 0) {
            //Time for insertion sort
            System.out.print("Insertion sort time: ");
            long insertionTimeValue;
            long insertionStart = System.currentTimeMillis();
            array = insertionSort(array);
            long insertionEnd = System.currentTimeMillis();
            insertionTimeValue = (insertionEnd - insertionStart);
            System.out.println(insertionTimeValue + " milliseconds");
            try {
                PrintWriter out = new PrintWriter(outFileInsertionSort);
                for(int i = 0; i < array.length; i++) {
                    out.println(array[i]);
                }
                out.close();

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
                System.exit(1);
            }

        }
        else if(sortMethod.compareToIgnoreCase("Merge Sort") == 0) {
            //Time for merge sort
            System.out.print("Merge sort time: ");
            long mergeTimeValue;
            long mergeStart = System.currentTimeMillis();
            array = mergeSort(array);
            long mergeEnd = System.currentTimeMillis();
            mergeTimeValue = (mergeEnd - mergeStart);
            System.out.println(mergeTimeValue + " milliseconds");
            try {
                PrintWriter out = new PrintWriter(outFileMergeSort);
                for(int i = 0; i < array.length; i++) {
                    out.println(array[i]);
                }
                out.close();

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
                System.exit(1);
            }

        }
        else if(sortMethod.compareToIgnoreCase("Heap Sort") == 0) {
            //Time for heap sort
            System.out.print("Heap sort time: ");
            long heapTimeValue;
            long heapStart = System.currentTimeMillis();
            array = heapSort(array);
            long heapEnd = System.currentTimeMillis();
            heapTimeValue = (heapEnd - heapStart);
            System.out.println(heapTimeValue + " milliseconds");
            try {
                PrintWriter out = new PrintWriter(outFileHeapSort);
                for(int i = 0; i < array.length; i++) {
                    out.println(array[i]);
                }
                out.close();

            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
                System.exit(1);
            }
        }
        else {
            System.out.println("Entered request not able to be accepted. Try again");
            System.out.println("Insertion Sort");
            System.out.println("Merge Sort");
            System.out.println("Heap Sort");
        }


        //ARGS: -i, "filename.txt", -o, "out_name"


    }
    public static void readFromFile(String filename, LinkedList<String> list) {
        Scanner fin = null;
        int i = 0;
        try {
            fin = new Scanner(new File (filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        }
        while(fin.hasNext()) {
            list.add(fin.next());
            i++;
        }
        fin.close();
    }
    //FIX INSERTION SORT
    public static String[] insertionSort(String array[]) {
        String temp;
        for(int i=0;i< array.length;i++){
            for(int j=i+1;j< array.length;j++){
                if(array[i].compareTo(array[j])>0){
                    temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }
    public static String[] mergeSort(String [] array) {
        int length = array.length;
        if(length < 2) {
            return array; //array is sorted
            //base case
        }
        int middleElement = length/2;
        String [] leftPos = new String [middleElement]; //left position of array
        String [] rightPos = new String [length - middleElement]; //right position of array
        for(int i = 0; i < middleElement; i++) {
            leftPos[i] = array[i];
        }
        for(int j = middleElement; j < length; j++) {
            rightPos[j - middleElement] = array[j];
        }
        mergeSort(leftPos);
        mergeSort(rightPos);
        return merge(array, leftPos, rightPos);
    }
    public static String[] merge(String [] array, String [] leftPos, String [] rightPos) {
        int leftSize = leftPos.length;
        int rightSize = rightPos.length;
        int i = 0;
        int j = 0;
        int k = 0;

        while((i < leftSize) && (j < rightSize)) {
            if(leftPos[i].compareTo(rightPos[j]) < 0) {
                array[k] = leftPos[i];
                i++;
            }
            else {
                array[k] = rightPos[j];
                j++;
            }
            k++;
        }
        while(i < leftSize) {
            array[k] = leftPos[i];
            i++;
            k++;
        }
        while(j < rightSize) {
            array[k] = rightPos[j];
            j++;
            k++;
        }
        return array;
    }
    public static String[] heapSort(String array[])
    {
        int size = array.length;

        array = buildHeap(array);
        for (int i=size-1; i>=0; i--)
        {
            String temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
        return array;
    }
    public static String[] buildHeap(String array[]){
        // arrange the array to build the heap
        int size = array.length;
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(array, size, i);
        }
        return array;
    }
    public static void heapify(String array[], int size, int i)
    {
        int l = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        //left bigger than root itself
        if ((left < size) && (array[left].compareTo(array[l]) > 0)) {
            l = left;
        }

        //right > largest value
        if ((right < size) && (array[right].compareTo(array[l]) > 0)) {
            l = right;
        }
        if (l != i)
        {
            String temp = array[i];
            array[i] = array[l];
            array[l] = temp;

            heapify(array, size, l);
        }
    }
//    public static long heapSortTime(String array[]) {
//        long timeValue;
//        long start = System.currentTimeMillis();
//        array = heapSort(array);
//        long end = System.currentTimeMillis();
//        timeValue = (end - start)/1000;
//        return timeValue;
//    }
//    public static long insertionSortTime(String array[]) {
//        long timeValue;
//        long start = System.currentTimeMillis();
//        array = insertionSort(array);
//        long end = System.currentTimeMillis();
//        timeValue = (end - start)/1000;
//        return timeValue;
//    }
//    public static float mergeSortTime(String array[]) {
//        float timeValue;
//        float start = System.currentTimeMillis();
//        array = mergeSort(array);
//        float end = System.currentTimeMillis();
//        timeValue = (end - start)/1000;
//        return timeValue;
//    }
    public static void printArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
