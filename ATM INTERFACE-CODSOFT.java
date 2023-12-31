import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATMGUI extends JFrame implements ActionListener {
    private BankAccount bankAccount;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;

    private JButton TryAgainButton;

    public ATMGUI(BankAccount bankAccount) {
        this.bankAccount = bankAccount;

        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        balanceLabel = new JLabel("Balance: $" + bankAccount.getBalance());
        balanceLabel.setHorizontalAlignment(JLabel.CENTER);
        add(balanceLabel);

        JPanel amountPanel = new JPanel(new FlowLayout());
        amountField = new JTextField(12);
        amountPanel.add(new JLabel("Amount: "));
        amountPanel.add(amountField);
        add(amountPanel);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        TryAgainButton = new JButton("Try Again");
        TryAgainButton.addActionListener(this);
        add(TryAgainButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            double amount = getAmountFromField();
            boolean success = bankAccount.withdraw(amount);
            if (success) {
                showMessage("Withdrawal successful!");
            } else {
                showMessage("Insufficient balance or invalid amount.");
            }
        } else if (e.getSource() == depositButton) {
            double amount = getAmountFromField();
            bankAccount.deposit(amount);
            showMessage("Deposit successful!");
        } else if (e.getSource() == checkBalanceButton) {
            double balance = bankAccount.getBalance();
            showMessage("Your account balance is: $" + balance);

        } else if (e.getSource() == TryAgainButton) {
            double balance = bankAccount.getBalance();
            showMessage("Your account balance is: $" + balance);
        }
        updateBalanceLabel();
        clearAmountField();
    }

    private double getAmountFromField() {
        String amountString = amountField.getText();
        try {
            return Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + bankAccount.getBalance());
    }

    private void clearAmountField() {
        amountField.setText("");
    }

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0); // Initial account balance
        ATMGUI atm = new ATMGUI(bankAccount);
        atm.setVisible(true);
    }
}
