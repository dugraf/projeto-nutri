package project.nutri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import project.nutri.controller.util.CallWindow;

@Component
public class MenuController implements Initializable
{
    @FXML
    private MenuItem menuItemCreate;

    @FXML
    private MenuItem menuItemSearch;

    private CallWindow callWindow = new CallWindow();

    @FXML
    public void onMenuItemCreateAction()
    {
        callWindow.openWindow("/templates/UserForm.fxml", "Cadastro de Usuário");
    }

    @FXML
    public void onMenuItemSearchAction()
    {
        callWindow.openWindow("/templates/UserSearch.fxml", "Usuários de sistema");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
