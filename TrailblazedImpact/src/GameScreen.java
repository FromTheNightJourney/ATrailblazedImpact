import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;

import java.util.Objects;
import java.util.Random;

public class GameScreen {
    private Stage stage;
    private CharacterTemp playerCharacter;
    private CharacterTemp bossCharacter;
    private Text playerHealthText; // Instance variable to store a reference to the player's health text
    private Text bossHealthText; // Instance variable to store a reference to the boss's health text
    private Random random = new Random();
    private MediaPlayer mediaPlayer2;
    public GameScreen(CharacterTemp selectedCharacter, MediaPlayer mediaPlayer) {
        stage = new Stage();
        stage.setTitle("The Battle Begins!");
        stage.setResizable(false);

        playerCharacter = selectedCharacter;
        bossCharacter = new Cocolia();

        // Create the game screen layout
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundImage(
                new Image("resources/battleimg.jpg"), null, null, null, null)));

        mediaPlayer.stop();
        playtheMP3();

        // Add player image
        String imagePath = playerCharacter.getImagePath();
        Image playerImage = new Image(imagePath);
        ImageView playerImageView = new ImageView(playerImage);

        // Create buttons for basic attack, skill, and ultimate
        Button basicAttackButton = new Button("Basic Attack");
        Button skillButton = new Button("Skill");
        Button ultimateButton = new Button("Ultimate");

        // Apply CSS style to increase button size
        String buttonStyle = "-fx-font-size: 16px; -fx-min-width: 210; -fx-min-height: 100px;";
        basicAttackButton.setStyle(buttonStyle);
        skillButton.setStyle(buttonStyle);
        ultimateButton.setStyle(buttonStyle);

        // Set event handlers for the buttons
        basicAttackButton.setOnAction(e -> performBasicAttack());
        skillButton.setOnAction(e -> performSkill());
        ultimateButton.setOnAction(e -> performUltimate());

        // Create an HBox to hold the buttons
        HBox buttonsBox = new HBox(basicAttackButton, skillButton, ultimateButton);
        buttonsBox.setAlignment(Pos.CENTER_RIGHT);
        buttonsBox.setSpacing(0);

        // Create a Text object to display the character's health
        playerHealthText = new Text(playerCharacter.getName() + "'s HP: " + playerCharacter.getHp() + " / " + playerCharacter.getMaxHP());

        // Create a StackPane to hold the healthText
        StackPane healthTextContainer = new StackPane(playerHealthText);
        healthTextContainer.setAlignment(Pos.CENTER);
        healthTextContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        // Create a VBox to hold the healthTextContainer and buttons HBox
        VBox buttonsContainer = new VBox(healthTextContainer, buttonsBox);
        buttonsContainer.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsContainer.setPadding(new Insets(0));
        buttonsContainer.setSpacing(0);

        // Create an HBox to hold the player image and buttons container
        HBox contentBox = new HBox(playerImageView, buttonsContainer);
        contentBox.setAlignment(Pos.BOTTOM_LEFT);
        contentBox.setSpacing(0);
        contentBox.setPadding(new Insets(0, 0, 0, 30)); // Adjust the padding values here

        // Create an ImageView for the boss image
        Image bossImage = new Image("resources/cocowia.png");
        ImageView bossImageView = new ImageView(bossImage);
        bossImageView.setFitWidth(200);
        bossImageView.setFitHeight(200);

        // Create a Rectangle object
        Rectangle rectangle = new Rectangle(600, 50);
        rectangle.setFill(Color.WHITE);

        // Create a Text object to display the boss's health
        bossHealthText = new Text("\n                                           Cocolia's HP: " + bossCharacter.getHp() + " / " + bossCharacter.getMaxHP());

        // TextArea Object
        TextArea textArea = new TextArea();
        textArea.setPrefSize(600, 130);
        textArea.setStyle("-fx-control-inner-background: lightgray;");
        textArea.setEditable(false);

        // ScrollPane for the TextArea
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        // OutputStream to display the GUI onto the TextArea
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.appendText(String.valueOf((char) b));
            }
        };

        // Make the system write to the TextArea
        System.setOut(new PrintStream(outputStream, true));

        // Create a VBox to hold the textArea
        VBox textAreaContainer = new VBox(scrollPane);
        textAreaContainer.setAlignment(Pos.TOP_LEFT);
        textAreaContainer.setPadding(new Insets(-150, 0, 0, 0)); // Adjust padding as needed
        textAreaContainer.setMaxSize(600, 130); // Set maximum size for the container
        scrollPane.setPrefViewportHeight(130); // Adjust the height as needed

        // Add the textAreaContainer to the root layout
        root.getChildren().add(textAreaContainer);

        // Create a StackPane to hold the Rectangle and Text
        StackPane stackPane = new StackPane(rectangle, bossHealthText);
        stackPane.setAlignment(Pos.TOP_LEFT);

        // Create an HBox to hold the stackPane and bossImageView
        HBox topBox = new HBox(stackPane, bossImageView);
        topBox.setAlignment(Pos.TOP_RIGHT);

        // Create a VBox to hold the topBox and textAreaContainer
        VBox topContentBox = new VBox(topBox, textAreaContainer);
        topContentBox.setAlignment(Pos.TOP_LEFT); // Align content to top left corner

        // Add the topContentBox and content box to the root layout
        root.getChildren().addAll(topContentBox, contentBox);

        // Create the game scene
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        // Set up fade transition for smooth transition effect
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public void show(Stage primaryStage) {
        stage.show();
    }

    private void performBasicAttack() {
        playerCharacter.attack(bossCharacter);
        updateHealthText();
        performCocoliaTurn();
    }

    private void performSkill() {
        playerCharacter.skill(bossCharacter);
        updateHealthText();
        performCocoliaTurn();
    }

    private void performUltimate() {
        playerCharacter.ultimate(bossCharacter);
        updateHealthText();
        performCocoliaTurn();
    }

    private void performCocoliaTurn() {
        checkGameResult();
        // Choose a random attack for Cocolia
        int attackChoice = random.nextInt(3);
        switch (attackChoice) {
            case 0:
                bossCharacter.attack(playerCharacter);
                break;
            case 1:
                ((Cocolia) bossCharacter).chillOfBonePiercingCoagulation(playerCharacter);
                break;
            case 2:
                ((Cocolia) bossCharacter).hoarfrostOfEternalIsolation(playerCharacter);
                break;
            default:
                break;
        }
        updateHealthText();
        checkGameResult();
        playerCharacter.reduceCooldowns();
    }

    private void updateHealthText() {
        // Update player character's health text
        playerHealthText.setText(playerCharacter.getName() + "'s HP: " + playerCharacter.getHp() + " / " + playerCharacter.getMaxHP());

        // Update boss character's health text
        bossHealthText.setText("Cocolia's HP: " + bossCharacter.getHp() + " / " + bossCharacter.getMaxHP() + "\n");
    }

    private void checkGameResult() {
        if (bossCharacter.isDefeated()) {
            System.out.println("Congratulations! You defeated the boss!");
            mediaPlayer2.stop();
            winsound();
            PauseTransition pause = new PauseTransition((Duration.seconds(5)));
            pause.setOnFinished(event -> Platform.exit()); // Call Platform.exit() after the pause
            pause.play();
        } else if (playerCharacter.isDefeated()) {
            System.out.println("Game over! You were defeated by the boss.");
            losssound();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> Platform.exit()); // Call Platform.exit() after the pause
            pause.play();
        }
    }

    public void playtheMP3() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("resources/The_Battle.mp3")).toExternalForm());
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.setVolume(0.1);
        mediaPlayer2.play();
    }

    public void losssound() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("resources/loss.mp3")).toExternalForm());
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.setVolume(0.1);
        mediaPlayer2.play();
    }
    public void winsound() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("resources/win.mp3")).toExternalForm());
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.setVolume(0.1);
        mediaPlayer2.play();
    }


}
