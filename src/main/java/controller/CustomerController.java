package controller;

import dto.CustomerDTO;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CustomerController {
    private final String filepath = "customerList.txt";


    public void customerList() {

        try (
                FileInputStream fileIn = new FileInputStream(filepath);
                ObjectInputStream objectInput = new ObjectInputStream(fileIn)
        ) {

            while (true) {
                CustomerDTO customerDTO = (CustomerDTO) objectInput.readObject();

                System.out.print("ID: " + customerDTO.getIntCustomerID() + "\t");
                System.out.print("IBAN: TR" + customerDTO.getIntCustomerIBAN() + "\t");
                System.out.print("Name: " + customerDTO.getStrCustomerName() + "\t");
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

}
