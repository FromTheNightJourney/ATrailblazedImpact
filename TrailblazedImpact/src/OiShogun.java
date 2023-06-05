public class OiShogun extends CharacterTemp {
    private int skillDuration; // Number of turns the skill lasts

    public OiShogun() {
        super("OiShogun", 204, 712, 10746, 10746, 102, 0.45, 0.6, "resources/rairai.png", 5, 7);
        this.skillDuration = 0; // Skill starts with zero duration
    }

    public void skill(CharacterTemp target) {
        if (getSkillCooldown() > 0) { // Check if the skill is on cooldown
            System.out.println(getName() + "'s skill is on cooldown. Turns remaining: " + getSkillCooldown() + "\n");
            return; // Do not perform the skill action if the skill is on cooldown
        }

        // Check if the skill is already active
        //skill CD is 5 turns
        if (skillDuration > 0) {
            System.out.println(getName() + "'s skill is already active!");
            // You can add additional logic here, such as displaying a message or applying different effects if the skill is already active
        } else {
            // Boosts attack by 20% for 2 turns
            System.out.println(getName() + " uses skill: Attack Boost!" + "\n");

            // Apply the attack boost logic here
            double attackBoost = getAttack() * 0.2; // Calculate the attack boost (20% of current attack)
            setAttack(getAttack() + (int) attackBoost); // Increase the attack attribute by the boost amount

            // Set the skill duration to 2 turns
            skillDuration = 2;

            setSkillCooldown(5);
        }

    }

    public void ultimate(CharacterTemp target) {
        if (getUltimateCooldown() > 0) { // Check if the ultimate is on cooldown
            System.out.println(getName() + "'s ultimate is on cooldown. Turns remaining: " + getUltimateCooldown() + "\n");
            return; // Do not perform the ultimate action if the ultimate is on cooldown
        }

        // Calls upon the might of the sky
        // Boosts attack by 50%
        // Skill CD is 7 turns
        System.out.println(getName() + " uses ultimate: Thunder's Might!");


        // Apply the attack boost logic here
        double attackBoost = getAttack() * 0.5; // Calculate the attack boost (50% of current attack)
        setAttack(getAttack() + (int) attackBoost); // Increase the attack attribute by the boost amount

        int ultimate_damage = 3000;
        int additional_damage = getAttack() * 10;
        int total = ultimate_damage + additional_damage;
        target.takeDamage(total);
        System.out.println("It dealt " + total + " damage!\n");

        setUltimateCooldown(7);

    }
}


