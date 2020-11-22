package kuchuk.home.list.arrayList;

import kuchuk.home.list.arrayList.ArrayList;
import kuchuk.home.list.linkedList.LinkedList;

import java.util.Random;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Random rnd = new Random();

        ArrayList<Integer> lst = new ArrayList<>();

        // создаем новый лист.
        for (int i = 0; i < 10; ++i) {
            lst.add(i);
            lst.add(-i);
        }
        printListByIndex(lst);

        // удаляем несколько элементов.
        for (int i = 0; i < 3 + rnd.nextInt(7); ++i)
            lst.remove(rnd.nextInt(lst.size()));
        printListByIndex(lst);


        // демонстрация работы contains.
        System.out.println(ANSI_GREEN + "Проверка принадлежности элементов" + ANSI_RESET);
        for (int i = 0; i < 10; ++i) {
            printContains(lst, i);
            printContains(lst, -i);
            System.out.println();
        }

        // демонстрация работы clear.

        lst.clear();
        System.out.println(ANSI_GREEN + "Очитсили лист" + ANSI_RESET);
        printListByIndex(lst);

    }

    public static void printListByIndex(ArrayList<Integer> lst) {
        System.out.println(ANSI_GREEN + "Вывод листа (size = " + lst.size() + ")" + ANSI_RESET);
        for (int i = 0; i < lst.size(); ++i)
            System.out.print(lst.get(i) + " ");
        System.out.println();
    }

    public static void printContains(ArrayList<Integer> lst, int element){
        System.out.print("check(" + element + ") - ");
        if (lst.contains(element))
            System.out.print("yes   ");
        else
            System.out.print("no    ");
    }
}
