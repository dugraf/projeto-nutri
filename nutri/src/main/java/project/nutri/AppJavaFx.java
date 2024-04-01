package project.nutri;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import project.nutri.controller.LoginController;

public class AppJavaFx extends Application {
    private static ConfigurableApplicationContext springContext;

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

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
    