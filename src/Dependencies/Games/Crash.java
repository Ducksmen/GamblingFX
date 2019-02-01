package Dependencies.Games;

import Dependencies.Systems.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Crash Game
 * @Authors Afaq Anwar & Wai Hin Leung
 * @Version 01/31/2019
 */
public class Crash extends GamblingGame {
    // The current multiplier of the Game.
    private double currentMultiplier;
    // The multiplier at which the Game will end.
    private double bustingMultiplier;
    // HashMap that represents each player along with their multiplier.
    private HashMap<User, Double> currentPlayerMultipliers;
    private HashMap<User, Integer> currentPlayerBets;

    /**
     * Main Constructor.
     */
    public Crash() {
        this.currentMultiplier = generateRandomMultiplier();
        this.bustingMultiplier = generateRandomMultiplier();
        currentPlayerMultipliers = generateInitialPlayerMap(userManager.getCurrentActiveUsers());
        this.currentPlayerBets = generateInitialBetMap(userManager.getCurrentActiveUsers());
    }

    // Getters & Setters for some private fields.
    public double getCurrentMultiplier() { return this.currentMultiplier; }
    public void setCurrentMultiplier(double currentMultiplier) { this.currentMultiplier = currentMultiplier; }
    public double getBustingMultiplier() { return this.bustingMultiplier; }

    /**
     * @return Random Double between 0 - 10,000.
     */
    private double generateRandomMultiplier() { return (Math.random() * 10000); }

    /**
     * Populates the HashMap with initial data.
     * @param userList The ArrayList of playing Users.
     * @return HashMap that maps a User and their starting multiplier (0.0).
     */
    private HashMap<User, Double> generateInitialPlayerMap(ArrayList<User> userList) {
        HashMap<User, Double> playerMap = new HashMap<>();
        for (User currUser : userList) {
            playerMap.put(currUser, 0.0);
        }
        return playerMap;
    }
    private HashMap<User, Integer> generateInitialBetMap(ArrayList<User> userList) {
        HashMap<User, Integer> betMap = new HashMap<>();
        for (User currUser : userList) {
            betMap.put(currUser, 0);
        }
        return betMap;
    }
    /**
     * Updates all of the multipliers of the players that are currently playing.
     */
    public void updatePlayerMultipliers() {
        for (User currUser : this.userManager.getCurrentActiveUsers()) {
            if (userManager.getStatusOfUser(currUser).equals("Playing"))
            currentPlayerMultipliers.replace(currUser, currentMultiplier);
        }
    }
    public void placedBet(int bet, User currUser){
        currentPlayerBets.replace(currUser, bet);
    }

}
