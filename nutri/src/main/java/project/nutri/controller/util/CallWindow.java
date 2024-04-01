package project.nutri.controller.util;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import project.nutri.AppJavaFx;

public class CallWindow
{
    private static Scene mainScene;

    public void openWindow(String fxmlPath, String title)
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
            stage.setScene(mainScene);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Scene getMainScene() {
        return mainScene;
    }
}
