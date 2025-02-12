package org.example.assignment3;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CarPane extends Pane {

    private double w = 1200;
    //    private double h = 400;
    private double baseX=24;
    private int dx = 0; //Increment position of x

    private ImageView obCar;

    private Rectangle obStart;
    private Rectangle obFinish;


    private BooleanProperty raceFinished;

    CarPane() {
       setPrefWidth(1200);

        setPrefHeight(100);


        obStart = new Rectangle(75,0,10,100);
        obStart.setStyle("-fx-fill: green");
        getChildren().add(obStart);

        obFinish = new Rectangle(w-100,0,10,100);
        obFinish.setStyle("-fx-fill: Red");
        getChildren().add(obFinish);

        setStyle("-fx-background-color: burlywood");


        obCar = new ImageView("file:images/car1.png");
        obCar.setFitWidth(50);
        obCar.setFitHeight(50);
        obCar.setX(baseX);
        obCar.setY(50);


        getChildren().add(obCar);

        raceFinished = new SimpleBooleanProperty();
        raceFinished.set(false);


    }


    public synchronized void move() {

        if (baseX > w - 100)  //Reach the finish line
        {
            baseX = 24; //Update the car's position to starting line
            dx = 0;

            raceFinished.set(true); //

            System.out.println("Hello Finished");
        }
        else { //Otherwise update teh car's position
            baseX += dx; //Increase the x position of the car
        }

        this.obCar.setX(baseX); // Set the car to its newly updated position

    }

    public BooleanProperty raceFinishedProperty()
    {
        return raceFinished;
    }

    public synchronized double getBaseX()
    {
        return baseX;
    }

    public synchronized void setDx(int speed)
    {
        this.dx = speed;
    }

    public synchronized int getDx()
    {
        return dx;
    }

    public synchronized void speedUp()
    {
        if(this.dx<5)
        {
            this.dx++;

            System.out.println("My Speed is " + dx);
        }
    }

    public synchronized void slowDown()
    {
        if(this.dx>=1)
            this.dx--;
        System.out.println("My Speed is " + dx);
    }



    public void setW(double w) {
        this.w = w;
        getChildren().remove(obFinish);
        obFinish = new Rectangle(w-100,0,10,100);
        obFinish.setStyle("-fx-fill: Red");
        getChildren().add(obFinish);
        this.obCar.setX(baseX);
    }



    public void resetRace() {
        dx=0;
        baseX = 24;

        this.obCar.setX(baseX);
        this.raceFinished.set(false);


    }


}
