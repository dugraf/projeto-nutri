package project.nutri.controller.util;

import javafx.scene.control.TextField;

public class Constraints
{
    public static void setTextFieldMaxLength(TextField txt, int max)
    {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.length() > max)
                txt.setText(oldValue);
        });
    }

    public static void setTextFieldMinMaxLength(TextField txt, int min, int max)
    {
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null && newValue.length() > max && newValue.length() < min)
                txt.setText(oldValue);
        });
    }
}
