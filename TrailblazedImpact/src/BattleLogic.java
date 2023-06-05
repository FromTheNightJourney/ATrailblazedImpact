import java.util.Scanner;

public class BattleLogic {
    private CharacterTemp playerCharacter;
    private CharacterTemp enemyCharacter;
    private boolean isPlayerTurn;

    public BattleLogic(CharacterTemp playerCharacter, CharacterTemp enemyCharacter) {
        this.playerCharacter = playerCharacter;
        this.enemyCharacter = enemyCharacter;
        this.isPlayerTurn = true;
    }

    public static void setPlayerCharacter(CharacterTemp character) {
        // Set the player character
        character.setPlayerControlled(true);
    }
}