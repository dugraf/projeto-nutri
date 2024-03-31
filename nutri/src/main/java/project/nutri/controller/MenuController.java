package project.nutri.controller;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import project.nutri.controller.util.CallWindow;

@Component
public class MenuController
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
}
