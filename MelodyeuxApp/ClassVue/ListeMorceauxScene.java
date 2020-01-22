import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class ListeMorceauxScene extends WindowClass {


    private ArrayList<String> morceauxTab;
    private String[] morceauxAfficheTab;
    private int cptMorceau;
    private int maxNbPage;

    private Button backButton;
    private Button nextButton;

    private final int BORDER_SIZE = 12;


    public ListeMorceauxScene() {
        super();

        System.out.println(Main.numScene);

        scene.getStylesheets().add("css/ListeMorceauxScene.css");

        morceauxTab = new ArrayList<>();
        cptMorceau = 0;
        morceauxAfficheTab = new String[4];

        //region Definition des éléments graphique et positionnement
        backButton = new Button("Précédent");
        backButton.setMinSize(WIDTH/2 + (BORDER_SIZE >> 1), HEIGHT/3 + (BORDER_SIZE >> 1));
        backButton.setLayoutX(0);
        backButton.setLayoutY(2*HEIGHT/3);
        backButton.setStyle("-fx-font-size:"+ WIDTH*0.08);

        nextButton = new Button("Suivant");
        nextButton.setMinSize(WIDTH/2 + (BORDER_SIZE >> 1), HEIGHT/3 + (BORDER_SIZE >> 1));
        nextButton.setLayoutX(WIDTH/2);
        nextButton.setLayoutY(2*HEIGHT/3);
        nextButton.setStyle("-fx-font-size:"+ WIDTH*0.08);


        nextButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->{
            if (cptMorceau/4+1 < maxNbPage)
                cptMorceau+=4;
            else
                return;
            InitAfficheTab();
            InitButtons();
        });

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (cptMorceau != 0)
                cptMorceau-=4;
            else{
                Main.setActiveScene(Main.myStage, 2);
                return;
            }

            InitAfficheTab();
            InitButtons();
        });


        //endregion

        InitTabMorceau();
        if (morceauxTab.size()%4 == 0){
            maxNbPage = morceauxTab.size()/4;
        }
        else
            maxNbPage = morceauxTab.size()/4 +1;

        InitAfficheTab();
        InitButtons();

        addMultipleToScene(backButton, nextButton);


    }

    private void InitAfficheTab() {
        for (int i = 0; i < morceauxAfficheTab.length; i++){
            morceauxAfficheTab[i] = "";
        }
        int j = 0;
        for (int i = cptMorceau; i < cptMorceau+4; i++){

            morceauxAfficheTab[j] = morceauxTab.get(i);
            j++;

            if (i == morceauxTab.size()-1)
                break;


        }
    }

    private void InitButtons() {
        Button b;
        int i = 0;
        int j = 0;
        for (String m : morceauxAfficheTab){
            b = new Button(m);
            b.setMinSize(WIDTH/2 + (BORDER_SIZE >> 1), HEIGHT/3 + (BORDER_SIZE >> 1));
            b.setLayoutX(j*WIDTH/2);
            b.setLayoutY(i*HEIGHT/3);
            b.setStyle("-fx-font-size:"+ WIDTH*0.10);
            addToScene(b);
            j++;
            if (j > 1){
                j = 0;
                i++;
            }

        }
    }

    private void InitTabMorceau() {
        for (int i = 0; i < 8; i++){
            morceauxTab.add(Integer.toString(i));
        }
    }
}
