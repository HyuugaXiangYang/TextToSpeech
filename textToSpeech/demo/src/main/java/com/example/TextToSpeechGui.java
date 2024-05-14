package com.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class TextToSpeechGui extends Application {

    //global objects
    private static final int APP_WIDTH = 375;
    private static final int APP_HEIGHT = 475;
    private TextArea textArea;
    private ComboBox<String> voices, rates, volumes;


    @Override
    public void start(Stage stage) throws IOException {
        // scene = new Scene(loadFXML("primary"), 640, 480);
        Scene scene = creatScene();
        //adding style.css sheet
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Text To Speech App");
        stage.setScene(scene);
        stage.show();
    }

 

    private Scene creatScene(){

        //vertical boxes
        VBox box = new VBox();
        //load up css style
        box.getStyleClass().addAll("body");

        //insert label
        Label textToSpeechLabel = new Label("Text-To-Speech");
        //to make sure the label stays centered all the time
        textToSpeechLabel.setMaxWidth(Double.MAX_VALUE);
        textToSpeechLabel.setAlignment(Pos.CENTER);
        //load up css style on label
        textToSpeechLabel.getStyleClass().add("text-to-speech-label");
        //to show label
        box.getChildren().add(textToSpeechLabel);




        //insert text area
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.getStyleClass().addAll("text-area");
        //for padding
        StackPane textAreaPane = new StackPane();
        //add margin around left and right of thext area
        textAreaPane.setPadding(new Insets(0, 15, 0, 15));
        textAreaPane.getChildren().add(textArea);
        //to show text area
        box.getChildren().addAll(textAreaPane);

        //adding grid pane
        GridPane settingsPane = createSettingComponents();

        box.getChildren().add(settingsPane);

        //create button

        Button speakButton = createImageButton();
        //event actioner
        speakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                String msg = textArea.getText();
                String voice = voices.getValue();
                String rate = rates.getValue();
                String volume = volumes.getValue();

                TextToSpeechController.speak(msg, voice, rate, volume);
            }
        });

        //add padding
        StackPane speakButtonPane = new StackPane();
        speakButtonPane.setPadding(new Insets(20, 20, 0, 20));
        speakButtonPane.getChildren().add(speakButton);
        box.getChildren().add(speakButtonPane);


















        //need return 
        return new Scene(box, APP_WIDTH, APP_HEIGHT);
        
        
    }


    private GridPane createSettingComponents(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //labels
        Label voiceLabel = new Label("Voice");
        voiceLabel.getStyleClass().addAll("setting-label");
        Label rateLabel = new Label("Rate");
        rateLabel.getStyleClass().addAll("setting-label");
        Label volumeLabel = new Label("Volume");
        volumeLabel.getStyleClass().addAll("setting-label");

        //adding labels
        gridPane.add(voiceLabel, 0, 0);
        gridPane.add(rateLabel, 1, 0);
        gridPane.add(volumeLabel, 2, 0);

        //center labels
        GridPane.setHalignment(voiceLabel, HPos.CENTER);
        GridPane.setHalignment(rateLabel, HPos.CENTER);
        GridPane.setHalignment(volumeLabel, HPos.CENTER);


        //combo boxes
        //voices
        voices = new ComboBox<>();
        voices.getItems().addAll(
            TextToSpeechController.getVoices()
        );
        voices.setValue(voices.getItems().get(0));
        voices.getStyleClass().add("setting-combo-box");
    
        //rates
        rates = new ComboBox<>();
        rates.getItems().addAll(
            TextToSpeechController.getSpeedRates()   
        );
        rates.setValue(rates.getItems().get(2));
        rates.getStyleClass().add("setting-combo-box");

        //volumes
        volumes = new ComboBox<>();
        volumes.getItems().addAll(
            TextToSpeechController.getVolumeLevels()
        );
        volumes.setValue(volumes.getItems().get(0));
        volumes.getStyleClass().add("setting-combo-box");

        //adding combo boxes
        gridPane.add(voices, 0, 1);
        gridPane.add(rates, 1, 1);
        gridPane.add(volumes, 2, 1);

        //



        gridPane.setAlignment(Pos.CENTER);








        return gridPane;

    }

    private Button createImageButton(){

        Button button = new Button ("Speak");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);
        button.getStyleClass().add("speak-btn");


        //add image to button
        ImageView imageView = new ImageView(
                new Image(
                    getClass().getResourceAsStream("speak.png")
                )
        );


        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        button.setGraphic(imageView);

        return button;




















    }

























    public static void main(String[] args) {
        launch();
    }

}