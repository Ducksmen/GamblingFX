package Dependencies.Games;

public class JackpotTestRunner {
    public static void main(String[] args)
    {
        Jackpot a = new Jackpot();
        a.fillPlayerNames();
        a.fillPlayerPool();
        a.addPlayerName(5,"Jack");
        a.addPlayerBet(5,"200");
        a.addPlayerPercent(5);
        a.addToBettingArr(5);
        a.pickWinner();
    }
}
