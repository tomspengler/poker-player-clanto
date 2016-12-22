package org.leanpoker.player;

/**
 * Created by teamrocket on 22.12.16.
 */
public class Card {


    String rank;
    String suit;


    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }


}
