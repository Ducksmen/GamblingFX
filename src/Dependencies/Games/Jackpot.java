package Dependencies.Games;

import Controllers.LoginController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import Dependencies.Systems.User;
import Dependencies.Systems.UserManager;

/**
 * Jack Hoang and Kent Li
 */
public class Jackpot extends GamblingGame
{
    private int betTotal = 0;

    private ArrayList<String> playerPool = new ArrayList<>();

    private ArrayList<String> playerNames = new ArrayList<>();

    private ArrayList<String> array3 = new ArrayList<>();

    private ArrayList<String> bettingArray = new ArrayList<>();

    private ArrayList<String> winPercent = new ArrayList<>();

    private String win = "";

    private int sum = 0;

    private String name;

    private String money;

    public Jackpot()
    {
        this.betTotal = betTotal;
        this.playerPool = playerPool;
        this.playerNames = playerNames;
        this.array3 = array3;
        this.winPercent = winPercent;
        this.sum = sum;
        this.win = win;
    }

    public ArrayList<String> fillPlayerNames()
    {
        playerNames.add(0,"Bot1");
        playerNames.add(1,"Bot2");
        playerNames.add(2,"Bot3");
        playerNames.add(3,"Bot4");
        playerNames.add(4,"Bot5");

        return playerNames;
    }

    public ArrayList<String> fillPlayerPool()
    {
        Random rand = new Random();
        for(int i = 0; i < 5; i++)
        {
            playerPool.add(Integer.toString((rand.nextInt(1000))));
        }
        array3.add(playerNames.get(0) + ", " + playerPool.get(0));
        array3.add(playerNames.get(1) + ", " + playerPool.get(1));
        array3.add(playerNames.get(2) + ", " + playerPool.get(2));
        array3.add(playerNames.get(3) + ", " + playerPool.get(3));
        array3.add(playerNames.get(4) + ", " + playerPool.get(4));
        return array3;
    }

    /*public ArrayList<String> fillPlayerPercent()
    {
        findPlayerPoolSum();
        double win0 = (Double.valueOf(playerPool.get(0))/sum) * 100;
        double win1 = (Double.valueOf(playerPool.get(1))/sum) * 100;
        double win2 = (Double.valueOf(playerPool.get(2))/sum) * 100;
        double win3 = (Double.valueOf(playerPool.get(3))/sum) * 100;
        double win4 = (Double.valueOf(playerPool.get(4))/sum) * 100;
        winPercent.add(array3.get(0) + ", " + win0);
        winPercent.add(array3.get(1) + ", " + win1);
        winPercent.add(array3.get(2) + ", " + win2);
        winPercent.add(array3.get(3) + ", " + win3);
        winPercent.add(array3.get(4) + ", " + win4);
        return winPercent;
    }*/

    public int findPlayerPoolSum()
    {
        for(int i = 0; i < playerPool.size(); i++)
        {
            sum+=Integer.parseInt(playerPool.get(i));
        }
        return sum;
    }

    public ArrayList<String> addPlayerName(int i, String name)
    {
        playerNames.add(i,name);
        return playerNames;
    }

    public ArrayList<String> addPlayerBet(int i, String bet)
    {
        playerPool.add(i,bet);
        array3.add(playerNames.get(i) + ", " + playerPool.get(i));
        return array3;
    }

    public ArrayList<String> addPlayerPercent(int i)
    {
        findPlayerPoolSum();
        double win0 = (Double.valueOf(playerPool.get(0))/sum) * 100;
        double win1 = (Double.valueOf(playerPool.get(1))/sum) * 100;
        double win2 = (Double.valueOf(playerPool.get(2))/sum) * 100;
        double win3 = (Double.valueOf(playerPool.get(3))/sum) * 100;
        double win4 = (Double.valueOf(playerPool.get(4))/sum) * 100;
        double wini = (Double.valueOf(playerPool.get(i))/sum) * 100;
        winPercent.add(array3.get(0) + ", " + win0);
        winPercent.add(array3.get(1) + ", " + win1);
        winPercent.add(array3.get(2) + ", " + win2);
        winPercent.add(array3.get(3) + ", " + win3);
        winPercent.add(array3.get(4) + ", " + win4);
        winPercent.add(array3.get(i) + ", " + wini);
        for(String s: winPercent)
        {
            System.out.println(s);
        }
        return winPercent;
    }

    /*public ArrayList<String> bettingArr()
    {
        for(int i = 0; i < Integer.parseInt(playerPool.get(0)); i++)
        {
            bettingArray.add(playerNames.get(0));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(1)); i++)
        {
            bettingArray.add(playerNames.get(1));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(2)); i++)
        {
            bettingArray.add(playerNames.get(2));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(3)); i++)
        {
            bettingArray.add(playerNames.get(3));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(4)); i++)
        {
            bettingArray.add(playerNames.get(4));
        }
        //Collections.shuffle(bettingArray);
        return bettingArray;
    }*/

    public ArrayList<String> addToBettingArr(int j)
    {
        for(int i = 0; i < Integer.parseInt(playerPool.get(0)); i++)
        {
            bettingArray.add(playerNames.get(0));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(1)); i++)
        {
            bettingArray.add(playerNames.get(1));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(2)); i++)
        {
            bettingArray.add(playerNames.get(2));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(3)); i++)
        {
            bettingArray.add(playerNames.get(3));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(4)); i++)
        {
            bettingArray.add(playerNames.get(4));
        }
        for(int i = 0; i < Integer.parseInt(playerPool.get(j)); i++)
        {
            bettingArray.add(playerNames.get(j));
        }
        Collections.shuffle(bettingArray);
        return bettingArray;
    }

    public String pickWinner()
    {
        Random k = new Random();
        int winningIndex = k.nextInt(bettingArray.size());
        win = bettingArray.get(winningIndex);
        System.out.println("The Winner Is: " + win + ".");
        return win;
    }
}
