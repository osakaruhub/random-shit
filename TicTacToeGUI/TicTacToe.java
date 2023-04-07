import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe{
  static JPopupMenu popUp=new JPopupMenu();
  ImageIcon x=new ImageIcon("x.png");
  ImageIcon o=new ImageIcon("o.png");
  static Boolean endgame=false,isClicked=false;
  static int c=0;
  JButton[][] feld=new JButton[3][3];
  JFrame frame;
  static JPanel game;

  public void buttonProdukion(){
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        feld[i][j]=new JButton();
        feld[i][j].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e){
            JButton btn=(JButton) e.getSource();
            if(c%2==0){
              btn.setIcon(x);
              btn.setActionCommand("1");
            }else{btn.setIcon(o);
                  btn.setActionCommand("2");}
            isClicked=true;
        }
        });
        feld[i][j].setSize(64,64);
        feld[i][j].setBackground(Color.WHITE);
        game.add(feld[i][j]);
      }
    }
  }

  public void SpielFeld(){
    frame=new JFrame("TicTacToe");
    game=new JPanel();
    game.setSize(544,544);
    game.setBackground(Color.WHITE);
    GridLayout grid=new GridLayout(3,3);
    game.setLayout(grid);
    frame.setSize(544,544);
    frame.add(game);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    popUp.setVisible(true);
    JMenuItem restart=new JMenuItem(new AbstractAction("Restart") {
      public void actionPerformed(ActionEvent e){
        Restart();
        popUp.setVisible(false);
        }
      });
    JMenuItem exit=new JMenuItem(new AbstractAction() {
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });
    popUp.add(exit);
    popUp.add(restart);
  }

  public void Spiel(){
    for(int i=0;i<3;i++){
      if((feld[i][0].getActionCommand().equals("1")||feld[i][0].getActionCommand().equals("2"))&&feld[i][0].getActionCommand().equals(feld[i][1].getActionCommand())&&feld[i][1].getActionCommand().equals(feld[i][2].getActionCommand())){
        endgame=true;
      }
      else if((feld[0][i].getActionCommand().equals("1")||feld[0][i].getActionCommand().equals("2"))&&feld[0][i].getActionCommand().equals(feld[1][i].getActionCommand())&&feld[1][i].getActionCommand().equals(feld[2][i].getActionCommand())){
        endgame=true;
     }
    }
    if ((feld[0][0].getActionCommand().equals("1")||feld[0][0].getActionCommand().equals("2"))&&feld[0][0].getActionCommand().equals(feld[1][1].getActionCommand())&&feld[1][1].getActionCommand().equals(feld[2][2].getActionCommand())){
      endgame=true;
    }
    else if((feld[2][0].getActionCommand().equals("1")||feld[2][0].getActionCommand().equals("2"))&&feld[2][0].getActionCommand().equals(feld[1][1].getActionCommand())&&feld[1][1].getActionCommand().equals(feld[0][2].getActionCommand())){
      endgame=true; 
    }
    isClicked=false;
  }

  public Boolean Restart(){
    for(int i=0;i<feld.length;i++){
      for(int j=0;j<feld.length;j++){
        feld[i][j].setActionCommand("3");
        feld[i][j].setIcon(null);
      }
    }
    popUp.setVisible(false);
    c=0;
    endgame=false;
    return true;
  }

  public static void main(String [] args){
    TicTacToe TTT=new TicTacToe();
    TTT.SpielFeld();
    TTT.buttonProdukion();
    do{
      for(;endgame==false;c++){
        while(isClicked==false){System.out.print("");}
          TTT.Spiel();
      }
      popUp.setToolTipText("null");
    }while(TTT.Restart()==true);
  }
}