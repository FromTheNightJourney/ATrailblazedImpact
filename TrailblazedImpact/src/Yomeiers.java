import java.util.Random;

public class Yomeiers extends CharacterTemp {


    public Yomeiers() {
        super("Firework Girl", 262, 500, 8200, 8200, 140,
                0.5, 0.5, "resources/yomeer.png", 3, 5);
    }

    public void skill(CharacterTemp target) {
        if (getSkillCooldown() > 0) {
            System.out.println(getName() + "'s skill is on cooldown. Turns remaining: " + getSkillCooldown() + "\n");
            return;
        }

        // Additional damage between 150 - 450
        // Skill CD is 3 rounds
        int additionalDamage = new Random().nextInt(301) + 150;
        System.out.println(getName() + " used skill!");
        System.out.println(getName() + " dealt " + additionalDamage + " additional damage to " + target.getName() + "\n");
        target.takeDamage(additionalDamage);

        setSkillCooldown(3);

    }

    public void ultimate(CharacterTemp target) {
        if (getUltimateCooldown() > 0) {
            System.out.println(getName() + "'s ultimate is on cooldown. Turns remaining: " + getUltimateCooldown() + "\n");
            return;
        }

        // Boost crit rate and crit damage to 1.0 for three turns
        // Skill CD is 5 rounds
        setCritRate(1.0);
        setCritDamage(1.0);
        int ultimateDamage = (int) (getAttack() * 5.0);
        System.out.println(getName() + " used ultimate!");
        System.out.println(getName() + " shoots fireworks and deals massive damage to " + target.getName());
        target.takeDamage(ultimateDamage);
        System.out.println(getName() + " crit rate and crit damage are boosted!" + "\n");

        setUltimateCooldown(5);

    }
}