public class Toy {
    private int id;
    private String name;
    private int count;
    private float chance;

    public Toy(int id, String name, int count, float chance) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.chance = chance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public float getChance() {
        return chance;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
