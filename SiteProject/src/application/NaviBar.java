package application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NaviBar {

    public Node createNaviBar(HostServices hostServices) {
        VBox root = new VBox();
        HBox hb = new HBox();
        HBox box = new HBox();

        Button livres = new Button("Top 10 des Livres");
        Button deNous = new Button("A propos de nous");
        Button boutique = new Button("Boutique");
        Button blog = new Button("Blog");
        Button seConnecter = new Button("Se connecter");

        Button test = new Button("test");
        Label label = new Label("Trouve moi un livre");
        box.setMargin(test, new Insets(10, 10, 10, 10));

        box.getChildren().addAll(test, label);
        hb.setMargin(livres, new Insets(10, 10, 10, 10));
        hb.setMargin(deNous, new Insets(10, 10, 10, 10));
        hb.setMargin(boutique, new Insets(10, 10, 10, 10));
        hb.setMargin(blog, new Insets(10, 200, 10, 10));
        hb.setMargin(seConnecter, new Insets(10, 10, 10, 10));

        hb.setAlignment(Pos.TOP_CENTER);

        hb.setStyle("-fx-background-color: lightblue;");
        box.setStyle("-fx-background-color: skyblue;");

        livres.setStyle("-fx-background-color: lightblue;");
        deNous.setStyle("-fx-background-color: lightblue;");
        boutique.setStyle("-fx-background-color: lightblue;");
        blog.setStyle("-fx-background-color: lightblue;");
        seConnecter.setStyle("-fx-background-color: lightblue;");

        livres.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        deNous.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        boutique.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        blog.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        seConnecter.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        hb.setPrefHeight(35);
        hb.getChildren().addAll(livres, deNous, boutique, blog, seConnecter);

        EventHandler<MouseEvent> onButton1 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                livres.setCursor(Cursor.HAND);
                livres.setStyle("-fx-background-color: skyblue;");
            }
        };

        EventHandler<MouseEvent> onButton2 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                deNous.setCursor(Cursor.HAND);
                deNous.setStyle("-fx-background-color: skyblue;");
            }
        };

        EventHandler<MouseEvent> onButton3 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boutique.setCursor(Cursor.HAND);
                boutique.setStyle("-fx-background-color: skyblue;");
            }
        };

        EventHandler<MouseEvent> onButton4 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                blog.setCursor(Cursor.HAND);
                blog.setStyle("-fx-background-color: skyblue;");
            }
        };

        EventHandler<MouseEvent> onButton5 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                seConnecter.setCursor(Cursor.HAND);
                seConnecter.setStyle("-fx-background-color: skyblue;");
            }
        };

        livres.addEventHandler(MouseEvent.MOUSE_ENTERED, onButton1);
        deNous.addEventHandler(MouseEvent.MOUSE_ENTERED, onButton2);
        boutique.addEventHandler(MouseEvent.MOUSE_ENTERED, onButton3);
        blog.addEventHandler(MouseEvent.MOUSE_ENTERED, onButton4);
        seConnecter.addEventHandler(MouseEvent.MOUSE_ENTERED, onButton5);

        EventHandler<MouseEvent> exitButton1 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                livres.setStyle("-fx-background-color: lightblue;");
            }
        };

        EventHandler<MouseEvent> exitButton2 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                deNous.setStyle("-fx-background-color: lightblue;");
            }
        };

        EventHandler<MouseEvent> exitButton3 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                boutique.setStyle("-fx-background-color: lightblue;");
            }
        };

        EventHandler<MouseEvent> exitButton4 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                blog.setStyle("-fx-background-color: lightblue;");
            }
        };

        EventHandler<MouseEvent> exitButton5 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                seConnecter.setStyle("-fx-background-color: lightblue;");
            }
        };

        livres.addEventHandler(MouseEvent.MOUSE_EXITED, exitButton1);
        deNous.addEventHandler(MouseEvent.MOUSE_EXITED, exitButton2);
        boutique.addEventHandler(MouseEvent.MOUSE_EXITED, exitButton3);
        blog.addEventHandler(MouseEvent.MOUSE_EXITED, exitButton4);
        seConnecter.addEventHandler(MouseEvent.MOUSE_EXITED, exitButton5);

        EventHandler<MouseEvent> saleShop = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                hostServices.showDocument("https://www.amazon.fr/livre-achat-occasion-litterature-roman/b?ie=UTF8&node=301061");
            }
        };

        EventHandler<MouseEvent> blogSent = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                hostServices.showDocument("https://blog.trouvemoiunlivre.com/");
            }
        };
        
        
        // evenement pour le bouton se connecter 
        EventHandler<MouseEvent> seConnecter1 = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                hostServices.showDocument("https://www.trouvemoiunlivre.com/AuthForm/Auth");
            }
        };
        
        boutique.addEventHandler(MouseEvent.MOUSE_CLICKED, saleShop);
        blog.addEventHandler(MouseEvent.MOUSE_CLICKED, blogSent);
        seConnecter.addEventHandler(MouseEvent.MOUSE_CLICKED, seConnecter1);

        root.getChildren().addAll(box, hb);

        return root;
    }
    
}

