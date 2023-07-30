import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyManager {
    private final List<Toy> toys = new ArrayList<>();
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

    private Toy getrandomtoy(){
        Random rnd = new Random();
        Toy toytodeploy = null;
        float chance = rnd.nextFloat(weightSum);
        for (Toy toy:toys) {
            if(toy.getChance()<chance)
                chance-= toy.getChance();
            else {
                toy.setCount(toy.getCount()-1);
                toytodeploy = toy;
                if(toy.getCount()==0){
                    toys.remove(toy);
                    weightRecalc();
                    }
                }
        }
        return toytodeploy;
    }
    public void menu(){
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - добавить игрушку");
        System.out.println("2 - Проверить добавленные игрушки");
        System.out.println("3 - Изменить вероятность выпадения игрушки");
        System.out.println("4 - Разыграть игрушку");
        System.out.println("Введите ответ: ");
        int ans = scanner.nextInt();
        switch (ans) {
            case 1 -> toysadd();
            case 2 -> checktoys();
            case 3 -> changechance();
            case 4 -> toydraw();
        }
    }

    private void changechance(){
        if(toys.size()==0){
            System.out.println("Нет игрушек в списке розыгрышей, пожалуйста, добавьте несколько игрушек");
            return;
        }
        int count = 0;
        System.out.println("Какой игрушке изменить шанс выпадения?: ");
        for (Toy toy :
                toys) {
            System.out.printf("%d - %s \n",(count+1),toy.getName());
        }
        int ans = scanner.nextInt();
        System.out.println("Введите новый шанс: ");
        toys.get(ans - 1).setChance(scanner.nextFloat());
        menu();
    }
    private void checktoys(){
        if(toys.size()==0){
            System.out.println("Нет игрушек в списке розыгрышей, пожалуйста, добавьте несколько игрушек");
            return;
        }
        for (Toy toy :
                toys) {
            System.out.printf("Имя игрушки: %s \n Количество: %d \n Шанс выпадения %.2f \n",toy.getName(),toy.getCount(),toy.getChance());
        }
        menu();
    }
    private void toysadd() {
            toyadd();
            weightRecalc();
            menu();

    }

    public void toydraw(){
        Toy draw = getrandomtoy();
        System.out.printf("Вы выиграли %s \n Шанс выиграша составлял : %.2f\n",draw.getName(),draw.getChance());
        menu();
    }
}
