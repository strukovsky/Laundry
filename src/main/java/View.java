import clothes.Cloth;
import clothes.ClothColor;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(View.class);
    private static View instance;

    public static View getInstance() {
        if(instance == null)
            instance = new View();
        return instance;
    }

    private View(){}
    private ClothColor parseClothColor(String color)
    {
        if(color.equals("L"))
            return ClothColor.Light;
        if(color.equals("D"))
            return ClothColor.Dark;
        return ClothColor.Colored;
    }
    private int parseTemperature(String temperature)
    {
        try {
            return Integer.parseInt(temperature);
        }
        catch (Exception e)
        {
            System.out.println("Error parsing temperature " + temperature);
        }return 0;
    }
    public Cloth readCloth()
    {
        System.out.println("Enter info about cloth as follows separated by spaces");
        System.out.println("temperature colour of cloth");
        System.out.println("Color can have following values L - light D - dark C - coloured");
        String entered = scanner.nextLine();
        String[] arr = entered.split(" ");
        if(arr.length != 2)
        {
            logger.error("Inserted incorrect string of length " + arr.length);
            System.err.println("Error in the string");
            return null;
        }
        else
        {
            int temperature = parseTemperature(arr[0]);
            ClothColor color = parseClothColor(arr[1]);
            if(temperature == 0 || color == null) {
                logger.error("There is an error in following inserted data "+entered);
                return null;
            }
            return new Cloth(temperature, color);

        }
    }

    public void showMachines(Controller controller)
    {
        for(WashingMachine machine: controller.getMachines())
        {
            ClothColor color = machine.getFirstClothColor();
            int temperature = machine.getFirstClothTemperature();
            if(color == null || temperature == 0)
                continue;
            System.out.println(machine.id + ": "+ color + " " + temperature + " ˚C");
        }
    }

    public void writeCloth(Cloth washed) {
        System.out.println(washed + " is ready");
    }
}
