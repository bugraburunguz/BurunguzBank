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
    public List<CustomerDTO> customerDTOList = new ArrayList();

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
            try {
                fileInputStream = new FileInputStream(filepath);
                objectInputStream = new ObjectInputStream(fileInputStream);
            } catch (IOException var12) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, var12);
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
            } catch (FileNotFoundException var13) {
                System.out.println("File not found");
            } catch (IOException var14) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException var15) {
                var15.printStackTrace();
            }
        }


        btnBack.addActionListener(e -> {
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


            try {
                fileOutputStream = new FileOutputStream(new File(filepath));
                objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (CustomerDTO customerDTO : customerDTOList) {
                    objectOutputStream.writeObject(customerDTO);
                    objectOutputStream.flush();
                }
            } catch (FileNotFoundException var7) {
                System.out.println("File not found");
            } catch (IOException var8) {
                System.out.println("Error initializing stream");
            }

            try {
                objectOutputStream.close();
                fileOutputStream.close();

            } catch (IOException var6) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, var6);
            }


        });

    }

    public static void main(String[] args) {
        new SignUp();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(pnlSignUp);
        setVisible(true);
        pack();
    }
}






