import clothes.Cloth;
import clothes.ClothColor;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Controller {
    private final int countOfMachines = 3;
    private final ArrayList<WashingMachine> machines = new ArrayList<>();
    private final ArrayDeque<Cloth> queue = new ArrayDeque<>();
    private static final Logger logger = Logger.getLogger(Controller.class);

    public ArrayList<WashingMachine> getMachines() {
        return machines;
    }

    public Controller() {

        for (int i = 0; i < countOfMachines; i++) {
            machines.add(new WashingMachine(i+1));
        }
    }

    public int getCountOfMachines() {
        return countOfMachines;
    }

    private WashingMachine findSuitableWashing(Cloth cloth) {
        ClothColor requiredColor = cloth.getColor();
        int requiredTemperature = cloth.getTemperature();
        for (WashingMachine machine : machines) {
            ClothColor color = machine.getFirstClothColor();
            int temperature = machine.getFirstClothTemperature();
            if (color == null && temperature == 0) // if machine is empty then give out this machine
                return machine;
            if (requiredColor.equals(color))
                if (Math.abs(requiredTemperature - temperature) <= 2) // if difference between degrees of required and
                    // actual then return this machine
                    return machine;
        }
        return null;
    }

    private void receiveClothes(Cloth cloth) {
        WashingMachine machine = findSuitableWashing(cloth);
        if (machine != null) {
            Cloth washed = machine.wash(cloth);
            logger.info("Send to machine " + cloth);
            if (washed != null) {
                View.getInstance().writeCloth(washed);
                logger.info("Send to user " + washed);
            }
        } else {
            queue.add(cloth);
        }
        View.getInstance().showMachines(this);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        while (true) {
            Cloth c = View.getInstance().readCloth();
            if (c != null)
                controller.receiveClothes(c);
        }
    }


}
