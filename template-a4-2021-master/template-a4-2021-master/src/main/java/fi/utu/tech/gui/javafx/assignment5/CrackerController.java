package fi.utu.tech.gui.javafx.assignment5;

import fi.utu.tech.gui.javafx.WordIterator;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CrackerController {

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;

    @FXML
    private Label statusLabel;

    //c3557ca22ada1ccafcc43f8013ef0251
    @FXML
    void crackBtnAction(ActionEvent event) {
        HashCrackTask crackerTask = new HashCrackTask(hashInputField.getText(), 4, WordIterator.DEFAULT_DICT, "md5", "utf-8");
        Thread thread = new Thread(crackerTask);
        thread.setDaemon(true);
        thread.start();
        crackBtn.setDisable(true);
        statusLabel.textProperty().bind(crackerTask.messageProperty());
        crackerTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                reversedList.getItems().add(crackerTask.getValue());
                crackBtn.setDisable(false);
            }
        });
    }




}
