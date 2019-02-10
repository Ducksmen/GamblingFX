package Dependencies.Games;

import java.util.Random;

public class JackpotTestRunner {
    public static void main(String[] args)
    {
        Jackpot a = new Jackpot();
        Random k = new Random();
        a.fillPlayerNames();
        a.fillPlayerPool();
        a.addPlayerName(5,"Jack");
        a.addPlayerBet(5,Integer.toString(k.nextInt(1000)));
        a.addPlayerPercent(5);
        a.addToBettingArr(5);
        a.pickWinner();
    }
}
