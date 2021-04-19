
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
    private Button buttom;

    @FXML
    private Button skip;

    ComparableCard CardsOnField;
 
    @FXML
    private Button enter;

    private int [] indexCom1,indexCom2,indexCom3;

    private TimeCount time = new TimeCount();


    @FXML
    private void setUp(ActionEvent event){
       
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

    public void checkLimitCards(){ 
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

    private void firstPlay(){
        int turn = GameSesstion.findFirsTurn(playerHand, Com1Hand, Com2Hand, Com3Hand);
        if(turn==0){
            paneList.get(0).getChildren().clear();
            playerHand.get(0).setStatus(false);
            game.decreasePlayerhand(1);
            CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(0).getImageview());
            CardsOnField=playerHand.get(0);
            bot1Play();
            bot2Play();
            bot3Play();
           
        }
        else if(turn==1){
           
            Com1Hand.get(0).setStatus(false);
            game.decreaseCom1hand(1);
            CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(0).getImageview());
            CardsOnField=Com1Hand.get(0);
            bot2Play();
            bot3Play();
        }
        else if(turn==2){
            Com2Hand.get(0).setStatus(false);
            game.decreaseCom2hand(1);
            CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(0).getImageview());
            CardsOnField=Com2Hand.get(0);
            bot3Play();
        }
        else if(turn==3){
            Com3Hand.get(0).setStatus(false);
            game.decreaseCom3hand(1);
            CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(0).getImageview());
            CardsOnField=Com3Hand.get(0);
        }
    }
    
    private void endTurn(){
        fethButton();
        checkLimitCards();
        if(game.getCom1CanPlay()==true){
            bot1Play();
        }
        fethButton();
        checkLimitCards();
        System.out.println(CardsOnField);
        if(game.getCom2CanPlay()==true){
            bot2Play();
        }
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
           

            for(int loop=0;loop<4;loop++)
            {
                game.setStatusButtontrue(loop);
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
            
            game.setStartStage(false);
        }

        if(game.getPlayerCanPlay()==false){
           endTurn();
        }
        

    }

    @FXML
    private void playerSkip(ActionEvent event){
        game.plusSkip(1);
        System.out.println("skip : "+game.getSkip());
        game.setPlayerCanPlay(false);
        endTurn();
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

    

    

   

}
