package kuchuk.home.third;

import java.io.FileNotFoundException;

public class MainController {

    public static void main(String[] args) {
        ClassRoom students;
        String pathToTxt = "stud.txt";

        try{
            students = ClassRoom.createFromTxt(pathToTxt);

            Menu menu = new Menu(students);

            while(menu.printMenu()){
                System.out.println();
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Не удалось считать данные по пути {" + pathToTxt + "}");
        }

    }
}
