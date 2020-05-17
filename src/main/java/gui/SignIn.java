package gui;

import dto.CustomerDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SignIn extends JFrame {
    private final String filepath = "customerList.txt";

    private JButton btnSignUp;
    private JButton btnBack;
    private JPasswordField edtCustomerPassword;
    private JTextField edtCustomerNumber;
    private JButton btnLogin;
    private JPanel SignInPanel;
    private JLabel txtErrorLabel;

    public SignIn() {
        super("Burunguz Bank APP");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(SignInPanel);
        setVisible(true);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int intCustomerNumber = Integer.parseInt(edtCustomerNumber.getText());
                int intCustomerPassword = Integer.parseInt(edtCustomerPassword.getText());
                try (
                        FileInputStream fileIn = new FileInputStream(filepath);
                        ObjectInputStream objectInput = new ObjectInputStream(fileIn)
                ) {

                    while (true) {
                        CustomerDTO customerDTO = (CustomerDTO) objectInput.readObject();

                        if (customerDTO.getIntCustomerNumber() == intCustomerNumber
                                && customerDTO.getIntCustomerPassword() == intCustomerPassword) {

                            System.out.println("Giriş Yapıldı");

                        } else {
                            txtErrorLabel.setText("Giriş Başarısız");
                            System.out.println("giriş başarısız!!");


                        }
                    }
                } catch (EOFException eof) {
                    System.out.println("Reached end of file");
                } catch (IOException | ClassNotFoundException ex) {
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
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new SignIn();

    }
}
