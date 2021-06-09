import java.util.ArrayList;

public class Player {
    private String name;
    private Deck baseDeck;
    private Deck winDeck;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Deck getBaseDeck() { return baseDeck; }
    public void setBaseDeck(Deck baseDeck) { this.baseDeck = baseDeck; }
    public Deck getWinDeck() { return winDeck; }
    public void setWinDeck(Deck winDeck) { this.winDeck = winDeck; }

    public Player (String name, Deck baseDeck, Deck winDeck) {
        this.name = name;
        this.baseDeck = baseDeck;
        this.winDeck = new Deck();
    }

    public void addCardBase(Card card) {
        baseDeck.addCard(card);
    }

    public void addCardWin(Deck winDeck, Card card) {
        winDeck.addCard(card);
    }

    public Card drawCard(Deck baseDeck) { return baseDeck.removeTopCard();
    }

    public boolean outOfCards(Deck baseDeck) {
        return baseDeck.isEmpty();
    }

    public String toString () {
        return name;
    }
}
