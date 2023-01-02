package com.example.autogex;

import com.example.autogex.infos.Question;
import com.example.autogex.infos.QuestionInfo;
import com.example.autogex.infos.Repetition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Question_Controller {
    int step=1;
    @FXML
    TextField maxSize,minSize,fixedSize,prefix,suffix,number,caracterMulti;
    @FXML
    VBox add2,add3,add4;
    @FXML
    HBox hBox2,hBox3,hBox4;
    @FXML
    Label stepLabel;
    Question question = new Question();
    @FXML
    public void initialize(){
        add3.setOnMouseClicked(a->{
            String pref = prefix.getText();
            if(!pref.isEmpty()){
                question.prefix.add(pref);
            }
            prefix.setText("");
            hBox3.getChildren().add(new Label(pref));
        });
        add4.setOnMouseClicked(a->{
            String suff = suffix.getText();
            if(!suff.isEmpty()){
                question.suffix.add(suff);
            }
            suffix.setText("");
            hBox4.getChildren().add(new Label(suff));
        });
        add2.setOnMouseClicked(a->{
            if(!caracterMulti.getText().isEmpty()) {
                Repetition repetition;
                try {
                    repetition = new Repetition(caracterMulti.getText(), Integer.parseInt(number.getText()));
                } catch (NumberFormatException e) {
                    repetition = new Repetition(caracterMulti.getText(), 0);
                }
                question.repetitions.add(repetition);
            }
            hBox2.getChildren().add(new Label(caracterMulti.getText()+" : "+number.getText()+" fois"));
            caracterMulti.setText("");
            number.setText("");
        });
    }
    @FXML
    protected void onNext(ActionEvent event) throws IOException {
        step++;
        if(step<=QuestionInfo.numberOfPart) {
            stepLabel.setText(String.valueOf(step));
            question.maxSize=Integer.parseInt(maxSize.getText());
            maxSize.setText("");
            question.minSize=Integer.parseInt(minSize.getText());
            minSize.setText("");
            question.fixedSize=Integer.parseInt(fixedSize.getText());
            fixedSize.setText("");
            String suff = suffix.getText();
            if(!suff.isEmpty()){
                question.suffix.add(suff);
            }
            String pref = prefix.getText();
            if(!pref.isEmpty()){
                question.prefix.add(pref);
            }
            if(!caracterMulti.getText().isEmpty()) {
                Repetition repetition;
                try {
                    repetition = new Repetition(caracterMulti.getText(), Integer.parseInt(number.getText()));
                } catch (NumberFormatException e) {
                    repetition = new Repetition(caracterMulti.getText(), 0);
                }
                question.repetitions.add(repetition);
            }
            prefix.setText("");
            suffix.setText("");
            number.setText("");
            caracterMulti.setText("");
            QuestionInfo.questions.add(question);
            question=new Question();
        }else{
            return;
        }
    }
}
