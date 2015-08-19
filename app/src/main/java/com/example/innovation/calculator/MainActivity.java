package com.example.innovation.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    public String calculatorInputText = "";
    public double previousResult = 0.0;
    public boolean haveOperator = true;
    public boolean firstTime = true;
    Double firstOperand = 0.0;
    Double secondOperand = 0.0;
    String operandString = "";
    String operatorToApplyForCalculation = "";
    boolean readyForOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void resetDisplayText(){
        haveOperator = false;
        firstOperand = 0.0;
        secondOperand = 0.0;
        previousResult = 0.0;
        operandString = "";
        operatorToApplyForCalculation = "";
        firstTime = true;
        readyForOperator = false;
        calculatorInputText = "";
        showDisplayText("", "");
    }

    public void showDisplayText(String result, String input) {
        EditText editText = (EditText) findViewById(R.id.input_output);
        editText.setText(result + "\n" + input);
    }

    public void processOperand(String buttonValue){
        String resultString = "";
        readyForOperator = true;
        operandString = operandString + buttonValue;
        calculatorInputText = calculatorInputText + buttonValue;
        if (previousResult ==0.0){
            resultString = "";
        }
        else {
            resultString = Double.toString(previousResult);
        }
        System.out.println("In processOperand result string "+resultString+"and calcInputtext is:"+calculatorInputText);
        showDisplayText(resultString, calculatorInputText);
    }

    public void processOperator(String buttonValue){
        String resultString = "";
        if (buttonValue == "C"){
            resetDisplayText();
        }
        else {
            if (readyForOperator == false){
                System.out.println("Operator is not ready");
                return;
            }
            if (buttonValue == "="){
                readyForOperator = true;
            }
            else {
                readyForOperator = false;
                calculatorInputText = calculatorInputText + buttonValue;
            }

            if (firstTime == true) {
                System.out.println("It is first time");
                firstOperand = Double.parseDouble(operandString);
                operandString = "";
                firstTime = false;
                resultString = "";
                operatorToApplyForCalculation = buttonValue;
            }
            else {
                        System.out.println("Operandone is true and Operandtwo is false");
                        secondOperand = Double.parseDouble(operandString);
                if (buttonValue != "=")
                        operandString = ""; //Shashi - this needs to be corrected so that it reads next operator seamlessly after =
                        System.out.println("Operandone and Operandtwo is true");
                        Double result = calculate();
                        firstOperand = result;
                        previousResult = result;
                        resultString = Double.toString(result);
                        operatorToApplyForCalculation = buttonValue;
            }
            showDisplayText(resultString, calculatorInputText);
        }
    }

    public double calculate(){
        //parse the operator and perform action
        Double result = 0.0;
        switch (operatorToApplyForCalculation){
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "%":
                result = firstOperand % secondOperand;
                break;
        }
        return result;
    }
    public void readOne(View view) {
        processOperand("1");
    }
    public void readTwo(View view) {
        processOperand("2");
    }
    public void readThree(View view) {
        processOperand("3");
    }
    public void readFour(View view) {
        processOperand("4");
    }
    public void readFive(View view) {
        processOperand("5");
    }
    public void readSix(View view) {
        processOperand("6");
    }
    public void readSeven(View view) {
        processOperand("7");
    }
    public void readEight(View view) {
        processOperand("8");
    }
    public void readNine(View view) {
        processOperand("9");
    }
    public void readZero(View view) {
        processOperand("0");
    }
    public void readPlus(View view) {
        processOperator("+");
    }
    public void readMinus(View view) {
        processOperator("-");
    }
    public void readMultiply(View view) {
        processOperator("*");
    }
    public void readDivide(View view) {
        processOperator("/");
    }
    public void readPercentage(View view) {
        processOperator("%");
    }
    public void readEqual(View view) {
        processOperator("=");
    }
    public void readClear(View view) {
        processOperator("C");
    }
}
