package assingments;

import java.util.Scanner;
import java.util.logging.Handler;
public class GoFish {

    private static int PLAYER1_SCORE = 0;
    private static int PLAYER2_SCORE = 0;
    private static int PLAYER3_SCORE = 0;
    private static int PLAYER4_SCORE = 0;
    private static final String HEARTS = "H";
    private static final String CLUBS = "C";
    private static final String SPADES = "S";
    private static final String DIAMONDS = "D";
    private static final int POSSIBLE_VALUES = 13;
    private static final String JACK = "J";
    private static final String ACE = "A";
    private static final String QUEEN = "Q";
    private static String PLAYER1AFTERHAND = "";
    private static String PLAYER2AFTERHAND = "";
    private static String PLAYER3AFTERHAND = "";
    private static String PLAYER4AFTERHAND = "";
    private static final String KING = "K";
    private static final int NUM_SUITS = 4;
    private static boolean PLAYER1FISH = false;
    static Scanner in = new Scanner(System.in);
    private static final String CARD_VALUES = "2345678910JQKAT";

    // main method
    public static void main(String args[]) throws InterruptedException {
        String hand1 = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " ";
        String hand2 = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        String hand3 = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        String hand4 = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        displayHand(hand1, false, "Current hand: ");
        displayHand(hand2, true, "Player 2s Hand: ");
        displayHand(hand3, true, "Player 3s Hand: ");
        displayHand(hand4, true, "Player 4s Hand: ");
        int preScore = 0;
        PLAYER1AFTERHAND = hand1.trim().toLowerCase().replace("t","10");
        PLAYER2AFTERHAND = hand2.trim().toLowerCase().replace("t","10");
        PLAYER3AFTERHAND = hand3.trim().toLowerCase().replace("t","10");
        PLAYER4AFTERHAND = hand4.trim().toLowerCase().replace("t","10");
        hand1 = removeDupesAndScore(hand1,1);
        if(PLAYER1_SCORE > preScore) {
            System.out.println("we found a pair, Hand updated.");
            displayHand(hand1, false, "Your Hand: ");
        }
        hand1 = hand1.toLowerCase().replace("t","10").toUpperCase();
        while(PLAYER1_SCORE < 10 && PLAYER2_SCORE < 10 && PLAYER3_SCORE < 10 && PLAYER4_SCORE < 10) {
            // runs constantly
            hand1 = PLAYER1AFTERHAND;
            hand2 = PLAYER2AFTERHAND;
            hand3 = PLAYER3AFTERHAND;
            hand4 = PLAYER4AFTERHAND;
            // set stuff later cause other methods update state
            askCards(hand1, hand2, hand3, hand4);
            hand1 = PLAYER1AFTERHAND;
            hand2 = PLAYER2AFTERHAND;
            hand3 = PLAYER3AFTERHAND;
            hand4 = PLAYER4AFTERHAND;
            if(PLAYER1FISH) {
                hand1 = hand1 + getCard() + " ";
                hand1 = removeDupesAndScore(hand1,1);
            }

            displayScores();
            System.out.println("It is player 2's turn.");
            Thread.sleep(1500);
            aiturn(hand1,hand2,hand3,hand4,2);
            hand1 = PLAYER1AFTERHAND;
            hand2 = PLAYER2AFTERHAND;
            hand3 = PLAYER3AFTERHAND;
            hand4 = PLAYER4AFTERHAND;
            Thread.sleep(1500);
            aiturn(hand1,hand2,hand3,hand4,3);
            hand1 = PLAYER1AFTERHAND;
            hand2 = PLAYER2AFTERHAND;
            hand3 = PLAYER3AFTERHAND;
            hand4 = PLAYER4AFTERHAND;
            Thread.sleep(1500);
            aiturn(hand1,hand2,hand3,hand4,4);
            hand1 = PLAYER1AFTERHAND;
            hand2 = PLAYER2AFTERHAND;
            hand3 = PLAYER3AFTERHAND;
            hand4 = PLAYER4AFTERHAND;
            Thread.sleep(1500);
            System.out.println("scores:");
            hand1 = removeDupesAndScore(hand1,1);
            hand1 = fillZero(hand1);
            hand2 = removeDupesAndScore(hand2,2);
            hand2 = fillZero(hand2);
            hand3 = removeDupesAndScore(hand3,3);
            hand3 = fillZero(hand3);
            hand4 = removeDupesAndScore(hand4,4);
            hand4 = fillZero(hand4);
            displayScores();
            Thread.sleep(1500);
            displayHand(hand1, false, "Currend Hand: ");
            displayHand(hand2, true, "player 2's Hand: ");

            displayHand(hand3, true, "Player 3's Hand: ");

            displayHand(hand4, true, "Player 4's Hand: ");
        }
        System.out.println("game over! final scores!");
        displayScores();
        System.out.println("do you want to play again? Y/N");
        if(in.nextLine().toLowerCase().contains("Y")) {
            playAgain();
        }


    }

    private static String fillZero(String hand) {
        if(hand.replace(" ","").length() < 2) {
            hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        }
        return hand;
    }

    private static void displayScores() {
        System.out.println("Player 1 score: " + PLAYER1_SCORE);
        System.out.println("Player 2 score: " + PLAYER2_SCORE);
        System.out.println("Player 3 score: " + PLAYER3_SCORE);
        System.out.println("Player 4 score: " + PLAYER4_SCORE);
    }

    // randomly picks from allowed cards
    private static String randomFromAllowed(String allowedRandoms) {
        allowedRandoms = allowedRandoms.replace("10","t");
        int randomPick = (int) (Math.random() * CARD_VALUES.length());
        if(!allowedRandoms.contains("" + CARD_VALUES.charAt(randomPick))) return randomFromAllowed(allowedRandoms.replace("t","10"));
        return ("" + CARD_VALUES.charAt(randomPick)).replace("t","10").toUpperCase();
    }

    // gets random player, used for ai turns
    private static int randomPlayer(int blacklistedPlayer) {
        int randomPick = (int) (Math.random() * 4) + 1;
        if(randomPick == blacklistedPlayer) return randomPlayer(blacklistedPlayer);
        return randomPick;
    }

    // plays again
    public static void playAgain() throws InterruptedException {
        main(null);
    }












    // runs the ai turn
    private static void aiturn(String hand1, String hand2, String hand3, String hand4, int player) {
        hand1 = hand1.replace("10","t");

        if(player == 2) {
            int playerToChooseFrom = randomPlayer(2);
            String hand2Suitless = "";
            for(int i = 0; i < hand2.length(); i++) {
                if(CARD_VALUES.contains("" + hand2.charAt(i))) {
                    // real card value
                    hand2Suitless += hand2.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(hand2Suitless);
            System.out.println("p2 asked " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(hand1.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER1AFTERHAND = PLAYER1AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1))," ").toUpperCase();
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND+ " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND + " " + getCard();
                }
            } else if(playerToChooseFrom == 3) {
                if(hand3.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER3AFTERHAND.toLowerCase().charAt(PLAYER3AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER3AFTERHAND.toLowerCase().charAt(PLAYER3AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND + " " + getCard();
                }
            } else if(playerToChooseFrom == 4) {
                if(hand4.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER4AFTERHAND.toLowerCase().charAt(PLAYER4AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER4AFTERHAND.toLowerCase().charAt(PLAYER4AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER2AFTERHAND = PLAYER4AFTERHAND + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " + getCard() ;
                }
            }
        } else if(player == 4) {
            int playerToChooseFrom = randomPlayer(4);
            String hand4Suitless = "";
            for(int i = 0; i < hand4.length(); i++) {
                if(CARD_VALUES.contains("" + hand4.charAt(i))) {
                    // real card value
                    hand4Suitless += hand4.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(hand4Suitless);
            System.out.println("Player 4 asks player " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(hand1.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER1AFTERHAND = PLAYER1AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " + getCard();
                }
            } else if(playerToChooseFrom == 2) {
                if(hand2.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 2 has a pair!");
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER2AFTERHAND.toLowerCase().charAt(PLAYER2AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER2AFTERHAND.toLowerCase().charAt(PLAYER2AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " + getCard() + " ";
                }
            } else if(playerToChooseFrom == 3) {
                if(hand4.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 3 has a pair!");
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER3AFTERHAND.toLowerCase().charAt(PLAYER3AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER3AFTERHAND.toLowerCase().charAt(PLAYER3AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " +  pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND + " " + getCard();
                }
            }

        } else if(player == 3) {
            int playerToChooseFrom = randomPlayer(3);
            String hand3Suitless = "";
            for(int i = 0; i < hand3.length(); i++) {
                if(CARD_VALUES.contains("" + hand3.charAt(i))) {
                    // real card value
                    hand3Suitless += hand3.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(hand3Suitless);
            System.out.println("Player 3 asks player " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(hand1.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER1AFTERHAND = PLAYER1AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1AFTERHAND.toLowerCase().charAt(PLAYER1AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " + getCard();
                }
            } else if(playerToChooseFrom == 2) {
                if(hand2.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 2 has a pair!");
                    PLAYER2AFTERHAND = PLAYER2AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER2AFTERHAND.toLowerCase().charAt(PLAYER2AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER2AFTERHAND.toLowerCase().charAt(PLAYER2AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " + getCard();
                }
            } else if(playerToChooseFrom == 4) {
                if(hand4.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 4 has a pair!");
                    PLAYER4AFTERHAND = PLAYER4AFTERHAND.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER4AFTERHAND.toLowerCase().charAt(PLAYER4AFTERHAND.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER4AFTERHAND.toLowerCase().charAt(PLAYER4AFTERHAND.indexOf(randomChoice) + 1);
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3AFTERHAND = PLAYER3AFTERHAND + " " + getCard();
                }
            }
        }
        PLAYER1AFTERHAND = PLAYER1AFTERHAND.toUpperCase().replace("T","10");
        PLAYER2AFTERHAND = PLAYER2AFTERHAND.toUpperCase().replace("T","10");
        PLAYER3AFTERHAND = PLAYER3AFTERHAND.toUpperCase().replace("T","10");
        PLAYER4AFTERHAND = PLAYER4AFTERHAND.toUpperCase().replace("T","10");

    }

    private static void askCards(String hand1, String hand2, String hand3, String hand4){
        PLAYER1FISH = false;
        System.out.println("who do you want to steal cards from: enter p2, p3, or p4 ");
        String playerGuess = in.nextLine().toLowerCase();

      //  guess for player 2
  if (playerGuess.equals("player 3") || playerGuess.equals("p3")){
            System.out.println("mr " + playerGuess + " what cards do you have to guess? ");
            String cardPairsP3 = in.nextLine().toUpperCase();
            int selectedCardP3 = hand3.indexOf(cardPairsP3);
            int selectedCardSelfP3 = hand1.indexOf(cardPairsP3);
            if (CARD_VALUES.indexOf(cardPairsP3) == -1) {
                System.out.println("please input a real card");
                askCards(hand2, hand2, hand3, hand4);
                return;
            }

            else if (selectedCardP3 == -1 && selectedCardSelfP3 == -1){
                System.out.println("you dont have that card. ");
                askCards(hand1, hand2, hand3, hand4);
                return;
            }
            else if (selectedCardP3 == -1) {
                System.out.println("Go Fish!");
                PLAYER1FISH = true;
                return;
            }
            if (CARD_VALUES.indexOf(cardPairsP3)!= -1){
                String p1NewHand = hand1 + hand3.substring(selectedCardP3, (selectedCardP3 + 2)) + " ";
                String beforeNewCard = hand3.substring(0, selectedCardP3);
                System.out.println("selectedcardp3: " + selectedCardP3 + " sout " + hand3 );
                String afterNewCard = hand3.substring(selectedCardP3 + 3);
                String p3NewHand = beforeNewCard + afterNewCard;
                hand3 = p3NewHand;
                hand1 = p1NewHand;
                System.out.println(playerGuess + " Has a pair!");
                hand3 = hand3 + " " + getCard();
                displayHand(hand1, false, "Your New Hand: ");
                System.out.println("Your Score: " + PLAYER1_SCORE);
                PLAYER3AFTERHAND = p3NewHand;
                PLAYER1AFTERHAND = p1NewHand;
                //displayHand(hand2, false, "p2 new hand ");
            }
        }         if (playerGuess.equals("player 2") || playerGuess.equals("p2")) {
            System.out.println("What cards do you have, " + playerGuess + "? (i.e 2, J, etc) : ");
            String cardPairs = in.nextLine().toUpperCase();
            int selectedCard = hand2.indexOf(cardPairs);
            int selectedCardSelf = hand1.indexOf(cardPairs);


            if (CARD_VALUES.indexOf(cardPairs) == -1) {
                System.out.println("that card doesnt exist ( 2,3,4,J,K,Q,A)");
                askCards(hand2, hand2, hand3, hand4);

                return;

            } else if (selectedCard == -1 && selectedCardSelf == -1) {
                System.out.println("you dont have that card. ");
                askCards(hand1, hand2, hand3, hand4);
                return;
            } else if (selectedCard == -1) {
                System.out.println("go fish!");
                PLAYER1FISH = true;
                return;
                // start other players turn
            }

            if (CARD_VALUES.indexOf(cardPairs) != -1) {
                String p1NewHand = hand1 + hand2.substring(selectedCard, (selectedCard + 2)) + " ";
                String beforeNewCard = hand2.substring(0, selectedCard);

                String afterNewCard = hand2.substring(selectedCard + 3);

                String p2NewHand = beforeNewCard + afterNewCard;

                hand2 = p2NewHand;

                hand1 = p1NewHand;

                System.out.println(playerGuess + " Has a pair!");
                hand2 = hand2 + " " + getCard();
//                PLAYER1_SCORE++;
                System.out.println("Your Score: " + PLAYER1_SCORE);
                displayHand(hand1, false, "Your New Hand: ");
                PLAYER2AFTERHAND = hand2;
                PLAYER1AFTERHAND = hand1;
                //displayHand(hand2, false, "p2 new hand ");
            }
            // player 3 guessing
        }
        //player 4 guessing
        else if (playerGuess.equals("player 4") || playerGuess.equals("p4")){
            System.out.println("What cards do you have, " + playerGuess + "? (i.e 2, J, etc) : ");
            String cardPairsP4 = in.nextLine().toUpperCase();
            int selectedCardP4= hand3.indexOf(cardPairsP4);
            int selectedCardSelfP4 = hand1.indexOf(cardPairsP4);


            if (CARD_VALUES.indexOf(cardPairsP4) == -1) {
                System.out.println("that card doesnt exist ( 2,3,4,J,K,Q,A");
                askCards(hand2, hand2, hand3, hand4);

                return;

            }

            else if (selectedCardP4 == -1 && selectedCardSelfP4 == -1){
                System.out.println("you dont have that card. ");
                askCards(hand1, hand2, hand3, hand4);
                return;
            }

            else if (selectedCardP4 == -1) {
                System.out.println("go fish!");
                PLAYER1FISH = true;
                // start other players turn
                return;
            }

            if (CARD_VALUES.indexOf(cardPairsP4) != -1){
                String p1NewHand = hand1 + hand3.substring(selectedCardP4, (selectedCardP4 + 2)) + " ";
                String beforeNewCard = hand3.substring(0, selectedCardP4);
                if(selectedCardP4 + 3 > hand3.length()) askCards(hand1,hand2,hand3,hand4);
                String afterNewCard = hand3.substring(selectedCardP4 + 3);

                String p4NewHand = beforeNewCard + afterNewCard;

                hand4 = p4NewHand;

                hand1 = p1NewHand;

                System.out.println(playerGuess + " Has a pair!");
                hand4 = hand4 + " " + getCard();
                displayHand(hand1, false, "Your New Hand: ");
                PLAYER4AFTERHAND = p4NewHand;
                PLAYER1AFTERHAND = p1NewHand;
                System.out.println("Your Score: " + PLAYER1_SCORE);
                //displayHand(hand2, false, "p2 new hand ");
            }






        }

        else {
            System.out.println("Please select a valid player, You Are Player 1, Avaliable Players: 2, 3, 4");
            askCards(hand1, hand2, hand3, hand4);
        }
    }


    private static String removeCardType(String hand, char cardSuitless) {

        hand = hand.toLowerCase();
        // max of 2 pairs
        String pair1Type = "PLACEHOLDER";
        String pair2Type = "PLACEHOLDER";
        int removedCardsOfType = 0;
        int maxRemovedCardsOfType = 2;
        for(int i = 0; i < hand.length() - 1; i++) {
            if(!(i + 1 > hand.length())) {
                char currentChar = hand.charAt(i);
                char nextChar = hand.charAt(i + 1);
                if(pair1Type.length() > 3) {
                    if (currentChar == cardSuitless && removedCardsOfType < maxRemovedCardsOfType) {
                        pair1Type = "" + currentChar + nextChar + " ";
                        // has to be a match
                        removedCardsOfType++;
                    }
                } else {
                    if(currentChar == cardSuitless && removedCardsOfType < maxRemovedCardsOfType) {
                        pair2Type = "" + currentChar + nextChar + " ";
                        // has to be a match
                        removedCardsOfType++;
                    }
                }
            }
        }
        hand = hand.replace(pair1Type,"");
        hand = hand.replace(pair2Type,"");
        return hand.toUpperCase();
    }


    private static int increaseScore(int player) {
        int score = 0;
        if (player == 1) {
            score = PLAYER1_SCORE + 1;
            PLAYER1_SCORE++;
        }
        if (player == 2) {
            score = PLAYER2_SCORE + 1;
            PLAYER2_SCORE++;
        } else if (player == 3) {
            score = PLAYER3_SCORE + 1;
            PLAYER3_SCORE++;
        } else if (player == 4) {
            score = PLAYER4_SCORE + 1;
            PLAYER4_SCORE++;
        }
        return score;
    }

    //finds pairs and keeps score
    // also removes them from the deck and draws a new one
    private static String removeDupesAndScore(String cards, int player) {
        cards = cards.toLowerCase();
        cards = cards.replace("10","t");
        int pairs = 0;
        int score = 0;
        String alreadyUsedCards = "";
        String removeNext = "";
        boolean alreadyDoneFirstPair = false;
        boolean alreadyDoneSecondPair = false;
        for (int i = 0; i < cards.length(); i++) {
            for(int j = 0; j < cards.length(); j++) {
                if(i == j) continue;
                char currentChar = cards.toLowerCase().charAt(i);
                char comparativeChar = cards.toLowerCase().charAt(j);
                if(CARD_VALUES.toLowerCase().indexOf(currentChar) != -1 && CARD_VALUES.toLowerCase().indexOf(comparativeChar) != -1) {
                    if (pairs == 1 && !alreadyDoneFirstPair) {
                        alreadyDoneFirstPair = true;
                        score = increaseScore(player);
                    }
                    if (pairs == 2 && !alreadyDoneSecondPair) {
                        alreadyDoneSecondPair = true;
                        score = increaseScore(player);
                    }
                    if(alreadyUsedCards.contains("" + currentChar) || alreadyUsedCards.contains("" + comparativeChar)) continue;
                    if(currentChar == comparativeChar) {
                        pairs++;
                        alreadyUsedCards = alreadyUsedCards + currentChar + comparativeChar;
                        removeNext += currentChar;
                    }




                }
            }

        }
        cards = cards.toUpperCase();
        if(cards.charAt(cards.length() - 1) != ' ') cards = cards + " ";
        if(removeNext.length() > 1) {
            cards = removeCardType(cards,removeNext.charAt(1));
            cards = removeCardType(cards,removeNext.charAt(0));
            return cards.replace("T","10");
        } else if(removeNext.length() == 1) {
            cards = removeCardType(cards, removeNext.charAt(0));
            return cards.replace("T", "10");
        }
        return cards.replace("T","10");
    }


    private static void displayHand(String cards, boolean isHidden, String label) {
        cards = cards.toLowerCase().replace("t","10");
        cards = cards.toUpperCase();
        String result = "";
        if (isHidden)
            result += label + "XX " + "XX " + "XX " + "XX " + "XX ";
        else
            result += label + cards;

        System.out.println(result);
    }



    public static String getCard() {
        return getValue() + getSuit();
    }

    private static String getSuit() {
        int iSuit = (int) (Math.random() * NUM_SUITS) + 1;

        if (iSuit == 1)
            return HEARTS;
        else if (iSuit == 2)
            return SPADES;
        else if (iSuit == 3)
            return CLUBS;
        else
            return DIAMONDS;

    }


    private static String getValue() {
        int iValue = (int) (Math.random() * POSSIBLE_VALUES) + 1;

        if (iValue == 1)
            return ACE;
        else if (iValue == 11)
            return JACK;
        else if (iValue == 12)
            return QUEEN;
        else if (iValue == 13)
            return KING;
        else
            return "" + iValue;
    }


}


