import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Plat> plats;
    private List<Boisson> boissons;
    private int tableNumber;
    private boolean pret;


    public static void prendreCommande(){
        
    }

    // Constructeur
    public Order(int tableNumber) {
        this.plats = new ArrayList<>();
        this.boissons = new ArrayList<>();
        this.tableNumber = tableNumber;
        this.pret = false;
    }

    // Ajouter un plat
    public void addPlat(Plat plat) {
        this.plats.add(plat);
    }

    // Ajouter une boisson
    public void addBoisson(Boisson boisson) {
        this.boissons.add(boisson);
    }

    // Getters et Setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isPret() {
        return pret;
    }

    public void setPret(boolean pret) {
        this.pret = pret;
    }

    // Calculer le total de la commande
    public double getTotal() {
        double total = 0;
        for (Plat plat : plats) {
            total += plat.prix;
        }
        for (Boisson boisson : boissons) {
            total += boisson.prix;
        }
        return total;
    }

    // Afficher la commande
    public void afficherCommande() {
        System.out.println("Commande pour la table: " + tableNumber);
        for (Plat plat : plats) {
            System.out.println(plat);
        }
        for (Boisson boisson : boissons) {
            System.out.println(boisson);
        }
        System.out.println("Total: " + getTotal() + " €");
    }

    public List<Boisson> getBoissons() {
        return boissons;
    }
    // Marquer la commande comme prête
    public void marquerCommePret() {
        setPret(true);
    }
}

