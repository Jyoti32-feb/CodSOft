import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search for Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent(sms);
                    break;
                case 2:
                    removeStudent(sms);
                    break;
                case 3:
                    searchStudent(sms);
                    break;
                case 4:
                    displayAllStudents(sms);
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(StudentManagementSystem sms) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();

        Student student = new Student(name, rollNo, grade);
        sms.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent(StudentManagementSystem sms) {
        System.out.print("Enter roll number of the student to remove: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = sms.findStudentByRollNo(rollNo);
        if (student != null) {
            sms.removeStudent(student);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with roll number " + rollNo + " not found.");
        }
    }

    private static void searchStudent(StudentManagementSystem sms) {
        System.out.print("Enter roll number of the student to search: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = sms.findStudentByRollNo(rollNo);
        if (student != null) {
            System.out.println("Student details: " + student);
        } else {
            System.out.println("Student with roll number " + rollNo + " not found.");
        }
    }

    private static void displayAllStudents(StudentManagementSystem sms) {
        System.out.println("All students:");
        List<Student> students = sms.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

