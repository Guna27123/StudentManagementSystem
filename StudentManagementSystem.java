
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> searchStudent();
                case 4 -> updateStudent();
                case 5 -> deleteStudent();
                case 6 -> sortStudentsByName();
                case 0 -> {
                    running = false;
                    System.out.println("Thank you!");
                }
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Update student");
        System.out.println("5. Delete student");
        System.out.println("6. Sort students by name");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        int id = readInt("Enter student ID: ");

        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        String name = readNonEmpty("Enter student name: ");
        String course = readNonEmpty("Enter course: ");
        double marks = readDouble("Enter marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }

        students.add(new Student(id, name, course, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\nID | Name | Course | Marks");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {
        int id = readInt("Enter student ID: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        int id = readInt("Enter student ID to update: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmpty("Enter new name: ");
        String course = readNonEmpty("Enter new course: ");
        double marks = readDouble("Enter new marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks.");
            return;
        }

        student.setName(name);
        student.setCourse(course);
        student.setMarks(marks);

        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {
        int id = readInt("Enter student ID to delete: ");
        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully.");
    }

    private static void sortStudentsByName() {
        students.sort(
            Comparator.comparing(
                Student::getName,
                String.CASE_INSENSITIVE_ORDER
            )
        );

        System.out.println("Students sorted by name.");
        viewStudents();
    }

    private static Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}

class Student {
    private final int id;
    private String name;
    private String course;
    private double marks;

    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + course + " | " + marks;
    }
}
