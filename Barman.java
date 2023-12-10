import java.util.List;

public class Barman extends Employee {

    // Méthode pour préparer les boissons d'une commande
    public void prepareBoissons(Order commande) {
        if (commande == null || commande.getBoissons().isEmpty()) {
            System.out.println("Aucune boisson à préparer pour cette commande.");
            return;
        }

        System.out
                .println("Préparation des boissons pour la commande de la table " + commande.getTableNumber() + "...");
        for (Boisson boisson : commande.getBoissons()) {
            // simulé
            System.out.println("Préparation de la boisson : " + boisson.getNom());
            // Simuler un délai (facultatif)
            try {
                Thread.sleep(500); // 0.5 seconde pour la simulation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interruption lors de la préparation de la boisson.");
            }
        }

        System.out.println("Toutes les boissons pour la commande de la table " + commande.getTableNumber()
                + " ont été préparées.");
        // Marquer seulement les boissons de la commande comme prêtes
        commande.setBoissonsPretes(true);

        // Vérifier si la commande est prête à être servie
        if (commande.getPlats().isEmpty() || commande.getPlatsPrets()) {
            commande.setPret(true);
        }
    }
}
