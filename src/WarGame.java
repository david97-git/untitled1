import java.util.ArrayList;

public class WarGame {
    private String player1;
    private String player2;
    private final Deck baseDeck1;
    private final Deck baseDeck2;
    private final Deck winDeck1;
    private final Deck winDeck2;
    //private Deck bigDeck;

    public WarGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.baseDeck1 = new Deck();
        this.baseDeck2 = new Deck();
        this.winDeck1 = new Deck();
        this.winDeck2 = new Deck();
        //this.bigDeck = new Deck();
    }

    public String getPlayer1() { return player1; }
    public String getPlayer2() { return player2; }
    public void setPlayer1(String player1) { this.player1 = player1; }
    public void setPlayer2(String player2) { this.player2 = player2; }

    public void initializeGame () {
        System.out.println("Initializing the game...");
        Deck bigDeck = new Deck(true);
        bigDeck.shuffle();
        if (player1.charAt(0) > player2.charAt(0)) {
            while (!bigDeck.isEmpty()) {
                baseDeck2.addCard(bigDeck.removeTopCard());
                baseDeck1.addCard((bigDeck.removeTopCard()));
            }
        }
        else {
            while (!bigDeck.isEmpty()){
                baseDeck1.addCard(bigDeck.removeTopCard());
                baseDeck2.addCard((bigDeck.removeTopCard()));
            }
        }
    }

    public boolean isFirstToPlay (Player firstPlayer, Player secondPlayer){
        String name1 = firstPlayer.toString();
        String name2 = secondPlayer.toString();
        return name1.charAt(0) < name2.charAt(0);
    }

    public void transferDeck (Deck formerDeck, Deck newDeck){
        while (!formerDeck.isEmpty()){
            newDeck.addCard(formerDeck.removeTopCard());}
    }

    public void checkEmpty (Deck baseDeck, Deck winDeck) {
        if (baseDeck.isEmpty() && !winDeck.isEmpty()) {
            winDeck.shuffle();
            transferDeck(winDeck, baseDeck);
        }
    }

    public String start(){
        initializeGame();
        Player firstPlayer = new Player(player1, baseDeck1, winDeck1);
        Player secondPlayer = new Player(player2, baseDeck2, winDeck2);
        Player tmp = new Player(player2,baseDeck2, winDeck2);
        if (!isFirstToPlay(firstPlayer, secondPlayer)){
            secondPlayer = firstPlayer;
            firstPlayer = tmp;
        }

        int counter = 1;
        while(true){
            System.out.println("------------------------- " + "Round number " + counter + " -------------------------");
            counter++;
            Deck battleDeck = new Deck();
            checkEmpty(baseDeck1, winDeck1);
            if (baseDeck1.isEmpty())
                return secondPlayer.getName();
            Card card1 = firstPlayer.drawCard(baseDeck1);
            System.out.println(firstPlayer.getName() + " drew " + card1.toString());
            checkEmpty(baseDeck2, winDeck2);
            if (baseDeck2.isEmpty())
                return firstPlayer.getName();
            Card card2 = secondPlayer.drawCard(baseDeck2);
            System.out.println(secondPlayer.getName() + " drew " + card2.toString());

            if(card1.compare(card2) == 1){
                firstPlayer.addCardWin(winDeck1, card1);
                firstPlayer.addCardWin(winDeck1, card2);
                System.out.println(firstPlayer.getName() + " won");
                checkEmpty(baseDeck2, winDeck2);
                if (baseDeck2.isEmpty())
                    return firstPlayer.getName();
            }

            else if (card1.compare(card2) == -1){
                secondPlayer.addCardWin(winDeck2, card1);
                secondPlayer.addCardWin(winDeck2, card2);
                System.out.println(secondPlayer.getName() + " won");
                checkEmpty(baseDeck1, winDeck1);
                if (baseDeck1.isEmpty())
                    return secondPlayer.getName();
            }

            int warCount = 0;

            while (card1.compare(card2) == 0){
                if (warCount == 0){
                    System.out.println("Starting a war...");
                    warCount++;}
                for (int i = 0; i < 2; i++){
                    checkEmpty(baseDeck1, winDeck1);
                    if (baseDeck1.isEmpty())
                        return secondPlayer.getName();
                    battleDeck.addCard(firstPlayer.drawCard(baseDeck1));
                    System.out.println(firstPlayer.getName() + " drew a war card");
                    checkEmpty(baseDeck2, winDeck2);
                    if (baseDeck2.isEmpty())
                        return firstPlayer.getName();
                    battleDeck.addCard(secondPlayer.drawCard(baseDeck2));
                    System.out.println(secondPlayer.getName() + " drew a war card");
                }
                checkEmpty(baseDeck1, winDeck1);
                if (baseDeck1.isEmpty())
                    return secondPlayer.getName();
                card1 = firstPlayer.drawCard(baseDeck1);
                System.out.println(firstPlayer.getName() + " drew " + card1.toString());
                checkEmpty(baseDeck2, winDeck2);
                if (baseDeck2.isEmpty())
                    return firstPlayer.getName();
                card2 = secondPlayer.drawCard(baseDeck2);
                System.out.println(secondPlayer.getName() + " drew " + card2.toString());
                battleDeck.addCard(card1);
                battleDeck.addCard(card2);

                if (card1.compare(card2) == 1){
                    transferDeck(battleDeck, winDeck1);
                    System.out.println(firstPlayer.getName() + " won the war");
                    checkEmpty(baseDeck2, winDeck2);
                    if (baseDeck2.isEmpty())
                        return firstPlayer.getName();
                    break;
                }
                else if (card1.compare(card2) == -1){
                    transferDeck(battleDeck, winDeck2);
                    System.out.println(secondPlayer.getName() + " won the war");
                    checkEmpty(baseDeck1, winDeck1);
                    if (baseDeck1.isEmpty())
                        return secondPlayer.getName();
                    break;
                }
            }
        }
//    return "";
    }
}
