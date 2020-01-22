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


        quit = new Button("QUITTER");
        quit.setMinSize(WIDTH*0.5, HEIGHT*0.1);

        quit.setLayoutX((WIDTH-quit.getMinWidth())/2);
        quit.setLayoutY(HEIGHT*0.7);
        quit.setStyle("-fx-font-size:" +  WIDTH*0.05);
        //On défini le style du bouton
        quit.setId("quitButton");

        melodyeuxButton = new Button("MELOD'yeux");
        melodyeuxButton.setMinSize(WIDTH*0.75,HEIGHT*0.50);
        melodyeuxButton.setLayoutX((WIDTH-melodyeuxButton.getMinWidth())/2);
        melodyeuxButton.setLayoutY(HEIGHT*0.05);
        melodyeuxButton.setStyle("-fx-font-size:" +  WIDTH*0.13);
        melodyeuxButton.setId("melodyeuxButton");

        //endregion

        //region Region de Gestion des boutons
        //On ajoute des listener aux boutons pour gérer les evenements (ici click) On le fait pour chaque bouton
        melodyeuxButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            Main.setActiveScene(Main.myStage, 1);
        });

        quit.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            Platform.exit();
        });

        //endregion

        //On ajoute les éléments à la scene !
        addMultipleToScene(melodyeuxButton,quit);

    }

}
