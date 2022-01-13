package classes;

public class CustomThemeSetters {

    // THEME STYLE SETTER METHODS
    public static void setToNoirTheme() {

        GlassMoonNotepad.getMendesTextArea().getLayout().setStyle("-fx-background-color: #282828");
        LateralMenuBar.fontSlider.setStyle("-fx-font-size: 15px; -fx-background-color: transparent;");
        GlassMoonNotepad.lateralMenuBar.setBarColor("#373737");
        MendesTextArea.label.setStyle("-fx-text-fill: grey");
        LateralMenuBar.lateralBarLayout.setStyle("-fx-background-color: #090a0c," +
                "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22);");

        // faz os hoovers dos botões do frame
        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: gray";
        final String HOVERED_BUTTON_STYLE_X = "-fx-background-color: rgb(255,0,0,130)";
        for (int i = 0; i < LateralMenuBar.frameButtons.length; i++) {
            int finalI = i;
            LateralMenuBar.frameButtons[i].setStyle(IDLE_BUTTON_STYLE);
            LateralMenuBar.frameButtons[i].setOnMouseExited(e -> LateralMenuBar.frameButtons[finalI].setStyle(IDLE_BUTTON_STYLE));
            if (LateralMenuBar.frameButtons[finalI].getText().equals("x")) {
                LateralMenuBar.frameButtons[i].setOnMouseEntered(e -> LateralMenuBar.frameButtons[finalI].setStyle(HOVERED_BUTTON_STYLE_X));
            }
            else {
                LateralMenuBar.frameButtons[i].setOnMouseEntered(e -> LateralMenuBar.frameButtons[finalI].setStyle(HOVERED_BUTTON_STYLE));
            }
        }


        for (int i = 0; i < LateralMenuBar.utilityButtons.length; i++) {
            LateralMenuBar.utilityButtons[i].setStyle("-fx-background-color: #090a0c," +
                    "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22);");
        }


    }
}
