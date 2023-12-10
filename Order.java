import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Plat> plats;
    private List<Boisson> boissons;
    private int tableNumber;
    private boolean pret;
    private boolean platsPrets = false;
    private boolean boissonsPretes = false;
    private boolean servie;

    public void setPlatsPrets(boolean prêt) {
        this.platsPrets = prêt;
    }

    public void setBoissonsPretes(boolean prêt) {
        this.boissonsPretes = prêt;
    }

    public boolean getPlatsPrets() {
        return platsPrets;
    }

    public boolean getBoissonsPretes() {
        return boissonsPretes;
    }

    public boolean isPret() {
        return platsPrets && boissonsPretes;
    }

    // Méthode pour définir si la commande a été servie
    public void setServie(boolean servie) {
        this.servie = servie;
    }

    // Méthode pour obtenir l'état de la commande (servie ou non)
    public boolean isServie() {
        return servie;
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

    public List<Plat> getPlats() {
        return plats;
    }

    // Marquer la commande comme prête
    public void marquerCommePret() {
        setPret(true);
    }
}
