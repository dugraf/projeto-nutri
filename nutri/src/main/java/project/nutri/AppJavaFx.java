package project.nutri;

import java.io.IOException;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppJavaFx extends Application {
    private ConfigurableApplicationContext springContext;

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/LoginView.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent parent = loader.load();
            mainScene = new Scene(parent);
            Image icon = new Image(getClass().getResourceAsStream("/templates/imgs/nutrition.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Aplicação Nutri");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.springContext = new SpringApplicationBuilder()
                .sources(AppSpring.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.springContext.close();
        Platform.exit();
    }

    public static Scene getMainScene()
    {
        return mainScene;
    }
}
    