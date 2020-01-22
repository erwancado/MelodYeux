import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class ReprendreLectureScene extends WindowClass{


    private Button lectureButton;
    private Button repertoireButton;
    private Button ajouterPartitionButton;

    private int BORDER_SIZE = 10;

    //Constructeur de la class
    public ReprendreLectureScene() {

        //On appelle le constructeur de la class mère pour initialiser la scene et le group
        super();

        scene.getStylesheets().add("css/ReprendreLectureScene.css");

        //region Definition des éléments graphique et positionnement
       /*On crée ensuite les élément présent dans notre page SecondPageScene
       (à modifier pour que ça corresponde aux nom de nos pages)*/

        lectureButton = new Button("Reprendre lecture");
        lectureButton.setId("lectureButton");
        lectureButton.setLayoutX(0);
        lectureButton.setLayoutY(0);
        lectureButton.setMinSize(WIDTH- BORDER_SIZE*2, HEIGHT/2 - BORDER_SIZE*2);

        repertoireButton = new Button("Répertoire");
        repertoireButton.setId("repertoireButton");
        repertoireButton.setLayoutX(0);
        repertoireButton.setLayoutY((HEIGHT/2) - BORDER_SIZE*3);
        repertoireButton.setMinSize(WIDTH/2- BORDER_SIZE*2, HEIGHT/2 - BORDER_SIZE*2);

        ajouterPartitionButton = new Button("Ajouter\npartition");
        ajouterPartitionButton.setId("ajouterPartButton");
        ajouterPartitionButton.setLayoutX((HEIGHT/2) - BORDER_SIZE*3);
        ajouterPartitionButton.setLayoutY((HEIGHT/2) - BORDER_SIZE*3);
        ajouterPartitionButton.setMinSize((WIDTH)/2- BORDER_SIZE*2, HEIGHT/2 - BORDER_SIZE*2);

        //endregion


        //region Region de Gestion des boutons

        repertoireButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Main.setActiveScene(Main.myStage, 3);
        });

        //endregion

        //On ajoute nos éléments à notre scene
        addMultipleToScene(lectureButton, repertoireButton, ajouterPartitionButton);
    }

    public void resizeButton(){
        lectureButton.setMinSize(WIDTH - BORDER_SIZE*2, HEIGHT/2- BORDER_SIZE*2 );
        repertoireButton.setMinSize(WIDTH - BORDER_SIZE*2, HEIGHT/2- BORDER_SIZE*2);
        ajouterPartitionButton.setMinSize(WIDTH - BORDER_SIZE*2, HEIGHT/2- BORDER_SIZE*2);

    }
}
