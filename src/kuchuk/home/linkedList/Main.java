package kuchuk.home.linkedList;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random();

        LinkedList<Integer> lst = new LinkedList<>();

        // создаем новый лист.
        for(int i = 0; i < 10; ++i){
            lst.add(i);
            lst.add(-i);
        }
        printListByIndex(lst);

        // удаляем несколько элементов.
        for(int i = 0; i < 3 + rnd.nextInt(7); ++i)
            lst.remove(rnd.nextInt(lst.size()));
        printListByIndex(lst);
    }

    public static void printListByIndex(LinkedList<Integer> lst){
        System.out.println("Вывод листа (size = " + lst.size() + ")");
        for(int i = 0; i < lst.size(); ++i)
            System.out.print(lst.get(i) + " ");
        System.out.println();
    }
}
