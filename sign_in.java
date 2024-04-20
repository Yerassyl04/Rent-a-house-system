import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class sign_in extends JFrame {
    private final JButton backButton; // back to main page

    private final Container container;
    private final JLabel background;
    private final JLabel title2;
    private final JLabel email2;
    private final JTextField temail2;
    private final JLabel password2;
    private final JPasswordField tpassword2;
    private final JButton signInButton;

    private static Connection connection = null;
    private static PreparedStatement pst = null;

    private static final String dburl = "jdbc:postgresql://localhost:5432/ENU";
    private static final String username = "postgres";
    private static final String password = "admin";

    public void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dburl, username, password);
            System.out.println("Connection is Established successfully");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public sign_in(){

        connect();

        setTitle("Sign In Form");
        setSize(1940, 1040);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        ImageIcon backgroundImage = new ImageIcon("loginpage.png");
        background = new JLabel(backgroundImage);
        background.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        container.add(background);

        title2 = new JLabel("Login");
        title2.setSize(500,120);
        title2.setLocation(300,150);
        title2.setFont(new Font("Arial",Font.BOLD,65));
        title2.setForeground(Color.BLUE);
        background.add(title2);

        email2 = new JLabel(" Email");
        email2.setSize(500,30);
        email2.setLocation(150,300);
        email2.setFont(new Font("Arial",Font.BOLD,20));
        background.add(email2);

        temail2 = new JTextField();
        temail2.setSize(300,30);
        temail2.setLocation(300,300);
        temail2.setFont(new Font("Arial",Font.PLAIN,20));
        background.add(temail2);

        password2 = new JLabel(" Password");
        password2.setSize(500,30);
        password2.setLocation(150,350);
        password2.setFont(new Font("Arial",Font.BOLD,20));
        background.add(password2);

        tpassword2 = new JPasswordField();
        tpassword2.setSize(300,30);
        tpassword2.setLocation(300,350);
        tpassword2.setFont(new Font("Arial",Font.PLAIN,20));
        background.add(tpassword2);

        signInButton = new JButton("Login");
        signInButton.setFont(new Font("Arial", Font.BOLD, 22));
        signInButton.setSize(450, 40);
        signInButton.setLocation(150, 400);
        signInButton.setBackground(Color.BLUE);
        signInButton.setForeground(Color.white);
        background.add(signInButton);

        backButton = new JButton("Back to Main Page");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setSize(450, 40);
        backButton.setLocation(150, 450);
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.white);
        background.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                main_page mainPageFrame = new main_page();
                mainPageFrame.setVisible(true);
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = temail2.getText();
                String Password = String.valueOf(tpassword2.getPassword());

                if (authenticateUser(Email, Password)) {
                    String username = getUsernameFromDatabase(Email); // Retrieve username from database
                    dispose();
                    user_page userPage = new user_page(username); // Passing the username to user_page
                    userPage.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Email or Password");
                }
            }
        });


    }

    private String getUsernameFromDatabase(String email) {
        try {
            String query = "SELECT Username FROM users WHERE Email=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Username");
            } else {
                return "User";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "User";
        }
    }

    public boolean authenticateUser(String email, String password) {
        try {
            String query = "SELECT * FROM users WHERE Email=? AND Password=?";
            pst = connection.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        sign_in signInFrame = new sign_in();
        signInFrame.setVisible(true);
    }
}

