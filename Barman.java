import java.util.List;

public class Barman extends Employee {

    // Constructeur
    public Barman(String nom, String prenom, int age) {
        super();
    }

    // Méthode pour préparer les boissons d'une commande
    public void prepareBoissons(Order order) {
        List<Boisson> boissonsApreparer = order.getBoissons();

        if (boissonsApreparer.isEmpty()) {
            System.out.println("Aucune boisson à préparer pour cette commande.");
            return;
        }

        System.out.println("Préparation des boissons pour la table " + order.getTableNumber() + "...");
        for (Boisson boisson : boissonsApreparer) {
            // Logique spécifique pour la préparation de chaque boisson
            System.out.println("Préparation de " + boisson.getNom());
        }

        System.out.println("Les boissons pour la table " + order.getTableNumber() + " sont prêtes.");
    }
}
