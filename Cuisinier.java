import java.util.List;

class Cuisinier extends Employee {

    public void preparePlats(Order commande) {
        if (commande == null || commande.getPlats().isEmpty()) {
            System.out.println("Aucun plat à préparer pour cette commande.");
            return;
        }

        System.out.println("Préparation des plats pour la commande de la table " + commande.getTableNumber() + "...");
        for (Plat plat : commande.getPlats()) {
            // Ici, vous pouvez ajouter une logique de préparation, par exemple un délai
            // simulé
            System.out.println("Préparation du plat : " + plat.getName());
            // Simuler un délai (facultatif)
            try {
                Thread.sleep(1000); // 1 seconde pour la simulation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interruption lors de la préparation du plat.");
            }
        }

        System.out.println(
                "Tous les plats pour la commande de la table " + commande.getTableNumber() + " ont été préparés.");
        // Marquer seulement les plats de la commande comme prêts
        commande.setPlatsPrets(true);

        // Vérifier si la commande est prête à être servie
        if (commande.getBoissons().isEmpty() || commande.getBoissonsPretes()) {
            commande.setPret(true);
        }
    }
}
