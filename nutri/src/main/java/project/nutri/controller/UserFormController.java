package project.nutri.controller;

import javafx.event.EventHandler;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Alert.AlertType;
import project.nutri.controller.util.Alerts;
import project.nutri.controller.util.CallWindow;
import project.nutri.controller.util.Constraints;
import project.nutri.entities.User;
import project.nutri.services.UserService;

@Component
public class UserFormController implements Initializable
{
    @Autowired
    private UserService userService;

    private CallWindow callWindow;

    private User user;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    @FXML
    public void onBtSaveAction(ActionEvent event)
    {
        user = getFormData();
        userService.save(user);
        Alerts.showAlert("SALVO", "Usuário salvo no sistema", null, AlertType.CONFIRMATION);
    }

    private User getFormData()
    {
        return new User(null, txtName.getText(), txtEmail.getText(), txtPassword.getText(), LocalDateTime.now(), null);
    }

    @FXML
    public void onBtCancelAction()
    {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeRules();
    }

    private void initializeRules() {
        Constraints.setTextFieldMaxLength(txtName, 30);
        Constraints.setTextFieldMaxLength(txtEmail, 30);
        Constraints.setTextFieldMinMaxLength(txtPassword, 2, 3);
    }
}
