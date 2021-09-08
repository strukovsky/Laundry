import clothes.ClothColor;
import clothes.Cloth;
import org.apache.log4j.Logger;


import java.util.ArrayDeque;
import java.util.PriorityQueue;

public  class WashingMachine {
    private static final Logger l = Logger.getLogger(WashingMachine.class);
    private String washingPowder;
    private ArrayDeque<Cloth> queue;
    int id;

    public WashingMachine(int i)
    {
        id = i;
        queue = new ArrayDeque<>();
    }


    public String getWashingPowder() {
        return washingPowder;
    }

    public void setWashingPowder(String washingPowder) {
        this.washingPowder = washingPowder;
    }

    public WashingMachine(String s)
    {
        washingPowder = s;
    }

    public ClothColor getFirstClothColor()
    {
        if(queue.peek() == null)
            return null;
        return queue.peek().getColor();
    }

    public int getFirstClothTemperature()
    {
        if(queue.peek() == null)
            return 0;
        return queue.peek().getTemperature();
    }

     Cloth wash(Cloth cloth)
     {
        Cloth washed = queue.poll();
        l.info("This clothes is done "+"in "+id+"th machine: " + washed);
        queue.add(cloth);
        l.info("This clothes are added to washing machine "+id + ": " + cloth);
        return washed;
     }

}
