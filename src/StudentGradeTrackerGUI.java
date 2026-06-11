import javax.swing.*;
        import java.awt.*;
        import java.util.ArrayList;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90)
            return "A";
        else if (marks >= 80)
            return "B";
        else if (marks >= 70)
            return "C";
        else if (marks >= 60)
            return "D";
        else
            return "F";
    }
}

public class StudentGradeTrackerGUI extends JFrame {

    private ArrayList<Student> students = new ArrayList<>();

    private JTextField nameField;
    private JTextField marksField;
    private JTextArea reportArea;

    public StudentGradeTrackerGUI() {

        setTitle("Student Grade Tracker");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        inputPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("View Report");

        inputPanel.add(addButton);
        inputPanel.add(reportButton);

        reportArea = new JTextArea();
        reportArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(reportArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> addStudent());
        reportButton.addActionListener(e -> generateReport());
    }

    private void addStudent() {

        try {

            String name = nameField.getText();

            double marks = Double.parseDouble(marksField.getText());

            students.add(new Student(name, marks));

            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully!");

            nameField.setText("");
            marksField.setText("");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Enter valid marks!");

        }
    }

    private void generateReport() {

        if (students.isEmpty()) {

            reportArea.setText("No student data available.");
            return;
        }

        double total = 0;

        double highest = students.get(0).marks;
        double lowest = students.get(0).marks;

        String highestStudent = students.get(0).name;
        String lowestStudent = students.get(0).name;

        StringBuilder report = new StringBuilder();

        report.append("========== STUDENT REPORT ==========\n\n");

        for (Student s : students) {

            report.append("Name: ")
                    .append(s.name)
                    .append(" | Marks: ")
                    .append(s.marks)
                    .append(" | Grade: ")
                    .append(s.getGrade())
                    .append("\n");

            total += s.marks;

            if (s.marks > highest) {
                highest = s.marks;
                highestStudent = s.name;
            }

            if (s.marks < lowest) {
                lowest = s.marks;
                lowestStudent = s.name;
            }
        }

        double average = total / students.size();

        report.append("\n-------------------------------\n");
        report.append("Average Score : ")
                .append(String.format("%.2f", average))
                .append("\n");

        report.append("Highest Score : ")
                .append(highest)
                .append(" (")
                .append(highestStudent)
                .append(")\n");

        report.append("Lowest Score  : ")
                .append(lowest)
                .append(" (")
                .append(lowestStudent)
                .append(")\n");

        reportArea.setText(report.toString());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentGradeTrackerGUI().setVisible(true);
        });
    }
}