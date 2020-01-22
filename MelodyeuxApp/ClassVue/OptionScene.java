import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;


public class OptionScene extends WindowClass {


    Slider contrasteSlider;

    public OptionScene() {

        super();

        this.contrasteSlider = new Slider();
        this.contrasteSlider.setMin(0);
        this.contrasteSlider.setMax(100);
        this.contrasteSlider.setLayoutX(Main.WIDTH/2);
        this.contrasteSlider.setLayoutY(Main.HEIGHT/2);
        this.contrasteSlider.setMinWidth(Main.WIDTH/2);
        this.contrasteSlider.setMinHeight(Main.HEIGHT/2);
        this.contrasteSlider.setValue(50);

        this.contrasteSlider.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {

            Main.myStage.setOpacity(this.contrasteSlider.getValue());
        });

        addToScene(contrasteSlider);

    }
}
