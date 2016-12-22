package org.leanpoker.player;

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

        return currentBuyIn+minimumRaise;
    }

    public static void showdown(JsonElement game) {
    }
}
