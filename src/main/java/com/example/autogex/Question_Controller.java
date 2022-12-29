package com.example.autogex;

import com.example.autogex.infos.QuestionInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Question_Controller {
    @FXML
    TextField textField;
    @FXML
    protected void onChange(){
        try {
            QuestionInfo.numberOfPart = Integer.parseInt(textField.getText());
        }catch (NumberFormatException e){

        }
    }
}
