import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private static final int STAKE = 100;

    private Scanner input;
    private Deck deck;
    private Player player;
    private int playerBet;
    private Card card1;
    private Card card2;
    private Card card3;
    private int handCount;


    public Game() {
        //Initializes scanner
        input = new Scanner(System.in);

        //Creates and shuffles the deck
        deck = new Deck();
        deck.shuffle();
        
        //Gets the player name and initializes player
        updateDisplay("Welcome to the In-Between game simulator! What is your name?");
        String name = input.nextLine();
        player = new Player(name, STAKE);

        //Insert a cool game explination here, when you actually do that
        updateDisplay("The game works like this . . . ");

        //Game loop
        while(player.getChips() > 0) {
            //Increments hand count
            handCount++;
            
            //Gets the players bet
            int wantBet = 0;
            while(true) {
                try {
                    updateDisplay("How much would you like to bet? You have to bet at least 1 chip.");
                    wantBet = input.nextInt();
                    if(wantBet >= 1 && wantBet <= player.getChips()) {
                        break;
                    }
                    else {
                        updateDisplay("You cannot bet that amount of chips. You can only bet an amount of chips between 1 and your current chip count.");
                        doSleep(3000);
                    }
                }
                catch(InputMismatchException e) {
                    input.next();
                    updateDisplay("That is not a real number. Please input a real number between 1 and your current chip count.");
                    doSleep(2000);
                }
            }
            playerBet = wantBet; 
            player.bet(wantBet);

            //Deals the cards
            card1 = deck.dealCard();
            updateDisplay("");
            doSleep(1000);

            card2 = deck.dealCard();
            
            //Case: both cards are the same
            if(card1.getRank() == card2.getRank()) {
                updateDisplay("The first two dealt cards have the same value. You win!");
                player.addChips(playerBet * 2);
                doSleep(2000);
            }
            //Case: cards are apart by 1
            else if(Math.abs(card1.getRank() - card2.getRank()) == 1) {
                updateDisplay("The first two dealt cards are apart by 1. You lose.");
                doSleep(2000);
            }
            else {
                updateDisplay("");
                doSleep(1000);
                
                

                //let player double bet
                String response;
                if(input.hasNextLine()) {
                    input.nextLine();
                }

                while(true) {
                    updateDisplay("Would you like to double your bet? Type 'yes' or 'no'");
                    response = input.nextLine();
                    response = response.toLowerCase();
                    if((response.equals("yes") || response.equals("no"))) {
                        break;
                    }
                    else {
                        updateDisplay("That is not a valid respone, please type either 'yes' or 'no'");
                        doSleep(3000);
                    }
                }
                if(response.equals("yes")) {
                    int temp = playerBet;
                    playerBet = Math.min(playerBet*2, player.getChips());
                    player.bet(temp);
                }

                card3 = deck.dealCard();
                updateDisplay("");
                doSleep(2000);
                
                //if inbetween
                if((card1.getRank() < card3.getRank() && card3.getRank() < card2.getRank()) || (card2.getRank() < card3.getRank() && card3.getRank() < card1.getRank())) {
                    updateDisplay("The third card is inbetween the other two cards. You win!");
                    doSleep(3000);
                    player.addChips(playerBet * 2);
                }
                else {
                    updateDisplay("The third card is not inbetween the other two cards. You lose.");
                    doSleep(3000);
                }
            }
            card1 = null;
            card2 = null;
            card3 = null;

            if(deck.getNumCards() > 10) {
                deck = new Deck();
                deck.shuffle();
            }
        }

        updateDisplay("You have run out of tokens. You have lost the game. You lasted for " + handCount + " hands.");
        doSleep(3000);

        
        input.close();
    }

    public void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void doSleep(int duration) {
        try {
            Thread.sleep(duration);
        }
        catch(InterruptedException e) {
            System.out.println("The Thread.sleep() failed, somehow");
        }
    }

    public void updateDisplay(String message) {
        clearConsole();
        
        System.out.println("In-Between Game Simulator");
        System.out.println("------------------------------------------------------------------------");
        
        if(player != null) {
            System.out.println("Player Name: " + player.getName());
            System.out.println("Player Chips: " + player.getChips());
            System.out.println("Current Player Bet: " + playerBet);
        }
        else {
            System.out.println("Player Name: ");
        }

        System.out.println("Hand Count: " + handCount);
        
        System.out.println("------------------------------------------------------------------------");

        if(card1 != null) {
            System.out.println("First Card: " + card1.toString());
        }
        else {
            System.out.println("First Card: ");
        }

        if(card2 != null) {
            System.out.println("Second Card: " + card2.toString());
        }
        else {
            System.out.println("Second Card: ");
        }

        if(card3 != null) {
            System.out.println("Third Card: " + card3.toString());
        }
        else {
            System.out.println("Third Card: ");
        }

        System.out.println("------------------------------------------------------------------------");

        System.out.println(message);

        System.out.println("------------------------------------------------------------------------");
    }




}