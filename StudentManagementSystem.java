import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private List<Student> students;
    private String dataFile = "students_data.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadStudentsFromStorage();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToStorage();
    }

    public void removeStudent(Student student) {
        students.remove(student);
        saveStudentsToStorage();
    }

    public Student findStudentByRollNo(int rollNo) {
        for (Student student : students) {
            if (student.getRollNo() == rollNo) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    private void loadStudentsFromStorage() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            students = (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If the file is not found or data cannot be read, start with an empty list
            students = new ArrayList<>();
        }
    }

    private void saveStudentsToStorage() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


