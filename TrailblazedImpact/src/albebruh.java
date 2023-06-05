public class albebruh extends CharacterTemp {
    private int skillDuration; // Number of turns the skill lasts

    public albebruh() {
        super("Albebruh", 204, 712, 10746, 10746, 102, 0.45, 0.6, "resources/albebruh.png", 3, 5);
        this.skillDuration = 0; // Skill starts with zero duration
    }

    public void skill(CharacterTemp target) {
        if (getSkillCooldown() > 0) { // Check if the skill is on cooldown
            System.out.println(getName() + "'s skill is on cooldown. Turns remaining: " + getSkillCooldown() + "\n");
            return; // Do not perform the skill action if the skill is on cooldown
        }

        // Check if the skill is already active
        // 3 round CD
        if (skillDuration > 0) {
            System.out.println(getName() + "'s skill is already active!\n");
            // You can add additional logic here, such as displaying a message or applying different effects if the skill is already active
        } else {
            // Places an isotoma bloom down that lasts for 3 turns
            // Does additional 300 Geo DMG
            System.out.println(getName() + " uses skill: Isotoma Bloom!\n");

            // Calculate the skill damage
            int skillDamage = 300; // Additional Geo DMG

            // Apply the skill effects here
            // For example, you can create a new class for the Isotoma Bloom, which can store its own duration and damage value.
            // In this case, you would create an instance of the Isotoma Bloom and keep track of its duration separately.

            // Set the skill duration to 3 turns
            skillDuration = 3;
            setSkillCooldown(3);
        }
    }

    public void ultimate(CharacterTemp target) {
        if (getUltimateCooldown() > 0) { // Check if the ultimate is on cooldown
            System.out.println(getName() + "'s ultimate is on cooldown. Turns remaining: " + getUltimateCooldown() + "\n");
            return; // Do not perform the ultimate action if the ultimate is on cooldown
        }

        // Creates an isotomic explosion
        // Does AOE damage
        // 700%
        // Does additional 5 isotoma bloom attacks
        // 6 round CD
        System.out.println(getName() + " uses ultimate: Isotomic Explosion!\n");

        // Calculate the ultimate damage
        int ultimateDamage = getAttack() * 7; // 700% of attack

        // Calculate the additional damage from the isotoma blooms
        int additionalDamage = 5 * 300; // 5 isotoma blooms * 300 Geo DMG

        // Calculate the total damage
        int totalDamage = ultimateDamage + additionalDamage;

        target.takeDamage(totalDamage);

        setUltimateCooldown(6);
    }
}