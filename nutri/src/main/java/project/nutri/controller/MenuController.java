package project.nutri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import project.nutri.controller.util.CallWindow;
import project.nutri.controller.UserListController;

@Component
public class MenuController implements Initializable
{
    private CallWindow callWindow = new CallWindow();

    @FXML
    private Menu menuUsers;

    @FXML
    private VBox vBoxUser;

    @FXML
    public void onMenuUsersAction()
    {
        callWindow.openWindow("/templates/UserList.fxml", "Usuários de sistema", (UserListController controller) -> {
            controller.updateTableView();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        vBoxUser.setOnMouseEntered(event -> vBoxUser.setCursor(Cursor.HAND));
        vBoxUser.setOnMouseExited(event -> vBoxUser.setCursor(Cursor.DEFAULT));
    }
}
