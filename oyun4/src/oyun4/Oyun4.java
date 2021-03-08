/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oyun4;

import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author risin
 */
public class Oyun4 extends Application {
    Scene scene;
     BallPane ballPane=new BallPane();
    @Override
    public void start(Stage primaryStage) {
   
       HBox hbox=new HBox(15);
       hbox.setAlignment(Pos.CENTER);
       
       ScrollBar hiz= new ScrollBar();
       hiz.setMax(100);
       hiz.setValue(50);
       ballPane.rateProperty().bind(hiz.valueProperty());
       
       Color color=new Color(Math.random(),Math.random(),Math.random(),1);
       ballPane.getChildren().add(new Ball(30,30,20,color));
        
       BorderPane pane=new BorderPane();
       pane.setCenter(ballPane);
       pane.setTop(hiz);
       pane.setBottom(hbox);
       
       scene= new Scene(pane,2000,700);
       primaryStage.setTitle("(MOUSE İLE SAĞ TIKLAYINCA DURUYOR), (MOUSE İLE SOL TIKLAYINCA TERS YÖNE GİDİYOR DURDUYSA BAŞLATILIYOR), (YÖN TUŞLARI İLE RENK DEĞİŞTİRİYOR) VE (ÜSTTEKİ BAR HIZINI AYARLIYOR) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
       primaryStage.setScene(scene);
       primaryStage.show();
       
    }
    private class BallPane extends Pane{
        private Timeline animation;
        
        public BallPane(){
            animation =new Timeline(new KeyFrame(Duration.millis(50), e -> hareket()));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
        public void play(){
            animation.play();
        }
        public void pause(){
            animation.pause();
        }
        public DoubleProperty rateProperty(){
            return animation.rateProperty();
        }
        
        protected void hareket(){
            for(Node node:this.getChildren()){
                Ball ball=(Ball)node;
           ballPane.setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
            ball.dx*=-1; 
            ball.dy*=-1;
            ball.setCenterX(ball.dx+ball.getCenterX());
            ball.setCenterY(ball.dy+ball.getCenterY());
            play();
            } else if (event.getButton() == MouseButton.SECONDARY)
            {
            ball.dx*=-1; 
            ball.dy*=-1;
            ball.setCenterX(ball.dx+ball.getCenterX());
            ball.setCenterY(ball.dy+ball.getCenterY());
            pause();
            }
        });
           scene.setOnKeyPressed(event -> {
        switch (event.getCode()) { case UP:Color color=new Color(Math.random(),Math.random(),Math.random(),1);ball.setFill(color);break;
        case DOWN: Color color2=new Color(Math.random(),Math.random(),Math.random(),1); ball.setFill(color2);break;
        case RIGHT:  Color color3=new Color(Math.random(),Math.random(),Math.random(),1); ball.setFill(color3); break;
        case LEFT:  Color color4=new Color(Math.random(),Math.random(),Math.random(),1); ball.setFill(color4); break;
        }
        });   
        
            if(ball.getCenterX()<ball.getRadius()||ball.getCenterX()>getWidth()-ball.getRadius()){
                ball.dx*=-1;
                System.out.println(ball.dx);
            }
            if(ball.getCenterY()<ball.getRadius()||ball.getCenterY()>getHeight()-ball.getRadius()){
                ball.dy*=-1;
                 System.out.println(ball.dy);
            }
            ball.setCenterX(ball.dx+ball.getCenterX());
            ball.setCenterY(ball.dy+ball.getCenterY());
        }
    }
    }
    class Ball extends Circle{
        private double dx=1,dy=1;
        Ball(double x, double y , double radius, Color color){
            super(x,y,radius);
            setFill(color);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
