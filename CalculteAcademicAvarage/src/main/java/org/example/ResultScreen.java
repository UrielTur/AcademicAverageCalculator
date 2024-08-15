package org.example;
import javax.swing.*;
import java.awt.*;

public class ResultScreen extends JPanel {
    private final JLabel titleText;
    private final short xTitle = (short) (Window.getWINDOW_WIDTH()/6);
    private final short yTitle = (short) (Window.getWINDOW_HEIGHT()/6);
    private final short widthTitle = 750;
    private final byte heightTitle = 80;
    private final JLabel gradeTotal;
    private final JButton historyButton;
    private final JButton backButton;
    private final Color gray = new Color(43, 55, 59);


    public ResultScreen() {
        setSize(Window.getWINDOW_WIDTH(), Window.getWINDOW_HEIGHT());
        setLayout(null);
        setBackground(gray);

        titleText = new JLabel("הציון הממוצע האקדמי שלך: ");
        titleText.setBounds(xTitle, yTitle, widthTitle, heightTitle);
        titleText.setFont(new Font("Arial", Font.BOLD, 45));
        titleText.setForeground(Color.white);
        add(titleText);

        gradeTotal = new JLabel();
        gradeTotal.setBounds(xTitle - 150, yTitle + 100, widthTitle - 300, heightTitle - 20);
        gradeTotal.setFont(new Font("Arial", Font.BOLD, 30));
        gradeTotal.setHorizontalAlignment(JTextField.RIGHT);
        gradeTotal.setForeground(Color.white);
        add(gradeTotal);

        backButton = new JButton("חזור");
        backButton.setBounds(xTitle + 125, yTitle + 300, widthTitle - 450, heightTitle - 20);
        backButton.setFont(new Font("Arial" , Font.BOLD , 19));
        backButton.setBackground(new Color(52 ,204, 255));
        backButton.setForeground(Color.WHITE);
        add(backButton);

        historyButton = new JButton("היסטוריה");
        historyButton.setBounds(xTitle + 125, yTitle + 400, widthTitle - 450, heightTitle - 20);
        historyButton.setFont(new Font("Arial" , Font.BOLD , 19));
        historyButton.setForeground(Color.WHITE);
        historyButton.setBackground(new Color(218, 160, 7));
        add(historyButton);

    }

    public void setGradeTotal(float gradeTotal) {
        this.gradeTotal.setText(String.valueOf(gradeTotal));
    }

    public JButton getBackButton(){
        return backButton;
    }

    public JButton getHistoryButton() {
        return historyButton;
    }
}
