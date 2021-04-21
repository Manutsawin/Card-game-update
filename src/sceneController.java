
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class sceneController {

    private ArrayList<ComparableCard> playerHand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com1Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com2Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com3Hand = new ArrayList<ComparableCard>(); 

    private ArrayList<javafx.scene.image.ImageView> imageviewButtonOn = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonOff = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonPush = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonTurnMark = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands0 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands1 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands2 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands3 = new ArrayList<javafx.scene.image.ImageView>();
   
    private GameSesstion game = new GameSesstion();

    @FXML
    private ArrayList<Pane> paneList ;

    @FXML
    private ArrayList<Pane> paneTopList ;

    @FXML
    private ArrayList<Pane> BList ;

    @FXML
    private ArrayList<Pane> CardOnFieldPlayerList ;

    @FXML
    private ArrayList<Pane> CardOnFieldComthreeList ;
    
    @FXML
    private ArrayList<Pane> CardOnFieldComtwoList ;

    @FXML
    private ArrayList<Pane> CardOnFieldComoneList ;

    @FXML
    private ArrayList<Pane> cardOnHandsList ;

    @FXML
    private Button buttom;

    @FXML
    private Button skip;

    @FXML
    private Button next;
    
    @FXML
    private Button exBT;

    @FXML
    private Pane turnPlayer;

    @FXML
    private Pane turnBot1;

    @FXML
    private Pane turnBot2;

    @FXML
    private Pane turnBot3;

    // @FXML
    // private Pane cardOnHands;

    // @FXML
    // private Pane cardOnHands1;

    // @FXML
    // private Pane cardOnHands3;

    // @FXML
    // private Pane cardOnHands2;

    ComparableCard CardsOnField;
 
    @FXML
    private Button enter;

    private int [] indexCom1,indexCom2,indexCom3;

    private TimeCount time = new TimeCount();
    

    @FXML
    public void setUp(){
       
        
        RandomHand rand = new RandomHand();
        this.playerHand = rand.getPlayerHand();
        this.Com1Hand = rand.getCom1Hand();
        this.Com2Hand = rand.getCom2Hand();
        this.Com3Hand = rand.getCom3Hand();
        CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(0).getImageview());
        CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(0).getImageview());
        CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(0).getImageview());
        CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(0).getImageview());
        for(int loop=0;loop<13;loop++)
        {
            paneTopList.get(loop).getChildren().clear();
            paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
        }
        
        game = new GameSesstion();

        imageviewButtonOn = SetpicMainPages.setpicOn();
        imageviewButtonOff = SetpicMainPages.setpicOff();
        imageviewButtonPush = SetpicMainPages.setpicPush();
        imageviewButtonTurnMark = SetpicMainPages.setpicTurnMark();
        imageviewNumberHands0 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands1 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands2 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands3 = SetpicMainPages.setpicNumberHands();

        game.setSelectStage(game.getStageGame());
        for(int loop=0;loop<4;loop++)
        {
            BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
        }
        BList.get(game.getSelectStage()).getChildren().clear();
        BList.get(game.getSelectStage()).getChildren().add(imageviewButtonPush.get(game.getSelectStage()));
        fethButton();
        checkLimitCards();
        game.setSelectCards(0);
        System.out.println(time.getTimecount());  
        setNumberHands();
        firstPlay();  
    }

   

    private void fethButton(){
        if(!ComparableCard.twoCardsOnHand(playerHand)){
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(1);
        } 
        if(!ComparableCard.threeCardsOnHand(playerHand)){
            BList.get(2).getChildren().clear();
            BList.get(2).getChildren().add(imageviewButtonOff.get(2));
            game.setStatusButtonfalse(2);
        }
        if(!ComparableCard.fourCardsOnHand(playerHand)){
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
            game.setStatusButtonfalse(3);
        } 

        if(game.getStageGame()==0){
            game.setStatusButtonfalse(1);
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(3);
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
        }
        else if(game.getStageGame()==1){
            game.setStatusButtonfalse(0);
            BList.get(0).getChildren().clear();
            BList.get(0).getChildren().add(imageviewButtonOff.get(0));
            game.setStatusButtonfalse(2);
            BList.get(2).getChildren().clear();
            BList.get(2).getChildren().add(imageviewButtonOff.get(2));
        }
        else if(game.getStageGame()==2){
            game.setStatusButtonfalse(0);
            BList.get(0).getChildren().clear();
            BList.get(0).getChildren().add(imageviewButtonOff.get(0));
            game.setStatusButtonfalse(1);
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(3);
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
        }

    }

    private void refeshBT(){
        for(int loop=0;loop<4;loop++){
            if(game.getStatusButton(loop)==true){
                BList.get(loop).getChildren().clear();
                BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
            }
            else{
                BList.get(loop).getChildren().clear();
                BList.get(loop).getChildren().add(imageviewButtonOff.get(loop));
            }
        }
    }

    private void setNumberHands(){
        for(int loop=13;loop>=0;loop--){
            if(game.getNumPlayerhand()==loop){
                cardOnHandsList.get(0).getChildren().clear();
                cardOnHandsList.get(0).getChildren().add(imageviewNumberHands0.get(loop));
            }
            if(game.getNumCom1hand()==loop){
                cardOnHandsList.get(1).getChildren().clear();
                cardOnHandsList.get(1).getChildren().add(imageviewNumberHands1.get(loop));
            }
            if(game.getNumCom2hand()==loop){
                cardOnHandsList.get(2).getChildren().clear();
                cardOnHandsList.get(2).getChildren().add(imageviewNumberHands2.get(loop));
            }
            if(game.getNumCom3hand()==loop){
                cardOnHandsList.get(3).getChildren().clear();
                cardOnHandsList.get(3).getChildren().add(imageviewNumberHands3.get(loop));
            }
        }
        
        
    }

    private void checkLimitCards(){ 
        game.setLimitSelectCards(game.getStageGame()+1);
    }

    @FXML
    private void clickonCards(MouseEvent event)
    {   
        fethButton();
        
        int value = Integer.parseInt(((Pane)event.getSource()).getId());
        System.out.println("Time :"+time.getTimecount());
        System.out.println("Stage : "+game.getStageGame());
        System.out.println("Skip : "+game.getSkip());
        System.out.println(CardsOnField);
        System.out.println("Select : "+game.getSelectCards()+" Limit : "+game.getLimitSelectCards());
        System.out.println("Player : "+game.getNumPlayerhand()+"||"+" COM1 : "+game.getNumCom1hand()+"||"+" COM2 : "+game.getNumCom2hand()+"||"+" COM3 : "+game.getNumCom3hand());
       if(game.getSelectCards()<game.getLimitSelectCards()&&playerHand.get(value).getStatus()==true) {
            if(game.getSelectStage()==0)
            {
                if(playerHand.get(value).checkValue1(CardsOnField)==1)
                {
                    paneList.get(value).getChildren().clear();
                    paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                    game.setPlayerSelectIndex(game.getSelectCards(),value);
                    game.plusSelectCards();
                }
            }
            else if(game.getSelectStage()==1)
            {
               
                if(ComparableCard.isTwoCard(playerHand, playerHand.get(value).getValue())){
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue())
                    {
                        
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    } 
                }
            }
            else if(game.getSelectStage()==2)
            {
               
                if(ComparableCard.isThreeCard(playerHand, playerHand.get(value).getValue())){
  
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue()||game.getStageGame()==0){
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    }
                }
            }
            else if(game.getSelectStage()==3)
            {
                if(ComparableCard.isFourCard(playerHand, playerHand.get(value).getValue())){
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue()||game.getStageGame()==1){
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    }
                }
            }
       }
        
        
    }

    @FXML
    private void clickonCardsTop(MouseEvent event)
    {
        int value = Integer.parseInt(((Pane)event.getSource()).getId());
        if(playerHand.get(value).getStatus()==true){
            paneTopList.get(value).getChildren().clear();
            paneList.get(value).getChildren().add(playerHand.get(value).imageview);
            game.decreaseSelectCards();
        }
        
       
    }

    @FXML
    private void selectStange(MouseEvent event)
    {
        for(int loop=0;loop<13;loop++){
            if(playerHand.get(loop).getStatus()==true)
            {
                paneTopList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
            }
        }
        
        int value = Integer.parseInt(((Pane)event.getSource()).getId());


        if(game.getStatusButton(value)||game.getStartStage()==true)
        {
            BList.get(game.getSelectStage()).getChildren().clear();
            BList.get(game.getSelectStage()).getChildren().add(imageviewButtonOn.get(game.getSelectStage()));
            BList.get(value).getChildren().clear();
            BList.get(value).getChildren().add(imageviewButtonPush.get(value));

            game.setSelectStage(value);
            game.setLimitSelectCards(value+1);
            game.setSelectCards(0);
            System.out.println(game.getSelectStage());
            
        }
        
    }

    @FXML
    private void playerEnterCard(ActionEvent event){

        if(game.getTurn()==0){
            game.setStageGame(game.getSelectStage());

            if(game.getStageGame()==0){
                paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);
                game.decreasePlayerhand(1);
            }
            else if(game.getStageGame()==1){
                paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                if(playerHand.get(game.getPlayerSelectIndex(0)).checkValue1(playerHand.get(game.getPlayerSelectIndex(0)))==1){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                }
                else{
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                }
                playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false);  
                game.decreasePlayerhand(2);
            }
            else if(game.getStageGame()==2){
                paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(2)).getChildren().clear();

                System.out.println(game.getPlayerSelectIndex(0));
                System.out.println(game.getPlayerSelectIndex(1));
                System.out.println(game.getPlayerSelectIndex(2));

                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                CardOnFieldPlayerList.get(2).getChildren().add(playerHand.get(game.getPlayerSelectIndex(2)).imageview);
                if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==1){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                }
                else if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==2){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                }
                else if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==3){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(2));
                }
                playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false); 
                playerHand.get(game.getPlayerSelectIndex(2)).setStatus(false);
                game.decreasePlayerhand(3);   
            
            }
            else if(game.getStageGame()==3){
                paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(2)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(3)).getChildren().clear();
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                CardOnFieldPlayerList.get(2).getChildren().add(playerHand.get(game.getPlayerSelectIndex(2)).imageview);
                CardOnFieldPlayerList.get(3).getChildren().add(playerHand.get(game.getPlayerSelectIndex(3)).imageview);
                if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==1){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                }
                else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==2){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                }
                else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==3){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(2));
                }
                else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==4){
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(3));
                }
                playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false); 
                playerHand.get(game.getPlayerSelectIndex(2)).setStatus(false);
                playerHand.get(game.getPlayerSelectIndex(3)).setStatus(false);
                game.decreasePlayerhand(4);   

            }
            game.setSelectCards(0);
            fethButton(); 
            endTurn();
        }
    }

    private void firstPlay(){
        try {
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().clear();
            turnBot2.getChildren().clear();
            turnBot3.getChildren().clear();
        }
        catch (Exception ex) {
            System.out.println("clear pic turn on firstPlay");
            
        }
        CardOnFieldComthreeList.get(0).getChildren().clear();
        CardOnFieldComtwoList.get(0).getChildren().clear();
        CardOnFieldComoneList.get(0).getChildren().clear();
        CardOnFieldPlayerList.get(0).getChildren().clear();
        int turn = GameSesstion.findFirsTurn(playerHand, Com1Hand, Com2Hand, Com3Hand);
        game.setTurn(turn);
        if(turn==0){
            paneList.get(0).getChildren().clear();
            playerHand.get(0).setStatus(false);
            game.decreasePlayerhand(1);
            CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(0).getImageview());
            CardsOnField=playerHand.get(0);
            game.plusTurn(1);
            turnBot1.getChildren().add(imageviewButtonTurnMark.get(0));
            // bot1Play();
            // bot2Play();
            // bot3Play();
            // game.setTurn(0);
           
        }
        else if(turn==1){
           
            Com1Hand.get(0).setStatus(false);
            game.decreaseCom1hand(1);
            CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(0).getImageview());
            CardsOnField=Com1Hand.get(0);
            turnBot2.getChildren().add(imageviewButtonTurnMark.get(0));
            game.plusTurn(1);
           
            // bot2Play();
            // bot3Play();
            // game.setTurn(0);
        }
        else if(turn==2){
            Com2Hand.get(0).setStatus(false);
            game.decreaseCom2hand(1);
            CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(0).getImageview());
            CardsOnField=Com2Hand.get(0);
            turnBot3.getChildren().add(imageviewButtonTurnMark.get(0));
            game.plusTurn(1);
            
            // bot3Play();
            // game.setTurn(0);
        }
        else if(turn==3){
            Com3Hand.get(0).setStatus(false);
            game.decreaseCom3hand(1);
            CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(0).getImageview());
            CardsOnField=Com3Hand.get(0);
            turnPlayer.getChildren().add(imageviewButtonTurnMark.get(0));
            game.setTurn(0);
           
            // game.setTurn(0);
        }

        
        
    }

    @FXML
    private void next(){
        
        if(game.getTurn()!=0||game.getPlayerCanPlay()==false){
            endTurn();
        }
        
    }


    
    private void endTurn(){
        if(game.getTurn()==1){
            
            fethButton();
            checkLimitCards();
            if(game.getCom1CanPlay()==true){
                bot1Play();
            }
        }
        else if(game.getTurn()==2){
           
            fethButton();
            checkLimitCards();
            System.out.println(CardsOnField);
            if(game.getCom2CanPlay()==true){
                bot2Play();
            }
        }
        else if(game.getTurn()==3){
           
            fethButton();
            checkLimitCards();
            System.out.println(CardsOnField);
            if(game.getCom3CanPlay()==true){
                bot3Play();
            }

            System.out.println(CardsOnField);
            fethButton();
            checkLimitCards();
        
            if(game.getSkip()==3&&game.getPlayerCanPlay()==true){
                
                for(int loop=0;loop<4;loop++){
                    CardOnFieldPlayerList.get(loop).getChildren().clear();
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setStartStage(true);
                game.setSkip(0);
            }

            if(game.getStartStage()==true){
                System.out.println("new game");
                
                game.setStageGame(0);
                game.setSelectStage(0);
                game.setLimitSelectCards(1);

                game.setCom1CanPlay(true);
                game.setCom2CanPlay(true);
                game.setCom3CanPlay(true);
                game.setPlayerCanPlay(true);
                CardsOnField.setValue(0);
                game.setStartStage(false);
                
                try {
                   
                    for(int loop=0;loop<4;loop++)
                    {
                        game.setStatusButtontrue(loop);
                    }
                    for(int loop=0;loop<4;loop++)
                    {
                        BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
                    }
                    if(!ComparableCard.twoCardsOnHand(playerHand)){
                        BList.get(1).getChildren().clear();
                        BList.get(1).getChildren().add(imageviewButtonOff.get(1));
                        game.setStatusButtonfalse(1);
                    } 
                    if(!ComparableCard.threeCardsOnHand(playerHand)){
                        BList.get(2).getChildren().clear();
                        BList.get(2).getChildren().add(imageviewButtonOff.get(2));
                        game.setStatusButtonfalse(2);
                    }
                    if(!ComparableCard.fourCardsOnHand(playerHand)){
                        BList.get(3).getChildren().clear();
                        BList.get(3).getChildren().add(imageviewButtonOff.get(3));
                        game.setStatusButtonfalse(3);
                    } 
                }
                catch (Exception ex) {
                    System.out.println("set and claer pic on methods endturn");
                    
                }

                
            
            }
           
        }

        
        
        
        if(game.getTurn()==3){
            game.setTurn(0);
        }
        else{
            game.plusTurn(1);
        }

        checkVictory();

        if(game.getTurn()==0){
            turnBot3.getChildren().clear();
            turnPlayer.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==1){
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==2){
            turnBot1.getChildren().clear();
            turnBot2.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==3){
            turnBot2.getChildren().clear();
            turnBot3.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        
        setNumberHands();
        try {
            refeshBT();
        }
        catch (Exception ex) {
            System.out.println("refeshBT()");
            
        }

        if(game.getTurn()==0&&game.getPlayerCanPlay()==false){
            next();
        }

        if(game.getTurn()==1&&game.getCom1CanPlay()==false){
            next();
        }

        if(game.getTurn()==2&&game.getCom2CanPlay()==false){
            next();
        }

        if(game.getTurn()==3&&game.getCom3CanPlay()==false){
            next();
        }

        if(game.getNumPlayerhand()==0){
            playerSkip();
        }

        if(game.getTurn()==1&&game.getNumCom1hand()==0){
            next();
        }

        if(game.getTurn()==2&&game.getNumCom2hand()==0){
            next();
        }

        if(game.getTurn()==3&&game.getNumCom3hand()==0){
            next();
        }


    }

    @FXML
    private void playerSkip(){
        if(game.getTurn()==0){
            game.plusSkip(1);
            System.out.println("skip : "+game.getSkip());
            game.setPlayerCanPlay(false);
            for(int loop =0 ;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
            }
            endTurn();
        }
       
    }

    private void bot1Play(){
        
        System.out.println("turn Bot 1");

        if(game.getSkip()==3&&game.getCom1CanPlay()==true){
            for(int loop=0;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
                CardOnFieldComoneList.get(loop).getChildren().clear();
                CardOnFieldComtwoList.get(loop).getChildren().clear();
                CardOnFieldComthreeList.get(loop).getChildren().clear();
            }
            game.setStartStage(true);
            game.setSkip(0);
        }
        
        indexCom1 = Bot.botCalculate(Com1Hand,CardsOnField, game);
            if(indexCom1[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                }
                game.setCom1CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom1[4]==0){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                game.decreaseCom1hand(1);
                CardsOnField=Com1Hand.get(indexCom1[0]);
                game.setStageGame(0);
                System.out.println("COM1 Enter Card");
            }
            else if(indexCom1[4]==1){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                game.decreaseCom1hand(2);
                CardsOnField=Com1Hand.get(indexCom1[1]);
                game.setStageGame(1);
                System.out.println("COM1 Enter Card");

            }
            else if(indexCom1[4]==2){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                CardOnFieldComoneList.get(2).getChildren().add(Com1Hand.get(indexCom1[2]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                Com1Hand.get(indexCom1[2]).setStatus(false);
                game.decreaseCom1hand(3);
                CardsOnField=Com1Hand.get(indexCom1[2]);
                game.setStageGame(2);
                System.out.println("COM1 Enter Card");

            }
            else if(indexCom1[4]==3){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                CardOnFieldComoneList.get(2).getChildren().add(Com1Hand.get(indexCom1[2]).imageview);
                CardOnFieldComoneList.get(3).getChildren().add(Com1Hand.get(indexCom1[3]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                Com1Hand.get(indexCom1[2]).setStatus(false);
                Com1Hand.get(indexCom1[3]).setStatus(false);
                game.decreaseCom1hand(4);
                CardsOnField=Com1Hand.get(indexCom1[3]);
                game.setStageGame(3);
                System.out.println("COM1 Enter Card");
            }

    }

    private void bot2Play(){

        System.out.println("turn Bot 2");
       
        if(game.getSkip()==3&&game.getCom2CanPlay()==true){
            for(int loop=0;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
                CardOnFieldComoneList.get(loop).getChildren().clear();
                CardOnFieldComtwoList.get(loop).getChildren().clear();
                CardOnFieldComthreeList.get(loop).getChildren().clear();
            }
            game.setStartStage(true);
            game.setSkip(0);
        }

        indexCom2 = Bot.botCalculate(Com2Hand,CardsOnField, game);
            if(indexCom2[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                }
                game.setCom2CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom2[4]==0){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                game.decreaseCom2hand(1);
                CardsOnField=Com2Hand.get(indexCom2[0]);
                game.setStageGame(0);
                System.out.println("COM2 Enter Card");
            }
            else if(indexCom2[4]==1){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                game.decreaseCom2hand(2);
                CardsOnField=Com2Hand.get(indexCom2[1]);
                game.setStageGame(1);
                System.out.println("COM2 Enter Card");

            }
            else if(indexCom2[4]==2){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                CardOnFieldComtwoList.get(2).getChildren().add(Com2Hand.get(indexCom2[2]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                Com2Hand.get(indexCom2[2]).setStatus(false);
                game.decreaseCom2hand(3);
                CardsOnField=Com2Hand.get(indexCom2[2]);
                game.setStageGame(2);
                System.out.println("COM2 Enter Card");

            }
            else if(indexCom2[4]==3){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                CardOnFieldComtwoList.get(2).getChildren().add(Com2Hand.get(indexCom2[2]).imageview);
                CardOnFieldComtwoList.get(3).getChildren().add(Com2Hand.get(indexCom2[3]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                Com2Hand.get(indexCom2[2]).setStatus(false);
                Com2Hand.get(indexCom2[3]).setStatus(false);
                CardsOnField=Com2Hand.get(indexCom2[3]);
                game.decreaseCom2hand(4);
                game.setStageGame(3);
                System.out.println("COM2 Enter Card");
            }

    }

    private void bot3Play(){

        System.out.println("turn Bot 3" );
            
            if(game.getSkip()==3&&game.getCom3CanPlay()==true){
                for(int loop=0;loop<4;loop++){
                    
                    CardOnFieldPlayerList.get(loop).getChildren().clear();
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setStartStage(true);
                game.setSkip(0);
            }

            indexCom3 = Bot.botCalculate(Com3Hand,CardsOnField, game);
            if(indexCom3[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setCom3CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom3[4]==0){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                game.decreaseCom3hand(1);
                CardsOnField=Com3Hand.get(indexCom3[0]);
                game.setStageGame(0);
                System.out.println("COM3 Enter Card");
            }
            else if(indexCom3[4]==1){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                game.decreaseCom3hand(2);
                CardsOnField=Com3Hand.get(indexCom3[1]);
                game.setStageGame(1);
                System.out.println("COM3 Enter Card");

            }
            else if(indexCom3[4]==2){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                CardOnFieldComthreeList.get(2).getChildren().add(Com3Hand.get(indexCom3[2]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                Com3Hand.get(indexCom3[2]).setStatus(false);
                game.decreaseCom3hand(3);
                CardsOnField=Com3Hand.get(indexCom3[2]);
                game.setStageGame(2);
                System.out.println("COM3 Enter Card");

            }
            else if(indexCom3[4]==3){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                CardOnFieldComthreeList.get(2).getChildren().add(Com3Hand.get(indexCom3[2]).imageview);
                CardOnFieldComthreeList.get(3).getChildren().add(Com3Hand.get(indexCom3[3]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                Com3Hand.get(indexCom3[2]).setStatus(false);
                Com3Hand.get(indexCom3[3]).setStatus(false);
                CardsOnField=Com3Hand.get(indexCom3[3]);
                game.decreaseCom3hand(4);
                game.setStageGame(3);
                System.out.println("COM3 Enter Card");
            }


    }

    private void checkVictory(){
        if(game.getNumPlayerhand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==0){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),0);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom1hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==1){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),1);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom2hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==2){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),2);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom3hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==3){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),3);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getOrderVictory()==4){
            nextRound();
        }
        
    }

    private void nextRound(){
        RandomHand rand = new RandomHand();
        this.playerHand = rand.getPlayerHand();
        this.Com1Hand = rand.getCom1Hand();
        this.Com2Hand = rand.getCom2Hand();
        this.Com3Hand = rand.getCom3Hand();
        for(int loop=0;loop<13;loop++)
        {
            paneTopList.get(loop).getChildren().clear();
            paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
        }
        
        game = new GameSesstion();

        imageviewButtonOn = SetpicMainPages.setpicOn();
        imageviewButtonOff = SetpicMainPages.setpicOff();
        imageviewButtonPush = SetpicMainPages.setpicPush();
        imageviewButtonTurnMark = SetpicMainPages.setpicTurnMark();
        imageviewNumberHands0 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands1 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands2 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands3 = SetpicMainPages.setpicNumberHands();

        game.setSelectStage(game.getStageGame());
        for(int loop=0;loop<4;loop++)
        {
            BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
        }
        BList.get(game.getSelectStage()).getChildren().clear();
        BList.get(game.getSelectStage()).getChildren().add(imageviewButtonPush.get(game.getSelectStage()));
        fethButton();
        checkLimitCards();
        game.setSelectCards(0);
        System.out.println(time.getTimecount());  
        setNumberHands();
        for(int loop=0;loop<4;loop++){
            CardOnFieldPlayerList.get(loop).getChildren().clear();
            CardOnFieldComoneList.get(loop).getChildren().clear();
            CardOnFieldComtwoList.get(loop).getChildren().clear();
            CardOnFieldComthreeList.get(loop).getChildren().clear();
        }
        try {
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().clear();
            turnBot2.getChildren().clear();
            turnBot3.getChildren().clear();
        }
        catch (Exception ex) {
            System.out.println("clear pic turn on nextRound");
            
        }
    }

    @FXML
    private void exchange(){
        
        playerHand = ComparableCard.tabulateListCards(playerHand, 3, 4, Com1Hand.get(11), Com1Hand.get(12));
        System.out.println(playerHand.size());
        System.out.println("card1 : "+Com1Hand.get(11));
        System.out.println("card2 : "+Com1Hand.get(12));
        try {
            for(int loop=0;loop<13;loop++)
            {
                paneList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
                System.out.println(playerHand.get(loop));
            }
        }
        catch (Exception ex) {
            System.out.println("exchange");
            
        }
        
        
    }
    
   

}
