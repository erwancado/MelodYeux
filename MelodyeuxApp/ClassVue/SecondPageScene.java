import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class SecondPageScene extends WindowClass{


    private Button partitionButton;
    private Button optionButton;

    private int BORDER_SIZE = 10;

    //Constructeur de la class
    public SecondPageScene() {

        //On appelle le constructeur de la class mère pour initialiser la scene et le group
        super();

        scene.getStylesheets().add("css/SecondPageScene.css");

        //region Definition des éléments graphique et positionnement
       /*On crée ensuite les élément présent dans notre page SecondPageScene
       (à modifier pour que ça corresponde aux nom de nos pages)*/

        partitionButton = new Button("Partition");
        partitionButton.setId("partitionButton");
        partitionButton.setLayoutX(0);
        partitionButton.setLayoutY(0);
        partitionButton.setMinSize(Main.myStage.getWidth()- BORDER_SIZE*2, Main.myStage.getHeight()/2 - BORDER_SIZE*2);

        optionButton = new Button("Option");
        optionButton.setId("optionButton");
        optionButton.setLayoutX(0);
        optionButton.setLayoutY((Main.HEIGHT/2) - BORDER_SIZE*3);
        optionButton.setMinSize(Main.myStage.getWidth()- BORDER_SIZE*2, Main.myStage.getHeight()/2 - BORDER_SIZE*2);

        //endregion


        //region Region de Gestion des boutons

        optionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Main.setActiveScene(Main.myStage, 4);
        });
        partitionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Main.setActiveScene(Main.myStage, 2);
        });

        //endregion

        //On ajoute nos éléments à notre scene
        addMultipleToScene(partitionButton, optionButton);
    }

    public void resizeButton(){
        partitionButton.setMinSize(Main.myStage.getWidth() - BORDER_SIZE*2, Main.myStage.getHeight()/2- BORDER_SIZE*2 );
        optionButton.setMinSize(Main.myStage.getWidth() - BORDER_SIZE*2, Main.myStage.getHeight()/2- BORDER_SIZE*2);
    }
}
