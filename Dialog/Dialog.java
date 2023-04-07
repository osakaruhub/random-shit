import javax.swing.*;

public class Dialog{
  public static void main(String [] args){
    JFileChooser chooser=new JFileChooser();
    chooser.showOpenDialog(null);
  }
}
