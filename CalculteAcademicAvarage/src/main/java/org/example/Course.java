package org.example;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class Course extends JPanel {
    private final JTextField courseName;
    private final JSlider gradeSlider;
    private final JComboBox<Integer> pointsComboBox;
    private final JLabel gradeInNum;
    private final Hashtable<Integer, JLabel> labelTable;

    private int yOfCourseName = 120 + 20;
    private int yOfNumGrade = 105;
    private int yOfNumSliderGrade = 140;
    private int yOfCombo = 140;


    public Course() {
        setLayout(null);

        courseName = new JTextField();
        courseName.setBounds((Window.getWINDOW_WIDTH() - 170 - 40), yOfCourseName, 130, 45);
        courseName.setHorizontalAlignment(JTextField.CENTER);
        courseName.setFont(new Font("Arial", Font.BOLD, 15));
        courseName.setForeground(Color.white);
        courseName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, "הזן את שם הקורס", TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.ITALIC, 12), Color.white),
                BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        courseName.setOpaque(false);
        add(courseName);

        gradeInNum = new JLabel("60");
        gradeInNum.setFont(new Font("Arial", Font.BOLD, 13));
        gradeInNum.setBounds((Window.getWINDOW_WIDTH() / 2 - 40), yOfNumGrade, 350, 45);
        gradeInNum.setForeground(Color.WHITE);
        add(gradeInNum);

        gradeSlider = new JSlider(JSlider.HORIZONTAL, 50, 100, 60);
        gradeSlider.setBounds((int) (Window.getWINDOW_WIDTH() / 3.5 - 40), yOfNumSliderGrade, 350, 45);
        gradeSlider.setMajorTickSpacing(10);
        gradeSlider.setMinorTickSpacing(5);
        gradeSlider.setPaintTicks(true);
        gradeSlider.setPaintLabels(true);

        labelTable = new Hashtable<>();
        for (int i = 50; i <= 100; i += 10) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.WHITE);
            labelTable.put(i, label);
        }
        gradeSlider.setLabelTable(labelTable);
        gradeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gradeInNum.setText(String.valueOf(gradeSlider.getValue()));
            }
        });
        gradeSlider.setFont(new Font("Arial", Font.BOLD, 14));
        gradeSlider.setForeground(Color.WHITE);
        gradeSlider.setBackground(new Color(43, 55, 59));
        add(gradeSlider);

        pointsComboBox = new JComboBox<>();
        pointsComboBox.setBackground(Color.white);
        for (int j = 1; j <= 5; j++) {
            pointsComboBox.addItem(j);
        }
        pointsComboBox.setSelectedItem(5);
        pointsComboBox.setFont(new Font("Arial", Font.BOLD, 13));
        pointsComboBox.setBounds(70 - 15, yOfCombo, 60, 45);
        pointsComboBox.setBackground(Color.WHITE);
        add(pointsComboBox);
    }

    public JTextField getCourseName() {
        return courseName;
    }

    public JLabel getGradeInNum() {
        return gradeInNum;
    }

    public JSlider getGradeSlider() {
        return gradeSlider;
    }

    public JComboBox<Integer> getPointsComboBox() {
        return pointsComboBox;
    }

    public int getYOfCourseName() {
        return yOfCourseName;
    }

    public int getYOfNumGrade() {
        return yOfNumGrade;
    }

    public int getYOfNumSliderGrade() {
        return yOfNumSliderGrade;
    }

    public int getYOfCombo() {
        return yOfCombo;
    }

    public int setYOfCourseName(int yOfCourseName) {
        this.yOfCourseName = yOfCourseName;
        return yOfCourseName;
    }

    public int setYOfNumGrade(int yOfNumGrade) {
        this.yOfNumGrade = yOfNumGrade;
        return yOfNumGrade;
    }

    public int setYOfNumSliderGrade(int yOfNumSliderGrade) {
        this.yOfNumSliderGrade = yOfNumSliderGrade;
        return yOfNumSliderGrade;
    }

    public int setYOfCombo(int yOfCombo) {
        this.yOfCombo = yOfCombo;
        return yOfCombo;
    }

    public void setPointsComboBox(int value) {
        this.pointsComboBox.setSelectedIndex(value);
    }

    public void setGradeInNum(int value) {
        this.gradeInNum.setText(String.valueOf(value));
    }

    public void setCourseName(String courseName) {
        this.courseName.setText(courseName);
    }

    public void setGradeSlider(int gradeSliderValue) {
        this.gradeSlider.setValue(gradeSliderValue);
    }
}
