package dto;

import enums.CompaniesDTO;

import java.io.Serializable;

abstract class DebtDTO implements Serializable {

    public int intDebtID;
    public int intCustomerdebt;
    public CompaniesDTO companiesDTO;

    public DebtDTO(int intDebtID, int intCustomerdebt, CompaniesDTO company) {
        this.intDebtID = intDebtID;
        this.intCustomerdebt = intCustomerdebt;
        this.companiesDTO = company;
    }

}