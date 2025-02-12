package org.example.assignment3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.math.MathContext;
//import javafx.scene.paint.Color;

public class ControlBox extends HBox{
    private Label a; //Left operand
    private Label o; //Operator
    private Label b; //Right operand
    private TextField c; //Your entered result
    private Label e;  //equal sign
    double width;
    double height;
    private int mathResult;

    public ControlBox() {
        this.a = new Label();
        this.o = new Label();
        this.b = new Label();
        this.c = new TextField( );
        this.e = new Label(" = ");
        this.width = 200;
        this.height = 50;
//        addLabels();
        addLabels();
        setOperatorAndNum();

//        this.getChildren().add(a);
//        this.getChildren().add(o);
//        this.getChildren().add(b);
//        this.getChildren().add(e);
//        this.getChildren().add(c);
//        this.setAlignment(Pos.CENTER);
//        this.setWidth(width);
//        this.setHeight(height);

    }
    public void addLabels()
    {
        this.getChildren().add(a);
        this.getChildren().add(o);
        this.getChildren().add(b);
        this.getChildren().add(e);
        this.getChildren().add(c);
        this.setAlignment(Pos.CENTER);
        this.setWidth(width);
        this.setHeight(height);
    }
    public void setOperatorAndNum()
    {
        String operator = getOperator();
        o.setText(operator);
        if (operator.equals(" * "))
        {
            a.setText((int) ((Math.random() * 12) + 1) +"");
            b.setText((int) ((Math.random() * 12) + 1) +"");
            mathResult = Integer.parseInt(a.getText()) * Integer.parseInt(b.getText());
        }
        else
        {
            a.setText((int) ((Math.random() * 100) + 1) +"");
            b.setText((int) ((Math.random() * 99) + 1) +"");

            if (operator.equals(" - ")) {
                mathResult = Integer.parseInt(a.getText()) - Integer.parseInt(b.getText());
            } else {
                mathResult = Integer.parseInt(a.getText()) + Integer.parseInt(b.getText());
            }
        }
    }
    public void removeLabel()
    {
        this.getChildren().remove(a);
        this.getChildren().remove(o);
        this.getChildren().remove(b);
        this.getChildren().remove(e);
        this.getChildren().remove(c);
    }
    public boolean checkAnswer()
    {
        return mathResult == Integer.parseInt(c.getText()) ;
    }
    public void changeTheNumber()
    {
        this.getChildren().add(a);
        this.getChildren().add(o);
        this.getChildren().add(b);
        this.getChildren().add(e);
        c.clear();
        this.getChildren().add(c);

    }

    public String getOperator()
    {
        String operator = "";
        int x = (int)(Math.random() * 3) %3;
        switch (x)
        {
            case 0 -> operator = " + ";
            case 1 -> operator = " - ";
            case 2 -> operator = " * ";
        }
        return operator;
    }













}
