import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator{
  static private JFrame frame=new JFrame("Calculator");
  static private JTextField inputField,outputField;
  ImageIcon power=new ImageIcon("power.png");
  ImageIcon root=new ImageIcon("root.png");
  
  public void UI(){
    JPanel panel=new JPanel(new GridLayout(4,5));
    inputField=new JTextField(20);
    inputField.setText("");
    outputField=new JTextField(20);
    outputField.setEditable(false);
    
    String[] buttons={"7","8","9","^","√","4","5","6","*","/","1","2","3","+","-","0",".","C","AC","="};
    for(String button:buttons){
      JButton btn=new JButton(button);
      if(button.equals("C")){
        btn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            inputField.setText(inputField.getText().substring(0,inputField.getText().length()-1));
          }
        });
      }
      else if(button.equals("AC")){
        btn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            inputField.setText("");
          }
        });
      }
      else if(button.equals("=")){
        btn.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e){
            Calculate();
          }
        });
      }
      else{
        btn.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            inputField.setText(inputField.getText()+btn.getText());
          }
        });
      }
      panel.add(btn);
    }
    frame.add(inputField,BorderLayout.NORTH);
    frame.add(outputField,BorderLayout.CENTER);
    frame.add(panel,BorderLayout.SOUTH);
    frame.setTitle("Calculator");
    frame.setSize(300,300);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void Calculate(){
    String expression=inputField.getText();
    System.out.println(expression);
    ScriptEngineManager mgr=new ScriptEngineManager();
    ScriptEngine engine=mgr.getEngineByName("js");

    Pattern rootPattern=Pattern.compile("√\\d+\\.?\\d*");                     // pattern for matching √a
    Matcher rootMatcher=rootPattern.matcher(expression);
    while (rootMatcher.find()) {
      String sqrt=rootMatcher.group();
      double radicand=Double.parseDouble(sqrt.substring(1));                  // get the number after √ to calculate sqrt
      double result=Math.sqrt(radicand);
      expression=expression.replace(sqrt,result+"");
      rootMatcher=rootPattern.matcher(expression);
    }
    Pattern exponentPattern=Pattern.compile("\\d+\\.?\\d*\\^\\d+\\.?\\d*");   // pattern for matching a^b (exponentiation)
    Matcher exponentMatcher=exponentPattern.matcher(expression);
    while (exponentMatcher.find()) {
      String exponentiation=exponentMatcher.group();
      String[] parts=exponentiation.split("\\^");                             // splits the exponentiation into two for Math.pow
      double base=Double.parseDouble(parts[0]);
      double exp=Double.parseDouble(parts[1]);
      double result=Math.pow(base, exp);
      expression=expression.replace(exponentiation,result+"");
      exponentMatcher=exponentPattern.matcher(expression);
    }
    try{
      Object result=engine.eval(expression);                                  // evaluates the (simpflied) expression
        inputField.setText(result+"");
    }catch(ScriptException e){
      inputField.setText("Invalid expression");
    }
  }
  
  public static void main(String[] args){
    Calculator Calc=new Calculator();
    Calc.UI();
  }
}  
