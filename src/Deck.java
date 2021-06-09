import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class Deck {

    private static final int NB_CARDS = 13;
    private static final int SHUFFLE_OCCUR = 50;
    private ArrayList<Card> allCards;

//    public ArrayList<Card> getAllCards() {
//        return allCards;
//    }
//    public Deck (ArrayList<Card> allCards){
//        this.allCards = allCards;
//    }
    public Deck (){
        this.allCards = new ArrayList<Card>();
    }
    public Deck (boolean check){
        if (check){
            this.allCards = new ArrayList<Card>();
            for (int i = 1; i <= NB_CARDS; i++)
                allCards.add(new Card(i, Shape.CLUBS));
            for (int i = 1; i <= NB_CARDS; i++)
                allCards.add(new Card(i, Shape.DIAMONDS));
            for (int i = 1; i <= NB_CARDS; i++)
                allCards.add(new Card(i, Shape.SPADES));
            for (int i = 1; i <= NB_CARDS; i++)
                allCards.add(new Card(i, Shape.HEARTS));
        }
//        Collections.reverse(allCards);
    }

    public void addCard(Card card){
        allCards.add(0,card);
    }

    public Card removeTopCard(){
        Card lastCard = allCards.get(allCards.size() - 1);
        allCards.remove(allCards.size()-1);
        return lastCard;
    }

    public Card removeFirstCard(){
        Card firstCard = allCards.get(0);
        allCards.remove(0);
        return firstCard;
    }

    public boolean isEmpty(){
        return allCards.isEmpty();
    }

    public void shuffle(){

        for (int i = 0; i < SHUFFLE_OCCUR; i++){
            int index1 = Main.rnd.nextInt(allCards.size());
            int index2 = Main.rnd.nextInt(allCards.size());
            Collections.swap(allCards,index1, index2);
//            Collections.reverse(allCards);
        }
    }

}
