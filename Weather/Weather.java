java.net.URL;
java.net.URLConnection;

public class Weather{
  private TextField weatherinput;
  URL site=new URL("https://wetter.com/deutschland/hessen/frankfurt-am-main/DE0004098.html");
  HttpURLConnection con=(HttpURLConnection) url.openConnection();
  BufferedReader in=new BufferedReader(new InputStreamReader())

  public weather{
    weatherinput=new TextField("give a location");
    add(weatherinput);
    submit.addActionListener(new ActionEvent{
      public void ActionPerformed(ActionEvent e){
        con.setRequestMethod("GET");
      }
    });


  }
}
