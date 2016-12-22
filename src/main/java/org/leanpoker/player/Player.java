package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        JsonObject json;
        json = request.getAsJsonObject();

        JsonElement smallBlindString = json.get("small_blind");
        int smallblind = smallBlindString.getAsInt();

        int currentBuyIn = json.get("current_buy_in").getAsInt();
        int minimumRaise = json.get("minimum_raise").getAsInt();

        JsonArray communityCards = json.get("community_cards").getAsJsonArray();
        for (int k=0; k < communityCards.size(); k++) {
            JsonObject currentCard = communityCards.get(k).getAsJsonObject();
            String rank = currentCard.get("rank").getAsString();
            String suit = currentCard.get("suit").getAsString();

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

                    System.out.println("**** current Card: " + rank + " - " + suit);

                }
            }
        }

        return currentBuyIn+minimumRaise;
    }

    public static void showdown(JsonElement game) {
    }
}
