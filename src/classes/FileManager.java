package classes;

import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileManager {

    public void saveContentInFileByOs() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(GlassMoonNotepad.stage);
        if (file != null) {
            writeInFile(GlassMoonNotepad.getMendesTextArea().getTxtArea().getText(), file);
        }
    }

    public void readContentFromFileByOs() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text File (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(GlassMoonNotepad.stage);
        if (file != null) {
            readFromFile(file);
        }

    }

    private void writeInFile(String content, File file){
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        }
        catch (IOException ex) {
            System.out.println("deu não");
        }
    }

    private void readFromFile(File file) {
        try {
            List<String> textAsList = Files.readAllLines(file.toPath());
            String text = "";

            for (int i = 0; i < textAsList.size(); i++) {
                if (i == 0) text =  textAsList.get(i); // for not having an extra space
                else text = text + "\n" + textAsList.get(i);
            }

            GlassMoonNotepad.getMendesTextArea().getTxtArea().clear();
            GlassMoonNotepad.getMendesTextArea().getTxtArea().setText(text);
        }
        catch (IOException ex) {
            System.out.println("deu não");
        }
    }

}
