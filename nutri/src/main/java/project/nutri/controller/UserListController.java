package project.nutri.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.nutri.controller.util.CallWindow;
import project.nutri.controller.util.Utils;
import project.nutri.controller.util.listeners.DataListener;
import project.nutri.entities.User;
import project.nutri.services.UserService;

@Component
public class UserListController implements Initializable, DataListener
{
    @Autowired
    private UserService userService;

    private CallWindow callWindow = new CallWindow();

    @FXML
    private TableView<User> tableViewUser;

    @FXML
    private TableColumn<User, Long> tColumnId;

    @FXML
    private TableColumn<User, String> tColumnName;

    @FXML
    private TableColumn<User, String> tColumnEmail;

    @FXML
    private TableColumn<User, String> tColumnPassword;

    @FXML
    private VBox vBoxNewUser;

    private ObservableList<User> observableList;

    @FXML
    public void onVBoxNewUserAction()
    {
        callWindow.openWindow("/templates/UserForm.fxml", "Usuários de sistema", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        showTable();
        vBoxNewUser.setOnMouseEntered(event -> vBoxNewUser.setCursor(Cursor.HAND));
        vBoxNewUser.setOnMouseExited(event -> vBoxNewUser.setCursor(Cursor.DEFAULT));
    }

    private void showTable() {
        tColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        Utils.formatPassword(tColumnPassword);
        tColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        Stage stage = (Stage) CallWindow.getMainScene().getWindow();
        tableViewUser.prefWidthProperty().bind(stage.heightProperty()); //TABLEVIEW acompanhando a largura da janela (fazer metodo)
    }

    public void updateTableView()
    {
        List<User> list = userService.findAll();
        observableList = FXCollections.observableArrayList(list);
        tableViewUser.setItems(observableList);
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }
    
}
