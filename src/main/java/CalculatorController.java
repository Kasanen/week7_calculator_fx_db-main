
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField number1Field;
    @FXML
    private TextField number2Field;
    @FXML
    private Label resultLabel;

    @FXML
    private void onCalculateClick() {
        try {
            double num1 = Double.parseDouble(number1Field.getText());
            double num2 = Double.parseDouble(number2Field.getText());

            double sum = sum(num1, num2);
            double product = product(num1, num2);
            double sub = sub(num1, num2);
            double div;
            if (num2 != 0) {
                div = div(num1, num2);
            } else {
                div = Double.NaN;
            }

            resultLabel.setText("Sum: " + sum + ", Product: " + product + ", Subtract: " + sub + ", Division: " + div);

            // Save to DB
            ResultService.saveResult(num1, num2, sum, product, sub, div);

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter valid numbers!");
        }
    }

    public double sum(double num1, double num2) {
        return num1 + num2;
    }

    public double product(double num1, double num2) {
        return num1 * num2;
    }

    public double sub(double num1, double num2) {
        return num1 - num2;
    }

    public double div(double num1, double num2) {
        return num1 / num2;
    }
}