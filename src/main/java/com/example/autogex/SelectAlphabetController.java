package com.example.autogex;

import com.example.autogex.infos.QuestionInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SelectAlphabetController {
    @FXML
    TextField textField;
    @FXML
    protected void onSelect(ActionEvent event){
        Button button = (Button) event.getSource();
        if(QuestionInfo.alphabet.contains(button.getText())){
            button.setStyle("-fx-background-color: #2E5DD6;");
            QuestionInfo.alphabet.remove(button.getText());
        }else{
            button.setStyle("-fx-background-color: #41E906;");
            QuestionInfo.alphabet.add(button.getText());
        }
    }
    @FXML
    protected void onNext(ActionEvent event) throws IOException {
        QuestionInfo.alphabet.addAll(List.of(textField.getText().split(",")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("part_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Question sur votre expression");
    }
}
