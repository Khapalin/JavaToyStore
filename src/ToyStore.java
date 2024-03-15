import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                break;
            }
        }
    }

    public void playToys() {
        for (Toy toy : toys) {
            int numPrizes = (int) ((toy.getWeight() / 100) * toy.getQuantity());
            for (int i = 0; i < numPrizes; i++) {
                prizeToys.add(toy);
            }
        }

        if (prizeToys.isEmpty()) {
            System.out.println("Нет игрушек, с которыми можно было бы играть.");
            return;
        }

        Random random = new Random();
        Toy prizeToy = prizeToys.remove(random.nextInt(prizeToys.size()));
        prizeToy.decreaseQuantity();

        try {
            FileWriter writer = new FileWriter("prize_toys.txt", true);
            writer.write(prizeToy.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Призовая игрушка: " + prizeToy.toString());
    }

    public void showAvailableToys() {
        if (toys.isEmpty()) {
            System.out.println("Призовая игрушка отсутствует.");
            return;
        }

        for (Toy toy : toys) {
            System.out.println(toy.toString());
        }
    }
}