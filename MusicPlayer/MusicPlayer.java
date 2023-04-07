import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.io.File;
import java.lang.Object;

public class MusicPlayer{
  private JFrame frame=new JFrame();  
  private JPanel panel;
  private JButton play;
  private JButton pause;
  private JButton stop;
  private JSlider timeSlider;
  private JButton previous;
  private JButton next;
  private MediaPlayer mediaPlayer;
  private JFileChooser fileChooser;

  public void UI(){
    fileChooser=new JFileChooser("choose the music that you want to play");
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      Media media = new Media(selectedFile.toURI().toString());
    }

    // Create the media player
    mediaPlayer = new MediaPlayer(media);        

    play=new JButton();
    pause=new JButton();
    next=new JButton();
    stop=new JButton();
    previous=new JButton();
    play.addActionListener(e -> mediaPlayer.play());
    pause.addActionListener(e -> mediaPlayer.pause());
    stop.addActionListener(e -> mediaPlayer.stop());

    // Add a change listener to the time slider
    timeSlider.addChangeListener(e -> {
      if (!timeSlider.getValueIsAdjusting()) {
        mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(timeSlider.getValue() / 100.0));
      }
    });

    // Bind the slider value to the media player position
    mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
      double duration = mediaPlayer.getTotalDuration().toMillis();
      double current = mediaPlayer.getCurrentTime().toMillis();
      timeSlider.setValue((int) (current / duration * 100));
      });
    play=new JButton("play");
    previous=new JButton("previous");
    timeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    frame.add(timeSlider,BorderLayout.SOUTH);
    panel.add(next);
    panel.add(play);
    panel.add(previous);
    frame.add(panel,BorderLayout.SOUTH);
  }
  public static void main(String[] args){
    MusicPlayer MP=new MusicPlayer();
    MP.UI();
  }
}
