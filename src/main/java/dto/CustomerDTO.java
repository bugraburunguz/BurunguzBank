package dto;

import enums.CompaniesDTO;
import gui.MainScreen;
import gui.SignUp;
import interfaces.Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDTO extends DebtDTO implements Serializable, Controller {
    private static final long serialVersionUID = 6846058699968947809L;

    private final String filepath = "customerList.txt";
    public CustomerDTO customer;
    public List<CustomerDTO> customerDTOList = new ArrayList<>();

    public FileOutputStream fileOutputStream = null;
    public ObjectOutputStream objectOutputStream = null;

    public FileInputStream fileInputStream = null;
    public ObjectInputStream objectInputStream = null;


    private int intCustomerID;
    private int intCustomerIBAN;
    private int intCustomerNumber;
    private int intCustomerPassword;
    private String strCustomerName;
    private String strCustomerSurname;
    private int intCustomerBalance;
    private long intIdentityNumber;
    private long intPhoneNumber;
    /*
     * Ad Soyad: Buğra Bürüngüz
     *
     * Müşteri No: 24032
     *
     * IBAN: 71033
     *
     * Ad Soyad: Ahmet Kaya
     *
     * Müşteri No: 26303
     *
     * IBAN: 97209
     *
     *
     * */

    public CustomerDTO(int intCustomerID,
                       int intCustomerIBAN,
                       int intCustomerNumber,
                       int intCustomerPassword,
                       String strCustomerName,
                       String strCustomerSurname,
                       int intCustomerBalance,
                       long intIdentityNumber,
                       long intPhoneNumber,
                       int intDebtID,
                       CompaniesDTO companiesDTO,
                       int intCustomerDebt) {

        super(intDebtID, intCustomerDebt, companiesDTO);

        this.intCustomerID = intCustomerID;
        this.intCustomerIBAN = intCustomerIBAN;
        this.intCustomerNumber = intCustomerNumber;
        this.intCustomerPassword = intCustomerPassword;
        this.strCustomerName = strCustomerName;
        this.strCustomerSurname = strCustomerSurname;
        this.intCustomerBalance = intCustomerBalance;
        this.intIdentityNumber = intIdentityNumber;
        this.intPhoneNumber = intPhoneNumber;

    }

    public int getIntCustomerID() {
        return intCustomerID;
    }

    public void setIntCustomerID(int intCustomerID) {
        this.intCustomerID = intCustomerID;
    }

    public int getIntCustomerIBAN() {
        return intCustomerIBAN;
    }

    public void setIntCustomerIBAN(int intCustomerIBAN) {
        this.intCustomerIBAN = intCustomerIBAN;
    }

    public String getStrCustomerName() {
        return strCustomerName;
    }

    public void setStrCustomerName(String strCustomerName) {
        this.strCustomerName = strCustomerName;
    }

    public String getStrCustomerSurname() {
        return strCustomerSurname;
    }

    public void setStrCustomerSurname(String strCustomerSurname) {
        this.strCustomerSurname = strCustomerSurname;
    }

    public int getIntCustomerBalance() {
        return intCustomerBalance;
    }

    public void setIntCustomerBalance(int intCustomerBalance) {
        this.intCustomerBalance = intCustomerBalance;
    }

    public int getIntCustomerPassword() {
        return intCustomerPassword;
    }

    public void setIntCustomerPassword(int intCustomerPassword) {
        this.intCustomerPassword = intCustomerPassword;
    }

    public int getIntCustomerNumber() {
        return intCustomerNumber;
    }

    public void setIntCustomerNumber(int intCustomerNumber) {
        this.intCustomerNumber = intCustomerNumber;
    }

    public long getIntIdentityNumber() {
        return intIdentityNumber;
    }

    public void setIntIdentityNumber(long intIdentityNumber) {
        this.intIdentityNumber = intIdentityNumber;
    }

    public long getIntPhoneNumber() {
        return intPhoneNumber;
    }

    public void setIntPhoneNumber(long intPhoneNumber) {
        this.intPhoneNumber = intPhoneNumber;
    }

    private void readObject(ObjectInputStream test) throws IOException, ClassNotFoundException {
        test.defaultReadObject();
    }

    public void signUp(int intCustomerPassword,
                       String stringCustomerName,
                       String stringCustomerSurname,
                       long longCustomerIdentityNumber,
                       long longCustomerPhoneNumber

    ) {
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


        //Randomize
        int customerID = ThreadLocalRandom.current().nextInt(1, 99999);
        int customerIBAN = ThreadLocalRandom.current().nextInt(10000, 99999);
        int intCustomerNumber = ThreadLocalRandom.current().nextInt(10000, 99999);
        //
        customer = new CustomerDTO(customerID,
                customerIBAN,
                intCustomerNumber,
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

    }

}
