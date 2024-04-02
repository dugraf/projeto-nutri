package project.nutri.controller.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class Utils {
    public static <T> void formatPassword(TableColumn<T, String> tableColumn) {
        tableColumn.setCellFactory(column -> {
            TableCell<T, String> cell = new TableCell<T, String>(){
                @Override
                protected void updateItem(String password, boolean empty) {
                    super.updateItem(password, empty);
                    if (empty || password == null) {
                        setText(null);
                    } 
                    else
                    {
                        StringBuilder hiddenPassword = new StringBuilder();
                        if(password != null)
                            for (int i = 0; i < password.length() / 3; i++)
                                hiddenPassword.append("*");
                        setText(hiddenPassword.toString());
                    }
                }
            };
            return cell;
        });        
    }
}
