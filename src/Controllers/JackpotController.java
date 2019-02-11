package Controllers;

import Dependencies.Games.Jackpot;
import Dependencies.Games.jackpotTable;
import com.jfoenix.controls.JFXDialog;
import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class JackpotController implements Initializable
{
    @FXML private Label yourName;
    @FXML private Label balanceNum;
    @FXML private Label currentBet;
    @FXML private Button startButton;
    @FXML private Label timeNum;
    @FXML private Label winPercent;
    @FXML private Button submitButton;
    @FXML private TextField field;
    @FXML private Label prompt;
    @FXML private Label totalBet;
    @FXML private StackPane stackPane;
    @FXML private TableView<jackpotTable> tableView;
    @FXML private TableColumn<jackpotTable, String> nameColumn;
    @FXML private TableColumn<jackpotTable, String> betColumn;
    @FXML private TableColumn<jackpotTable, String> percentColumn;

    private long time2;
    private String winner;
    private double winPercentage;
    private boolean gameState = false;
    private Jackpot jp;

    public void start()
    {
        if (!gameState)
        {
            gameState = true;

            long step = System.nanoTime() + 15000000000L;
            new AnimationTimer()
            {
                public void handle(long now)
                {
                    if (now > step)
                    {
                        timeNum.setText("0");
                    }
                    if (now < step)
                    {
                        time2 = now - step;
                        timeNum.setText(Long.toString(-time2/1000000000));
                        if(Long.toString(-time2/1000000000).equals("0"))
                        {
                            runGame();
                            JFXDialog dialog = new JFXDialog();
                            dialog.setContent(new Label(winner + " won with " + winPercentage + "%."));
                            dialog.show(stackPane);
                            PauseTransition pause = new PauseTransition(Duration.seconds(4));
                            pause.setOnFinished(event -> {
                                dialog.close();
                            });
                            pause.play();
                        }
                    }
                }
            }.start();
        }
    }

    private boolean isNumber()
    {
        return (field.getText().matches("^[0-9]*$") || field.getText().length() > 10 || field.getText().length() == 0);
    }

    public void handler(javafx.event.ActionEvent e)
    {
        if (gameState)
        {
            if(isNumber())
            {
                prompt.setText("Bet Received.");
                currentBet.setText(field.getText());
                totalBet.setText(currentBet.getText());
                balanceNum.setText(Integer.toString(Integer.parseInt(balanceNum.getText()) - Integer.parseInt(currentBet.getText())));
                winPercent.setText(Integer.toString((Integer.valueOf(currentBet.getText()) / Integer.valueOf(totalBet.getText())) * 100) + "%");
                submitButton.setVisible(false);
                field.setEditable(false);
                nameColumn.setCellValueFactory(new PropertyValueFactory<jackpotTable, String>("Name"));
                betColumn.setCellValueFactory(new PropertyValueFactory<jackpotTable, String>("Bet"));
                percentColumn.setCellValueFactory(new PropertyValueFactory<jackpotTable, String>("Percent"));

                tableView.setItems(getData());
            }
            else
            {
                prompt.setText("Integers Only, 0-100K");
            }
        }
    }

    public ObservableList<jackpotTable> getData()
    {
        ObservableList<jackpotTable> data = FXCollections.observableArrayList();
        data.add(new jackpotTable(LoginController.currentUser.getFirstName() + " " + LoginController.currentUser.getLastName(),
                currentBet.getText(),
                Integer.toString((Integer.valueOf(currentBet.getText()) / Integer.valueOf(totalBet.getText())) * 100) + "%"));
        return data;
    }

    public void runGame()
    {
        if (gameState) {
            jp.fillPlayerNames();
            jp.fillPlayerPool();
            jp.addPlayerName(5, LoginController.currentUser.getFirstName() + " " + LoginController.currentUser.getLastName());
            jp.addPlayerBet(5, currentBet.getText());
            jp.addPlayerPercent(5);
            jp.addToBettingArr(5);
            winner = jp.pickWinner();
            winPercentage = Double.parseDouble(jp.returnWinnerPercent());
            gameState = false;
        }
        //Our main problem is that jack actually ran all of this at once which is why the table isn't updating.
        //If you want to fix this problem, I was thinking of actually
        //Filling the table and name first with the bots
        //Then on the handle(button click), it will use the addPlayerName etc to add the player info ONTO the table
        //Then finally when the time counts down to 0 it will run jp.pickWinner instead of runGame all at once
        //I hope this comment helps just contact me on discord for more info about our project.
        //I'm really sorry for the shitty coding cuz this pseudo hardcoding was the easiest way for me to do it.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jp = new Jackpot();
        jp.addCurrentPlayer(LoginController.currentUser);
        balanceNum.setText(Integer.toString(LoginController.currentUser.getBalance()));
        yourName.setText(LoginController.currentUser.getFirstName() + " " + LoginController.currentUser.getLastName());
    }
}
