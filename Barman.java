import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

            System.out.println("Préparation de la boisson : " + boisson.getNom());
            // Simuler un délai
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

    // Ecran permettant de preparer les boissons
    public static void ecranBar(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;
        while (continuer) {
            App.printHeader("Écran Bar - Sélectionnez une action:");
            App.printOption("Préparer les boissons d'une commande", 1);
            App.printOption("Retour", 2);

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    // Afficher les commandes en attente de préparation des boissons
                    List<Order> commandes = restaurant.getOrders().stream()
                            .filter(c -> !c.isPret() && !c.getBoissons().isEmpty())
                            .collect(Collectors.toList());

                    if (commandes.isEmpty()) {
                        System.out.println(
                                "Il n'y a actuellement aucune commande en attente de préparation des boissons.");
                    } else {
                        System.out.println("Commandes en attente de préparation des boissons:");
                        for (Order commande : commandes) {
                            // Vérifier que la commande n'est pas servie et que les boissons ne sont pas
                            // encore prêtes
                            if (!commande.isServie() && !commande.getBoissonsPretes()) {
                                System.out.println("Commande Numéro: " + commande.getTableNumber());
                            }
                        }
                        System.out.println("Entrez le numéro de la commande à préparer:");
                        int numCommande = scanner.nextInt();

                        Order commandeApreparer = null;
                        for (Order c : commandes) {
                            if (c.getTableNumber() == numCommande) {
                                commandeApreparer = c;
                                break;
                            }
                        }

                        if (commandeApreparer != null) {
                            Barman barman = selectBarman(restaurant, scanner);
                            barman.prepareBoissons(commandeApreparer);

                            // Marquer les boissons comme prêtes
                            commandeApreparer.setBoissonsPretes(true);

                            // Vérifier si les plats sont déjà prêts
                            if (commandeApreparer.getPlats().isEmpty() || commandeApreparer.getPlatsPrets()) {
                                commandeApreparer.setPlatsPrets(true);
                                commandeApreparer.setPret(true);
                                // Logique pour gérer une commande entièrement prête

                                System.out.println("La commande pour la table " + numCommande
                                        + " est maintenant prête à être servie.");
                                // Ajouter ici la logique pour marquer la commande comme servie ou la déplacer
                                // vers la file des commandes prêtes
                            } else {
                                System.out.println("Les boissons de la commande pour la table " + numCommande
                                        + " sont prêtes. En attente de préparation des plats.");
                            }
                        } else {
                            System.out.println("Aucune commande trouvée avec le numéro " + numCommande);
                        }
                    }
                    break;
                case 2:
                    continuer = false;
                    break;

                default:
                    System.out.println("Choix non valide. Veuillez réessayer.");
            }
        }
    }

    // Permet de selectionner le barman que l'on veut
    private static Barman selectBarman(Restaurant restaurant, Scanner scanner) {
        List<Barman> barmans = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Barman)
                .map(e -> (Barman) e)
                .collect(Collectors.toList());

        if (barmans.isEmpty()) {
            System.out.println("Aucun barman disponible.");
            return null;
        }
        App.printHeader("Choisissez un barman :");
        for (int i = 0; i < barmans.size(); i++) {
            System.out.println((i + 1) + " - " + barmans.get(i).getName());
        }

        int choix = App.lireChoix(scanner);
        if (choix < 1 || choix > barmans.size()) {
            System.out.println("Choix invalide.");
            return null;
        }

        return barmans.get(choix - 1);
    }
}
