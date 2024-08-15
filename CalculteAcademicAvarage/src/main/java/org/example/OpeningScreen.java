package org.example;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OpeningScreen extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(43, 55, 59);
    private static final Color SECONDARY_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.decode("#01C38D");
    private final JLabel titleText;
    private final short xTitle = (short) (Window.getWINDOW_WIDTH()/3.5);
    private final short yTitle = (short) (Window.getWINDOW_HEIGHT()/6);
    private final short widthTitle = 550;
    private final byte heightTitle = 70;
    private final JTextField userName;
    private final JButton enterButton;
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/login_schema";
    public static final String DB_USERNAME = "root";
    public static final String PASSWORD = "Uriel052";
    public static final String DB_USERS_TABLE_NAME = "USERS";
    public static final String DB_GRADES_TABLE_NAME = "GRADES";


    public OpeningScreen(){
        setSize(Window.getWINDOW_WIDTH() , Window.getWINDOW_HEIGHT());
        setLayout(null);
        setBackground(PRIMARY_COLOR);

        titleText = new JLabel("חישוב ממוצע אקדמי");
        titleText.setBounds(xTitle - 25, yTitle, widthTitle, heightTitle);
        titleText.setFont(new Font("Arial" , Font.BOLD, 50));
        titleText.setForeground(TEXT_COLOR);
        add(titleText);

        userName = new JTextField();
        userName.setBounds(xTitle+50, yTitle+200, widthTitle-300, heightTitle-10);
        userName.setBackground(SECONDARY_COLOR);
        userName.setForeground(Color.BLACK);
        userName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.WHITE),
                        "הזן את השם שלך",
                        TitledBorder.RIGHT,
                        TitledBorder.DEFAULT_POSITION,
                        null,
                        TEXT_COLOR
                ),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        userName.setFont(new Font("Arial" , Font.BOLD , 15));
        userName.setHorizontalAlignment(JTextField.RIGHT);
        add(userName);

        enterButton = new JButton("המשך");
        enterButton.setBounds(xTitle+125, yTitle+300, widthTitle-450, heightTitle-20);
        enterButton.setFont(new Font("Arial" , Font.BOLD , 15));
        enterButton.setBackground(TEXT_COLOR);
        add(enterButton);

    }

    public JButton getEnterButton() {
        return enterButton;
    }
    public String getUserName() {
        return userName.getText();
    }
}
