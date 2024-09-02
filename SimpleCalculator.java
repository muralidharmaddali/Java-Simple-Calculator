import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private String currentOperator;
    private double firstOperand, secondOperand;
    private boolean isOperatorClicked;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE", "<-", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        isOperatorClicked = false;
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                display.setText("");
                firstOperand = 0;
                secondOperand = 0;
                currentOperator = "";
                isOperatorClicked = false;
                break;

            case "CE":
                display.setText("");
                break;

            case "<-":
                String text = display.getText();
                if (!text.isEmpty()) {
                    display.setText(text.substring(0, text.length() - 1));
                }
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                if (!isOperatorClicked) {
                    firstOperand = Double.parseDouble(display.getText());
                    currentOperator = command;
                    isOperatorClicked = true;
                    display.setText("");
                }
                break;

            case "=":
                if (isOperatorClicked) {
                    secondOperand = Double.parseDouble(display.getText());
                    double result = calculate(firstOperand, secondOperand, currentOperator);
                    display.setText(String.valueOf(result));
                    isOperatorClicked = false;
                }
                break;

            default:
                display.setText(display.getText() + command);
        }
    }

    private double calculate(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                return firstOperand / secondOperand;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
