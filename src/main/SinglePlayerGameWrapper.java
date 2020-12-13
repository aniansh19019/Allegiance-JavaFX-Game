package main;

import javafx.stage.Stage;

public class SinglePlayerGameWrapper
{
    private SinglePlayerGame game;

    public SinglePlayerGame getGame()
    {
        return game;
    }

    public void setGame(SinglePlayerGame game)
    {
        this.game = game;
    }

    public SinglePlayerGameWrapper(Stage stage)
    {
        this.game = new SinglePlayerGame(stage, this);
    }
}
