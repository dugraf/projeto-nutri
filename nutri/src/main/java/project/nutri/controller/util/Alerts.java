package project.nutri.controller.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Alerts
{
    public static void showAlert(String title, String header, String content, AlertType type)
    {
        Alert alert = new Alert(type);
        if(alert.getAlertType() == AlertType.CONFIRMATION)
        {
            ImageView icon = new ImageView("/templates/imgs/confirmation.png");
            alert.getDialogPane().setGraphic(icon);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public static Optional<ButtonType> showConfirmation(String title, String content)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
