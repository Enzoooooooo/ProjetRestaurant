import java.util.ArrayList;
import java.util.List;

class Menu {
    private List<Plat> plats;
    private List<Boisson> boissons;

    public Menu() {
        plats = new ArrayList<>();
        boissons = new ArrayList<>();
    }

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }

    public void ajouterBoisson(Boisson boisson) {
        boissons.add(boisson);
    }

    public void afficherMenu() {
        System.out.println("Menu:");
        System.out.println("Plats:");
        for (Plat plat : plats) {
            System.out.println(" - " + plat.getName() + " : " + plat.getPrix() + " euros");
        }
        System.out.println("Boissons:");
        for (Boisson boisson : boissons) {
            System.out.println(" - " + boisson.getNom() + " : " + boisson.getPrix() + " euros ");
        }
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public List<Boisson> getBoissons() {
        return boissons;
    }
}
