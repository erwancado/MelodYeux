import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


/*
Classe mère, toutes les pages devrons hériter de cette classe, c'est elle qui s'occupe de gérer les scenes
 */
public class WindowClass {

    protected Group group;
    protected Scene scene;

    public WindowClass() {

        group = new Group();
        scene = new Scene(group, Color.BLACK);

        //On gère les différentes action en fonction des touches!
        scene.setOnKeyPressed(key -> {

            switch (key.getCode()){
                case ALT:
                    Platform.exit();
                    break;
                case LEFT:
                    Main.setActiveScene(Main.myStage, --Main.numScene);
                    break;
                case RIGHT:
                    Main.setActiveScene(Main.myStage, ++Main.numScene);
                    break;
            }
        });

    }

    //Permet d'ajouter des elements à la scene
    public void addToScene(Node e){
        group.getChildren().add(e);
    }

    //Permet d'ajouter plusieurs elements à la scene
    public void addMultipleToScene(Node ... elements){
        group.getChildren().addAll(elements);
    }

    public Scene getScene(){
        return this.scene;
    }
}
