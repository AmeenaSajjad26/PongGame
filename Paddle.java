// AMEENA SAJJAD
//4/16/17
//Paddle.java
//The purpose of this code is to create a pong game

//ANALYSIS
//Using the code from BallPane.java and BounceBallControl.java (chapter 15), create a "Pong for One" game 
//A rectangular paddle moves back and forth via mouse drag along the bottom of the pane
//the bottom of the  paddle should be about 1/2 the  diameter of the ball off the bottom. 
//If the ball connects with the paddle, then it bounces at a 90 degree angle back into the pane space.
//If the ball misses the paddle, then the score is decremented by 1. 
//The game ends when 20 points are lost. 
//A label that displays the score (you can start at 20 and go to zero if you want...)
//For every 10 paddle connections in a row, the ball moves faster
//For every 10 paddle connections in a row, the ball changes color
//For every (2?) paddle misses in a row, the paddle grows in length

//DESIGN-PSEUDOCODE
//import all the required packages
//Create a paddle that extends pane
//set a widht of a stage and a height of a stage
//Create a variable called score and connect
//Create a circle object adn an rectangle object
//Create a constructor with no arguments in which set the style and protperties of the score being displayed
//Create an animaion that will stimulate the ball and increase speed
//Start the program with displaying 20 points
//If the ball touches the paddle ten times then then increase the speed and change the ball color.
//If the paddle misses two ball in a row increase the paddle height
//If the score is equal to 0 end exit the program

//TESTING
// I tested the result by running the program and the program compiled sucessfully without any errors
/////////////////////////////////////

package pongforone;

import javafx.beans.property.DoubleProperty;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

 class Paddle extends Pane
 {
  final double widthOfStage = 1000;
  final double heightOfStage = 600;
  
  int score = 20;
  int connect = 0;
  
  public final double radius = 15;
  
  private double x = radius;
  private double y = radius;
  private double dx = 1;
  private double dy = 1;
  
  //parameters x, y, width, and height
  public double height = 15;
  private double width = 16;
  private double xR = 1;
  private double yR = heightOfStage-height;
  
  //To create horizontally the score label and socre text in the center of HbOX
  HBox boxScore = new HBox();
  
  //Displays the score 
  Label label = new Label("SCORE =");
  
  //Displays the score points  
  Label scoreOfText = new Label(Integer.toString(score));
  
  //Circle object
  private Circle circle = new Circle(x, y, radius);
  private Timeline animation;
  
  //Rectangle object
  private  Rectangle rectangle = new Rectangle(xR, yR, width, height);
  
  //Constructor with no parameters
  public Paddle()
  {
    circle.setFill(Color.RED); 
    rectangle.setFill(Color.GREEN);
    
    //To style the label 
    label.setTextFill(Color.PINK);
    label.setFont(Font.font("airel", FontWeight.BOLD, FontPosture.ITALIC, 20));
    
    //Set the style of label
    scoreOfText.setStyle("-fx-text-fill: blue");
    scoreOfText.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        boxScore.setSpacing(15);

    //set properties of hBox and and them hbox
    boxScore.setAlignment(Pos.CENTER);
    
    boxScore.getChildren().addAll(label, scoreOfText);
    
    //To add ball,paddle and score label in the pane
    this.getChildren().addAll(boxScore, circle, rectangle); 
    
    //animation for the ball
    animation = new Timeline(new KeyFrame(Duration.millis(15), e -> moveBall()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
  }
  
  //To increase speed 
  public void increaseSpeed() 
  {
    animation.setRate(animation.getRate() + 0.1);
  }
  
  //To stimulate the ball and stimulate the ball
  protected void moveBall() 
  {
    boolean test = true;
    if (x < radius || x > widthOfStage - radius) 
    dx *= -1; 
    
    // Check the sides for y
    if (y < radius) 
        dy *= -1; 
    
    //when ball connects with the paddle
    if((y + radius) >= rectangle.getY() && rectangle.getX() <=x && x<=(rectangle.getX() + width))
    {
        connect++;
        dy *= -1 ;
        
        //for every 10 paddle in a row the ball moves faster and the ball changes color 
        if(connect % 10 == 0)
        {
            increaseSpeed();
            circle.setFill(new Color(Math.random(),Math.random(),Math.random(),Math.random()));
        }    
    }
    else
        
        if((y + radius) > heightOfStage)
            {
                score--; 
                scoreOfText.setText(Integer.toString(score));
                
                //for every 2 paddle misses in a row the paddle grows in length
                if(score % 2 == 0)         
                {
                    width *= 2;
                    rectangle.setWidth(width);
                }
                
                //if the score = to 0 exit the program 
                if(score == 0)
                System.exit(0);

                // Adjust ball position to start point
                x = radius;
                y = radius;
                circle.setCenterX(x);
                circle.setCenterY(y); 
                test=  false;
            }
    
    if(test)
    {
    //to adjust the ball position
    x += dx;
    y += dy;
    circle.setCenterX(x);
    circle.setCenterY(y);
    }
    test=true;
  }
  
   //set new position for x of rectangle 
  protected void setXcordinates( double x)
  {
      // Check for sides
      if (x < 0) 
        x=0; 
      if (x > widthOfStage-width) 
         x=widthOfStage-width; 
   
    rectangle.setX(x); 
  }
}