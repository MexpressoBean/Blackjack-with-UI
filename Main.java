/**********************************************************************************
 * Name        : Kevin Ramirez
 * Date        : 12/15/19
 * Program Name: Blackjack - with GUI (JavaFX)
 * File        : Main.java
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
 ***********************************************************************************/

import javafx.application.Application;

public class Main
{
    public static void main(String[] args)
    {
        // launches the application
        Application.launch(MyApp.class, args);


    } // END main function
} // END Main class
