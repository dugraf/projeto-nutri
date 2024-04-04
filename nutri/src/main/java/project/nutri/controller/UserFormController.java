package project.nutri.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import project.nutri.controller.util.Alerts;
import project.nutri.controller.util.Constraints;
import project.nutri.entities.User;
import project.nutri.services.UserService;
import project.nutri.services.exceptions.DataIntegrityException;

@Component
public class UserFormController implements Initializable
{
    @Autowired
    private UserService userService;

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
    private Label labelNameError;

    @FXML
    private Label labelEmailError;

    @FXML
    private Label labelPasswordError;

    @FXML
    public void onBtSaveAction(ActionEvent event)
    {
        try
        {
            user = getFormData();
            userService.save(user);
            Stage stage = (Stage) btSave.getScene().getWindow();
            stage.close();
            Alerts.showAlert("SALVO", "Usuário salvo no sistema", null, AlertType.CONFIRMATION);
        }
        catch(DataIntegrityException e)
        {
            setErrorMessages(e.getErrors());
        }
    }

    private User getFormData()
    {
        User user = new User();
        DataIntegrityException exception = new DataIntegrityException("Erro de integridade");

        if(txtName.getText() == null || txtName.getText().trim().equals(""))
            exception.addError("name", "Campo NOME não pode estar vazio!");
        user.setName(txtName.getText());

        if(txtEmail.getText() == null || txtEmail.getText().trim().equals(""))
            exception.addError("email", "Campo E-MAIL não pode estar vazio!");
        user.setEmail(txtEmail.getText());
        
        if(txtPassword.getText() == null || txtPassword.getText().trim().equals(""))
            exception.addError("password", "Campo SENHA não pode estar vazio!");
        user.setPassword(txtPassword.getText());

        user.setRegistrationDate(LocalDateTime.now());

        if(exception.getErrors().size() > 0)
            throw exception;

        return user;
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

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        labelNameError.setText((fields.contains("name") ? errors.get("name") : ""));
        labelEmailError.setText((fields.contains("email") ? errors.get("email") : ""));
        labelPasswordError.setText((fields.contains("password") ? errors.get("password") : ""));
    }
}
