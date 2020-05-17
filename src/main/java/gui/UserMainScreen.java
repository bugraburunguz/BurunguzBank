package gui;

import dto.CustomerDTO;

import javax.swing.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UserMainScreen extends JFrame {

    private JLabel txtCustomerName;
    private JLabel txtCustomerNumber;
    private JLabel txtCustomerMoney;
    private JButton btnSendMoney;
    private JButton btnPayDebt;
    private JPanel pnlUserMainScreen;
    private JTextArea txtCustomerDebts;

    public UserMainScreen() {
        super("Burunguz Bank APP");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(pnlUserMainScreen);
        setVisible(true);
        String filepath = "customerList.txt";
        try (
                FileInputStream fileIn = new FileInputStream(filepath);
                ObjectInputStream objectInput = new ObjectInputStream(fileIn)
        ) {

            while (true) {
                CustomerDTO customerDTO = (CustomerDTO) objectInput.readObject();

                txtCustomerName.setText(customerDTO.getStrCustomerName() + " " + customerDTO.getStrCustomerSurname());
                txtCustomerDebts.setText(String.valueOf(customerDTO.getCustomerDebt()));
                txtCustomerMoney.setText(String.valueOf(customerDTO.getIntCustomerBalance()));
                txtCustomerNumber.setText(String.valueOf(customerDTO.getIntCustomerNumber()));
                System.out.print("ID: " + customerDTO.getIntCustomerID() + "\t");
                System.out.print("IBAN: TR" + customerDTO.getIntCustomerIBAN() + "\t");

                System.out.println("Surname: " + customerDTO.getStrCustomerSurname() + "\t");
                System.out.println("T.C: " + customerDTO.getIntIdentityNumber());
                System.out.println("Pone: " + customerDTO.getIntPhoneNumber());
                System.out.println("Customer Number: " + customerDTO.getIntCustomerNumber() + "\t");
                System.out.println("Customer Password: " + customerDTO.getIntCustomerPassword() + "\t");
                System.out.println("Balance: " + customerDTO.getIntCustomerBalance() + "\t");
            }

        } catch (EOFException eof) {
            System.out.println("Reached end of file");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UserMainScreen();
    }
}

