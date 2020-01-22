import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

public class AccueilScene extends WindowClass {


    private Button melodyeuxButton;
    private Button quit;

    public AccueilScene() {
        //On appelle le constructeur de la classe mère
        super();

        scene.getStylesheets().add("css/AccueilScene.css");

        //region Definition des éléments graphique et positionnement

        //On définit tout les élément dont on a besoin et on les positionne

        //hbox = new HBox();
        quit = new Button("QUITTER");
        quit.setMinSize(1000, 200);
        quit.setLayoutX(300);
        quit.setLayoutY(700);
        //On défini le style du bouton
        quit.setId("quitButton");

        melodyeuxButton = new Button("MELOD'yeux");
        melodyeuxButton.setMinSize(1400,600);
        melodyeuxButton.setLayoutX(100);
        melodyeuxButton.setLayoutY(50);
        melodyeuxButton.setId("melodyeuxButton");

        //endregion

        //region Region de Gestion des boutons
        //On ajoute des listener aux boutons pour gérer les evenements (ici click) On le fait pour chaque bouton
        melodyeuxButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            Main.setActiveScene(Main.myStage, ++Main.numScene);
        });

        quit.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            Platform.exit();
        });

        //endregion

        //On ajoute les éléments à la scene !
        addMultipleToScene(melodyeuxButton,quit);

    }

}
