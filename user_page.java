import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class user_page extends JFrame {

    private final String username;
    private final JList<String> rentlist;
    private final JTextField streetField;
    private final JTextField priceField;
    private final JComboBox<Integer> roomsComboBox;



    // userlar engizedi datany
    private final JTextField offerstreet;
    private final JTextField offerprice;
    private final JComboBox<Integer> offerRoomsComboBox;
    private final JTextField offerAreaField;
    private final JComboBox<String> offerFurnitureField;
    private final JComboBox<String> offerHouseTypeComboBox;
    private final JComboBox<String> offerConditionComboBox;
    private final JTextField phoneField;

    public user_page(String username) {
        this.username = username;

        setTitle("User Page");
        setSize(1940, 1040);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("emptypage.png");
        JLabel background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1940, 1040);
        add(background);

        JLabel titleLabel = new JLabel("Welcome to Pater.kz " + username);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setBounds(450, 40, 1200, 60);
        background.add(titleLabel);

        JLabel streetLabel = new JLabel("Street Name:");
        streetLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        streetLabel.setBounds(100, 150, 150, 30);
        background.add(streetLabel);

        streetField = new JTextField();
        streetField.setFont(new Font("Arial", Font.PLAIN, 20));
        streetField.setBounds(300, 150, 200, 30);
        background.add(streetField);

        JLabel priceLabel = new JLabel("Max price:");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        priceLabel.setBounds(100, 200, 150, 30);
        background.add(priceLabel);

        priceField = new JTextField();
        priceField.setFont(new Font("Arial", Font.PLAIN, 20));
        priceField.setBounds(300, 200, 150, 30);
        background.add(priceField);

        JLabel roomsLabel = new JLabel("Rooms:");
        roomsLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        roomsLabel.setBounds(100, 250, 70, 30);
        background.add(roomsLabel);

        Integer[] roomNumbers = {1, 2, 3, 4, 5, 6, 7};
        roomsComboBox = new JComboBox<>(roomNumbers);
        roomsComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        roomsComboBox.setBounds(230, 250, 70, 30);
        background.add(roomsComboBox);

        JButton filterButton = new JButton("Filter");
        filterButton.setFont(new Font("Arial", Font.BOLD, 30));
        filterButton.setBounds(400, 250, 150, 30);
        filterButton.setBackground(Color.BLUE);
        filterButton.setForeground(Color.white);
        filterButton.addActionListener(e -> filterHouses());
        background.add(filterButton);

        JButton sortButton = new JButton("Sort by price");
        sortButton.setFont(new Font("Arial", Font.BOLD, 25));
        sortButton.setBounds(600, 250, 210, 30); // Adjust the position as needed
        sortButton.setBackground(Color.BLUE);
        sortButton.setForeground(Color.white);
        sortButton.addActionListener(e -> sortbyprice());
        background.add(sortButton);

        JButton buyButton = new JButton("Buy House");
        buyButton.setFont(new Font("Arial", Font.BOLD, 30));
        buyButton.setBounds(100, 720, 300, 30);
        buyButton.setBackground(Color.GREEN);
        buyButton.addActionListener(e -> buyhouse());
        background.add(buyButton);



        rentlist = new JList<>(getRentalHousesData());
        rentlist.setFont(new Font("Arial", Font.PLAIN, 20));
        rentlist.setBounds(100, 300, 1200, 400);

    // PAAAANNNEEEELLLL
        JScrollPane scrollPane = new JScrollPane(rentlist);
        scrollPane.setBounds(100, 300, 1190, 400);
        background.add(scrollPane);


      // Offered by users
        JLabel offerLabel = new JLabel("Add your offer!");
        offerLabel.setFont(new Font("Arial", Font.BOLD, 45));
        offerLabel.setBounds(1430, 170, 500, 50);
        offerLabel.setForeground(Color.BLUE);
        background.add(offerLabel);

        JLabel offerStreetLabel = new JLabel("Street Name:");
        offerStreetLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerStreetLabel.setBounds(1300, 300, 150, 30);
        background.add(offerStreetLabel);

        offerstreet = new JTextField();
        offerstreet.setFont(new Font("Arial", Font.PLAIN, 25));
        offerstreet.setBounds(1500, 300, 200, 30);
        background.add(offerstreet);

        JLabel offerPriceLabel = new JLabel("Price:");
        offerPriceLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerPriceLabel.setBounds(1300, 350, 100, 30);
        background.add(offerPriceLabel);

        offerprice = new JTextField();
        offerprice.setFont(new Font("Arial", Font.PLAIN, 25));
        offerprice.setBounds(1500, 350, 150, 30);
        background.add(offerprice);

        JLabel offerRoomsLabel = new JLabel("Rooms:");
        offerRoomsLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerRoomsLabel.setBounds(1300, 450, 100, 30);
        background.add(offerRoomsLabel);

        offerRoomsComboBox = new JComboBox<>(roomNumbers);
        offerRoomsComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        offerRoomsComboBox.setBounds(1500, 450, 70, 30);
        background.add(offerRoomsComboBox);

        JLabel offerAreaLabel = new JLabel("Area:");
        offerAreaLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerAreaLabel.setBounds(1300, 400, 70, 30);
        background.add(offerAreaLabel);

        offerAreaField = new JTextField();
        offerAreaField.setFont(new Font("Arial", Font.PLAIN, 25));
        offerAreaField.setBounds(1500, 400, 150, 30);
        background.add(offerAreaField);

        JLabel offerFurnitureLabel = new JLabel("Furniture:");
        offerFurnitureLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerFurnitureLabel.setBounds(1300, 500, 120, 30);
        background.add(offerFurnitureLabel);

        String[] furniture= {"not equipped", "fully furnished", "partly furnished"};
        offerFurnitureField = new JComboBox<>(furniture);
        offerFurnitureField.setFont(new Font("Arial", Font.PLAIN, 25));
        offerFurnitureField.setBounds(1500, 500, 150, 30);
        background.add(offerFurnitureField);

        JLabel offerHouseTypeLabel = new JLabel("House Type:");
        offerHouseTypeLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerHouseTypeLabel.setBounds(1300, 550, 160, 30);
        background.add(offerHouseTypeLabel);

        String[] houseTypes = {"House", "Apartment"};
        offerHouseTypeComboBox = new JComboBox<>(houseTypes);
        offerHouseTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        offerHouseTypeComboBox.setBounds(1500, 550, 120, 30);
        background.add(offerHouseTypeComboBox);

        JLabel offerConditionLabel = new JLabel("Condition:");
        offerConditionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        offerConditionLabel.setBounds(1300, 600, 120, 30);
        background.add(offerConditionLabel);

        String[] conditions = {"New", "Used", "Renewed"};
        offerConditionComboBox = new JComboBox<>(conditions);
        offerConditionComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        offerConditionComboBox.setBounds(1500, 600, 120, 30);
        background.add(offerConditionComboBox);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        phoneLabel.setBounds(1300, 650, 100, 30);
        background.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setFont(new Font("Arial", Font.PLAIN, 25));
        phoneField.setBounds(1500, 650, 200, 30);
        background.add(phoneField);

        JButton submitOfferButton = new JButton("Add data");
        submitOfferButton.setFont(new Font("Arial", Font.BOLD, 30));
        submitOfferButton.setBounds(1400, 700, 180, 30);
        submitOfferButton.setBackground(Color.BLUE);
        submitOfferButton.setForeground(Color.white);
        submitOfferButton.addActionListener(e -> submitOffer());
        background.add(submitOfferButton);
    }

    // user offer functionsssssssssssssssss
    private void submitOffer() {
        String street = offerstreet.getText();
        String priceStr = offerprice.getText();
        int rooms = (int) offerRoomsComboBox.getSelectedItem();
        String areaStr = offerAreaField.getText();  // Get area as string
        String furniture = (String) offerFurnitureField.getSelectedItem();
        String houseType = (String) offerHouseTypeComboBox.getSelectedItem();
        String condition = (String) offerConditionComboBox.getSelectedItem();
        String phone = phoneField.getText();

        if (street.isEmpty() || priceStr.isEmpty() || areaStr.isEmpty() || furniture.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields.");
            return;
        }

        double price, area;
        try {
            price = Double.parseDouble(priceStr);
            area = Double.parseDouble(areaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter valid numbers for Price and Area.");
            return;
        }

        try {
            String dburl = "jdbc:postgresql://localhost:5432/ENU";
            String dbUsername = "postgres";
            String dbPassword = "admin";

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);

            String query = "INSERT INTO rentalhouses (haddress, rooms, area, furniture, house_type, condition, price, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, street);
            pst.setInt(2, rooms);
            pst.setDouble(3, area);  // Set area as double
            pst.setString(4, furniture);
            pst.setString(5, houseType);
            pst.setString(6, condition);
            pst.setDouble(7, price);
            pst.setString(8, phone);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Your offer submitted.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed.");
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void filterHouses() {
        String street = streetField.getText();
        String maxPriceStr = priceField.getText();
        double maxPrice = Double.MAX_VALUE;
        int selectedRooms = (int) roomsComboBox.getSelectedItem();

        if (!maxPriceStr.isEmpty()) {
            try {
                maxPrice = Double.parseDouble(maxPriceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Max Price.");
                return;
            }
        }

        ArrayList<String> filteredHouses = new ArrayList<>();

        try {
            String dburl = "jdbc:postgresql://localhost:5432/ENU";
            String dbUsername = "postgres";
            String dbPassword = "admin";

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);

            // fetch data
            String query = "SELECT haddress, rooms, area, furniture, house_type, condition, price, phone FROM rentalhouses WHERE haddress LIKE ? AND price <= ? AND rooms = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "%" + street + "%");
            pst.setDouble(2, maxPrice);
            pst.setInt(3, selectedRooms);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String houseInfo = String.format("Address: %s, Rooms: %d, Area: %s sqm, Furniture: %s, House Type: %s, Condition: %s, Price: %s, Phone: %s",
                        resultSet.getString("haddress"),
                        resultSet.getInt("rooms"),
                        resultSet.getString("area"),
                        resultSet.getString("furniture"),
                        resultSet.getString("house_type"),
                        resultSet.getString("condition"),
                        resultSet.getString("price"),
                        resultSet.getString("phone"));
                filteredHouses.add(houseInfo);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        rentlist.setListData(filteredHouses.toArray(new String[0]));
    }

    private String[] getRentalHousesData() {
        return filterhouse("", Double.MAX_VALUE).toArray(new String[0]);
    }

    private List<String> filterhouse(String street, double maxPrice) {
        List<String> houses = new ArrayList<>();

        try {
            String dburl = "jdbc:postgresql://localhost:5432/ENU";
            String dbUsername = "postgres";
            String dbPassword = "admin";

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);

            //  fetch data
            String query = "SELECT haddress, rooms, area, furniture, house_type, condition, price, phone FROM rentalhouses WHERE haddress LIKE ? AND price <= ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, "%" + street + "%");
            pst.setDouble(2, maxPrice);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String houseInfo = String.format("Address: %s, Rooms: %d, Area: %s sqm, Furniture: %s, House Type: %s, Condition: %s, Price: %s, Phone: %s",
                        resultSet.getString("haddress"),
                        resultSet.getInt("rooms"),
                        resultSet.getString("area"),
                        resultSet.getString("furniture"),
                        resultSet.getString("house_type"),
                        resultSet.getString("condition"),
                        resultSet.getString("price"),
                        resultSet.getString("phone"));
                houses.add(houseInfo);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return houses;
    }
    // buy houseee bottttonnn
    private void buyhouse() {
        int selectedIndex = rentlist.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Select a house to buy.");
            return;
        }

        String selectedHouseInfo = (String) rentlist.getSelectedValue();
        String[] houseParts = selectedHouseInfo.split(", ");
        String address = houseParts[0].substring(9);
        int rooms = Integer.parseInt(houseParts[1].substring(7));
        String priceStr = houseParts[6].substring(7);

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Failed");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to buy " + price + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String dburl = "jdbc:postgresql://localhost:5432/ENU";
                String dbUsername = "postgres";
                String dbPassword = "admin";

                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);

                String deleteQuery = "DELETE FROM rentalhouses WHERE haddress = ?";
                PreparedStatement deletePst = connection.prepareStatement(deleteQuery);
                deletePst.setString(1, address);
                int rowsDeleted = deletePst.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "House purchased successfully.");
                    // Refresh the list after purchase
                    rentlist.setListData(getRentalHousesData());
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to buy the house.");
                }

                deletePst.close();
                connection.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // sorttttt minnn
    private void sortbyprice() {
        ArrayList<String> sortedHouses = new ArrayList<>();

        try {
            String dburl = "jdbc:postgresql://localhost:5432/ENU";
            String dbUsername = "postgres";
            String dbPassword = "admin";

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dburl, dbUsername, dbPassword);

            String query = "SELECT haddress, rooms, area, furniture, house_type, condition, price, phone FROM rentalhouses ORDER BY price ASC";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String houseInfo = String.format("Address: %s, Rooms: %d, Area: %s sqm, Furniture: %s, House Type: %s, Condition: %s, Price: %s, Phone: %s",
                        resultSet.getString("haddress"),
                        resultSet.getInt("rooms"),
                        resultSet.getString("area"),
                        resultSet.getString("furniture"),
                        resultSet.getString("house_type"),
                        resultSet.getString("condition"),
                        resultSet.getString("price"),
                         resultSet.getString("phone"));
                sortedHouses.add(houseInfo);
            }

            pst.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        rentlist.setListData(sortedHouses.toArray(new String[0]));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            user_page userPage = new user_page("");
            userPage.setVisible(true);
        });
    }
}
