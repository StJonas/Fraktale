package com.mycompany.fraktale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Fraktalepane extends BorderPane {

    Pane drawPane = new Pane();
    Group LineGroup = new Group();
    int counter = 0;
    Line line;

    public Fraktalepane() {
        this.setCenter(this.drawPane);
        this.setMargin(this.drawPane, new Insets(100));
        this.drawPane.setStyle("-fx-border-color:#424242; -fx-border-width:1px; -fx-background-color:rgba(255, 255, 255, 0.87);");

        Label lb = new Label("Depth of recursion:");
        lb.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        Label counterLabel = new Label(String.valueOf(counter));
        counterLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");

        Button bt = new Button("Draw");
        bt.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        bt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                counter++;
                counterLabel.setText(String.valueOf(counter));

                if(counter == 1) {
                    setUpLine();
                }

                draw(line, counter);
            }

        });

        HBox box = new HBox(20, lb, counterLabel, bt);
        this.setBottom(box);

        box.setAlignment(Pos.BASELINE_CENTER);
        box.setPadding(new Insets(30, 10, 10, 10));

        
    }

    private void draw(Line line, int counter) {
        if (counter <= 0) {
            return;
        }

        createJag(line);

        double distance = line.getEndX() - line.getStartX();
        double x1 = line.getStartX() + distance / 3;
        double y1 = line.getStartY();
        double x2 = line.getStartX() + 2 * distance / 3;
        double y2 = line.getStartY();

        Line leftLine = new Line(line.getStartX(), line.getStartY(), x1, y1);
        Line rightLine = new Line(x2, y2, line.getEndX(), line.getEndY());

        draw(leftLine, counter - 1);
        draw(rightLine, counter - 1);
    }

    private void setUpLine() {
        double length = 400;

        line = new Line(50, 200, length, 200);
        LineGroup.getChildren().add(line);
        drawPane.getChildren().add(LineGroup);
    }

    private void createJag(Line line) {

        double dy = (line.getStartY() - line.getEndY());

        double dx = (line.getEndX() - line.getStartX());

        double theta = Math.atan2(dy, dx);

        double distance = (Math.sqrt(Math.pow(dy, 2) + Math.pow(dx, 2))) / 3;

        double x1 = line.getStartX() + distance * Math.cos(theta);

        double y1 = line.getStartY() - distance * Math.sin(theta);

        double x2 = line.getEndX() + distance * Math.cos(theta + Math.toRadians(180));

        double y2 = line.getEndY() - distance * Math.sin(theta + Math.toRadians(180));

        double x3 = x2 + distance * Math.cos(theta + Math.toRadians(120));

        double y3 = y2 - distance * Math.sin(theta + Math.toRadians(120));

        Line l1 = new Line(line.getStartX(), line.getStartY(), x1, y1);

        Line l2 = new Line(x2, y2, line.getEndX(), line.getEndY());

        Line l3 = new Line(l1.getEndX(), l1.getEndY(), x3, y3);

        Line l4 = new Line(l3.getEndX(), l3.getEndY(), x2, y2);

        this.LineGroup.getChildren().remove(line);

        this.LineGroup.getChildren().addAll(l1, l2, l3, l4);

    }

}