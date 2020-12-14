package main;

import main.menu.main_menu.MainMenu;

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

    public SinglePlayerGameWrapper(MainMenu menu)
    {
        this.game = new SinglePlayerGame(menu, this);
    }
}
