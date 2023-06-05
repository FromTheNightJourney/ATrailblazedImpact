public class Cocolia extends CharacterTemp {
    public Cocolia() {
        super("Cocolia", 250, 800, 30000, 30000,80, 0.8, 0.4,"resources/cocowia.png",0, 0);
    }

    public void chillOfBonePiercingCoagulation(CharacterTemp target) {
        int damage = (int) (getAttack() * 3.0);
        target.takeDamage(damage);
        System.out.println(getName() + " uses Chill of Bone-Piercing Coagulation on " + target.getName() + " dealing " + damage + " damage.\n");
    }

    public void hoarfrostOfEternalIsolation(CharacterTemp target) {
        int damage = (int) (getAttack() * 1.5);
        target.takeDamage(damage);
        System.out.println(getName() + " uses Hoarfrost of Eternal Isolation on " + target.getName() + " dealing " + damage + " damage.\n");
    }

    public void wrathOfWinterlandSaints(CharacterTemp target) {
        // Enters the Charge state

        // Casts "Punishment of Endless Winter" on the target
        punishmentOfEndlessWinter(target);
    }

    public void punishmentOfEndlessWinter(CharacterTemp target) {
        int damage = (int) (getAttack() * 5.0);
        target.takeDamage(damage);
        System.out.println(getName() + " uses Punishment of Endless Winter on " + target.getName() + " dealing " + damage + " damage.\n");
    }
}
