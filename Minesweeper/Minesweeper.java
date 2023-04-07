import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.time.*;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.*;

public class Minesweeper{
  static Boolean endgame=false,lost=false;
	static int mines=10;
	static int minesLeft=10;
	static int flagsSet=0;
  static int time;
  static Clock clock=Clock.systemDefaultZone();
  ImageIcon b0=new ImageIcon("0.png");
  ImageIcon b1=new ImageIcon("1.png");
  ImageIcon b2=new ImageIcon("2.png");
  ImageIcon b3=new ImageIcon("3.png");
  ImageIcon b4=new ImageIcon("4.png");
  ImageIcon b5=new ImageIcon("5.png");
  ImageIcon b6=new ImageIcon("6.png");
  ImageIcon b7=new ImageIcon("7.png");
  ImageIcon b8=new ImageIcon("8.png");
  ImageIcon facingDown=new ImageIcon("facingDown.png");
  ImageIcon mine=new ImageIcon("mine.png");
  ImageIcon flagged=new ImageIcon("flagged.png");
	Random rand=new Random();
  JButton[][] feld=new JButton[8][8];
	JFrame frame=new JFrame("Minesweeper");
  static JPopupMenu popUp=new JPopupMenu();
	JPanel game=new JPanel();
	JPanel legend=new JPanel();	
  JLabel mineLabel=new JLabel(mines+"");
  JLabel minesLeftLabel=new JLabel(minesLeft+"");
  JLabel flagsSetLabel=new JLabel(flagsSet+"");
  JLabel timeLabel=new JLabel(time+"");

	public void Feld(){
		frame.setSize(1024,1024);
		frame.setBackground(Color.WHITE);
		game.setLayout(new GridLayout(8,8));
		for(int i=0;i<feld.length;i++){
			for(int j=0;j<feld.length;j++){
			  feld[i][j]=new JButton();
        feld[i][j].setSize(32,32);
        feld[i][j].setBackground(Color.GRAY);
        int v=16;
				int c=rand.nextInt(20);
				if(c<=15&&v!=0){
					feld[i][j].setActionCommand("mine");
          v--;
				}else{feld[i][j].setActionCommand("safe");
        }
        feld[i][j].addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            JButton btn=(JButton) e.getSource();
            if(btn.getActionCommand()=="mine"){
              btn.setIcon(mine);
              lost=true;
              endgame=true;
            }
            if(btn.getActionCommand()=="safe"){
              Radar(btn);
            }

          }
        });
        feld[i][j].addMouseListener(new MouseAdapter(){
          public void mouseClicked(MouseEvent e){
            JButton btn=(JButton) e.getSource();
            if(SwingUtilities.isRightMouseButton(e)&&btn.getIcon()==null){
              btn.setIcon(flagged);
              flagsSet++;
            }else{btn.setIcon(null);}
          } 
        });
        feld[i][j].setIcon(facingDown);
        game.add(feld[i][j]);
		  }
		}
    legend.setSize(100,1024);
    legend.add(mineLabel);
    legend.add(minesLeftLabel);
    legend.add(flagsSetLabel);
    legend.add(timeLabel);
    frame.add(legend);
    frame.add(game);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
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
    popUp.add(restart);
    popUp.add(exit);
  }
  public void Restart(){
    int v=16;
    for(int i=0;i<feld.length;i++){
      for(int j=0;j<feld.length;j++){
        feld[i][j].setAction(null);
        int c=rand.nextInt(50);
 				if(c<=25&&v>1){
					feld[i][j].setActionCommand("mine");
          v--;
				}else{
          feld[i][j].setActionCommand("safe");
        }
        feld[i][j].setIcon(null);
      }
    }
    lost=false;
    endgame=false;
    time=0;
  }
  public void Radar(JButton btn){
    int bomb=0;
    int x=0,y=0;
    for(int i=0;i<feld.length;i++){
      for(int j=0;j<feld[7].length;j++){
        if(btn==feld[i][j]){
          x=i;
          y=j;
        }
      }
    }
    for(int i=x-1;i<=x+1;i++){
      for(int j=y-1;j<=y+1;j++){
        if(i>=0&&i<8&&j>=0&&j<8&&!(i==x&&j==y)){
          if(feld[i][j].getActionCommand().equals("mine")){
            bomb++;
          }
        }
      }
    }
    switch (bomb){
      case 0: btn.setIcon(b0);
        break;
      case 1: btn.setIcon(b1);
        break;
      case 2: btn.setIcon(b2);
        break;
      case 3: btn.setIcon(b3);
        break;
      case 4: btn.setIcon(b4);
        break;
      case 5: btn.setIcon(b5);
        break;
      case 6: btn.setIcon(b6);
        break;
      case 7: btn.setIcon(b7);
        break;
      case 8: btn.setIcon(b8);
    }
  }
  public static void main(String[] args){
    Minesweeper Ms=new Minesweeper();
    Boolean restartpr=false;
    Ms.Feld();
    do{
      while(endgame==false){
        long t=clock.millis();
        if (t-clock.millis()%1000==0) {
          time++;
        }
      }
      if(lost==true){
        popUp.setLabel("You lost! :(");
      }else{popUp.setLabel("You Won!:)");}
      popUp.setVisible(true);
    }while(restartpr==true);
  }
}