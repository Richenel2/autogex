package com.example.autogex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class VerificationController {
    @FXML
    protected void onNext(ActionEvent event){
        alertBox("Le mot n'appartient pas a l'automate");
    }
    private void  alertBox(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }
}
