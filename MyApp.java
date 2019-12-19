/**********************************************************************************
 * Name        : Kevin Ramirez
 * Date        : 12/15/19
 * Program Name: Blackjack - with GUI (JavaFX)
 * File        : MyApp.java
 * Description : Vegas style blackjack game that starts with the player drawing
 *               two cards at random.  Based on the total value(score) of the
 *               cards, the player can choose to "hit" or "stay".
 *               If the player hits, then a card is drawn and is added to the score.
 *               If the players score is over 21, the game is over and the dealer
 *               wins as the player is busted.  If the player chooses to stay, the
 *               dealers turn starts.  The dealers cards and score is revealed.
 *               The dealer can either choose to hit or stay like the player.
 *               If the dealer hits and his score exceeds 21, the player wins.
 *               If the dealer stays, both the player and the dealers scores are
 *               displayed and whoever's score is closest to 21 without going over
 *               wins the game. This program was coded while learning Java.
 *
 *               Resources used: http://programmingbydoing.com
 *                               https://www.youtube.com/playlist?list=PLfu_Bpi_zcDNYL6171Op3S1ABtuyFV7Nr
 *                               https://entertainment.howstuffworks.com/blackjack3.htm
 *                               https://www.tutorialspoint.com/javafx/javafx_overview.htm
 *
 *               Update (12/16/19): Main game logic and UI elements are tested and working properly together.  Now
 *                                  program still needs to be styled and modified to look good.  Also needs a
 *                                  restart/reset game button that will start a new game from scratch.
 *                                  Also !! need to finish proper documentation.
 *
 ***********************************************************************************/

import java.io.FileNotFoundException;
import java.util.Random;  // Used for the generation of random numbers for the card values
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;



public class MyApp extends Application
{
    // --------------------------------------- VARIABLE DECLARATION -----------------------------------------------
    final String WINDOW_TITLE= "Blackjack";   // Constant (String) - used for the window title
    final int CARD_SPACE_1X  = 155;
    final int CARD_SPACE_2X  = 265;
    final int CARD_SPACE_4X  = 485;
    final int PLAYER_CARD_Y  = 380;
    final int DEALER_CARD_Y  = 100;
    final int CARD_WIDTH     = 100;
    final int CARD_HEIGHT    = 140;
    final int WINDOW_START_X = 220;           // Constant (Int) - used for the windows starting position on x axis
    final int WINDOW_START_Y = 60;            // Constant (Int) - used for the windows starting position on y axis
    final int WINDOW_H       = 600;           // Constant (Int) - used for the window height
    final int WINDOW_W       = 800;           // Constant (Int) - used for the window width
    final int BUTTON_W       = 100;           // Constant (Int) - used for the button width (preferred)
    final int MAX_SCORE      = 21;            // Constant (Int) - used to represent the winning score number
    final int DECK           = 2;             // Constant (Int) - used for the initial cards drawn
    int[] hitAr = new int[DECK]; // Array - used to store the returned values from the hit() function when player chooses hit
    int[] dealerHitAr = new int[DECK]; // Array - used to store the returned values from the hit() function when dealer chooses hit
    int[] playerCards = new int[DECK]; // Array - used to hold the values of the players two cards
    int[] dealerCards = new int[DECK]; // Array - used to hold the values of the dealers two cards
    int dealerNewCard        = 0;             // Int - used to hold the value of the a new card drawn by the dealer
    int newCard              = 0;             // Int - used to hold the value of the a new card drawn by the player
    int playerScore          = 0;             // Int - used to store the value of the players score
    int dealerScore          = 0;             // Int - used to store the value of the dealers score
    boolean dealersTurn      = false;         // Bool - used to indicate the start of the dealers turn
    boolean gameOver         = false;         // Bool - used to indicate the end of game
    ImageView cardSpace1P = new ImageView();
    ImageView cardSpace2P = new ImageView();
    ImageView cardSpace4P = new ImageView();

    ImageView cardSpace1D = new ImageView();
    ImageView cardSpace2D = new ImageView();
    ImageView cardSpace4D = new ImageView();





    @Override
    public void init()
    {
        System.out.println("Program started running.");
    }


    // This starts the program
    // Main blackjack program code located here
    @Override
    public void start(Stage stage) throws Exception
    {
        // -------------------------------------- WINDOW SETUP --------------------------------------
        // Every GUI window is called a stage
        stage.setTitle(WINDOW_TITLE);

        // Sets window width
        stage.setWidth(WINDOW_W);

        // Sets window height
        stage.setHeight(WINDOW_H);

        // Sets window starting position
        stage.setX(WINDOW_START_X);
        stage.setY(WINDOW_START_Y);


        // ----------------------------------- Window Components -----------------------------------------------------
        // Initialize card images
        Image cardBack= new Image(new FileInputStream("src/images/red_back.png"));



        // Window layout
        Pane pane = new Pane(); // creates new scene

        // Sets the scene for the stage as a pane layout
        stage.setScene(new Scene(pane, 300, 250)); // sets the scene for the window


        // --------------- Player cards -------------------------
        // Set image views size and locations of PLAYER cards
        cardSpace1P.setImage(cardBack);
        cardSpace1P.setX(CARD_SPACE_1X);
        cardSpace1P.setY(PLAYER_CARD_Y);
        cardSpace1P.setFitHeight(CARD_HEIGHT);
        cardSpace1P.setFitWidth(CARD_WIDTH);

        cardSpace2P.setImage(cardBack);
        cardSpace2P.setX(CARD_SPACE_2X);
        cardSpace2P.setY(PLAYER_CARD_Y);
        cardSpace2P.setFitHeight(CARD_HEIGHT);
        cardSpace2P.setFitWidth(CARD_WIDTH);



        cardSpace4P.setImage(cardBack);
        cardSpace4P.setX(CARD_SPACE_4X);
        cardSpace4P.setY(PLAYER_CARD_Y);
        cardSpace4P.setFitHeight(CARD_HEIGHT);
        cardSpace4P.setFitWidth(CARD_WIDTH);




        // --------------- Dealer cards -------------------------
        // Set image views size and locations of DEALER cards
        cardSpace1D.setImage(cardBack);
        cardSpace1D.setX(CARD_SPACE_1X);
        cardSpace1D.setY(DEALER_CARD_Y);
        cardSpace1D.setFitHeight(CARD_HEIGHT);
        cardSpace1D.setFitWidth(CARD_WIDTH);

        cardSpace2D.setImage(cardBack);
        cardSpace2D.setX(CARD_SPACE_2X);
        cardSpace2D.setY(DEALER_CARD_Y);
        cardSpace2D.setFitHeight(CARD_HEIGHT);
        cardSpace2D.setFitWidth(CARD_WIDTH);



        cardSpace4D.setImage(cardBack);
        cardSpace4D.setX(CARD_SPACE_4X);
        cardSpace4D.setY(DEALER_CARD_Y);
        cardSpace4D.setFitHeight(CARD_HEIGHT);
        cardSpace4D.setFitWidth(CARD_WIDTH);







        // Player dealt 2 starting cards
        playerCards = draw(); // Draws two cards for the player
        playerScore = updateScore(playerScore, playerCards);
        calcCardImageDisplay(playerCards[0], cardSpace1P);
        calcCardImageDisplay(playerCards[1], cardSpace2P);


        // Dealer dealt 2 starting cards
        dealerCards = draw(); // Draws two cards for the dealer, displays one, the other is hidden
        dealerScore = updateScore(dealerScore, dealerCards);


        // Label that shows one of the cards drawn by the dealer
        Label startingDealerInfo = new Label("Dealer has a " + dealerCards[0] + " showing, and a hidden card.");


        startingDealerInfo.setLayoutX(10);
        startingDealerInfo.setLayoutY(10);


        // Label that shows the dealer score
        Label dealerScoreBoard = new Label("Dealer score is hidden.");


        dealerScoreBoard.setLayoutX(10);
        dealerScoreBoard.setLayoutY(30);


        // Label that shows the two starting cards drawn by the player
        Label startingPlayerInfo = new Label("Starting cards: " + playerCards[0] + " and " + playerCards[1]);

        startingPlayerInfo.setLayoutX(10);
        startingPlayerInfo.setLayoutY(310);

        // Label that shows the players score
        Label playerScoreBoard = new Label("Player score: " + playerScore);

        playerScoreBoard.setLayoutX(10);
        playerScoreBoard.setLayoutY(330);

        // Button for hit(draw another card)
        Button hitButton  = new Button("Hit");

        hitButton.setPrefWidth(BUTTON_W);
        hitButton.setLayoutX(10);
        hitButton.setLayoutY(380);

        // Button for stay(keep score and pass the turn to the dealer)
        Button stayButton = new Button("Stay");

        stayButton.setPrefWidth(BUTTON_W);
        stayButton.setLayoutX(10);
        stayButton.setLayoutY(420);

        // HIDDEN BUTTON
        // Label that shows the dealers new card drawn
        Label newCardOut = new Label(" ");

        newCardOut.setLayoutX(10);
        newCardOut.setLayoutY(350);

        // A line that divides the player and dealer area
        Rectangle rectangle = new Rectangle(0, 300, 1000, 1);

        // Label that alerts the player that is the dealers turn
        Label dealerTurnAlert = new Label(" ");

        dealerTurnAlert.setLayoutX(260);
        dealerTurnAlert.setLayoutY(280);


        Label dealerNewCardOut = new Label(" ");

        dealerNewCardOut.setLayoutX(10);
        dealerNewCardOut.setLayoutY(50);


        Label dealerDecision = new Label(" ");

        dealerDecision.setLayoutX(10);
        dealerDecision.setLayoutY(70);


        // Button that initiates the dealers decision to either hit or stay
        Button dealerNextMoveButton = new Button("Next Move");

        dealerNextMoveButton.setPrefWidth(BUTTON_W);
        dealerNextMoveButton.setLayoutX(10);
        dealerNextMoveButton.setLayoutY(100);

        Button newGameButton = new Button("New Game");

        newGameButton.setPrefWidth(BUTTON_W);
        newGameButton.setLayoutX(10);
        newGameButton.setLayoutY(140);
        newGameButton.setVisible(false);





        // ------------------------------- ACTION HANDLERS FOR BUTTONS ------------------------------------------

        // Action handler for the hitButton
        hitButton.setOnAction(e -> {
            if(playerScore < MAX_SCORE && !dealersTurn)
            {
                hitAr = hit(playerScore);
                playerScore = hitAr[0];
                newCard = hitAr[1];
                try {
                    calcCardImageDisplay(newCard, cardSpace4P);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }


                newCardOut.setText("Player drew a " + newCard);
                playerScoreBoard.setText("Player score: " + playerScore);



                // Determines if the player has won or lost
                if(playerScore == MAX_SCORE)
                {
                    System.out.print("Player WIN!!\n");
                    dealerTurnAlert.setText("Game Over: Player Wins!!!");
                    newGameButton.setVisible(true);
                    gameOver = true;
                }
                else if(playerScore > MAX_SCORE)
                {
                    System.out.print("Dealer wins. Player busted...\n");
                    dealerTurnAlert.setText("Game Over: Dealer Wins. Player busted.");
                    newGameButton.setVisible(true);
                    gameOver = true;
                }
            }
        }); // END hitButton action handler



        // Action handler for the stayButton
        stayButton.setOnAction(e -> {
            if(playerScore < MAX_SCORE && !gameOver)
            {
                dealersTurn = true;

                dealerTurnAlert.setText("Player stays, now its the dealers turn.");

                startingDealerInfo.setText("Dealer starting cards: " + dealerCards[0] + " and " + dealerCards[1]);
                dealerScoreBoard.setText("Dealer score: " + dealerScore);

                try {
                    calcCardImageDisplay(dealerCards[0], cardSpace1D);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                try {
                    calcCardImageDisplay(dealerCards[1], cardSpace2D);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }); // END stayButton action handler



        // Action handler for nextMove button
        dealerNextMoveButton.setOnAction(e -> {
            if(dealerScore < 19 && dealersTurn)
            {
                System.out.print("Dealer chooses to hit.\n");
                dealerDecision.setText("Dealer chooses to hit.");

                dealerHitAr = hit(dealerScore);
                dealerScore = dealerHitAr[0];
                dealerNewCard = dealerHitAr[1];

                try {
                    calcCardImageDisplay(dealerNewCard, cardSpace4D);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                System.out.print("\nDealer drew a " + dealerNewCard + ". \n");
                System.out.print("Dealer total is " + dealerScore + "\n\n");

                dealerNewCardOut.setText("Dealer drew a " + dealerNewCard + ".");
                dealerScoreBoard.setText("Dealer score: " + dealerScore);


            } // END if(dealerScore < 19 && dealersTurn)

            // dealer chooses to stay when his score is greater than or equal to 16
            if(dealerScore >= 19 && !gameOver)
            {
                // if dealer is busted, "Dealer stays" will not be output as the dealer has
                // already lost the game
                if(dealerScore < MAX_SCORE)
                {
                    System.out.print("Dealer stays.\n\n");
                    dealerDecision.setText("Dealer stays.");
                }

                // outputs the two scores
                System.out.print("Your score is  : " + playerScore + "\n");
                System.out.print("Dealer score is: " + dealerScore + "\n");

                playerScoreBoard.setText("Player score: " + playerScore);
                dealerScoreBoard.setText("Dealer score: " + dealerScore);

                // determines the winner of the game based on the two scores
                if(playerScore > dealerScore)
                {
                    System.out.print("\nYOU WIN!!\n");
                    dealerTurnAlert.setText("Game Over: Player Wins!!!");
                    newGameButton.setVisible(true);
                    gameOver = true;
                }
                else
                {
                    if(dealerScore <= MAX_SCORE)
                    {
                        System.out.print("\nDealer wins. \n");
                        dealerTurnAlert.setText("Game Over: Dealer Wins.");
                        newGameButton.setVisible(true);
                        gameOver = true;
                    }
                    else
                    {
                        System.out.print("\nYOU WIN!! Dealer busted.\n");
                        dealerTurnAlert.setText("Game Over: Player Wins!!! Dealer busted.");
                        newGameButton.setVisible(true);
                        gameOver = true;
                    }
                }
            }// END if(dealerScore >= 19)
        }); // END nextMoveButton action handler



        newGameButton.setOnAction(e -> {
            try {
                resetGame(newGameButton,
                          startingDealerInfo,
                          dealerScoreBoard,
                          startingPlayerInfo,
                          playerScoreBoard,
                          newCardOut,
                          dealerTurnAlert,
                          dealerNewCardOut,
                          dealerDecision) ;
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });



        pane.getStylesheets().add("styles/styles.css");
        pane.setId("window");


        // Renders all components on the window(stage) (children of the root component
        pane.getChildren().addAll(startingPlayerInfo,
                                  playerScoreBoard,
                                  hitButton,
                                  stayButton,
                                  startingDealerInfo,
                                  dealerScoreBoard,
                                  newCardOut,
                                  rectangle,
                                  dealerTurnAlert,
                                  dealerNextMoveButton,
                                  dealerNewCardOut,
                                  dealerDecision,
                                  newGameButton,
                                  cardSpace1P,
                                  cardSpace2P,
                                  cardSpace4P,
                                  cardSpace1D,
                                  cardSpace2D,
                                  cardSpace4D); // adds this to the window
        // ----------------------------------- Window (stage) is displayed ----------------------------------------
        // Makes the window unable to be resized
        stage.setResizable(false);

        // Displays the window
        stage.show();

    } // END Start




    // This method runs after the program is ended
    @Override
    public void stop()
    {
        System.out.println("Program is finished running.");
    }






    //--------------------------------------- Functions --------------------------------------------------------

    // function that handles player card draws, will generate two cards for the player
    // function will get values for card 1 and 2 and output the cards
    // function returns an array of integers with 2 spaces for the two cards
    static int[] draw()
    {
        int card1 = getRandomNum();
        int card2 = getRandomNum();

        return new int[] {card1, card2};
    } // END draw



    // function that handles the hit option: draws a card and adds to the score
    // returns an array that stores the new card in [1] and the updated score in [0]
    static int[] hit(int score)
    {
        // ar[0] is the updated score
        // ar[1] is the new card drawn
        int[] ar = new int[2];

        ar[1] = getRandomNum(); // ar[1] gets the value of a new card drawn
        score = score + ar[1];  // score is updated to include the new card
        ar[0] = score;          // the score is stored into ar[0] to be returned out of the function

        return ar;              // ar is returned
    } // END hit



    // function that adds adds the two cards together and updates the score
    // function returns an int, score
    static int updateScore(int score, int[] cards)
    {
        int addedCardScore = (cards[0] + cards[1]);
        score = score + addedCardScore;

        return score;
    } // END updateScore



    // function that generates a random number between 1 and 11
    // returns an int (random number)
    static int getRandomNum()
    {
        final int MAX = 11; // Max number that will be produced
        final int MIN = 1;  // Min number that will be produced
        Random randomNumber = new Random();

        return randomNumber.nextInt((MAX - MIN) + 1) + MIN;
    } // END getRandomNum




    void calcCardImageDisplay(int num, ImageView space) throws FileNotFoundException
    {
        Image two      = new Image(new FileInputStream("src/images/2D.png"));
        Image three    = new Image(new FileInputStream("src/images/3D.png"));
        Image four     = new Image(new FileInputStream("src/images/4D.png"));
        Image five     = new Image(new FileInputStream("src/images/5D.png"));
        Image six      = new Image(new FileInputStream("src/images/6D.png"));
        Image seven    = new Image(new FileInputStream("src/images/7D.png"));
        Image eight    = new Image(new FileInputStream("src/images/8D.png"));
        Image nine     = new Image(new FileInputStream("src/images/9D.png"));
        Image ten      = new Image(new FileInputStream("src/images/10D.png"));
        Image ace      = new Image(new FileInputStream("src/images/AD.png"));
        Image cardBack2= new Image(new FileInputStream("src/images/red_back.png"));


        switch(num)
        {
            case 0: space.setImage(cardBack2);
                break;
            case 1:
            case 11:
                space.setImage(ace);
                break;
            case 2: space.setImage(two);
                break;
            case 3: space.setImage(three);
                break;
            case 4: space.setImage(four);
                break;
            case 5: space.setImage(five);
                break;
            case 6: space.setImage(six);
                break;
            case 7: space.setImage(seven);
                break;
            case 8: space.setImage(eight);
                break;
            case 9: space.setImage(nine);
                break;
            case 10: space.setImage(ten);
                break;
        }
    }// END calcCardImageDisplay(int num, ImageView space)






    void resetGame(Button newGameButton,
                   Label dealerStartingInfo,
                   Label dealerScoreBoard,
                   Label startingPlayerInfo,
                   Label playerScoreBoard,
                   Label newCardOut,
                   Label dealerTurnAlert,
                   Label dealerNewCardOut,
                   Label dealerDecision) throws FileNotFoundException
    {
        newGameButton.setVisible(false);

        // Player cards reset
        calcCardImageDisplay(0,cardSpace1P);
        calcCardImageDisplay(0,cardSpace2P);
        calcCardImageDisplay(0,cardSpace4P);

        // Dealer cards reset
        calcCardImageDisplay(0,cardSpace1D);
        calcCardImageDisplay(0,cardSpace2D);
        calcCardImageDisplay(0,cardSpace4D);


        playerScore = 0;
        dealerScore = 0;

        playerCards[0] = 0;
        playerCards[1] = 0;

        dealerCards[0] = 0;
        dealerCards[1] = 0;



        // Player dealt 2 starting cards
        playerCards = draw(); // Draws two cards for the player
        playerScore = updateScore(playerScore, playerCards);
        calcCardImageDisplay(playerCards[0], cardSpace1P);
        calcCardImageDisplay(playerCards[1], cardSpace2P);


        // Dealer dealt 2 starting cards
        dealerCards = draw(); // Draws two cards for the dealer, displays one, the other is hidden
        dealerScore = updateScore(dealerScore, dealerCards);
        calcCardImageDisplay(0, cardSpace1D);
        calcCardImageDisplay(0, cardSpace2D);


        if(playerScore == MAX_SCORE)
        {
            dealerTurnAlert.setText("Player wins.");
            newGameButton.setVisible(true);
        }
        else if(dealerScore == MAX_SCORE)
        {
            dealerTurnAlert.setText("Dealer wins.");
            newGameButton.setVisible(true);
        }

        dealerStartingInfo.setText("Dealer has a " + dealerCards[0] + " showing, and a hidden card.");
        dealerScoreBoard.setText("Dealer score is hidden.");

        startingPlayerInfo.setText("Starting cards: " + playerCards[0] + " and " + playerCards[1]);
        playerScoreBoard.setText("Player score: " + playerScore);

        newCardOut.setText(" ");
        dealerTurnAlert.setText(" ");
        dealerNewCardOut.setText(" ");
        dealerDecision.setText(" ");


        gameOver = false;
        dealersTurn = false;
    }

}// END myApp class
