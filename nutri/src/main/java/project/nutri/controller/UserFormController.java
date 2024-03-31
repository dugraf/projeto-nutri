package project.nutri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    }

    private User getFormData()
    {
        User user = new User();

        user.setName(name.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());

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
