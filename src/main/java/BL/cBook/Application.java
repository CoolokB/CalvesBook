package BL.cBook;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    @Autowired
    @Qualifier("mainParent")
    Parent main;

    @Override
    public void start(Stage stage) {

        stage.setTitle("CalvesBook");
        stage.setScene(new Scene(main));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/res/cow.png"));
        stage.setMinWidth(1200);
        stage.setMinHeight(500);

        stage.setOnCloseRequest((t) -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        launchApp(Application.class, args);
    }
}






