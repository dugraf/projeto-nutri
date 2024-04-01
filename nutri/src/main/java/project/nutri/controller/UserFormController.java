package project.nutri.controller;

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
import javafx.scene.control.Alert.AlertType;
import project.nutri.controller.util.Alerts;
import project.nutri.entities.User;
import project.nutri.services.UserService;

@Component
public class UserFormController implements Initializable
{
    @Autowired
    private UserService userService;

    private User user;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

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
        User user = new User(null, name.getText(), email.getText(), password.getText(), LocalDateTime.now(), null);
        return user;
    }

    @FXML
    public void onBtCancelAction()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}
