public class Card {
    private int rank;
    private String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public boolean equals(Card other) {
        return (rank == other.getRank()) && (suit.equals(other.getSuit()));
    }

    public String toString() {
        String returningRank = "";
        if(rank == 11) {
            returningRank = "Jack";
        }
        else if(rank == 12) {
            returningRank = "Queen";
        }
        else if(rank == 13) {
            returningRank = "King";
        }
        else if(rank == 14) {
            returningRank = "Ace";
        }
        else {
            returningRank += rank;
        }

        return returningRank + " of " + suit;
    }
}