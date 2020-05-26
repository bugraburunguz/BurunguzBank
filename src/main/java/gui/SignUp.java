package gui;

import dto.CustomerDTO;
import enums.CompaniesDTO;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUp extends JFrame {

    private final String filepath = "customerList.txt";
    public CustomerDTO customer;
    public List<CustomerDTO> customerDTOList = new ArrayList<>();

    public FileOutputStream fileOutputStream = null;
    public ObjectOutputStream objectOutputStream = null;

    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;

    private JPanel pnlSignUp;
    private JTextField txtCustomerName;
    private JTextField txtCustomerSurname;
    private JTextField txtCustomerIdentityNumber;
    private JTextField txtCustomerPhoneNumber;
    private JTextField txtCustomerPassword;
    private JButton btnSignUp;
    private JButton btnBack;

    public SignUp() {
        super("Burunguz Bank APP");
        initComponents();

        Path path = Paths.get(filepath);
        if (Files.exists(path)) {
            // file exist
            try {
                fileInputStream = new FileInputStream(filepath);
                objectInputStream = new ObjectInputStream(fileInputStream);
            } catch (IOException ex) {
                Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                customer = null;
                while (true) {
                    customer = (CustomerDTO) objectInputStream.readObject();
                    if (customer == null) {
                        break;
                    }
                    customerDTOList.add(customer);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
            }
        }

        btnBack.addActionListener(e -> {
            try {
                fileOutputStream = new FileOutputStream(new File(filepath));
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for (CustomerDTO pi : customerDTOList) {
                    objectOutputStream.writeObject(pi);
                    objectOutputStream.flush();
                }

            } catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
            } catch (IOException ioe) {
                System.out.println("Error initializing stream");
            }

            try {
                objectOutputStream.close();
                fileOutputStream.close();

            } catch (IOException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            new MainScreen();
            dispose();
        });
        btnSignUp.addActionListener(e -> {
            String stringCustomerName = txtCustomerName.getText().trim();
            String stringCustomerSurname = txtCustomerSurname.getText().trim().toUpperCase();
            long longCustomerIdentityNumber = Long.parseLong(txtCustomerIdentityNumber.getText().trim());
            long longCustomerPhoneNumber = Long.parseLong(txtCustomerPhoneNumber.getText().trim());
            int intCustomerPassword = Integer.parseInt(txtCustomerPassword.getText().trim());

            //Randomize
            int customerID = ThreadLocalRandom.current().nextInt(1, 99999);
            int customerIBAN = ThreadLocalRandom.current().nextInt(10000, 99999);
            int customerNumber = ThreadLocalRandom.current().nextInt(10000, 99999);
            //
            customer = null;
            customer = new CustomerDTO(customerID,
                    customerIBAN,
                    customerNumber,
                    intCustomerPassword,
                    stringCustomerName,
                    stringCustomerSurname,
                    0,
                    longCustomerIdentityNumber,
                    longCustomerPhoneNumber, 1, CompaniesDTO.NULL, 1);

            customerDTOList.add(customer);


            txtCustomerIdentityNumber.setText("");
            txtCustomerName.setText("");
            txtCustomerPassword.setText("");
            txtCustomerPhoneNumber.setText("");
            txtCustomerSurname.setText("");
        });

    }

    public static void main(String[] args) {
        new SignUp();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(pnlSignUp);
        setVisible(true);
        pack();
    }

}






