package kuchuk.home.third;

public class Student {
    private final String firstName;
    private final String lastName;
    private double averageMark = 0;
    private boolean firstMark = true;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public double getAverageMark() {
        return firstMark ? 0 : averageMark;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setNewMark(int mark) throws IllegalArgumentException {
        if (mark < 0 || mark > 10) {
            throw new IllegalArgumentException();
        }
        if (firstMark) {
            averageMark = mark;
            firstMark = false;
        } else {
            averageMark = (averageMark + mark) / 2;
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "  средний балл : " + averageMark;
    }


}
