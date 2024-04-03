package project.nutri.controller.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Alerts
{
    public static void showAlert(String title, String header, String content, AlertType type)
    {
        Alert alert = new Alert(type);
        setImage(alert);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private static void setImage(Alert alert) {
        ImageView icon = new ImageView("/templates/imgs/confirmation.png");
        if(alert.getAlertType() == AlertType.CONFIRMATION)
            alert.getDialogPane().setGraphic(icon);
    }
}
