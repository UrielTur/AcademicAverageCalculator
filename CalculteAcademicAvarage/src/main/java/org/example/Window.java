package org.example;
import db.MyJDBC;
import javax.swing.*;

public class Window extends JFrame {

    private static final short WINDOW_WIDTH = 800;
    private static final short WINDOW_HEIGHT = 750;
    private final OpeningScreen openingScreen;
    private final CalculateScreen calculateScreen;
    private final JLabel messageLabel;
    private final JLabel messageLabel1;
    private final ResultScreen resultScreen;
    private int gradePerCourse;
    private int pointsPerCourse;
    private int totalPoints = 0;
    private float totalGrades = 0;
    private float totalGradeAverage = 0;
    private String username;
    private HistoryGrades historyGrades;


    public Window(){
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Average Calculate");
        this.setLocationRelativeTo(null);

        this.openingScreen = new OpeningScreen();
        this.add(openingScreen);

        this.calculateScreen = new CalculateScreen();
        this.add(calculateScreen);
        this.calculateScreen.setVisible(false);

        this.resultScreen = new ResultScreen();
        this.add(resultScreen);
        this.resultScreen.setVisible(false);

        messageLabel = new JLabel("אנא הזן את שמך כדי להמשיך", JLabel.RIGHT);
        messageLabel1 = new JLabel("אנא הזן את שם הקורס", JLabel.RIGHT);

        this.openingScreen.getEnterButton().addActionListener(e -> {
            if (openingScreen.getUserName().isEmpty()){
                JOptionPane.showMessageDialog(null, messageLabel, null, JOptionPane.ERROR_MESSAGE);
            } else {
                username = openingScreen.getUserName();
                if (validateUserInput(username)){
                    if (MyJDBC.register(username)){
                        JOptionPane.showMessageDialog(openingScreen, "הנך משתמש חדש! ברוך הבא!");
                    }else {
                        JOptionPane.showMessageDialog(openingScreen, "הנך משתמש קיים! ברוך השב!");
                    }
                }
                this.openingScreen.setVisible(false);
                this.calculateScreen.setVisible(true);
            }
        });

        calculateScreen.getPressCalculate().addActionListener(e -> {
            if (historyGrades == null) {
                MyJDBC.show(username);
                historyGrades = new HistoryGrades();
                this.add(historyGrades);
                historyGrades.setVisible(true);

                historyGrades.getBackButton().addActionListener(ev -> {
                    resultScreen.setVisible(true);
                    historyGrades.setVisible(false);
                });
            } else {
                MyJDBC.showUpdate(username);
                historyGrades.getLastDetail();
            }

            if (!calculateScreen.getCourses().get(calculateScreen.getIndex()).getCourseName().getText().isEmpty()) {
                for (int i = 0; i <= calculateScreen.getIndex(); i++) {
                    gradePerCourse = calculateScreen.setGradePerCourse(Integer.parseInt(calculateScreen.getCourses().get(i).getGradeInNum().getText()));
                    pointsPerCourse = calculateScreen.setPointsPerCourse((Integer) calculateScreen.getCourses().get(i).getPointsComboBox().getSelectedItem());

                    totalGrades += gradePerCourse * pointsPerCourse;
                    totalPoints += calculateScreen.setPointsPerCourse((Integer) calculateScreen.getCourses().get(i).getPointsComboBox().getSelectedItem());
                }
                totalGradeAverage = totalGrades / totalPoints;
                String formattedAverage = String.format("%.2f", totalGradeAverage);

                resultScreen.setGradeTotal(Float.parseFloat(formattedAverage));
                resultScreen.setVisible(true);
                calculateScreen.setVisible(false);

                MyJDBC.match(username, Double.parseDouble(formattedAverage));
            }else {
                JOptionPane.showMessageDialog(null, messageLabel1, null, JOptionPane.ERROR_MESSAGE);
            }
        });

        calculateScreen.getResetButton().addActionListener(e -> {
            gradePerCourse=0;
            pointsPerCourse=0;
            totalGrades=0;
            totalPoints=0;
            totalGradeAverage=0;
        });

        calculateScreen.getDeleteLast().addActionListener(e -> {
            gradePerCourse=0;
            pointsPerCourse=0;
            totalGrades=0;
            totalPoints=0;
            totalGradeAverage=0;
        });



        resultScreen.getHistoryButton().addActionListener(e -> {
                historyGrades.setVisible(true);
                resultScreen.setVisible(false);
//            }
        });

        resultScreen.getBackButton().addActionListener(e -> {
            if (calculateScreen.getCourses().size()==1){
                gradePerCourse=0;
                pointsPerCourse=0;
                totalGrades=0;
                totalPoints=0;
                totalGradeAverage=0;
            }
            resultScreen.setVisible(false);
            calculateScreen.setVisible(true);
        });
    }

    public void showWindow(){
        setVisible(true);
    }
    public static short getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }
    public static short getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }
    public boolean validateUserInput(String username){
        if (username.length()==0){
            return false;
        }
        return true;
    }





}
