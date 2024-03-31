package project.nutri;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.nutri.controller.LoginController;

public class AppJavaFx extends Application {
    private static ConfigurableApplicationContext springContext;

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        LoginController.loadView(primaryStage);
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(AppSpring.class).run();
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
    }

    public static Scene getMainScene()
    {
        return mainScene;
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
    