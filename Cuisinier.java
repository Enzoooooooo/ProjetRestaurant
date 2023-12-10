import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static void ecranCuisine(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;
        while (continuer) {
            App.printHeader("Ecran Cuisine - Commandes de plats en cours :");
            App.printOption("Préparer les plats d'une commande", 1);
            App.printOption("Retour", 2);
            System.out.println();

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    // Afficher les commandes en attente de préparation des plats
                    List<Order> commandes = restaurant.getOrders().stream()
                            .filter(c -> !c.isPret() && !c.getPlats().isEmpty())
                            .collect(Collectors.toList());
                    if (commandes.isEmpty()) {
                        System.out
                                .println("Il n'y a actuellement aucune commande en attente de préparation des plats.");
                    } else {
                        System.out.println("Commandes en attente de préparation des plats:");
                        for (Order commande : commandes) {
                            // Vérifier que la commande n'est pas servie et que les plats ne sont pas encore
                            // prêts
                            if (!commande.isServie() && !commande.getPlatsPrets()) {
                                System.out.println("Commande Numéro: " + commande.getTableNumber());// Affiche le numéro
                                                                                                    // de la commande
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
                            Cuisinier cuisinier = selectCuisinier(restaurant, scanner);
                            cuisinier.preparePlats(commandeApreparer);

                            // Marquer les plats comme prêts
                            commandeApreparer.setPlatsPrets(true);

                            // Vérifier si la commande est entièrement prête
                            if (commandeApreparer.getBoissons().isEmpty() || commandeApreparer.getBoissonsPretes()) {
                                commandeApreparer.setBoissonsPretes(true);
                                commandeApreparer.setPret(true);
                                System.out.println("La commande pour la table " + numCommande
                                        + " est maintenant prête à être servie.");
                                // Ajouter ici la logique pour marquer la commande comme servie ou la déplacer
                                // vers la file des commandes prêtes
                            } else {
                                System.out.println("Les plats de la commande pour la table " + numCommande
                                        + " sont prêts. En attente de préparation des boissons.");
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

    // Permet de selectionner le cuisinier que l'on veut
    private static Cuisinier selectCuisinier(Restaurant restaurant, Scanner scanner) {
        List<Cuisinier> cuisiniers = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Cuisinier)
                .map(e -> (Cuisinier) e)
                .collect(Collectors.toList());

        if (cuisiniers.isEmpty()) {
            System.out.println("Aucun cuisinier disponible.");
            return null;
        }
        App.printHeader("Choisissez un cuisinier :");
        for (int i = 0; i < cuisiniers.size(); i++) {
            System.out.println((i + 1) + " - " + cuisiniers.get(i).getName());
        }

        int choix = lireChoix(scanner);
        if (choix < 1 || choix > cuisiniers.size()) {
            System.out.println("Choix invalide.");
            return null;
        }

        return cuisiniers.get(choix - 1);
    }

    public static int lireChoix(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next(); // Consomme l'entrée non valide
        }
        int choix = scanner.nextInt();
        scanner.nextLine(); // Nettoie le buffer après la lecture d'un int
        return choix;
    }

}
