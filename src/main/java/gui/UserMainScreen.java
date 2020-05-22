package gui;

import dto.CustomerDTO;
import enums.CompaniesDTO;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class UserMainScreen extends JFrame {

    private final String filepath = "customerList.txt";
    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;

    private JLabel txtCustomerName;
    private JLabel txtCustomerNumber;
    private JLabel txtCustomerMoney;
    private JButton btnSendMoney;
    private JButton btnPayDebt;
    private JPanel pnlUserMainScreen;
    private JTextArea txtCustomerDebts;
    private JButton btnDepositMoney;
    private JButton btnBackButton;
    private JButton btnWithdrawMoney;
    private JLabel txtIBAN;
    private JLabel txtError;

    public UserMainScreen() {
        btnWithdrawMoney.addActionListener(e -> {
            new WithDrawMoney();
            dispose();
        });

    }

    public UserMainScreen(ArrayList<CustomerDTO> customerDTOArrayList, int i) {
        super("Burunguz Bank APP");
        initComponents();

        txtCustomerName.setText("Kullanıcı Adı: " + customerDTOArrayList.get(i).getStrCustomerName() + " "
                + customerDTOArrayList.get(i).getStrCustomerSurname());

        if (customerDTOArrayList.get(i).companiesDTO == CompaniesDTO.NULL) {

            txtCustomerDebts.setText("Borcunuz Bulunmamaktadır ");

        } else {
            txtCustomerDebts.setText("Firma: " + customerDTOArrayList.get(i).companiesDTO.name()
                    + "\t" + "Borcunuz: " + customerDTOArrayList.get(i).intCustomerdebt + "TL");
        }
        txtIBAN.setText("IBAN: TR " + customerDTOArrayList.get(i).getIntCustomerIBAN());
        txtCustomerMoney.setText("Bakiye: " + customerDTOArrayList.get(i).getIntCustomerBalance() + "TL");
        txtCustomerNumber.setText("Müşteri Numarası: " + customerDTOArrayList.get(i).getIntCustomerNumber());

        btnDepositMoney.addActionListener(e -> {
            new DepositMoney(customerDTOArrayList, i);
            dispose();
        });
        btnWithdrawMoney.addActionListener(e -> {
            new WithDrawMoney(customerDTOArrayList, i);
            dispose();
        });

        btnSendMoney.addActionListener(e -> {
            new SendMoney();
            dispose();
        });
        if (customerDTOArrayList.get(i).companiesDTO == CompaniesDTO.NULL) {
            btnPayDebt.addActionListener(e -> {
                txtError.setText("Borç bilginiz bulunamadı.");
            });
        } else if (customerDTOArrayList.get(i).intCustomerdebt > customerDTOArrayList.get(i).getIntCustomerBalance()) {
            txtError.setText("Bakiye Yeterli Değil");

        } else {
            btnPayDebt.addActionListener(e -> {
                new PayDebt(customerDTOArrayList, i);
                dispose();
            });
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new UserMainScreen().setVisible(true));
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(pnlUserMainScreen);
        setVisible(true);
        pack();

        btnBackButton.addActionListener(e -> {
            new MainScreen();
            dispose();
        });


    }
}

