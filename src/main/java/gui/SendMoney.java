package gui;

import dto.CustomerDTO;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class SendMoney extends JFrame {

    private final String filepath = "customerList.txt";

    public FileOutputStream fileOutputStream = null;
    public ObjectOutputStream objectOutputStream = null;

    private JTextField txtIBAN;
    private JPanel pnlSendMoney;
    private JTextField txtMoney;
    private JButton btnSend;
    private JLabel txtError;

    public SendMoney() {
    }

    public SendMoney(ArrayList<CustomerDTO> customerDTOArrayList, int i) {
        super("Burunguz Bank APP");
        initComponents();

        btnSend.addActionListener(e -> {
            int intIban = Integer.parseInt(txtIBAN.getText().trim());
            int intMoney = Integer.parseInt(txtMoney.getText().trim());

            System.out.println("Buğranın" + customerDTOArrayList.get(i).getIntCustomerIBAN());

            for (int a = 0; a < customerDTOArrayList.size(); a++) {
                System.out.println(customerDTOArrayList.get(a).getIntCustomerIBAN());
                System.out.println(customerDTOArrayList.get(a).getIntCustomerNumber());
                System.out.println(customerDTOArrayList.get(a).getStrCustomerName());
                if (intIban == customerDTOArrayList.get(a).getIntCustomerIBAN()) {
                    int receiverBalance = customerDTOArrayList.get(a).getIntCustomerBalance();
                    customerDTOArrayList.get(i).setIntCustomerBalance(customerDTOArrayList.get(i).getIntCustomerBalance() - intMoney);
                    customerDTOArrayList.get(a).setIntCustomerBalance(receiverBalance + intMoney);

                    try {
                        fileOutputStream = new FileOutputStream(new File(filepath));
                        objectOutputStream = new ObjectOutputStream(fileOutputStream);

                        for (CustomerDTO customerDTO : customerDTOArrayList) {
                            objectOutputStream.writeObject(customerDTO);
                            objectOutputStream.flush();
                        }
                    } catch (FileNotFoundException var7) {
                        System.out.println("File not found");
                    } catch (IOException var8) {
                        System.out.println("Error initializing stream");
                    }
                    new UserMainScreen(customerDTOArrayList, i);
                    dispose();
                } else {
                    txtError.setText("Aradığınız kişi bulunamadı");
                }
            }

            try {
                fileOutputStream = new FileOutputStream(new File(filepath));
                objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (CustomerDTO customerDTO : customerDTOArrayList) {
                    objectOutputStream.writeObject(customerDTO);
                    objectOutputStream.flush();
                }
            } catch (FileNotFoundException var7) {
                System.out.println("File not found");
            } catch (IOException var8) {
                System.out.println("Error initializing stream");
            }
        });
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
        getContentPane().add(pnlSendMoney);
        setVisible(true);
        pack();
    }
}