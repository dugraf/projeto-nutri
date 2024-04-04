package project.nutri.controller.util;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditButtonConfigurer
{
    public static <T> void configureButtonCell(TableColumn<T, T> column, String imagePath, EventHandler<ActionEvent> actionHandler)
    {
        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        column.setCellFactory(param -> new TableCell<T, T>() {
            private final Button editButton = new Button();

            {
                Image editImage = new Image(getClass().getResourceAsStream(imagePath));
                ImageView imageView = new ImageView(editImage);
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                editButton.setGraphic(imageView);
                editButton.setStyle("-fx-background-color: transparent;");
            }

            @Override
            protected void updateItem(T user, boolean empty) {
                super.updateItem(user, empty);
                if (user == null || empty) {
                    setGraphic(null);
                } else {
                    editButton.setOnMouseEntered(event -> editButton.setCursor(Cursor.HAND));
                    editButton.setOnAction(actionHandler);
                    setGraphic(editButton);
                }
            }
        });
    }
}
