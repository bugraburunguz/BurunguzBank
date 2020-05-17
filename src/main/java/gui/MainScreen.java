package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {

    private JButton benSingUp;
    private JButton btnSignIn;
    private JPanel MainScreenPanel;

    public MainScreen() {
        super("Burunguz Bank APP");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(800, 400, 500, 300);
        getContentPane().add(MainScreenPanel);
        setVisible(true);
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new SignIn();
                dispose();
            }
        });
        benSingUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new SignUp();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}

