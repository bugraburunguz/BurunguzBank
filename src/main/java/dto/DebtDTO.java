package dto;

import enums.CompaniesDTO;

import java.io.Serializable;

public class DebtDTO implements Serializable {
    private static CompaniesDTO companyName;
    private static int debt;
    private int intDebtID;

    public DebtDTO(int intDebtID, CompaniesDTO companyName, int debt) {
        this.intDebtID = intDebtID;
        DebtDTO.companyName = companyName;
        DebtDTO.debt = debt;
    }

    public int getIntDebtID() {
        return intDebtID;
    }

    public void setIntDebtID(int intDebtID) {
        this.intDebtID = intDebtID;
    }

    public CompaniesDTO getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompaniesDTO companyName) {
        DebtDTO.companyName = companyName;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        DebtDTO.debt = debt;
    }
}
