import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;
    private StringBuilder currentInput;
    private double num1, num2, result;
    private char operator;
    private boolean justCalculated = false;

    public CalculatorGUI() {
        setTitle("Mashaka's Simple Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentInput = new StringBuilder();
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);

        // Panel for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "DEL"
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.addActionListener(this);
            buttonsPanel.add(btn);
        }

        add(display, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9\\.]")) {
            if (justCalculated) {
                currentInput.setLength(0); // reset after result
                justCalculated = false;
            }

            if (command.equals(".") && currentInput.toString().contains(".")) return;
            if (currentInput.length() == 0 && command.equals("0")) return;
            if (currentInput.toString().equals("Error")) currentInput.setLength(0);

            currentInput.append(command);
            display.setText(currentInput.toString());

        } else if (command.matches("[+\\-*/]")) {
            if (currentInput.length() == 0) {
                display.setText("Enter number first");
                return;
            }

            try {
                num1 = Double.parseDouble(currentInput.toString());
                operator = command.charAt(0);
                currentInput.setLength(0);
                display.setText("");
            } catch (NumberFormatException ex) {
                display.setText("Error");
                currentInput.setLength(0);
            }

        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(currentInput.toString());

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            display.setText("Cannot divide by zero");
                            return;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                currentInput = new StringBuilder(String.valueOf(result));
                justCalculated = true;

            } catch (NumberFormatException ex) {
                display.setText("Error");
            }

        } else if (command.equals("C")) {
            currentInput.setLength(0);
            display.setText("");
            num1 = num2 = result = 0;
            operator = ' ';

        } else if (command.equals("DEL")) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                display.setText(currentInput.toString());
            }
        }
    }
}
