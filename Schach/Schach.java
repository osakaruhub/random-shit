import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.applet.*;
public class Schach{
  JButton[][] spielFeld=new JButton[8][8];

  public void SpielFeld(){
    int g=0;
    JFrame frame=new JFrame("Schach");
    frame.setSize(512,512);
    GridLayout grid=new GridLayout(8,8);
    frame.setLayout(grid);
    for(int i=0;i<8;i++){
      g++;
      for(int j=0;j<8;j++){
        spielFeld[i][j]=new JButton();
        if(g%2==0){           //make checkerboard 
          spielFeld[i][j].setBackground(Color.BLACK);
        } else {spielFeld[i][j].setBackground(Color.WHITE);}
        spielFeld[i][j].setSize(64,64);
        frame.add(spielFeld[i][j]);
        g++;
      }
    }
    spielFeld[0][0]=Rook();
    spielFeld[0][7]=Rook();
    spielFeld[0][1]=Knight();
    spielFeld[0][6]=Knight();
    spielFeld[0][2]=Bishop();
    spielFeld[0][5]=Bishop();
    spielFeld[0][3]=Queen();
    spielFeld[0][4]=King();
    for(int i=0;i<8;i++){
      for(int j=0;j<8;j++){
        if(i==1||i==6){
          spielFeld[i][j]=Pawn();
        }
      }
    }
    frame.setVisible(true);
  }

  public static void main(String[] args){
    Schach S=new Schach();
    S.SpielFeld();
  }
}
