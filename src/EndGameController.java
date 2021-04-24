import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class EndGameController {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button gotoMenuButton;

    @FXML
    private AnchorPane acPane;

    @FXML
    private Pane s1;

    @FXML
    private Pane s2;

    @FXML
    private Pane s3;

    @FXML
    private Pane bg;

    private ArrayList<javafx.scene.image.ImageView> imageviewIcon = new ArrayList<javafx.scene.image.ImageView>();

    @FXML
    void gotoMenuButtonAction(ActionEvent event) throws IOException {

        Parent menuParent = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("Slave Menu");
        window.show();
    }

    @FXML
    void initialize() {
        bg.getChildren().add(SetpicMainPages.setpicBgEndGame());
        int []index = {0,1,2,3} ; 
        imageviewIcon = SetpicMainPages.setIcon();
        if(index[0]==0){
            s1.getChildren().add(imageviewIcon.get(0));
        }
        else if(index[0]==1){
            s1.getChildren().add(imageviewIcon.get(1));
        }
        else if(index[0]==2){
            s1.getChildren().add(imageviewIcon.get(2));
        }
        else if(index[0]==3){
            s1.getChildren().add(imageviewIcon.get(3));
        }

        if(index[1]==0){
            s2.getChildren().add(imageviewIcon.get(0));
        }
        else if(index[1]==1){
            s2.getChildren().add(imageviewIcon.get(1));
        }
        else if(index[1]==2){
            s2.getChildren().add(imageviewIcon.get(2));
        }
        else if(index[1]==3){
            s2.getChildren().add(imageviewIcon.get(3));
        }

        if(index[2]==0){
            s3.getChildren().add(imageviewIcon.get(0));
        }
        else if(index[2]==1){
            s3.getChildren().add(imageviewIcon.get(1));
        }
        else if(index[2]==2){
            s3.getChildren().add(imageviewIcon.get(2));
        }
        else if(index[2]==3){
            s3.getChildren().add(imageviewIcon.get(3));
        }
        


    }
}

