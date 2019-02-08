package Controllers;

import Dependencies.Games.Jackpot;
import Dependencies.Systems.User;
import Dependencies.Systems.UserManager;
import com.jfoenix.controls.JFXDialog;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import  javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

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
    private long time2;

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
                    }
                }
            }.start();
        }
        if(timeIsZero())
        {
            JFXDialog dialog = new JFXDialog();
            dialog.setContent(new Label("x won with x percent"));
            dialog.show(stackPane);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                dialog.close();
            });
            pause.play();
        }
    }

    private boolean timeIsZero()
    {
        return (time2 == 0);
    }

    private boolean isNumber()
    {
        return (field.getText().matches("^[0-9]*$"));
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
                //winPercent.setText(Integer.toString(Integer.valueOf(currentBet.getText()) / Integer.valueOf(totalBet.getText())));

            }
            else
            {
                prompt.setText("Integers Only, 0-Billion");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jp = new Jackpot();
        jp.addCurrentPlayer(LoginController.currentUser);
        balanceNum.setText(Integer.toString(LoginController.currentUser.getBalance()));
        yourName.setText(LoginController.currentUser.getFirstName() + " " + LoginController.currentUser.getLastName());
    }
}
