package classes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MendesTextArea {

    private VBox layout = new VBox();
    private TextArea txtA = new TextArea();

    private Boolean resizeBottom = false;
    private double dx;
    private double dy;

    public MendesTextArea() {
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(new Insets(0, GlassMoonNotepad.padding,0, 0));
        initTextArea();
        initResizeLabel();
    }


    private void initResizeLabel() {
        Label label = new Label("DRAG TO RESIZE ⇲");
        label.setFont(new Font(25));
        layout.getChildren().add(label);

        // gets the pressing action and allow being resized
        layout.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getX() >= label.getLayoutX()) {
                    resizeBottom = true;
                    dx = GlassMoonNotepad.stage.getWidth() - event.getX();
                    dy = GlassMoonNotepad.stage.getHeight() - event.getY();
                }
                else {
                    resizeBottom = false;
                }
            }
        });

        // if allowed by previous event resizes when dragged and if it respects the min W and H
        layout.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (resizeBottom) {
                    if (GlassMoonNotepad.minW < event.getX() + dx) GlassMoonNotepad.stage.setWidth(event.getX() + dx);
                    if (GlassMoonNotepad.minH < event.getY() + dy) GlassMoonNotepad.stage.setHeight(event.getY() + dy);
                    // returns it to floating widow if in full mode and resized mode but keeping it at the corner
                    if (LateralMenuBar.isMaximized) {
                        GlassMoonNotepad.stage.setMaximized(false);
                        LateralMenuBar.isMaximized = false;
                        GlassMoonNotepad.stage.setX(0);
                        GlassMoonNotepad.stage.setY(0);
                        GlassMoonNotepad.mainLayout.setPadding(new Insets(10,10,10,10)); // add the shaddows back
                    }
                }
            }
        });
    }

    private void initTextArea() {
        txtA.setWrapText(false);
        layout.getChildren().add(txtA);
    }

    public TextArea getTxtArea() { return txtA; }
    public VBox getLayout() { return layout; }
}
