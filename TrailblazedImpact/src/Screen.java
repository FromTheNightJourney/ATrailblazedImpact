import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import java.util.Objects;

public class Screen extends Application {

    // Warning suppressed because I find it annoying.
    // Also, I need it so the music plays throughout the program.
    @SuppressWarnings("FieldCanBeLocal")
    private MediaPlayer mediaPlayer;
    private Character selectedCharacter;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialization, music, and making sure app cannot be resized.
        primaryStage.setTitle("A Trailblazed Impact.");
        primaryStage.setResizable(false);

        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);



        playMP3();

        // Introductory text
        Text introText = new Text("This is a fan-made simulation.");
        introText.setFont(Font.font("Times New Roman", 28));
        introText.setFill(Color.WHITE);
        introText.setOpacity(0.0);

        Text introTextCont = new Text("All rights belong to Hoyoverse (COGNOSPHERE).");
        introTextCont.setFont(Font.font("Times New Roman", 28));
        introTextCont.setFill(Color.WHITE);
        introTextCont.setOpacity(0.0);

        // Calculations for positioning
        double introTextWidth = introText.getBoundsInLocal().getWidth();
        double introTextHeight = introText.getBoundsInLocal().getHeight();
        double introTextX = (scene.getWidth() - introTextWidth) / 2;
        double introTextY = (scene.getHeight() - introTextHeight) / 2;
        introText.setX(introTextX);
        introText.setY(introTextY);

        double rightsTextWidth = introTextCont.getBoundsInLocal().getWidth();
        double rightsTextHeight = introTextCont.getBoundsInLocal().getHeight();
        double rightsTextX = (scene.getWidth() - rightsTextWidth) / 2;
        double rightsTextY = introTextY + introTextHeight + 20;
        introTextCont.setX(rightsTextX);
        introTextCont.setY(rightsTextY);

        Text clickToContinueText = new Text("Click to continue");
        clickToContinueText.setFont(Font.font("Arial", 24));
        clickToContinueText.setFill(Color.WHITE);
        clickToContinueText.setOpacity(0.0);

        double clickToContinueTextX = (scene.getWidth() - clickToContinueText.getBoundsInLocal().getWidth()) / 2;
        double clickToContinueTextY = rightsTextY + rightsTextHeight + 20;
        clickToContinueText.setX(clickToContinueTextX);
        clickToContinueText.setY(clickToContinueTextY);

        // Title Screen
        Text trailblazedImpactText = new Text("A Trailblazed Impact");
        trailblazedImpactText.setFont(Font.font("Arial", 36));
        trailblazedImpactText.setFill(Color.WHITE);
        trailblazedImpactText.setOpacity(0.0);

        double trailblazedImpactTextX = (scene.getWidth() - trailblazedImpactText.getBoundsInLocal().getWidth()) / 2;
        double trailblazedImpactTextY = (scene.getHeight() - trailblazedImpactText.getBoundsInLocal().getHeight()) / 2;
        trailblazedImpactText.setX(trailblazedImpactTextX);
        trailblazedImpactText.setY(trailblazedImpactTextY);

        // Addition of texts to root pane.
        root.getChildren().addAll(introText, introTextCont, clickToContinueText, trailblazedImpactText);

        // Fade transition logic.
        FadeTransition introFadeTransition = createFadeTransition(introText, 0.0, 1.0, 2.0);
        FadeTransition rightsFadeTransition = createFadeTransition(introTextCont, 0.0, 1.0, 2.0);
        FadeTransition clickToContinueFadeTransition = createFadeTransition(clickToContinueText, 0.0, 1.0, 2.0);
        FadeTransition trailblazedImpactFadeTransition = createFadeTransition(trailblazedImpactText, 0.0, 1.0, 2.0);

        // Click event handling.
        clickToContinueFadeTransition.setOnFinished(event ->
                clickToContinueText.setOnMouseClicked(mouseEvent -> {
                    // Fade out animations.
                    FadeTransition introFadeOutTransition = createFadeTransition(introText, 1.0, 0.0, 1.0);
                    FadeTransition rightsFadeOutTransition = createFadeTransition(introTextCont, 1.0, 0.0, 1.0);
                    FadeTransition clickToContinueFadeOutTransition = createFadeTransition(clickToContinueText, 1.0, 0.0, 1.0);
                    clickToContinueFadeOutTransition.setOnFinished(fadeEvent -> {
                        root.getChildren().remove(introText);
                        root.getChildren().remove(introTextCont);
                        root.getChildren().remove(clickToContinueText);
                        trailblazedImpactFadeTransition.play();
                    });

                    introFadeOutTransition.play();
                    rightsFadeOutTransition.play();
                    clickToContinueFadeOutTransition.play();
                })
        );

        trailblazedImpactFadeTransition.setOnFinished(event -> {
            FadeTransition trailblazedImpactFadeOutTransition = createFadeTransition(trailblazedImpactText, 1.0, 0.0, 1.0);
            trailblazedImpactFadeOutTransition.setOnFinished(fadeEvent -> {
                // Preparation for menu screen.
                ImageView imageView = createImageView();
                root.getChildren().add(imageView);

                // Main menu button config.
                double buttonWidth = 250;
                double buttonHeight = 50;
                double cornerRadius = 50;

                // Button container for better visuals.
                VBox buttonContainer = new VBox(20);
                buttonContainer.setTranslateX((scene.getWidth() - buttonWidth) / 2);
                buttonContainer.setTranslateY((scene.getHeight() - buttonHeight * 3 - 100) / 2);
                buttonContainer.setSpacing(50);
                buttonContainer.setAlignment(Pos.CENTER);

                // Main button creations.
                StackPane playButton = createButton("Play", buttonWidth, buttonHeight, cornerRadius, () -> {
                    System.out.println("Play button clicked");
                    VBox characterMenu = CharacterSelect.showCharacterSelectionMenu(root, primaryStage, mediaPlayer);
                    root.getChildren().remove(buttonContainer);
                    root.getChildren().add(characterMenu);
                    //GameScreen gameScreen = new GameScreen();
                    //gameScreen.show();
                    //primaryStage.close();
                });

                StackPane charactersButton = createButton("Characters", buttonWidth, buttonHeight, cornerRadius, () -> {
                    System.out.println("Characters button clicked");
                    Text underDevelopmentText = new Text("Under Development\n" +
                            "Come Back Soon!");
                    underDevelopmentText.setFont(Font.font("Arial", 48));
                    underDevelopmentText.setFill(Color.WHITE);
                    underDevelopmentText.setStroke(Color.BLACK);
                    underDevelopmentText.setStrokeWidth(1.5);
                    underDevelopmentText.setTextAlignment(TextAlignment.CENTER);
                    underDevelopmentText.setStrokeType(StrokeType.OUTSIDE);
                    double underDevelopmentTextX = (scene.getWidth() - underDevelopmentText.getBoundsInLocal().getWidth()) / 2;
                    double underDevelopmentTextY = (scene.getHeight() - underDevelopmentText.getBoundsInLocal().getHeight()) / 2;
                    underDevelopmentText.setX(underDevelopmentTextX);
                    underDevelopmentText.setY(underDevelopmentTextY);
                    root.getChildren().add(underDevelopmentText);
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(e -> root.getChildren().remove(underDevelopmentText));
                    pause.play();
                });

                final StackPane[] continueButton = {null};

                StackPane disclaimerButton = createButton("Disclaimer", buttonWidth, buttonHeight, cornerRadius, () -> {
                    System.out.println("Disclaimer button clicked");
                    // Remove existing buttons
                    root.getChildren().remove(buttonContainer);

                    // Create and configure the disclaimer text
                    Text disclaimerText = new Text();
                    disclaimerText.setFont(Font.font("Arial", 18));
                    disclaimerText.setFill(Color.WHITE);
                    disclaimerText.setTextAlignment(TextAlignment.CENTER);
                    disclaimerText.setWrappingWidth(scene.getWidth() - 100);
                    disclaimerText.setStroke(Color.BLACK);
                    disclaimerText.setStrokeWidth(1.5);
                    disclaimerText.setStrokeType(StrokeType.OUTSIDE);
                    disclaimerText.setText("This is a fan-made battle game simulation based on the games" +
                            "'Honkai: Star Rail' and 'Genshin Impact'. This simulation will forever be free\n" +
                            "and under no circumstances will it require any form of monetary payment to play.\n" +
                            "I am not affiliated with Mihoyo or Hoyoverse in any way, shape, or form. The quality\n" +
                            "of the game does not reflect the quality of Mihoyo or Hoyoverse's original works\n" +
                            "and will never compare to the experience of the original games.\n" +
                            "Support the original creators by playing the original games through legal means.");

                    // Calculate the position of the disclaimer text
                    double disclaimerTextX = (scene.getWidth() - disclaimerText.getLayoutBounds().getWidth()) / 2;
                    double disclaimerTextY = (scene.getHeight() - disclaimerText.getLayoutBounds().getHeight()) / 2;
                    disclaimerText.setLayoutX(disclaimerTextX);
                    disclaimerText.setLayoutY(disclaimerTextY);

                    // Create the continue button
                    continueButton[0] = createButton("Continue", buttonWidth, buttonHeight, cornerRadius, () -> {
                        // Remove the disclaimer text and continue button
                        root.getChildren().removeAll(disclaimerText, continueButton[0]);

                        // Restore the main menu buttons
                        root.getChildren().add(buttonContainer);
                    });

                    // Calculate the position of the continue button
                    double continueButtonX = (scene.getWidth() - continueButton[0].getBoundsInLocal().getWidth()) / 2;
                    double continueButtonY = disclaimerTextY + disclaimerText.getLayoutBounds().getHeight() + 20;
                    continueButton[0].setLayoutX(continueButtonX);
                    continueButton[0].setLayoutY(continueButtonY);

                    // Add the disclaimer text and continue button to the root pane
                    root.getChildren().addAll(disclaimerText, continueButton[0]);
                });

                buttonContainer.getChildren().addAll(playButton, charactersButton, disclaimerButton);
                root.getChildren().add( buttonContainer);
            });
            trailblazedImpactFadeOutTransition.play();
        });

        // Play the fade-in animations.
        introFadeTransition.play();
        rightsFadeTransition.play();
        clickToContinueFadeTransition.play();

        primaryStage.show();
    }
    private FadeTransition createFadeTransition(Text text, double fromValue, double toValue, double durationSeconds) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(durationSeconds), text);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    // Utils for background image creation.
    private ImageView createImageView() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("resources/menu.jpg")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        return imageView;
    }

    // Utils for button creation.
    private StackPane createButton(String label, double width, double height,
                                   double cornerRadius, Runnable action) {
        Rectangle button = new Rectangle(width, height);
        button.setArcWidth(cornerRadius);
        button.setArcHeight(cornerRadius);
        button.setFill(Color.WHITE);

        Text buttonText = new Text(label);
        buttonText.setFont(Font.font("Arial", 24));
        buttonText.setFill(Color.BLACK);

        StackPane buttonPane = new StackPane(button, buttonText);

        // text input to system
        buttonPane.setOnMouseClicked(event -> action.run());

        return buttonPane;
    }

    // Utils for background music.
    public void playMP3() {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("resources/BGM_menu.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }


}


