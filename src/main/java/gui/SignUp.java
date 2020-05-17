package gui;

import dto.CustomerDTO;
import enums.CompaniesDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ThreadLocalRandom;

public class SignUp extends JFrame {
    private final String filepath = "customerList.txt";
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
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(pnlSignUp);
        setVisible(true);
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int intCustomerPassword = Integer.parseInt(txtCustomerPassword.getText());
                    String strCustomerSurname = txtCustomerSurname.getText();
                    String strCustomerName = txtCustomerName.getText();
                    long intCustomerIdentityNumber = Long.parseLong(txtCustomerIdentityNumber.getText());
                    long intCustomerPhoneNumber = Long.parseLong(txtCustomerPhoneNumber.getText());
                    FileOutputStream fileOut = new FileOutputStream(filepath);
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

                    //Randomize
                    int customerID = ThreadLocalRandom.current().nextInt(1, 99999);
                    int customerIBAN = ThreadLocalRandom.current().nextInt(10000, 99999);
                    int customerNumber = ThreadLocalRandom.current().nextInt(10000, 99999);
                    //

                    CustomerDTO student = new CustomerDTO(customerID,
                            customerIBAN,
                            customerNumber,
                            intCustomerPassword,
                            strCustomerName,
                            strCustomerSurname, 0, intCustomerIdentityNumber, intCustomerPhoneNumber,
                            0, CompaniesDTO.NULL, 0);


                    objectOut.writeObject(student);
                    objectOut.close();
                    System.out.println("The Object  was succesfully written to a file");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainScreen();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
