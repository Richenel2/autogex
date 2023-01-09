package com.example.autogex;

import com.example.autogex.infos.QuestionInfo;
import com.example.autogex.verfieAlpha.AmbigueAlphabet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectAlphabetController {
    @FXML
    TextField textField;

    @FXML
    Label alphabet;
    @FXML
    protected void onSelect(ActionEvent event){
        Button button = (Button) event.getSource();
        String txt = button.getText();
        if(QuestionInfo.alphabet.contains(txt.substring(0,1))){
            button.setStyle("-fx-background-color: #2E5DD6;");
            if(txt.length()<3){
                for (Character i : txt.toCharArray()){
                    QuestionInfo.alphabet.remove(i.toString());
                }
            }else if(txt.equals("a-z")){
                QuestionInfo.alphabet.removeAll(new ArrayList<>(Arrays.<String>asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")));
            }else if(txt.equals("A-Z")){
                QuestionInfo.alphabet.removeAll(new ArrayList<>(Arrays.<String>asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")));
            }else if(txt.equals("0-9")){
                QuestionInfo.alphabet.removeAll(new ArrayList<>(Arrays.<String>asList("0","1","2","3","4","5","6","7","8","9")));
            }
        }else{
            button.setStyle("-fx-background-color: #41E906;");
            if(txt.length()<3){
                for (Character i : txt.toCharArray()){
                    QuestionInfo.alphabet.add(i.toString());
                }
            }else if(txt.equals("a-z")){
                QuestionInfo.alphabet.addAll(new ArrayList<>(Arrays.<String>asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z")));
            }else if(txt.equals("A-Z")){
                QuestionInfo.alphabet.addAll(new ArrayList<>(Arrays.<String>asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")));
            }else if(txt.equals("0-9")){
                QuestionInfo.alphabet.addAll(new ArrayList<>(Arrays.<String>asList("0","1","2","3","4","5","6","7","8","9")));
            }
        }
        alphabet.setText(QuestionInfo.alphabet.toString());
    }
    @FXML
    protected void onNext(ActionEvent event) throws IOException {
        QuestionInfo.alphabet.addAll(List.of(textField.getText().split(",")));
        try{
            final List<String> hi =new AmbigueAlphabet().verifierAlphabet(QuestionInfo.alphabet);
        }catch (Exception e){
            VerificationController.alertBox(e.getMessage());
            QuestionInfo.alphabet.removeAll(List.of(textField.getText().split(",")));
            return;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomeController.class.getResource("part_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Question sur votre expression");
    }
}
