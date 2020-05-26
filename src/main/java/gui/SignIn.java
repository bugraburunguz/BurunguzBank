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
        //texti okuyup array lise yazdırma
        try {
            String filepath = "customerList.txt";
            fileInputStream = new FileInputStream(filepath);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();
        try {
            CustomerDTO customerDTO = null;

            while (true) {

                customerDTO = (CustomerDTO) objectInputStream.readObject();
                if (customerDTO == null) {
                    break;
                }

                customerDTOList.add(customerDTO);

            }
        } catch (FileNotFoundException var15) {
            System.out.println("File not found");
        } catch (IOException var16) {
            System.out.println("Error initializing stream");

        } catch (ClassNotFoundException var17) {
            var17.printStackTrace();
        }
        //
        btnLogin.addActionListener(e -> {

            int intCustomerNumber = Integer.parseInt(edtCustomerNumber.getText().trim());
            int intCustomerPassword = Integer.parseInt(String.valueOf(edtCustomerPassword.getPassword()));

            //liste içinde dönmek


            for (int i = 0; i < customerDTOList.size(); i++) {
                System.out.println(customerDTOList.get(i).getIntCustomerNumber());
                System.out.println(customerDTOList.get(i).getIntCustomerPassword());

                if (intCustomerNumber == customerDTOList.get(i).getIntCustomerNumber()
                        && intCustomerPassword == customerDTOList.get(i).getIntCustomerPassword()) {

                    new UserMainScreen(customerDTOList, i);//ilgili kişinin kullanıcısını unutmamak için
                    dispose();
                } else {
                    txtErrorLabel.setText("Hatalı Giriş Yaptınız.");

                }
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
        java.awt.EventQueue.invokeLater(() -> new SignIn().setVisible(true));
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(SignInPanel);
        setVisible(true);
        pack();
    }
}
