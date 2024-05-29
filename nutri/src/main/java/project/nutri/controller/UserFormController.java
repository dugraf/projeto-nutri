package project.nutri.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import project.nutri.controller.util.CallWindow;
import project.nutri.controller.util.Constraints;
import project.nutri.controller.util.Utils;
import project.nutri.controller.util.listeners.DataListener;
import project.nutri.entities.User;
import project.nutri.services.UserService;
import project.nutri.services.exceptions.DataIntegrityException;
import project.nutri.services.utils.Encrypt;

@Component
public class UserFormController implements Initializable
{
    @Autowired
    private UserService userService;

    private User user;

    private List<DataListener> dataChangeListeners = new ArrayList<>();

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

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    public void onBtSaveAction(ActionEvent event)
    {
        try
        {
            if(CallWindow.currentStage(event).getTitle().equals("Editar Usuário"))
            {
                Long id = user.getId();
                user = getFormData();
                user.setId(id);
                userService.saveOrUpdate(user);
            }
            else
            {
                user = getFormData();
                userService.saveOrUpdate(user);
            }
            Stage stage = (Stage) btSave.getScene().getWindow();
            stage.close();
            Alerts.showAlert("SALVO", "Usuário salvo no sistema", null, AlertType.CONFIRMATION);
            notifyDataListeners();
        }
        catch(DataIntegrityException e)
        {
            setErrorMessages(e.getErrors());
        }
    }

    private void notifyDataListeners() {
        for (DataListener listener : dataChangeListeners) {
            listener.onDataChanged();
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
        user.setPassword(Encrypt.encoder(txtPassword.getText()));

        user.setRegistrationDate(LocalDateTime.now());

        if(exception.getErrors().size() > 0)
            throw exception;

        return user;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event)
    {
        CallWindow.currentStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initializeRules();
    }

    private void initializeRules() { //REVIEW - CRIAR CONSTANTE
        Constraints.setTextFieldMaxLength(txtName, 30);
        Constraints.setTextFieldMaxLength(txtEmail, 40);
        Constraints.setTextFieldMinMaxLength(txtPassword, 2, 3);
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        labelNameError.setText((fields.contains("name") ? errors.get("name") : ""));
        labelEmailError.setText((fields.contains("email") ? errors.get("email") : ""));
        labelPasswordError.setText((fields.contains("password") ? errors.get("password") : ""));
    }

    public void subscribeDataChangeListener(DataListener listener)
    {
        dataChangeListeners.add(listener);
    }

    public void updateFormData() {
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        txtPassword.setText(user.getPassword());
    }
}
