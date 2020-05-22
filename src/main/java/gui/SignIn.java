package gui;

import dto.CustomerDTO;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignIn extends JFrame {

    private final String filepath = "customerList.txt";
    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;

    private JButton btnSignUp;
    private JButton btnBack;
    private JPasswordField edtCustomerPassword;
    private JTextField edtCustomerNumber;
    private JButton btnLogin;
    private JPanel SignInPanel;
    private JLabel txtErrorLabel;

    public SignIn() {
        super("Burunguz Bank APP");
        initComponents();
        btnLogin.addActionListener(e -> {
            int intCustomerNumber = Integer.parseInt(edtCustomerNumber.getText().trim());
            int intCustomerPassword = Integer.parseInt(String.valueOf(edtCustomerPassword.getPassword()));
            ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();
            try {
                fileInputStream = new FileInputStream(filepath);
                objectInputStream = new ObjectInputStream(fileInputStream);

            } catch (IOException var14) {
                Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, var14);
            }

            try {
                CustomerDTO customerDTO;

                while (true) {
                    customerDTO = (CustomerDTO) objectInputStream.readObject();
                    if (customerDTO == null) {
                        break;
                    }

                    customerDTOList.add(customerDTO);


                    for (int i = 0; i < customerDTOList.size(); i++) {
                        System.out.println(customerDTOList.get(i).getIntCustomerNumber());

                        if (intCustomerNumber == customerDTOList.get(i).getIntCustomerNumber()
                                && intCustomerPassword == customerDTOList.get(i).getIntCustomerPassword()) {


                            new UserMainScreen(customerDTOList, i);
                            dispose();

                        } else {
                            txtErrorLabel.setText("Giriş Başarısız.");
                        }
                    }
                }
            } catch (FileNotFoundException var15) {
                System.out.println("File not found");
            } catch (IOException var16) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException var17) {
                var17.printStackTrace();
            }
        });
        btnBack.addActionListener(e -> {

            new MainScreen();
            dispose();
        });
        btnSignUp.addActionListener(e -> {
            new SignUp();
            dispose();
        });
    }

    public static void main(String[] args) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignIn().setVisible(true);
            }
        });
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(SignInPanel);
        setVisible(true);
        pack();
    }
}
