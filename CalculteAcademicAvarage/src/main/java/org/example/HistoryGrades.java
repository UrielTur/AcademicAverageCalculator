package org.example;
import db.MyJDBC;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HistoryGrades extends JPanel {
    private final JLabel titleText;
    private final JLabel nameTitle;
    private final JLabel gradeTitle;
    private final JLabel dateTitle;
    private final JLabel timeTitle;
    private final short xTitle = (short) ((Window.getWINDOW_WIDTH()/6) - 50);
    private final short yTitle = 5;
    private final short widthTitle = 750;
    private final byte heightTitle = 80;
    private java.util.List<String> usernames;
    private java.util.List<Double> grades;
    private java.util.List<String> times;
    private java.util.List<String> dates;

    private java.util.List<JLabel> usernamesList;
    private java.util.List<JLabel> gradesList;
    private java.util.List<JLabel> timesList;
    private java.util.List<JLabel> datesList;
    private int yOfGrades = 100;
    private int counter = 0;
    private int heightOfPanel = Window.getWINDOW_HEIGHT();
    private int heightOfBackButton = Window.getWINDOW_HEIGHT() - 100;
    private final JPanel mainPanel;
    private final JScrollPane scroller;
    private final JButton backButton;
    private final Color gray = new Color(43, 55, 59);




    public HistoryGrades() {

        setSize(Window.getWINDOW_WIDTH(), heightOfPanel);
        setLayout(new BorderLayout());
        setBackground(gray);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(gray);

        titleText = new JLabel("היסטוריית ציוני ממוצע סופיים בעבר ");
        titleText.setBounds(xTitle, yTitle, widthTitle, heightTitle);
        titleText.setFont(new Font("Arial", Font.BOLD, 40));
        titleText.setForeground(Color.white);
        mainPanel.add(titleText);

        nameTitle = new JLabel("שם הסטודנט: ");
        nameTitle.setBounds(Window.getWINDOW_WIDTH() - 200, yOfGrades - 50, 150, 80);
        nameTitle.setFont(new Font("Arial", Font.BOLD, 19));
        nameTitle.setForeground(Color.white);
        mainPanel.add(nameTitle);

        gradeTitle = new JLabel("ציון: ");
        gradeTitle.setBounds(Window.getWINDOW_WIDTH() - 320, yOfGrades - 50, 100, 80);
        gradeTitle.setFont(new Font("Arial", Font.BOLD, 19));
        gradeTitle.setForeground(Color.white);
        mainPanel.add(gradeTitle);

        dateTitle = new JLabel("תאריך: ");
        dateTitle.setBounds(Window.getWINDOW_WIDTH() - 490, yOfGrades - 50, 100, 80);
        dateTitle.setFont(new Font("Arial", Font.BOLD, 19));
        dateTitle.setForeground(Color.white);
        mainPanel.add(dateTitle);

        timeTitle = new JLabel("שעה: ");
        timeTitle.setBounds(Window.getWINDOW_WIDTH() - 640, yOfGrades - 50, 100, 80);
        timeTitle.setFont(new Font("Arial", Font.BOLD, 19));
        timeTitle.setForeground(Color.white);
        mainPanel.add(timeTitle);

        backButton = new JButton("חזור");
        backButton.setBounds(25, heightOfBackButton, 80, heightTitle - 20);
        backButton.setFont(new Font("Arial", Font.BOLD, 17));
        backButton.setBackground(new Color(52, 204, 255));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            this.setVisible(false);
        });
        mainPanel.add(backButton);

        scroller = new JScrollPane(mainPanel);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getVerticalScrollBar().setUnitIncrement(5);
        add(scroller, BorderLayout.CENTER);

        this.usernames = MyJDBC.getStrings();
        this.grades = MyJDBC.getGrades();
        this.times = MyJDBC.getTimes();
        this.dates = MyJDBC.getDates();

        this.usernamesList = new ArrayList<>();
        this.gradesList = new ArrayList<>();
        this.timesList = new ArrayList<>();
        this.datesList = new ArrayList<>();


        for (int i = usernames.size() - 1; i >= 0; i--) {

            JLabel name = new JLabel(usernames.get(i));
            JLabel grade = new JLabel(String.valueOf(grades.get(i)));
            JLabel time = new JLabel(times.get(i));
            JLabel date = new JLabel(dates.get(i));

            usernamesList.add(name);
            gradesList.add(grade);
            timesList.add(time);
            datesList.add(date);


            name.setForeground(Color.white);
            grade.setForeground(Color.white);
            time.setForeground(Color.white);
            date.setForeground(Color.white);

            name.setFont(new Font("Arial", Font.BOLD, 16));
            grade.setFont(new Font("Arial", Font.BOLD, 16));
            time.setFont(new Font("Arial", Font.BOLD, 16));
            date.setFont(new Font("Arial", Font.BOLD, 16));

            name.setBounds(Window.getWINDOW_WIDTH() - 160, yOfGrades, 100, 80);
            grade.setBounds(Window.getWINDOW_WIDTH() - 310, yOfGrades, 100, 80);
            date.setBounds(Window.getWINDOW_WIDTH() - 500, yOfGrades, 100, 80);
            time.setBounds(Window.getWINDOW_WIDTH() - 650, yOfGrades, 100, 80);

            mainPanel.add(name);
            mainPanel.add(grade);
            mainPanel.add(time);
            mainPanel.add(date);

            yOfGrades += 50;
            counter++;

            if (counter >= 13) {
                heightOfBackButton += 50;
                heightOfPanel += 50;
                mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), heightOfPanel));
                backButton.setBounds(25, heightOfBackButton, 80, heightTitle - 20);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        }
    }




    public void getLastDetail(){

        heightOfPanel = Window.getWINDOW_HEIGHT();
        setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), heightOfPanel));
        mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), heightOfPanel));


        this.usernames = MyJDBC.getStrings();
        this.grades = MyJDBC.getGrades();
        this.times = MyJDBC.getTimes();
        this.dates = MyJDBC.getDates();


        int startIndex = usernames.size() - 1 ;  // מקום התחלה של פריטים חדשים


        JLabel name = new JLabel(usernames.get(startIndex));
        JLabel grade = new JLabel(String.valueOf(grades.get(startIndex)));
        JLabel time = new JLabel(times.get(startIndex));
        JLabel date = new JLabel(dates.get(startIndex));

        name.setForeground(Color.white);
        grade.setForeground(Color.white);
        time.setForeground(Color.white);
        date.setForeground(Color.white);

        name.setFont(new Font("Arial", Font.BOLD, 16));
        grade.setFont(new Font("Arial", Font.BOLD, 16));
        time.setFont(new Font("Arial", Font.BOLD, 16));
        date.setFont(new Font("Arial", Font.BOLD, 16));

        yOfGrades = 100;
        counter=0;
        heightOfBackButton = Window.getWINDOW_HEIGHT() - 100;

        name.setBounds(Window.getWINDOW_WIDTH() - 160, yOfGrades, 100, 80);
        grade.setBounds(Window.getWINDOW_WIDTH() - 310, yOfGrades, 100, 80);
        time.setBounds(Window.getWINDOW_WIDTH() - 500, yOfGrades, 100, 80);
        date.setBounds(Window.getWINDOW_WIDTH() - 650, yOfGrades, 100, 80);

        mainPanel.add(name);
        mainPanel.add(grade);
        mainPanel.add(time);
        mainPanel.add(date);


        for (int i = 0; i < usernamesList.size()-1; i++) {
            yOfGrades += 50;
            counter++;

            usernamesList.get(i).setBounds(Window.getWINDOW_WIDTH() - 160, yOfGrades, 100, 80);
            gradesList.get(i).setBounds(Window.getWINDOW_WIDTH() - 310, yOfGrades, 100, 80);
            timesList.get(i).setBounds(Window.getWINDOW_WIDTH() - 500, yOfGrades, 100, 80);
            datesList.get(i).setBounds(Window.getWINDOW_WIDTH() - 650, yOfGrades, 100, 80);


            if (counter >= 13) {
                heightOfBackButton += 50;
                heightOfPanel += 50;
                mainPanel.setPreferredSize(new Dimension(Window.getWINDOW_WIDTH(), heightOfPanel));
                backButton.setBounds(25, heightOfBackButton, 80, heightTitle - 20);

                repaint();
                revalidate();
                mainPanel.revalidate();
                mainPanel.repaint();
            }


        }


    }

    public JButton getBackButton(){
        return backButton;
    }


}
