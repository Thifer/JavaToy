import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyManager {
    private List<Toy> toys = new ArrayList<>();
    private int weightSum;
    Scanner scanner = new Scanner(System.in);

    private void toyadd(){
        int toyId = toys.size();
        System.out.print("Введите название игрушки: ");
        String toyName = scanner.next();
        System.out.print("Введите количество игрушек: ");
        int toyCount = scanner.nextInt();
        System.out.print("Введите вес(щанс выпадения) игрушки: ");
        float toyChance = scanner.nextFloat();
        toys.add(new Toy(toyId,toyName,toyCount,toyChance));

    }

    private void weightRecalc(){
        weightSum = 0;
        for (Toy toy: toys) {
            if(toy.getCount()>0)
                weightSum+=toy.getChance();
        }
    }

    private void getrandomtoy(){
        Random rnd = new Random();
        float chance = rnd.nextFloat(weightSum);
        for (Toy toy:toys) {
            if(toy.getChance()<chance)
                chance-= toy.getChance();
            else {
                toy.setCount(toy.getCount()-1);
                if(toy.getCount()==0)
                    weightRecalc();
                return;}
        }
    }
    public void menu(){
        System.out.print("Пожалуйста, введите сколько видов игрушек будет добавлено игрушек: ");
        int toycount = scanner.nextInt();
        System.out.println();
        for (int i = 0; i < toycount; i++) {
            toyadd();
            weightRecalc();
        }
    }

    public void test(){
        System.out.print("Введите количество попыток выпадения: ");
        int testcount = scanner.nextInt();
        for (int i = 0; i < testcount; i++) {
            getrandomtoy();
        }
        for (Toy toy :
                toys) {
            System.out.println(toy.getName()+" "+toy.getCount());
        }
    }
}
