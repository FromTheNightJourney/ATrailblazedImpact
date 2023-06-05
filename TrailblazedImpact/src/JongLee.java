public class JongLee extends CharacterTemp {
    private int skillCooldown;
    private int ultimateCooldown;

    public JongLee() {
        super("Tendo BanBro", 190, 500, 11000, 11000, 90, 0.9, 0.5, "resources/john.png",3 , 8);
        this.skillCooldown = 0;
        this.ultimateCooldown = 0;
    }

    public void skill(CharacterTemp target) {
        if (skillCooldown > 0) {
            System.out.println(getName() + "'s skill is on cooldown. Turns remaining: " + skillCooldown);
            return;
        }

        // Perform the skill action
        int hpRecovery = 2000;
        int maxHp = 11000;
        int currentHp = getHp();
        int newHp = Math.min(currentHp + hpRecovery, maxHp);
        setHp(newHp);

        System.out.println(getName() + " has recovered HP.\n");

        setSkillCooldown(2);
    }

    public void ultimate(CharacterTemp target) {
        if (ultimateCooldown > 0) {
            System.out.println(getName() + "'s ultimate is on cooldown. Turns remaining: " + ultimateCooldown + "\n");
            return;
        }

        // Perform the ultimate action
        int ultimateDamage = 11000;

        System.out.println(getName() + " used ultimate: Tendo Bansho!");
        System.out.println("Deals " + ultimateDamage + " damage to " + target.getName() + "\n");
        target.takeDamage(ultimateDamage);

        setUltimateCooldown(3);
    }
}
