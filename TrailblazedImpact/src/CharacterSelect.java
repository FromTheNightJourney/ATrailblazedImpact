import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CharacterSelect {
    public static VBox showCharacterSelectionMenu(Pane root, Stage primaryStage, MediaPlayer mediaPlayer) {
        VBox characterMenu = new VBox(20);
        characterMenu.setAlignment(Pos.CENTER);

        // Create and configure the title text
        Text titleText = new Text("                                               Character Selection");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleText.setFill(Color.WHITE);
        titleText.setStroke(Color.BLACK);
        titleText.setStrokeWidth(1.5);
        titleText.setStrokeType(StrokeType.OUTSIDE);
        titleText.setTextAlignment(TextAlignment.CENTER);

        // Create and configure the character images
        ImageView character1Image = new ImageView(new Image("resources/albebruh.png"));
        ImageView character2Image = new ImageView(new Image("resources/rairai.png"));
        ImageView character3Image = new ImageView(new Image("resources/john.png"));
        ImageView character4Image = new ImageView(new Image("resources/yomeer.png"));

        // Set the size of the character images
        double imageSize = 200;
        character1Image.setFitWidth(imageSize);
        character1Image.setFitHeight(imageSize);
        character2Image.setFitWidth(imageSize);
        character2Image.setFitHeight(imageSize);
        character3Image.setFitWidth(imageSize);
        character3Image.setFitHeight(imageSize);
        character4Image.setFitWidth(imageSize);
        character4Image.setFitHeight(imageSize);

        // Create and configure the character buttons
        Button character1Button = new Button("Albebruh");
        character1Button.setOnAction(event -> selectCharacterAndStartGame(primaryStage, new albebruh(), mediaPlayer));

        Button character2Button = new Button("Rairai");
        character2Button.setOnAction(event -> selectCharacterAndStartGame(primaryStage, new OiShogun(), mediaPlayer));

        Button character3Button = new Button("John Lee");
        character3Button.setOnAction(event -> selectCharacterAndStartGame(primaryStage, new JongLee(), mediaPlayer));

        Button character4Button = new Button("Yomeers");
        character4Button.setOnAction(event -> selectCharacterAndStartGame(primaryStage, new Yomeiers(), mediaPlayer));

        // Create a VBox for each character's image and button
        VBox character1Box = new VBox(10);
        character1Box.setAlignment(Pos.CENTER);
        character1Box.getChildren().addAll(character1Image, character1Button);

        VBox character2Box = new VBox(10);
        character2Box.setAlignment(Pos.CENTER);
        character2Box.getChildren().addAll(character2Image, character2Button);

        VBox character3Box = new VBox(10);
        character3Box.setAlignment(Pos.CENTER);
        character3Box.getChildren().addAll(character3Image, character3Button);

        VBox character4Box = new VBox(10);
        character4Box.setAlignment(Pos.CENTER);
        character4Box.getChildren().addAll(character4Image, character4Button);

        // Create a GridPane for character images and buttons
        GridPane characterGrid = new GridPane();
        characterGrid.setAlignment(Pos.CENTER);
        characterGrid.setHgap(70);
        characterGrid.setVgap(30);
        characterGrid.setPadding(new Insets(10));

        // Add an empty column to the left of the characterGrid
        ColumnConstraints emptyColumn = new ColumnConstraints(92.5);
        characterGrid.getColumnConstraints().add(0, emptyColumn);

        // Add the character boxes to the GridPane
        characterGrid.add(character1Box, 1, 0);
        characterGrid.add(character2Box, 2, 0);
        characterGrid.add(character3Box, 1, 1);
        characterGrid.add(character4Box, 2, 1);

        // Create a BorderPane to organize the title text and character grid
        BorderPane characterPane = new BorderPane();
        characterPane.setTop(titleText);
        characterPane.setCenter(characterGrid);

        // Add the characterPane to the character menu VBox
        characterMenu.getChildren().add(characterPane);

        return characterMenu;
    }

    private static void selectCharacterAndStartGame(Stage primaryStage, CharacterTemp selectedCharacter, MediaPlayer mediaPlayer) {
        primaryStage.close();
        BattleLogic.setPlayerCharacter(selectedCharacter);
        GameScreen gameScreen = new GameScreen(selectedCharacter, mediaPlayer);
        gameScreen.show(primaryStage);
    }
}
