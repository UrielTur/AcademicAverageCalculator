package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculateScreen extends JPanel {
    private final JLabel mainTitle;
    private final JLabel courseTitle;
    private final JLabel gradeTitle;
    private final JLabel pointsTitle;
    private final Course course1;
    private int index = 0;
    private final Color gray = new Color(43, 55, 59);
    private final List<Course> courses;
    private final List<JLabel> jLabelList;
    private final Color color =new Color(52 ,204, 255);
    private final Color TEXT_COLOR = Color.decode("#01C38D");
    private final JButton addCourse;
    private JButton deleteLast;
    private int yOfAddButton = 210;
    private static int yOfPressCalculateButton = Window.getWINDOW_HEIGHT() - 100;
    private int count = 1;
    private final JLabel countText;
    private final JPanel mainPanel;
    private final JScrollPane scroller;
    private final JLabel messageLabel;
    private JButton pressCalculate;
    private int gradePerCourse;
    private int pointsPerCourse;
    private JButton resetButton;


    public CalculateScreen() {
        setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(new BorderLayout());
        setBackground(gray);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(gray);

        course1 = new Course();

        mainTitle = new JLabel("אנא הזן: שם הקורס, הציון, ומספר נקודות הזכות");
        mainTitle.setBounds((Window.getWINDOW_WIDTH() / 5 ), 5, 500, 60);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 24));
        mainTitle.setForeground(TEXT_COLOR);
        mainTitle.setHorizontalTextPosition(JTextField.RIGHT);
        mainPanel.add(mainTitle);

        countText = new JLabel("." + String.valueOf(count));
        countText.setBounds((Window.getWINDOW_WIDTH() - 55), course1.getYOfCourseName() + 10 , 30, 30);
        countText.setFont(new Font("Arial", Font.BOLD, 17));
        countText.setForeground(TEXT_COLOR);
        countText.setHorizontalTextPosition(JLabel.RIGHT);
        mainPanel.add(countText);

        courseTitle = new JLabel("שם הקורס:");
        courseTitle.setBounds((Window.getWINDOW_WIDTH() - 140 - 40), 65, 150, 40);
        courseTitle.setFont(new Font("Arial", Font.BOLD, 18));
        courseTitle.setForeground(TEXT_COLOR);
        courseTitle.setHorizontalTextPosition(JLabel.RIGHT);
        mainPanel.add(courseTitle);

        gradeTitle = new JLabel("ציון סופי:");
        gradeTitle.setBounds((Window.getWINDOW_WIDTH() / 2 - 25 - 40), 65, 150, 40);
        gradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gradeTitle.setForeground(TEXT_COLOR);
        gradeTitle.setHorizontalTextPosition(JLabel.RIGHT);
        mainPanel.add(gradeTitle);

        pointsTitle = new JLabel("נקודות זכות:");
        pointsTitle.setBounds(70 - 30, 65, 150, 40);
        pointsTitle.setFont(new Font("Arial", Font.BOLD, 18));
        pointsTitle.setForeground(TEXT_COLOR);
        pointsTitle.setHorizontalTextPosition(JLabel.RIGHT);
        mainPanel.add(pointsTitle);

        jLabelList = new ArrayList<>();
        jLabelList.add(0, new JLabel(String.valueOf(count)));

        messageLabel = new JLabel("אנא הזן שם קורס", JLabel.RIGHT);

        courses = new ArrayList<>();

        mainPanel.add(course1.getCourseName());
        mainPanel.add(course1.getGradeInNum());
        mainPanel.add(course1.getGradeSlider());
        mainPanel.add(course1.getPointsComboBox());
        mainPanel.add(course1);
        courses.add(0 , course1);

        addCourse = new JButton("הוסף קורס +");
        addCourse.setBounds((Window.getWINDOW_WIDTH() - 150 - 30), yOfAddButton, 100, 40);
        addCourse.setBackground(TEXT_COLOR);
        addCourse.setForeground(Color.BLACK);
        addCourse.addActionListener(e -> {
            if (!courses.get(index).getCourseName().getText().isEmpty()){
                index++;
                count++;
                yOfAddButton += 100;
                addCourse.setBounds((Window.getWINDOW_WIDTH() - 150 - 30), yOfAddButton, 100, 40);

                courses.add(index, new Course());

                jLabelList.add(index, new JLabel("." + String.valueOf(count)));
                jLabelList.get(index).setBounds((Window.getWINDOW_WIDTH() - 55), courses.get(index).setYOfCourseName(courses.get(index - 1).getYOfCourseName() + 100) + 10 , 30, 30);
                jLabelList.get(index).setFont(new Font("Arial", Font.BOLD, 17));
                jLabelList.get(index).setForeground(TEXT_COLOR);

                mainPanel.add(jLabelList.get(index));

                mainPanel.add(courses.get(index).getCourseName());
                courses.get(index).getCourseName().setBounds((Window.getWINDOW_WIDTH() - 170 - 40), courses.get(index).setYOfCourseName(courses.get(index - 1).getYOfCourseName() + 100), 130, 45);

                mainPanel.add(courses.get(index).getGradeInNum());
                courses.get(index).getGradeInNum().setBounds((Window.getWINDOW_WIDTH() / 2 - 40), courses.get(index).setYOfNumGrade(courses.get(index - 1).getYOfNumGrade() + 100), 350, 45);

                mainPanel.add(courses.get(index).getGradeSlider());
                courses.get(index).getGradeSlider().setBounds((int) (Window.getWINDOW_WIDTH() / 3.5 - 40), courses.get(index).setYOfNumSliderGrade(courses.get(index - 1).getYOfNumSliderGrade() + 100), 350, 45);

                mainPanel.add(courses.get(index).getPointsComboBox());
                courses.get(index).getPointsComboBox().setBounds(70 - 10, courses.get(index).setYOfCombo(courses.get(index - 1).getYOfCombo() + 100), 60, 45);
            } else {
                JOptionPane.showMessageDialog(null, messageLabel, null, JOptionPane.ERROR_MESSAGE);
            }

            if (index >= 5){
                yOfPressCalculateButton += 100;
                pressCalculate.setBounds(Window.getWINDOW_WIDTH() / 2 - 90, yOfPressCalculateButton, 200, 50);
                resetButton.setBounds(25, yOfPressCalculateButton, 95, 50);
                deleteLast.setBounds(150, yOfPressCalculateButton, 110, 50);
            }

            mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), yOfPressCalculateButton + 100));
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        mainPanel.add(addCourse);

        pressCalculate = new JButton("בצע חישוב");
        pressCalculate.setBounds(Window.getWINDOW_WIDTH() / 2 - 90, yOfPressCalculateButton, 200, 50);
        pressCalculate.setFont(new Font("Arial" , Font.BOLD , 19));
        pressCalculate.setForeground(Color.WHITE);
        pressCalculate.setBackground(color);
        mainPanel.add(pressCalculate);


        scroller = new JScrollPane(mainPanel);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getVerticalScrollBar().setUnitIncrement(5);
        add(scroller, BorderLayout.CENTER);

        deleteLast = new JButton("מחק אחרון");
        deleteLast.setBounds(150, yOfPressCalculateButton, 110, 50);
        deleteLast.setFont(new Font("Arial" , Font.BOLD , 17));
        deleteLast.setForeground(Color.white);
        deleteLast.setBackground(new Color(0xFF0A0A));
        deleteLast.addActionListener(e -> {
            deleteLast();
        });
        mainPanel.add(deleteLast);

        resetButton = new JButton("אפס הכל");
        resetButton.setBounds(25, yOfPressCalculateButton, 95, 50);
        resetButton.setFont(new Font("Arial" , Font.BOLD , 17));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.red);
        resetButton.addActionListener(e -> {
            if (courses.size() >= 1) {
                resetButton();
            }
        });
        mainPanel.add(resetButton);

    }


    public void deleteLast(){

       if (index>0) {
            mainPanel.remove(jLabelList.get(index));
            mainPanel.remove(courses.get(index).getCourseName());
            mainPanel.remove(courses.get(index).getGradeInNum());
            mainPanel.remove(courses.get(index).getGradeSlider());
            mainPanel.remove(courses.get(index).getPointsComboBox());

            courses.remove(index);
            jLabelList.remove(index);

           yOfAddButton -= 100;
           addCourse.setBounds((Window.getWINDOW_WIDTH() - 150 - 30), yOfAddButton, 100, 40);

           if (index >= 5){
               yOfPressCalculateButton -= 100;
               pressCalculate.setBounds(Window.getWINDOW_WIDTH() / 2 - 90, yOfPressCalculateButton, 200, 50);
               resetButton.setBounds(25, yOfPressCalculateButton, 95, 50);
               deleteLast.setBounds(150, yOfPressCalculateButton, 110, 50);
           }
           mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), yOfPressCalculateButton + 100));

           index--;
           count--;
        }
        mainPanel.revalidate();
        mainPanel.repaint();

    }


    public void resetButton(){

        course1.setPointsComboBox(4);
        course1.setGradeSlider(60);
        course1.setGradeInNum(60);
        course1.setCourseName("");

        for (int i = 1; i < courses.size(); i++) {
            courses.get(i).setPointsComboBox(0);
            courses.get(i).setGradeInNum(0);

            mainPanel.remove(jLabelList.get(i));
            mainPanel.remove(courses.get(i).getCourseName());
            mainPanel.remove(courses.get(i).getGradeInNum());
            mainPanel.remove(courses.get(i).getGradeSlider());
            mainPanel.remove(courses.get(i).getPointsComboBox());
        }

        courses.clear();
        courses.add(0 , course1);

        index = 0;
        count = 1;

        yOfAddButton = 210;
        yOfPressCalculateButton = Window.getWINDOW_HEIGHT() - 100;

        deleteLast.setBounds(150, yOfPressCalculateButton, 110, 50);
        resetButton.setBounds(25, yOfPressCalculateButton, 95, 50);
        addCourse.setBounds((Window.getWINDOW_WIDTH() - 150 - 30), yOfAddButton, 100, 40);
        pressCalculate.setBounds(Window.getWINDOW_WIDTH() / 2 - 90, yOfPressCalculateButton, 200, 50);

        mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), yOfPressCalculateButton + 100));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public JButton getPressCalculate() {
        return pressCalculate;
    }

    public int setGradePerCourse(int gradePerCourse) {
        this.gradePerCourse = gradePerCourse;
        return gradePerCourse;
    }

    public int setPointsPerCourse(int pointsPerCourse) {
        this.pointsPerCourse = pointsPerCourse;
        return pointsPerCourse;
    }

    public int getIndex() {
        return index;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getDeleteLast(){
        return deleteLast;
    }

}
