package fi.utu.tech.gui.javafx.assignment8;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;


public class CrackerController {

    HashCrackService service;

    public void initialize() {
        service = new HashCrackService();
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if(service.getValue() != null)
                    reversedList.getItems().add(service.getValue());
            }
        });
        statusLabel.textProperty().bind(service.messageProperty());
        crackBtn.textProperty().bind(Bindings.when(service.stateProperty().isEqualTo(Worker.State.RUNNING)).then("Cancel").otherwise("Crack"));
        //crackingProgressBar.progressProperty().bind(Bindings.when(service.stateProperty().isEqualTo(Worker.State.RUNNING)).then(service.progressProperty()).otherwise(-1));
        crackingProgressBar.progressProperty().bind(service.progressProperty());
    }

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;

    @FXML
    private ProgressBar crackingProgressBar;

    @FXML
    private Label statusLabel;

    @FXML
    void crackBtnAction(ActionEvent event) {
        // TODO
        if(service.isRunning()){
            service.cancel();
        }else{
            service.setHashInputText(hashInputField.getText());
            service.restart();
        }
    }
}
