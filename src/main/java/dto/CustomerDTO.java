package dto;

import enums.CompaniesDTO;

import java.io.Serializable;
import java.text.ParseException;

public class CustomerDTO implements Serializable {
    private int intCustomerID;
    private int intCustomerIBAN;
    private int intCustomerNumber;
    private int intCustomerPassword;
    private String strCustomerName;
    private String strCustomerSurname;
    private int intCustomerBalance;
    private long intIdentityNumber;
    private long intPhoneNumber;
    private DebtDTO customerDebt;

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
                       int intCustomerdebt) throws ParseException {

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

    public DebtDTO getCustomerDebt() {
        return customerDebt;
    }

    public void setCustomerDebt(DebtDTO customerDebt) {
        this.customerDebt = customerDebt;
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
}
