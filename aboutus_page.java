import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class aboutus_page extends JFrame {
    private final JButton backButton;
    public aboutus_page() {
        setTitle("About Us");
        setSize(1940, 1040);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("emptypage.png");
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1940, 1040);
        add(background);

        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        titleLabel.setBounds(800, 60, 500, 60);
        background.add(titleLabel);

        JTextArea aboutUsText = new JTextArea();
        aboutUsText.setText("With our online rent management service, handling your rental properties becomes effortless..\n"
                + "Automate rent collection, manage maintenance requests, and enhance tenant communication all in one place.\n"
                + "Say goodbye to paperwork and late payments, and hello to streamlined property management.\n"
                + "Get start today where we made great system.\n"
                + "Rent or offer your flat in our system!.\n\n"
                + "         Our contact of manager for offers:\n\n"
                + "Manager: Iskakov Yerassyl Kairatovich\n"
                + "Email: thisyera@gmail.com\n");
        aboutUsText.setFont(new Font("Arial", Font.PLAIN, 30));
        aboutUsText.setBounds(50, 200, 1500, 500);
        aboutUsText.setEditable(false);
        aboutUsText.setOpaque(false);
        background.add(aboutUsText);

        backButton = new JButton("Back to Main Page");
        backButton.setFont(new Font("Arial", Font.BOLD, 22));
        backButton.setSize(450, 40);
        backButton.setLocation(750, 750); // Adjust the location as needed
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

        setLayout(null);
    }

    public static void main(String[] args) {
        aboutus_page aboutUsPage = new aboutus_page();
        aboutUsPage.setVisible(true);
    }
}

