package project.nutri.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import project.nutri.controller.util.Alerts;
import project.nutri.controller.util.CallWindow;
import project.nutri.controller.util.EditButtonConfigurer;
import project.nutri.controller.util.Utils;
import project.nutri.controller.util.listeners.DataListener;
import project.nutri.entities.User;
import project.nutri.services.UserService;
import project.nutri.services.exceptions.DbIntegrityException;

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
    private TableColumn<User, User> tColumnUpdate;

    @FXML
    private TableColumn<User, User> tColumnDelete;

    @FXML
    private VBox vBoxNewUser;

    private ObservableList<User> observableList;

    @FXML
    public void onVBoxNewUserAction() {
        callWindow.openWindow("/templates/UserForm.fxml", "Cadastro de Usuário", (UserFormController controller) -> {
            controller.subscribeDataChangeListener(this);
        });
        updateTableView();
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

        //Stage stage = (Stage) CallWindow.getMainScene().getWindow();
        //tableViewUser.prefWidthProperty().bind(stage.heightProperty()); //TABLEVIEW acompanhando a largura da janela (fazer metodo)
        //tableViewUser.prefHeightProperty().bind(stage.widthProperty());
    }
    
    private void initUpdateData() {
        EditButtonConfigurer.configureButtonCell(tColumnUpdate, "/templates/imgs/edit.png",
            event -> {
                Button button = (Button) event.getSource();
                TableCell<User, User> cell = (TableCell<User, User>) button.getParent();
                User user = cell.getTableRow().getItem();
                callWindow.openWindow("/templates/UserForm.fxml", "Editar Usuário", (UserFormController controller) -> {
                        controller.setUser(user);
                        controller.subscribeDataChangeListener(this);
                        controller.updateFormData();
                    });
                });
    }    

    private void initDeleteData() {
        EditButtonConfigurer.configureButtonCell(tColumnDelete, "/templates/imgs/delete.png",
            event -> {
                Button button = (Button) event.getSource();
                TableCell<User, User> cell = (TableCell<User, User>) button.getParent();
                User user = cell.getTableRow().getItem();
                Optional<ButtonType> result = Alerts.showConfirmation("Confirmação", "Você tem certeza?");
                if(result.get() == ButtonType.OK)
                    try {
                        userService.delete(user);
                        updateTableView();
                    } catch(DbIntegrityException e) {
                        Alerts.showAlert("Erro ao deletar", null, e.getMessage(), AlertType.ERROR);
                    }
                this.updateTableView();
            });
    }

    public void updateTableView()
    {
        List<User> list = userService.findAll();
        observableList = FXCollections.observableArrayList(list);
        tableViewUser.setItems(observableList);
        initUpdateData();
        initDeleteData();
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }
}
