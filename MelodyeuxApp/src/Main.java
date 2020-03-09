import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {

    //Les élément statics sont accessibles partout dans le code en écrivant Main.monElement
    public static Stage myStage;
    public static int numScene = 0;

    //Pour chaque nouvelle page on crée un instance de cette page ici
    private static AccueilScene accueilPage;
    private static SecondPageScene secondPageScene;
    private static OptionScene optionScene;
    private static ListeMorceauxScene liste_morceauxScene;
    private static ReprendreLectureScene reprendreLectureScene ;


    @Override
    public void start(Stage stage) throws Exception {

        //On initialise la stage
        myStage = stage;

        myStage.setMinWidth(Screen.getPrimary().getBounds().getWidth());
        myStage.setMinHeight(Screen.getPrimary().getBounds().getHeight());
        myStage.setX(0);
        myStage.setY(0);

        System.out.println("Stage : W :" + myStage.getMinWidth() + " , H:" + myStage.getMinHeight());
        myStage.setTitle("Melod'yeux");

        myStage.widthProperty().addListener((obs, oldVal, newVal)->{
            secondPageScene.resizeButton();
        });

        //region Initialisation des différentes pages
        accueilPage = new AccueilScene();
        secondPageScene = new SecondPageScene();
        optionScene = new OptionScene();
        liste_morceauxScene = new ListeMorceauxScene();
        reprendreLectureScene = new ReprendreLectureScene();

        //endregion

        Robot r = new Robot();
        r.mouseMove((int)Screen.getPrimary().getBounds().getWidth()/2,(int)Screen.getPrimary().getBounds().getHeight()/2);

        setActiveScene(stage, numScene);

    }

    /*
    Permet de modifier la scene active. Methode static pour être appelée par d'autres classes.
    Pour chaque nouvelle page, on ajoute un
    case x :
        //mon code
        break;
     */
    public static void setActiveScene(Stage stage, int numScene){

        switch(numScene){
            case 0:
                stage.setScene(accueilPage.getScene());
                break;
            case 1:
                stage.setScene(secondPageScene.getScene());
                break;
            case 2:
                stage.setScene(reprendreLectureScene.getScene());
                break;
            case 3:
                stage.setScene(liste_morceauxScene.getScene());
                break;
            /*case 4:
                stage.setScene(optionScene.getScene());
                break;*/
            default:
                stage.setScene(accueilPage.getScene());
                break;
        }
        stage.show();
    }


    public static void main(String[] args){
        launch(args);
    }

}
