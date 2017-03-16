/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w17s3calculator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author JWright
 */
public class CalculatorViewController implements Initializable {

    @FXML private Button oneButton;
    @FXML private Button twoButton;
    @FXML private Button threeButton;
    @FXML private Button fourButton;
    @FXML private Button fiveButton;
    @FXML private Button sixButton;
    @FXML private Button sevenButton;
    @FXML private Button eightButton;
    @FXML private Button nineButton;
    @FXML private Button zeroButton;
    @FXML private Button decimalButton;
    @FXML private Button plusButton;
    @FXML private Button minusButton;
    @FXML private Button divideButton;
    @FXML private Button multiplyButton;
    @FXML private Button equalsButton;
    
    @FXML private Label equationDisplay;
    @FXML private TextField display;
    
    private ArrayList<String> numberStack;
    private boolean overWriteDisplay;
    
    /**
     * This method will add the number from the display with the operator to
     * the numberStack.  It will then calculate and update the display
     */
    public void operatorButtonPushed(ActionEvent event)
    {
        String operator = ((Button)event.getSource()).getText();
        
        numberStack.add(display.getText());
        numberStack.add(operator);
        
        equationDisplay.setText(formatNumberStack());
        overWriteDisplay=true;
        
        //update the result
        display.setText(Double.toString(calculateStack()));
    }
    
    /**
     * This method will parse the numberStack and return the
     * value of performing all the calculations
     */
    public double calculateStack()
    {
        double result = 0;
        double num1 = 0;
        
        //get the first number in the stack
        if (!numberStack.isEmpty())
        {
            num1 = Double.parseDouble(numberStack.get(0));
            double num2;
            String operator = "=";
        
            for (int i=1; i < numberStack.size(); i++ )
            {
                String element = numberStack.get(i);
                
                //check if the element is a number of an operator
                if (element.matches("[0-9]"))
                {
                    num2 = Double.parseDouble(element);
                    result = calculate(num1, operator, num2);
                    num1 = result;
                }
                else
                    operator = element;
            }
        }
        return result;
    }

    /**
     * This method accepts 2 numbers and performs the math between them based
     * on the operator
     */
    public double calculate(double num1, String operator, double num2)
    {
        if (operator.equals("+"))
            return num1+num2;
        else if (operator.equals("-"))
            return num1-num2;
        else if (operator.equals("*"))
            return num1*num2;
        else if (operator.equals("/"))
            return num1/num2;
        else
            return 0;
    }
    
    
    /**
     * This method will handle updating the display
     * when a number button is pushed
     */
    public void numberButtonPushed(ActionEvent event)
    {
        //This casts the event as a "Button" and then the
        //getText() method returns the String from the front of Button
        String buttonValue = ((Button)event.getSource()).getText();
        
        if (buttonValue.equals(".") && display.getText().contains("."))
        {}  // ignore the button push
        
        //figure out if there is a value 0.  If yes, overwrite it
        else if (overWriteDisplay)
        {
            display.setText(buttonValue);
            overWriteDisplay = false;
        }
        else
            display.setText(display.getText() + buttonValue);
    }
    
    /**
     * This method will return a String of the elements
     * in the numberStack formatted for the display
     */
    public String formatNumberStack()
    {
        String result = "";
        for (String element:numberStack)
        {
            result += element;
        }
        return result;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        equationDisplay.setText("");
        display.setText("0");
        display.setEditable(false);
        numberStack = new ArrayList<>();
        overWriteDisplay = true;
    }    
    
}
