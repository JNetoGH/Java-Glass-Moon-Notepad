package classes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;

public class GlassMoonNotepad extends Application implements Runnable {

    private static Thread autoScalingElement;

    private static Image image = new Image("IconPNG.png");
    public static Image getImage() {return image;}

    private static double wSize = 700; // size of window as square
    public static double minW = 400; // minumum size allowed
    public static double minH = 550; // minumum size allowed
    public static double padding = wSize / 20; // 5% of screen size

    public static Stage stage;
    private static StackPane rootPane = new StackPane(); // will be the shadow
    public static HBox mainLayout = new HBox();
    private static Scene scene = new Scene(rootPane);

    public static LateralMenuBar lateralMenuBar; // static block initializes the lateral bar
    static {
        try {
            lateralMenuBar = new LateralMenuBar("#373737");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static DraggingArea draggingArea = new DraggingArea();
    private static MendesTextArea mendesTextArea = new MendesTextArea();

    @Override
    public void start(Stage primaryStage) throws Exception {

        // SETTING MAIN LAYOUT
        mainLayout.getChildren().addAll(lateralMenuBar.getLayout(), mendesTextArea.getLayout());

        // MAKE WINDOW ABLE TO BE EXPANDED AND DRAGGED
        draggingArea.manageDrgEvent();

        // INITIALIZING THREAD FOR AUTO-SCALING
        autoScalingElement = new Thread(this);
        autoScalingElement.start();

        // initializes stage values
        initStage(primaryStage);

        // starts with 18px font size by default
        LateralMenuBar.fontSlider.setValue(18);

        // INITIALIZING FEATURES FOR CUSTOM SETTINGS
        CustomThemeSetters.setToNoirTheme();

        // atribui o estilo as barras
        scene.getStylesheets().add("ScrollBarStyle.css");

        // makes the focus "which element will be used" by default in the text are
        mendesTextArea.getTxtArea().requestFocus();

        // shows the stage on screen will just show when everything is set
        stage.show();
    }

    // AUTO SCALING TXT AREA THREAD
    @Override
    public void run() {
        while (true) {
            wSize = stage.getWidth();
            mendesTextArea.getTxtArea().setPrefSize(mainLayout.getWidth() - lateralMenuBar.getWidth(), mainLayout.getHeight());
            padding = mainLayout.getWidth() / 20; // 5% of screen size
            mendesTextArea.getTxtArea().setPadding(new Insets(padding, 0, 0, padding));
            draggingArea.updateArea();
        }
    }

    // primaryStage SETTINGS
    private static void initStage (Stage primaryStage) {
        // INITIALIZING STAGE, ROOT AND SCENE
        GlassMoonNotepad.stage = primaryStage;

        // setting
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Glass Moon");
        stage.setHeight(600);
        stage.setWidth(600);
        stage.getIcons().add(image); //seta imagem tb como ícona da app

        // setting shadow
        final double RADIUS = 10;
        DropShadow dropShadow = new DropShadow(RADIUS, Color.rgb(25,25,25));
        mainLayout.setEffect(dropShadow);
        mainLayout.setPadding(new Insets(10,10,10,10));
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        rootPane.setStyle("-fx-background-color: transparent;");
        rootPane.getChildren().add(mainLayout);
    }


    public static MendesTextArea getMendesTextArea() {
        return mendesTextArea;
    }
    public static void main(String[] args) { launch(args); }

}

