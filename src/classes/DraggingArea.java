package classes;

import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;

public class DraggingArea {

    public Rectangle leftDragArea;

    public void updateArea() {
        leftDragArea = new Rectangle(GlassMoonNotepad.lateralMenuBar.getWidth(), GlassMoonNotepad.mainLayout.getHeight());
    }

    public void manageDrgEvent() {
        updateArea();
        GlassMoonNotepad.mainLayout.setOnMousePressed(pressEvent -> {
            GlassMoonNotepad.mainLayout.setOnMouseDragged(dragEvent -> {
                if (pressEvent.getX() <= leftDragArea.getWidth()) { // check if its in a valid range
                    // return it to floating widow mode
                    GlassMoonNotepad.stage.setMaximized(false);
                    LateralMenuBar.isMaximized = false;
                    GlassMoonNotepad.mainLayout.setPadding(new Insets(10,10,10,10)); // add the shadows back

                    // drags
                    GlassMoonNotepad.stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    GlassMoonNotepad.stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                    dragEvent.setDragDetect(false);
                }
            });
        });
    }

}
