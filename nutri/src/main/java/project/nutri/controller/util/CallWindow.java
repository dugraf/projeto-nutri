package project.nutri.controller.util;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.nutri.AppJavaFx;

public class CallWindow
{
    private static Scene mainScene;

    public <T> void openWindow(String fxmlPath, String title, Consumer<T> consumer)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setControllerFactory(AppJavaFx.getSpringContext()::getBean);
            Parent root = fxmlLoader.load();
            Image icon = new Image(getClass().getResourceAsStream("/templates/imgs/nutrition.png"));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.getIcons().add(icon);
            mainScene = new Scene(root);
            if(consumer != null)
            {
                T controller = fxmlLoader.getController();
                consumer.accept(controller);
            }
            stage.setScene(mainScene);
            stage.show();
        } catch(IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }
}
