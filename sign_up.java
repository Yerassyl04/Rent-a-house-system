import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class sign_up extends JFrame {
    private final JButton backButton; // back to main page

    private final Container container;
    private final JLabel background;
    private final JLabel title1;
    private final JLabel sName;
    private final JTextField tsName;
    private final JLabel email1;
    private final JTextField temail1;
    private final JLabel gender;
    private final JRadioButton male;
    private final JRadioButton female;
    private final ButtonGroup genGrp;
    private final JLabel dob;
    private final JComboBox<String> date;
    private final JComboBox<String> month;
    private final JComboBox<String> year;
    private final JLabel password1;
    private final JPasswordField tpassword1;
    private final JCheckBox term;
    private final JButton signUpButton;

    private static Connection connection = null;
    private static PreparedStatement pst = null;

    private static final String dburl = "jdbc:postgresql://localhost:5432/ENU";

    private static final String username = "postgres";  // user
    private static final String password = "admin";  // password

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


    public sign_up() {

        connect();

        setTitle("Sign Up Form");
        setSize(1940, 1040);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        ImageIcon backgroundImage = new ImageIcon("regpage.png");
        background = new JLabel(backgroundImage);
        background.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        container.add(background);

        title1 = new JLabel("Registration");
        title1.setSize(500, 120);
        title1.setLocation(300, 150);
        title1.setFont(new Font("Arial", Font.BOLD, 65));
        title1.setForeground(Color.BLUE);
        background.add(title1);

        sName = new JLabel(" Name");
        sName.setSize(500,30);
        sName.setLocation(150,300);
        sName.setFont(new Font("Arial",Font.BOLD,20));
        background.add(sName);

        tsName = new JTextField();
        tsName.setSize(300,30);
        tsName.setLocation(300,300);
        tsName.setFont(new Font("Arial",Font.PLAIN,20));
        background.add(tsName);

        email1 = new JLabel(" Email");
        email1.setSize(500,30);
        email1.setLocation(150,350);
        email1.setFont(new Font("Arial",Font.BOLD,20));
        background.add(email1);

        temail1 = new JTextField();
        temail1.setSize(300,30);
        temail1.setLocation(300,350);
        temail1.setFont(new Font("Arial",Font.PLAIN,20));
        background.add(temail1);

        gender = new JLabel(" Gender");
        gender.setSize(500,30);
        gender.setLocation(150,400);
        gender.setFont(new Font("Arial",Font.BOLD,20));
        background.add(gender);

        male = new JRadioButton("Male");
        male.setSize(150,50);
        male.setLocation(300,390);
        male.setFont(new Font("Arial",Font.BOLD,20));
        male.setBackground(Color.WHITE);
        background.add(male);

        female = new JRadioButton("Female");
        female.setSize(150,50);
        female.setLocation(450,390);
        female.setFont(new Font("Arial",Font.BOLD,20));
        female.setBackground(Color.WHITE);
        background.add(female);


        genGrp = new ButtonGroup();
        genGrp.add(male);
        genGrp.add(female);
        background.setBackground(Color.WHITE);

        dob = new JLabel(" Birthday");
        dob.setSize(500,30);
        dob.setLocation(150,450);
        dob.setFont(new Font("Arial",Font.BOLD,20));
        background.add(dob);

        String[] dates = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};

        String[] months = {"January", "February", "March", "April", "May",
                "Jun", "July", "August", "September", "October", "November",
                "December"};

        String[] years = {"1900","1910","1920","1930","1940","1950","1960","1970","1980","1990","1991","1992","1993","1994","1995", "1996", "1997", "1998", "1999",
                "2000", "2001","2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013","2014","2015","2016"};

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial",Font.PLAIN,20));
        date.setSize(60,30);
        date.setLocation(300,450);
        background.add(date);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial",Font.PLAIN,20));
        month.setSize(120,30);
        month.setLocation(360,450);
        background.add(month);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial",Font.PLAIN,20));
        year.setSize(80,30);
        year.setLocation(480,450);
        background.add(year);

        password1 = new JLabel(" Password");
        password1.setSize(500,30);
        password1.setLocation(150,500);
        password1.setFont(new Font("Arial",Font.BOLD,20));
        background.add(password1);

        tpassword1 = new JPasswordField();
        tpassword1.setSize(300,30);
        tpassword1.setLocation(300,500);
        tpassword1.setFont(new Font("Arial",Font.PLAIN,20));
        background.add(tpassword1);

        term = new JCheckBox("Accept all Terms and Conditions");
        term.setFont(new Font("Arial",Font.PLAIN,19));
        term.setSize(390,20);
        term.setLocation(150,560);
        term.setBackground(Color.WHITE);
        background.add(term);

        signUpButton = new JButton("Register");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 22));
        signUpButton.setSize(450, 40);
        signUpButton.setLocation(150, 610);
        signUpButton.setBackground(Color.BLUE);
        signUpButton.setForeground(Color.white);
        background.add(signUpButton);

        backButton = new JButton("Back to Main Page");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setSize(450, 40);
        backButton.setLocation(150, 660);
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

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Name, Email, Gender, Birthday, Password;

                if (term.isSelected()) {
                    Name = tsName.getText();
                    Email = temail1.getText();
                    if (male.isSelected()) {
                        Gender = "Male";
                    } else {
                        Gender = "Female";
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date selectedDate = null;
                    try {
                        selectedDate = sdf.parse(year.getSelectedItem() + "-" + (month.getSelectedIndex() + 1) + "-" + date.getSelectedItem());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());


                    Password = String.valueOf(tpassword1.getPassword());

                    try {
                        pst = connection.prepareStatement("insert into users(Username,Email,Gender,Bod,Password)values(?,?,?,?,?)");
                        pst.setString(1, Name);
                        pst.setString(2, Email);
                        pst.setString(3, Gender);
                        pst.setDate(4, sqlDate);
                        pst.setString(5, Password);

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "New user has been created.");
                    } catch (SQLException except) {
                        except.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please confirm with Terms and Conditions");
                }
            }
        });


    }

    public static void main(String[] args) {
        sign_up signUpFrame = new sign_up();
        signUpFrame.setVisible(true);
    }
}
