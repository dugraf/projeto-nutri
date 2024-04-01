package project.nutri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import project.nutri.controller.util.Alerts;
import project.nutri.controller.util.CallWindow;
import project.nutri.services.AuthenticationService;
import project.nutri.services.exceptions.AuthenticationException;

@Component
public class LoginController implements Initializable {

    private static CallWindow callWindow = new CallWindow();

    @Autowired
    private AuthenticationService authenticationService;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button btLogin;

    @FXML
    public void onBtLogin(ActionEvent event) {
        String login = userName.getText();
        String pass = password.getText();
        
        try
        {
            if(authenticationService.authentication(login, pass))
            {
                System.out.println("Logou!");
                openMainMenu();
                closeLoginWindow();
            }
            else
                throw new AuthenticationException();
        }
        catch(AuthenticationException e)
        {
            Alerts.showAlert("Erro ao logar", null, "Usuário ou senha inválidos!", AlertType.WARNING);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        btLogin.setOnMouseEntered(event -> btLogin.setCursor(Cursor.HAND));
        btLogin.setOnMouseExited(event -> btLogin.setCursor(Cursor.DEFAULT));
    }
    
    private void openMainMenu() {
        callWindow.openWindow("/templates/MenuView.fxml", "MENU - Nutri");
    }

    private void closeLoginWindow() {
        Stage stage = (Stage) btLogin.getScene().getWindow();
        stage.close();
    }

    public static void loadView(Stage stage) {
        callWindow.openWindow("/templates/LoginView.fxml", "Login - Nutri");
    }
}
