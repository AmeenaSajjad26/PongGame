//Ameena Sajjad
//Pong.java
//4/16/17

package pongforone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pong extends Application 
{
  @Override 
  public void start(Stage primaryStage)
  { 
    //create a BallPane object
    Paddle ballPane = new Paddle(); 
     
    //rectangular paddle moves back and forth via mouse drag
    ballPane.setOnMouseDragged(e ->
   //call method setXcordinates 
   ballPane.setXcordinates(e.getX())); 
    
    Scene scene = new Scene(ballPane, 1000, 600);
    primaryStage.setTitle("pong for one"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }
}
