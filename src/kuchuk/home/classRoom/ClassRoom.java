package kuchuk.home.classRoom;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClassRoom {
    private final List<Student> students;
    private final Random rnd = new Random();

    public ClassRoom(List<Student> inputStudents) {
        students = new ArrayList<>(inputStudents);
    }

    public Student randomChose() {
        int index = rnd.nextInt(students.size());

        return students.get(index);
    }

    public LinkedList<Student> foundStudentByLastName(String lastName) throws NoSuchElementException{
        LinkedList<Student> oneLastName = new LinkedList<>();
        for (Student person : students) {
            if (person.getLastName().equals(lastName)) {
                oneLastName.add(person);
            }
        }

        if(oneLastName.size() == 0){
            throw new NoSuchElementException();
        }

        return oneLastName;
    }

    public void addNewStudent(Student newStudent) {
        students.add(newStudent);
    }

    public void deleteStudent(Student delStudent) throws NoSuchElementException {
        if(students.contains(delStudent)){
            students.remove(delStudent);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    public void printAllStudents() {
        for (Object o : students.toArray()) {
            Student s = (Student) o;
            System.out.println(s);
        }
    }

    public static ClassRoom createFromTxt(String pathToTxt) throws FileNotFoundException {
        LinkedList<Student> students = new LinkedList<>();

        Scanner sc = new Scanner(new File(pathToTxt));
        while (sc.hasNextLine()) {
            String[] splitSource = sc.nextLine().split(" ");
            students.add(new Student(splitSource[1], splitSource[0]));
        }

        return new ClassRoom(students);
    }

}
