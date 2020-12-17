package widget;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;
import main.GlobalConfig;

public class ChooseShipTilePane
{
    private TilePane pane;
    private int shipSelected;
    private Image[][] shipTileImages;
    private ImageView[] tiles;
    private final static double tileScale = 0.45;

    private static final AudioClip hoverSound;
    private static final AudioClip clickSound;

    static
    {
        hoverSound = new AudioClip("file:res/sound/hover.mp3");
        clickSound = new AudioClip("file:res/sound/click.mp3");
    }

    public int getShipSelected()
    {
        return shipSelected;
    }

    private void clearTiles()
    {
        for(int i=0; i<4; i++)
        {
            tiles[i].setImage(shipTileImages[i][0]);
        }
    }

    public ChooseShipTilePane()
    {
        shipSelected=1;
        //init pane
        pane = new TilePane();
//        pane.setPrefRows(2);
        pane.setPrefColumns(2);
        pane.setHgap(-140);
        pane.setVgap(-140);
//        pane.setPrefTileHeight(100);
//        pane.setPrefTileWidth(50);
        //init images
        shipTileImages = new Image[4][3];
        tiles = new ImageView[4];

        //load images
        for(int i=0 ;i<4; i++)
        {
            shipTileImages[i][0] = new Image("file:res/img/ui_elements/ship_"+ (i+1) + "_normal.png");
            shipTileImages[i][1] = new Image("file:res/img/ui_elements/ship_"+ (i+1) + "_hover.png");
            shipTileImages[i][2] = new Image("file:res/img/ui_elements/ship_"+ (i+1) + "_click.png");

            if(shipSelected == i+1)
            {
                tiles[i] = new ImageView(shipTileImages[i][2]);
            }
            else
            {
                tiles[i] = new ImageView(shipTileImages[i][0]);
            }
            tiles[i].setScaleX(tileScale);
            tiles[i].setScaleY(tileScale);

            int finalI = i;
            tiles[i].setOnMouseEntered(e->{
                if(shipSelected == finalI +1)
                {
                    tiles[finalI].setImage(shipTileImages[finalI][2]);
                }
                else
                {
                    tiles[finalI].setImage(shipTileImages[finalI][1]);
                    if(GlobalConfig.isSoundOn())
                    hoverSound.play();
                }
            });
            tiles[i].setOnMouseExited(e->{
                if(shipSelected == finalI +1)
                {
                    tiles[finalI].setImage(shipTileImages[finalI][2]);
                }
                else
                {
                    tiles[finalI].setImage(shipTileImages[finalI][0]);
                }
            });
            tiles[i].setOnMousePressed(e->{

                if(shipSelected == finalI +1)
                {
                    tiles[finalI].setImage(shipTileImages[finalI][2]);
                }
                else
                {
                    clearTiles();//clear all tiles
                    tiles[finalI].setImage(shipTileImages[finalI][2]);
                    if(GlobalConfig.isSoundOn())
                    clickSound.play();
                }
            });
            tiles[i].setOnMouseReleased(e->{
                shipSelected=finalI+1;
            });





            pane.getChildren().add(tiles[i]);
        }

    }


    public Node getLayer()
    {
        return pane;
    }
}
