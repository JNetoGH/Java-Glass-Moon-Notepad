package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

public class LateralMenuBar {

    public static VBox lateralBarLayout = new VBox();
    private static double width = 130;
    public static String color;

    private static Dimension2D elementsDimensions = new Dimension2D(100,50);

    public static Slider fontSlider = new Slider(10,50,5);

    public static Button[] frameButtons = {new Button("-"), new Button("❐"), new Button("x")};
    public static Button[] utilityButtons = {new Button("Glass"), new Button("Open"), new Button("Save")};
    public static boolean isGlassed = false, isMaximized = false;

    public LateralMenuBar(String color) throws FileNotFoundException {
        // GENERAL SETTINGS
        LateralMenuBar.color = color;
        LateralMenuBar.lateralBarLayout.setStyle("-fx-background-color: " + color);
        LateralMenuBar.lateralBarLayout.setPrefWidth(width);
        LateralMenuBar.lateralBarLayout.setMaxWidth(width);
        LateralMenuBar.lateralBarLayout.setMinWidth(width);
        LateralMenuBar.lateralBarLayout.setSpacing(GlassMoonNotepad.padding);
        LateralMenuBar.lateralBarLayout.setAlignment(Pos.TOP_CENTER);

        // ADDING FRAME BUTTONS
        HBox frameButtonsLayout = new HBox();
        frameButtonsLayout.setAlignment(Pos.CENTER);
        LateralMenuBar.lateralBarLayout.getChildren().add(frameButtonsLayout);
        for (int i = 0; i < LateralMenuBar.frameButtons.length; i++) {
            frameButtonsLayout.getChildren().add(LateralMenuBar.frameButtons[i]);
            LateralMenuBar.frameButtons[i].setPrefSize(50, 25);
            // EVENTS
            int finalI = i;
            LateralMenuBar.frameButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (LateralMenuBar.frameButtons[finalI].getText()) {
                        case "-": GlassMoonNotepad.stage.setIconified(true); break;
                        case "x": System.exit(0); break;
                        case "❐":
                            if (!isMaximized) {
                                GlassMoonNotepad.stage.setMaximized(true);
                                isMaximized = true;
                                GlassMoonNotepad.mainLayout.setPadding(new Insets(0,0,0,0)); // remove the shaddows
                            }
                            else {
                                GlassMoonNotepad.stage.setMaximized(false);
                                isMaximized = false;
                                GlassMoonNotepad.mainLayout.setPadding(new Insets(10,10,10,10)); // add the shaddows
                            }
                            break;
                    }
                }
            });
        }

        ImageView imageView = new ImageView();
        imageView.setImage(GlassMoonNotepad.getImage());
        imageView.setFitWidth(elementsDimensions.getWidth());
        imageView.setPreserveRatio(true);
        LateralMenuBar.lateralBarLayout.getChildren().add(imageView);

        // ADDING SLIDER AND LINKING IT TO THE TXTA AREA FONT
        LateralMenuBar.fontSlider.setPrefSize(elementsDimensions.getWidth(), elementsDimensions.getHeight());
        LateralMenuBar.fontSlider.setMaxSize(elementsDimensions.getWidth(), elementsDimensions.getHeight());
        LateralMenuBar.fontSlider.valueProperty().addListener(new ChangeListener<Number>() { //links the slider with the text
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                GlassMoonNotepad.getMendesTextArea().getTxtArea().setFont(new Font(newValue.doubleValue()));
            }
        });
        LateralMenuBar.lateralBarLayout.getChildren().add(fontSlider);

        // ADDING UTILITY BUTTONS AND ITS EVENTS
        for (int i = 0; i < LateralMenuBar.utilityButtons.length; i++) {
            LateralMenuBar.lateralBarLayout.getChildren().add(LateralMenuBar.utilityButtons[i]);
            LateralMenuBar.utilityButtons[i].setPrefSize(elementsDimensions.getWidth(), elementsDimensions.getHeight());
            // EVENTS
            int finalI = i;
            LateralMenuBar.utilityButtons[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    switch (LateralMenuBar.utilityButtons[finalI].getText()) {
                        case "Open": new FileManager().readContentFromFileByOs(); break;
                        case "Save": new FileManager().saveContentInFileByOs(); break;
                        case "Glass":
                            if (!isGlassed) {
                                GlassMoonNotepad.stage.setOpacity(0.9);
                                isGlassed = true;
                            }
                            else {
                                GlassMoonNotepad.stage.setOpacity(1);
                                isGlassed = false;
                            }
                            break;
                    }
                }
            });
        }
    }

    public VBox getLayout (){ return lateralBarLayout; }
    public double getWidth() { return width; }
    public void setWidth(double width) { lateralBarLayout.setPrefWidth(width); }
    public void setBarColor (String color) { LateralMenuBar.color = color; }

}
