/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author SirExtreme
 */
public class TextEditor extends Application {

    /**
     * @param args the command line arguments
     */
    public void start(Stage primaryStage) throws Exception {
        // Utility package
        Utils utils = new Utils();

        Text text = new Text("Font: ");

        //Check if the font in the textArea is bold or not.
        TextArea textArea = new TextArea();
        textArea.setMinHeight(300);
        textArea.setMaxWidth(600);
        

        Button btnOpen = new Button("Open");
        btnOpen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser file = new FileChooser();
                file.setTitle("Open Text File");
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("EXTREME FILE (*.xtreme)", "*.xtreme");
                file.getExtensionFilters().add(filter);
                File fileOpn = file.showOpenDialog(primaryStage);
                // Checks if user has selected file; if yes, gets the path and applies to stage title, otherwise exits
                if (fileOpn.getPath() != null) {
                    String path = fileOpn.getPath();
                    primaryStage.setTitle("Text Editor - " + path);
                    try {
                        textArea.setText(utils.fileReader(fileOpn));
                    } catch (IOException ex) {
                        Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                }

            }

        });

        Button btnSave = new Button("Save");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String content = textArea.getText();
                FileChooser file = new FileChooser();
                file.setTitle("Save Text File");
                FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("EXTREME FILE (*.xtreme)", "*.xtreme");
                file.getExtensionFilters().add(filter);
                File fileSave = file.showSaveDialog(primaryStage);
                if (fileSave != null) {
                    utils.SaveFile(content, fileSave);
                    primaryStage.setTitle("Text Editor - " + fileSave.getPath());
                }

            }

        });

        //Separator
        Separator separatorFont = new Separator();
        separatorFont.setOrientation(Orientation.VERTICAL);

        //text  size
        Text txtSize = new Text("Size: ");
        Integer[] sizes = {8, 10, 12, 14, 16, 18, 20, 22, 24, 28, 32, 36, 40, 44, 48};
        ObservableList<Integer> sizeOptions = FXCollections.observableArrayList(sizes);
        ComboBox<Integer> comboboxSize = new ComboBox(sizeOptions);
        //Set the default size to 12
        comboboxSize.setValue(sizes[2]);

        // Gets all fonts available from the system and inputs into a ComboBox
        Object[] fonts = utils.returnFonts();

        //font type
        ObservableList<Object> options = FXCollections.observableArrayList(fonts);
        ComboBox<String> comboBox = new ComboBox(options);
        //Set the default font as Arial
        comboBox.setValue(fonts[10].toString());

        //Action to set the Font family
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String x = (String) comboBox.getValue();
                int size = comboboxSize.getValue();
                if (textArea.getFont().getStyle().equals("Regular")) {
                    textArea.setFont(Font.font(x, size));
                } else if (textArea.getFont().getStyle().equals("Italic")) {
                    textArea.setFont(Font.font(x, FontPosture.ITALIC, size));
                } else {
                    textArea.setFont(Font.font(x, FontWeight.BOLD, size));
                }

            }

        });

        //Action to set the font Size
        comboboxSize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int size = comboboxSize.getValue();
                String x = (String) comboBox.getValue();
                if (textArea.getFont().getStyle().equals("Regular")) {
                    textArea.setFont(Font.font(x, size));
                } else if (textArea.getFont().getStyle().equals("Italic")) {
                    textArea.setFont(Font.font(x, FontPosture.ITALIC, size));
                } else {
                    textArea.setFont(Font.font(x, FontWeight.BOLD, size));
                }

            }

        });

        //Set the font to bold
        Button btnBold = new Button("B");
        // The return statement of the code txtSize.getFont().getSize() is 12
        btnBold.setFont(Font.font("", FontWeight.BOLD, txtSize.getFont().getSize()));
        btnBold.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                int size = comboboxSize.getValue();
                String font = (String) comboBox.getValue();
                if (textArea.getFont().getStyle().equals("Regular") || textArea.getFont().getStyle().equals("Italic")) {
                    textArea.setFont(Font.font(font, FontWeight.BOLD, size));

                } else {
                    textArea.setFont(Font.font(font, FontWeight.NORMAL, size));
                }

            }

        });

        Button btnItalic = new Button("I");
        btnItalic.setFont(Font.font("", FontPosture.ITALIC, txtSize.getFont().getSize()));
        btnItalic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int size = comboboxSize.getValue();
                String font = (String) comboBox.getValue();
                if (textArea.getFont().getStyle().equals("Regular") || textArea.getFont().getStyle().equals("Bold")) {
                    textArea.setFont(Font.font(font, FontPosture.ITALIC, size));

                } else {
                    textArea.setFont(Font.font(font, FontPosture.REGULAR, size));
                }
            }

        });
        Separator separatorAbout = new Separator();
        separatorAbout.setOrientation(Orientation.VERTICAL);

        Button btnAbout = new Button("About");
        btnAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                screenAbout screenAbout = new screenAbout();
                Stage about = new Stage();
                try {
                    screenAbout.start(about);
                } catch (Exception ex) {
                    Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        ToolBar toolbar = new ToolBar(btnOpen, btnSave, separatorFont, text, comboBox, txtSize, comboboxSize, btnBold, btnItalic, separatorAbout, btnAbout);

        VBox vbox = new VBox();
        vbox.getChildren().add(toolbar);
        vbox.getChildren().add(textArea);

        Scene scene = new Scene(vbox, 580, 320);

        primaryStage.setTitle("Text Editor");
        primaryStage.getIcons().add(new Image(TextEditor.class.getResourceAsStream("favicon.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
