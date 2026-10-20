public class Deck {
    ArrayLog<Card> deck;

    public static final String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};

    public Deck() {
        deck = new ArrayLog<>("Deck");

        for(String s : suits) {
            for(int i = 2; i <= 14; i++) {
                deck.add(new Card(i, s));
            }
        }
    }

    public ArrayLog<Card> getDeck() {
        return deck;
    }

    public int getNumCards() {
        return deck.size();
    }

    public void shuffle() {
        ArrayLog<Card> shuffledDeck = new ArrayLog<>("Shuffled Deck");

        while (deck.size() > 0) {
            int randomIndex = (int)(Math.random() * deck.size());

            Card tempCard = deck.remove(randomIndex);
            shuffledDeck.add(tempCard);
        }

        deck = shuffledDeck;
    }

    public Card dealCard() {
        return deck.remove(0);
    }


}