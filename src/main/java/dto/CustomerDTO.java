package dto;

import enums.CompaniesDTO;
import gui.SignIn;
import interfaces.Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDTO extends DebtDTO implements Serializable, Controller {
    private static final long serialVersionUID = 6846058699968947809L;

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

    public boolean login(int intCustomerNumber, int intCustomerPassword) {
        final String filepath = "customerList.txt";
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        ArrayList<CustomerDTO> customerDTOList = new ArrayList<>();
        CustomerDTO customerDTO;
        try {
            fileInputStream = new FileInputStream(filepath);
            objectInputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException var14) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, var14);
        }

        try {

            while (true) {
                customerDTO = (CustomerDTO) objectInputStream.readObject();
                if (customerDTO == null) {
                    break;
                }

                customerDTOList.add(customerDTO);


                for (int i = 0; i < customerDTOList.size(); i++) {
                    System.out.println(customerDTOList.get(i).getIntCustomerNumber());

                    return intCustomerNumber == customerDTOList.get(i).getIntCustomerNumber()
                            && intCustomerPassword == customerDTOList.get(i).getIntCustomerPassword();
                }
            }
        } catch (FileNotFoundException var15) {
            System.out.println("File not found");
        } catch (IOException var16) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException var17) {
            var17.printStackTrace();
        }
        return false;
    }

}
