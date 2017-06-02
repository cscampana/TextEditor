/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author SirExtreme
 */
public class screenAbout extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Label lblEd = new Label("TextEditor - version 0.1 Alpha");
        lblEd.setFont(Font.font(18));
        Label lblRest = new Label("Program created by SirExtreme - Public domain" + "\r\n" + "Contact: " + "caike.campana2@gmail.com");
        
        
        
        
        
        
        
        
        
        VBox vbox = new VBox();
        vbox.getChildren().add(lblEd);
        vbox.getChildren().add(lblRest);

        Scene scene = new Scene(vbox, 300, 200);
           
        primaryStage.setTitle("About");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(TextEditor.class.getResourceAsStream("favicon.png")));
        primaryStage.show();

    }
    
}
