package BL.cBook.utils;

import BL.cBook.services.MyController;
import BL.cBook.services.MyControllerWithParameter;
import BL.cBook.uiControllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Loader {

    public static void load(MainController m, String path, String title) {

        init(m,path,title,null);
    }

    public static <T>void load(MainController m, String path, String title,T t) {

        init(m,path,title,t);
    }

    private static <T>void init(MainController mc, String path, String title,T t) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (t==null){
            MyController controller = loader.getController();
            controller.initialize(mc);
        }
        else {
            MyControllerWithParameter<T> controller = loader.getController();
            controller.initialize(mc,t);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/res/cow.png"));
        stage.setTitle(title);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }
}
