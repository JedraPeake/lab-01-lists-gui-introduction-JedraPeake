package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FXMLExampleController {
    @FXML private Text actiontarget;

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("hunter2")){
            actiontarget.setFill(Color.GREEN);
            actiontarget.setText ("Success!");
        }
        else{actiontarget.setFill(Color.RED);
            actiontarget.setText("Failure to Authenticate");}
    }


}
