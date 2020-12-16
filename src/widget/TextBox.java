package widget;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import main.GlobalConfig;
import org.w3c.dom.Text;

public class TextBox
{
    private String text;
    private Group group;
    private static final GlobalConfig config;

    static
    {
        config = new GlobalConfig();
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public TextBox()
    {
        //init ImageView
        Image textBoxImage = new Image("file:res/img/ui_elements/text_box.png");
        ImageView textBoxBackground = new ImageView(textBoxImage);
        //init Text Box

        TextField input = new TextField();
        input.setPromptText("Enter Player Name");
        input.setFont(Font.loadFont(config.getPRIMARY_FONT(), 30));
        input.setOnInputMethodTextChanged(e->{ this.text = input.getText();});
        input.setBorder(Border.EMPTY);
        input.setBackground(Background.EMPTY);
//        input.setAlignment(Pos.CENTER);
//        input.setMaxWidth(textBoxBackground.getFitWidth());
//        input.setTranslateY();
        input.setMinSize(textBoxImage.getWidth(), textBoxImage.getHeight());

        //init continue button


        //init Group
        group = new Group();
        group.getChildren().addAll(textBoxBackground, input);

    }

    public Node getLayer()
    {
        return group;
    }
}
