import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagement {
        public static void main(String[] args) {
            Inventory inventory = new Inventory();
            JFrame frame = new JFrame("Inventory Management");
            frame.setSize(800, 600);

            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(15);
            JLabel priceLabel = new JLabel("Price:");
            JTextField priceField = new JTextField(10);
            JLabel quantityLabel = new JLabel("Quantity:");
            JTextField quantityField = new JTextField(5);

            JButton addButton = new JButton("Add Product");
            JButton updateButton = new JButton("Update Product");
            JButton displayButton = new JButton("Display Products");

            JTextArea displayArea = new JTextArea(10, 30);
            displayArea.setEditable(false);

            JPanel panel = new JPanel();
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(priceLabel);
            panel.add(priceField);
            panel.add(quantityLabel);
            panel.add(quantityField);
            panel.add(addButton);
            panel.add(updateButton);
            panel.add(displayButton);
            panel.add(displayArea);

            frame.add(panel);
            frame.setVisible(true);
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String priceText = priceField.getText();
                    String quantityText = quantityField.getText();

                    if (priceText.matches("\\d+") && quantityText.matches("\\d+")) {
                        double price = Double.parseDouble(priceText);
                        int quantity = Integer.parseInt(quantityText);

                        if (inventory.addProduct(name, price, quantity)) {
                            displayArea.setText("Product added successfully.");
                        } else {
                            displayArea.setText("Invalid product name or inventory full.");
                        }
                    } else {
                        displayArea.setText("Price and Quantity must be numeric.");
                    }
                }
            });


            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String priceText = priceField.getText();
                    String quantityText = quantityField.getText();

                    if (priceText.matches("\\d+") && quantityText.matches("\\d+")) {
                        double price = Double.parseDouble(priceText);
                        int quantity = Integer.parseInt(quantityText);

                        if (inventory.addProduct(name, price, quantity)) {
                            displayArea.setText("Product updated successfully.");
                        } else {
                            displayArea.setText("Inventory is full! Cannot add more products.");
                        }
                    } else {
                        displayArea.setText("Price and Quantity must be numeric.");
                    }
                }
            });


            displayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String products = inventory.displayAllProducts();
                    displayArea.setText(products);
                }
            });
        }
    }


    class Product{
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String displayProductDetails() {
        return "Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}

class Inventory {
    private Product p1;
    private Product p2;
    private Product p3;

    public Inventory() {
        p1 = null;
        p2 = null;
        p3 = null;
    }

    public boolean addProduct(String name, double price, int quantity) {

        if (p1 != null && p1.getName().equals(name)) {
            p1.setPrice(price);
            p1.setQuantity(quantity);
            return true;
        } else if (p2 != null && p2.getName().equals(name)) {
            p2.setPrice(price);
            p2.setQuantity(quantity);
            return true;
        } else if (p3 != null && p3.getName().equals(name)) {
            p3.setPrice(price);
            p3.setQuantity(quantity);
            return true;
        } else if (p1 == null) {
            p1 = new Product(name, price, quantity);
            return true;
        } else if (p2 == null) {
            p2 = new Product(name, price, quantity);
            return true;
        } else if (p3 == null) {
            p3 = new Product(name, price, quantity);
            return true;
        }
        return false;
    }

    public String displayAllProducts() {
        String result = "";
        if (p1 != null) result += p1.displayProductDetails();
        if (p2 != null) result += p2.displayProductDetails();
        if (p3 != null) result += p3.displayProductDetails();
        return result;
    }
}
