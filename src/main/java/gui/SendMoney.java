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

public class SendMoney extends JFrame {

    private final String filepath = "customerList.txt";
    public CustomerDTO customer;
    public List<CustomerDTO> customerDTOList = new ArrayList();

    public FileOutputStream fileOutputStream = null;
    public ObjectOutputStream objectOutputStream = null;

    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;

    int intTotalReceiverMoney;
    int intReceiverIBAN;
    int personWhoReceiver;

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
                    for (int a = 0; a < customerDTOList.size(); a++) {
                        personWhoReceiver = a;
                    }
                }

            } catch (FileNotFoundException var13) {
                System.out.println("File not found");
            } catch (IOException var14) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException var15) {
                var15.printStackTrace();
            }
            btnSend.addActionListener(e -> {
                int intIban = Integer.parseInt(txtIBAN.getText().trim());
                int intMoney = Integer.parseInt(txtMoney.getText().trim());

                System.out.println("Buğranın" + customerDTOArrayList.get(i).getIntCustomerIBAN());
                System.out.println("Ahemetin" + customerDTOList.get(personWhoReceiver).getIntCustomerIBAN());
                if (intMoney > customerDTOArrayList.get(i).getIntCustomerBalance()) {
                    txtError.setText("Bakiyeniz Yeterli Değil. Bakiyeniz: "
                            + customerDTOArrayList.get(i).getIntCustomerBalance() + "TL");
                } else if (customerDTOList.get(personWhoReceiver).getIntCustomerIBAN() == intIban) {

                    customerDTOList.get(personWhoReceiver).setIntCustomerBalance(
                            intMoney + customerDTOList.get(personWhoReceiver).getIntCustomerBalance());
                    customerDTOArrayList.get(i).setIntCustomerBalance(
                            customerDTOArrayList.get(i).getIntCustomerBalance() - intMoney);
                } else {
                    txtError.setText("Iban Bulunamadı.");
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
                new UserMainScreen(customerDTOArrayList, i);
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
        getContentPane().add(pnlSendMoney);
        setVisible(true);
        pack();
    }
}