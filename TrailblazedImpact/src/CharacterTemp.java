import java.util.Random;
import javafx.scene.image.Image;

public class CharacterTemp {
    private String name;
    private int attack;
    private int defense;
    private int hp;
    private int maxHP;
    private int speed;
    private double critRate;
    private double critDamage;
    private String imagePath;
    private boolean playerControlled;
    private int skillCooldown; // Instance variable to keep track of the skill cooldown
    private int ultimateCooldown; // Instance variable to keep track of the ultimate cooldown

    public CharacterTemp(String name, int attack, int defense, int hp, int maxHP, int speed, double critRate, double critDamage, String imagePath,
                         int skillCooldown, int ultimateCooldown) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.maxHP = maxHP;
        this.speed = speed;
        this.critRate = critRate;
        this.critDamage = critDamage;
        this.imagePath = imagePath;
        this.playerControlled = false;
        this.skillCooldown = 0; // Initialize the skill cooldown to zero
        this.ultimateCooldown = 0; // Initialize the ultimate cooldown to zero
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    public void setPlayerControlled(boolean playerControlled) {
        this.playerControlled = playerControlled;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHP() {return maxHP;}

    public void setHp(int newHealth){
        this.hp = newHealth;
    }

    public double getCritRate() {
        return critRate;
    }

    public void setCritRate(double critRate) {
        this.critRate = critRate;
    }

    public double getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getSkillCooldown(){
        return skillCooldown;
    }

    // Updated method to take an integer argument representing the number of turns for the cooldown
    // and use it to set the value of the skillCooldown instance variable
    public void setSkillCooldown(int turns){
        this.skillCooldown = turns;
    }

    public int getUltimateCooldown(){
        return ultimateCooldown;
    }

    // Updated method to take an integer argument representing the number of turns for the cooldown
    // and use it to set the value of the ultimateCooldown instance variable
    public void setUltimateCooldown(int turns){
        this.ultimateCooldown = turns;
    }

    public void attack(CharacterTemp target) {
        executeAttack(target);
    }

    public void skill(CharacterTemp target) {
        if (skillCooldown > 0) { // Check if the skill is on cooldown
            System.out.println(getName() + "'s skill is on cooldown. Turns remaining: " + skillCooldown);
            return; // Do not perform the skill action if the skill is on cooldown
        }
        executeAttack(target);
        skillCooldown = 3; // Set the skill cooldown to 3 turns after performing the skill action
    }

    public void ultimate(CharacterTemp target) {
        if (ultimateCooldown > 0) { // Check if the ultimate is on cooldown
            System.out.println(getName() + "'s ultimate is on cooldown. Turns remaining: " + ultimateCooldown);
            return; // Do not perform the ultimate action if the ultimate is on cooldown
        }
        executeAttack(target);
        ultimateCooldown = 5; // Set the ultimate cooldown to 5 turns after performing the ultimate action
    }

    private boolean critCalc(){
        // getting character stats
        double critRate = getCritRate();

        // calculate total damage dealt
        Random random = new Random();

        return random.nextDouble() < critRate;
    }

    public void executeAttack(CharacterTemp target){

        // getting character stats
        int baseDMG = getAttack();
        double critMult = getCritDamage();
        boolean isCrit = critCalc();

        if (isCrit){
            System.out.println(getName() + " did a critical hit!");
        } else {
            System.out.println(getName() + " attacked " + target.getName());
        }

        int damage;

        // crit determination
        if (isCrit) {
            damage = (int) (baseDMG * (1 + critMult));
            System.out.println(damage + " CRIT!");
        } else {
            damage = baseDMG;

            System.out.println(getName() + " dealt " + damage);
        }


        int dealtDamage = getAttack();
        int currentHealth = target.getHp();
        int newHealth = currentHealth - dealtDamage;
        System.out.println("It dealt " + dealtDamage + "!\n");
        target.setHp(newHealth);
    }

    public void takeDamage(int damage) {
        int currentHealth = getHp();
        int newHealth = currentHealth - damage;
        setHp(newHealth);
    }

    public boolean isDefeated(){
        return hp <= 0;
    }

    public void reduceCooldowns() {
        // Reduce the skill cooldown by 1 if it's greater than 0
        if (skillCooldown > 0) {
            skillCooldown--;
        }

        // Reduce the ultimate cooldown by 1 if it's greater than 0
        if (ultimateCooldown > 0) {
            ultimateCooldown--;
        }
    }
}
