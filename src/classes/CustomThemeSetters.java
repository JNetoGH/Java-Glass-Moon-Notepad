package classes;

public class CustomThemeSetters {

    // THEME STYLE SETTER METHODS
    public static void setToNoirTheme() {
        GlassMoonNotepad.getMendesTextArea().getTxtArea().setStyle(
                "-fx-control-inner-background: #282828; -fx-text-fill: rgb(230,230,230); " +
                "-fx-text-box-border: #282828; -fx-focus-color: #282828; -fx-faint-focus-color: #282828;" +
                "-fx-background-color: #282828; -fx-border-color: #282828; -fx-color-label-visible: #282828; " +
                "-fx-progress-color: #282828;");

        GlassMoonNotepad.getMendesTextArea().getLayout().setStyle("-fx-text-fill: white; -fx-background-color: #282828");


        LateralMenuBar.fontSlider.setStyle("-fx-font-size: 20px; -fx-background-color: transparent;");


        GlassMoonNotepad.lateralMenuBar.setBarColor("#373737");

        LateralMenuBar.lateralBarLayout.setStyle("-fx-background-color: #090a0c," +
                "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22);");

        // buttons
        for (int i = 0; i < LateralMenuBar.utilityButtons.length; i++) {
            LateralMenuBar.utilityButtons[i].setStyle("-fx-text-fill: white; -fx-background-color: #090a0c," +
                    "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), linear-gradient(#20262b, #191d22);");
        }
        for (int i = 0; i < LateralMenuBar.frameButtons.length; i++) {
            LateralMenuBar.frameButtons[i].setStyle("-fx-text-fill: white; -fx-background-color: transparent;");
        }

        // checkbox
        LateralMenuBar.wrapText.setStyle("-fx-text-fill: white");

    }
}
