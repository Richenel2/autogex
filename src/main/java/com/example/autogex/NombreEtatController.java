package com.example.autogex;

import com.example.autogex.infos.QuestionInfo;
import com.example.autogex.infos.TransitionTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NombreEtatController {

    @FXML
    TextField textField;

    @FXML
    Label error;
    @FXML
    protected void onChange(){
        try {
            QuestionInfo.numberOfPart = Integer.parseInt(textField.getText());
            error.setText("");
        }catch (NumberFormatException e){
            error.setText("Veillez inserer un nombre");
        }
    }
    @FXML
    protected void onNext(ActionEvent event) throws IOException {
        try {
            TransitionTable.numberEtat = Integer.parseInt(textField.getText());
        }catch (NumberFormatException e){
            error.setText("Veillez inserer un nombre");
            return;
        }
        if(TransitionTable.numberEtat<=0){
            error.setText("Veillez inserer un nombre positif");
            return;
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("etats_initiaux_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
    }
}
