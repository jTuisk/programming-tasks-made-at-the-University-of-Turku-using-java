package fi.utu.tech.gui.javafx.assignment3;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import fi.utu.tech.gui.javafx.WordIterator;
import fi.utu.tech.gui.javafx.assignment1.HashCrack;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CrackerController {

    @FXML
    private ListView<String> reversedList;

    @FXML
    private TextField hashInputField;

    @FXML
    private Button crackBtn;

    //c3557ca22ada1ccafcc43f8013ef0251
    @FXML
    void crackBtnAction(ActionEvent event) {
        new Thread(
                () -> {
                    final var inputHash = hashInputField.getText();
                    crackBtn.setDisable(true);
                    try {
                        String result = new HashCrack(inputHash, 4, WordIterator.DEFAULT_DICT, "md5", "utf-8").bruteForce();
                        Platform.runLater(() -> {
                            reversedList.getItems().add(result);
                        });
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }finally {
                        crackBtn.setDisable(false);
                    }
                }
        ).start();
    }
}
