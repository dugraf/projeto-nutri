package project.nutri.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

@Component
public class LoginController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button btLogin;

    @FXML
    public void onBtLogin(ActionEvent event) {

    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        btLogin.setOnMouseEntered(event -> btLogin.setCursor(Cursor.HAND));
        btLogin.setOnMouseExited(event -> btLogin.setCursor(Cursor.DEFAULT));
    }

    public Button getBtLogin() {
        return btLogin;
    }
}
