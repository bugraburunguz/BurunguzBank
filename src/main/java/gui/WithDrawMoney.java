package gui;

import dto.CustomerDTO;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WithDrawMoney extends JFrame {

    private final String filepath = "customerList.txt";
    public CustomerDTO customer;
    public List<CustomerDTO> customerDTOList = new ArrayList();

    public FileOutputStream fileOutputStream = null;
    public ObjectOutputStream objectOutputStream = null;

    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;

    private JPanel pnlWithdrawMoney;
    private JTextField txtMoney;
    private JButton btnSend;
    private JLabel txtError;

    public WithDrawMoney() {


    }

    public WithDrawMoney(ArrayList<CustomerDTO> customerDTOArrayList, int i) {
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
            System.out.println(customerDTOArrayList.get(i).getIntCustomerBalance());
            btnSend.addActionListener(e -> {
                if (Integer.parseInt(txtMoney.getText()) > customerDTOArrayList.get(i).getIntCustomerBalance()) {
                    txtError.setText("Çekmek istediğiniz miktar paranızdan yüksek" +
                            "Paranız: " + customerDTOArrayList.get(i).getIntCustomerBalance() + "TL");
                } else {
                    int totalMoney = customerDTOArrayList.get(i).getIntCustomerBalance() - Integer.parseInt(txtMoney.getText());
                    customerDTOArrayList.get(i).setIntCustomerBalance(totalMoney);
                    System.out.println(customerDTOArrayList.get(i).getIntCustomerBalance());

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
                }
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
        getContentPane().add(pnlWithdrawMoney);
        setVisible(true);
        pack();
    }
}