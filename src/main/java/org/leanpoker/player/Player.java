package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        Card[] ourCards = new Card[2];
        Card[] communityCards = new Card[5];

        JsonObject json;
        json = request.getAsJsonObject();

        JsonElement smallBlindString = json.get("small_blind");
        int smallblind = smallBlindString.getAsInt();

        int currentBuyIn = json.get("current_buy_in").getAsInt();
        int minimumRaise = json.get("minimum_raise").getAsInt();

        JsonArray communityCardsArray = json.get("community_cards").getAsJsonArray();
        for (int k=0; k < communityCardsArray.size(); k++) {
            JsonObject currentCard = communityCardsArray.get(k).getAsJsonObject();
            String rank = currentCard.get("rank").getAsString();
            String suit = currentCard.get("suit").getAsString();

            communityCards[k] = new Card(rank, suit);
            System.out.println("**** current community card: " + rank + " - " + suit);
        }


        JsonArray players = json.get("players").getAsJsonArray();

        for (int i=0; i < players.size(); i++) {
            JsonObject player = players.get(i).getAsJsonObject();
            int id = player.get("id").getAsInt();
            String name = player.get("name").getAsString();

            System.out.println("**** " + id + " - " + name);

            if (name.equals("Clanto")) {
                JsonArray hole_cards = player.get("hole_cards").getAsJsonArray();

                for (int j=0; j < hole_cards.size(); j++) {
                    JsonObject currentCard = hole_cards.get(j).getAsJsonObject();
                    String rank = currentCard.get("rank").getAsString();
                    String suit = currentCard.get("suit").getAsString();

                    ourCards[j] = new Card(rank, suit);
                    System.out.println("**** current Card: " + rank + " - " + suit);

                }
            }
        }


        return  analyzeCards(ourCards, communityCards, currentBuyIn, minimumRaise); //currentBuyIn+minimumRaise;
    }

    private static int analyzeCards(Card[] cards, Card[] communityCards, int currentBuyIn, int minimumRaise) {
        int bet = 0;
        if (cards[0].suit.equals(cards[1].suit)) {
            bet = currentBuyIn + minimumRaise;
        }

        if (cards[0].rank.equals(cards[1].rank)) {
            bet = currentBuyIn + minimumRaise;
        }

        if (cards[0].equals("A") || cards[1].equals("A")) {
            bet = currentBuyIn + minimumRaise;
        }

        if (paarMitFlop(cards, communityCards)) {
            bet = currentBuyIn + minimumRaise;
        }

        System.out.println("***** BET: " + bet);
        return bet;
    }

    private static boolean paarMitFlop(Card[] cards, Card[] communityCards) {
        boolean isPaarMitFlop = false;

        for (int i=0; i < communityCards.length; i++) {
            if (communityCards[i].rank.equals(cards[0].rank) || communityCards[i].rank.equals(cards[1].rank)) {
                System.out.println("**** PaarMitFlop: " + communityCards[i].rank);
                isPaarMitFlop = true;
                break;
            }
        }


        return isPaarMitFlop;
    }

    public static void showdown(JsonElement game) {
    }
}
