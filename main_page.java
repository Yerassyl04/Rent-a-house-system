import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_page extends JFrame {

    public main_page() {
        setTitle("Main Page");
        setSize(1940, 1040);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("mainpage.png"); // Ensure correct path
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1940, 1040);
        add(background);

        JLabel titleLabel = new JLabel("Pater - rent a flat");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 65));
        titleLabel.setBounds(200, 150, 800, 120);
        background.add(titleLabel);

        JButton signUpButton = new JButton("Registration");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBounds(100, 62, 200, 40);
        signUpButton.setBackground(Color.BLUE);
        signUpButton.setForeground(Color.white);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                sign_up signUpPage = new sign_up();
                signUpPage.setVisible(true);
            }
        });
        background.add(signUpButton);

        JButton signInButton = new JButton("Login");
        signInButton.setFont(new Font("Arial", Font.BOLD, 20));
        signInButton.setBounds(400, 62, 200, 40);
        signInButton.setBackground(Color.BLUE);
        signInButton.setForeground(Color.white);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                sign_in signInPage = new sign_in();
                signInPage.setVisible(true);
            }
        });
        background.add(signInButton);

        JButton aboutUsButton = new JButton("About Us");
        aboutUsButton.setFont(new Font("Arial", Font.BOLD, 20));
        aboutUsButton.setBounds(700, 62, 200, 40);
        aboutUsButton.setBackground(Color.BLUE);
        aboutUsButton.setForeground(Color.white);

        aboutUsButton .addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    aboutus_page signInPage = new aboutus_page();
                    signInPage.setVisible(true);
                }
            });
        background.add(aboutUsButton );

        JButton contactsButton = new JButton("Contacts");
        contactsButton.setFont(new Font("Arial", Font.BOLD, 20));
        contactsButton.setBounds(1000, 62, 200, 40);
        contactsButton.setBackground(Color.BLUE);
        contactsButton.setForeground(Color.white);
        contactsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This is the Contacts page.\nYou can reach out to us at contact@example.com.");
            }
        });
        background.add(contactsButton);

        JButton newsButton = new JButton("News");
        newsButton.setFont(new Font("Arial", Font.BOLD, 20));
        newsButton.setBounds(1300, 62, 200, 40);
        newsButton.setBackground(Color.BLUE);
        newsButton.setForeground(Color.white);
        newsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This is the News page.\nStay tuned for our latest updates.");
            }
        });
        background.add(newsButton);

        JLabel rentFlatLabel = new JLabel("Rent a Flat with our system!" +"\n Best functions and filters");
        rentFlatLabel.setFont(new Font("Arial", Font.BOLD, 30));
        rentFlatLabel.setBounds(100, 400, 800, 80);
        rentFlatLabel.setForeground(Color.black);
        background.add(rentFlatLabel);


        JLabel tenantFlatLabel = new JLabel("Rent apartments with lower price in market");
        tenantFlatLabel.setFont(new Font("Arial", Font.BOLD, 30));
        tenantFlatLabel.setBounds(100, 500, 900, 80);
        tenantFlatLabel.setForeground(Color.black);
        background.add(tenantFlatLabel);

        setLayout(null);
    }

    public static void main(String[] args) {
        main_page mainPage = new main_page();
        mainPage.setVisible(true);
    }
}
