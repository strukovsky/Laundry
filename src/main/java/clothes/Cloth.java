package clothes;

public class Cloth {
    private int temperature;
    ClothColor color;
    private boolean dirty = true;

    public void setColor(ClothColor color) {
        this.color = color;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public ClothColor getColor() {
        return color;
    }

    public int getTemperature() {
        return temperature;
    }

    public Cloth(int temp, ClothColor color)
    {
        temperature = temp;
        this.color = color;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public String toString() {
        return color + " cloth of temperature " + temperature + " ˚C";
    }
}
