package fi.utu.tech.gui.javafx.assignment8;

import fi.utu.tech.gui.javafx.WordIterator;
import fi.utu.tech.gui.javafx.assignment7.HashCrackTask;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class HashCrackService extends Service<String> {

    String hashInputText;

    public void setHashInputText(String hashInputText){ this.hashInputText = hashInputText;}

    @Override
    protected Task<String> createTask() {
        return new HashCrackTask(this.hashInputText, 4, WordIterator.DEFAULT_DICT, "md5", "utf-8");
    }

}
