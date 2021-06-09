import java.util.concurrent.Callable;

public class Card {
    private int value;
    private Shape shape;

    public Card(int value, Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public int getValue() { return value; }
    public Shape getShape() { return shape; }
    public void setValue(int value) { this.value = value; }
    public void setShape(Shape shape) { this.shape = shape; }

    public int compare(Card other) {
        int comp = value - other.getValue();
        if (comp < 0)
            return -1;
        else if (comp == 0)
            return 0;
        else if (comp > 0)
            return 1;
        return 0;
    }

    public String toString() {
        char symbol = 0;
        if (shape == Shape.SPADES) {
            symbol = '♣';
        }
        else if (shape == Shape.DIAMONDS) {
            symbol = '♦';
        }
        else if (shape == Shape.CLUBS) {
            symbol = '♠' ;
        }
        else if (shape == Shape.HEARTS) {
            symbol = '♥';
        }
        switch (value)
        {
            case 1:
                return "Ace of" + " " + symbol;
            case 11:
                return "Jack of" + " " + symbol;
            case 12:
                return "Queen of" + " " + symbol;
            case 13:
                return "King of" + " " + symbol;
            default:
                return value + " " + "of" + " " + symbol;
        }
    }
}
