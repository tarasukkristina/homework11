package by.it.academy.homework11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of numbers: ");
        int count = scanner.nextInt();

        System.out.println("Enter numbers in array: ");
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        List<Integer> list = getListFromArray(array);

        long startTime = System.currentTimeMillis();

        Thread maxElementInArrayThread = new Thread(() -> getMaxElementInArray(list));
        Thread minElementInArrayThread = new Thread(() -> getMinElementInArray(list));


        maxElementInArrayThread.start();
        minElementInArrayThread.start();


        try {
            maxElementInArrayThread.join();
            minElementInArrayThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Search max and min element " + (endTime - startTime) + " milliseconds");
    }


    public static List<Integer> getListFromArray(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int elements : array) {
            list.add(elements);
        }
        return list;
    }


    public static void getMaxElementInArray(List<Integer> list) {

        Thread currentThread = Thread.currentThread();

        Integer foundMaxElementOfList = list.stream()
                .max((element1, element2) -> element1.compareTo(element2))
                .get();
        System.out.println("Max number is: " + foundMaxElementOfList);
        System.out.println("Thread " + currentThread.getName() + " in state: " + currentThread.getState());
    }

    public static void getMinElementInArray(List<Integer> list) {

        Thread currentThread = Thread.currentThread();

        Integer foundMinElementOfList = list.stream()
                .min((element1, element2) -> element1.compareTo(element2))
                .get();
        System.out.println("Min number is: " + foundMinElementOfList);
        System.out.println("Thread " + currentThread.getName() + " in state: " + currentThread.getState());
    }
}



