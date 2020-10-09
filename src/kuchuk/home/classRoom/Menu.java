package kuchuk.home.classRoom;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private menuCondition condition = menuCondition.GLOBAL;
    private final Scanner in = new Scanner(System.in);
    private final ClassRoom group;

    public Menu(ClassRoom newGroup) {
        group = newGroup;
    }

    public boolean printMenu() {
        switch (condition) {
            case GLOBAL: {
                handleGlobalMenu();
                break;
            }
            case ALL: {
                handleShowGroupMenu();
                condition = menuCondition.GLOBAL;
                break;
            }
            case RANDOM: {
                handleRandomMenu();
                condition = menuCondition.GLOBAL;
                break;
            }
            case LASTNAME: {
                try {
                    LinkedList<Student> oneLastName = foundByLastName();
                    Student requiredStudent;

                    if (oneLastName.size() > 1) {
                        requiredStudent = foundByFirstName(oneLastName);
                    } else {
                        requiredStudent = oneLastName.getFirst();
                    }

                    System.out.println("Отвечает " + requiredStudent.toString());
                    studentAssessment(requiredStudent);
                } catch (NoSuchElementException ex) {
                    System.out.println("Студент не найден");
                }

                condition = menuCondition.GLOBAL;
                break;
            }
            case ADD: {
                handleAddStudentMenu();
                condition = menuCondition.GLOBAL;
                break;
            }
            case DELETE: {
                handleDeleteStudentMenu();
                condition = menuCondition.GLOBAL;
                break;
            }
            case CLOSE: {
                group.printAllStudents();
                return false;
            }
        }
        return true;
    }

    private void handleGlobalMenu() {
        System.out.println("1. Показать список студентов");
        System.out.println("2. Выбрать случайного");
        System.out.println("3. Выбрать по фамилии");
        System.out.println("4. Добавить нового студента");
        System.out.println("5. Отчислить студента");
        System.out.println("6. Завершить программу");
        try {
            String input = in.nextLine();
            int param = Integer.parseInt(input);
            setConditionFromInt(param);
        } catch (NumberFormatException ex) {
            System.out.println("Неверный формат пункта меню, вводить можно только цифры [1 - 6]");
        }
    }

    private void setConditionFromInt(int param) {
        switch (param) {
            case 1: {
                condition = menuCondition.ALL;
                break;
            }
            case 2: {
                condition = menuCondition.RANDOM;
                break;
            }
            case 3: {
                condition = menuCondition.LASTNAME;
                break;
            }
            case 4: {
                condition = menuCondition.ADD;
                break;
            }
            case 5: {
                condition = menuCondition.DELETE;
                break;
            }
            case 6: {
                condition = menuCondition.CLOSE;
            }
            default: {
                System.out.println("Такого пункта меню не существует");
            }
        }
    }

    private void handleShowGroupMenu() {
        group.printAllStudents();
    }

    private LinkedList<Student> foundByLastName() {
        System.out.println("Введите фамилию студента");
        String lastName = in.nextLine();

        return group.foundStudentByLastName(lastName);
    }

    private Student foundByFirstName(LinkedList<Student> oneLastName) {
        System.out.println("Найдено несколько студентов с такой фамилией, введите имя студента");
        String firstName = in.nextLine();

        for (Student person : oneLastName) {
            if (person.getFirstName().equals(firstName)) {
                return person;
            }
        }

        throw new NoSuchElementException();
    }

    private void handleRandomMenu() {
        Student randomStudent = group.randomChose();

        System.out.println("Случайно выбран студент " + randomStudent);
        studentAssessment(randomStudent);
        System.out.println("Средний балл студента " + randomStudent.getLastName() + " = " + randomStudent.getAverageMark());
    }

    private void handleAddStudentMenu() {
        System.out.println("Введите фамилию студента");
        String lastName = in.nextLine();
        System.out.println("Введите имя студента");
        String firstName = in.nextLine();

        Student newStudent = new Student(firstName, lastName);
        group.addNewStudent(newStudent);
        System.out.println("Студент " + lastName + " успешно добавлен");
    }

    private void handleDeleteStudentMenu() {
        try {
            LinkedList<Student> oneLastName = foundByLastName();
            Student delStudent;

            if (oneLastName.size() > 1) {
                delStudent = foundByFirstName(oneLastName);
            } else {
                delStudent = oneLastName.getFirst();
            }

            group.deleteStudent(delStudent);
            System.out.println("Студент " + delStudent.getLastName() + " успешно удален");
        } catch (NoSuchElementException ex) {
            System.out.println("Студента с таким именем не существует");
        }
    }

    private void studentAssessment(Student student) {
        System.out.println("Введите оценку");
        String input = in.nextLine();
        try {
            int mark = Integer.parseInt(input);
            student.setNewMark(mark);
        } catch (IllegalArgumentException ex) {
            System.out.println("Неверный формат оценки int[0 - 10]");
        }
    }
}

