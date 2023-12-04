import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;

public class App {
    public static void main(String[] args) {

        Restaurant SummerEat = new Restaurant();
        SummerEat.chargerEmployes();

        // Initialisation des attributs de Restaurant
        // SummerEat.setEmployees(new ArrayList<Employee>()); renitialise les employées
        // si pas commenté
        // sinon
        SummerEat.setTables(new ArrayList<Table>());
        SummerEat.setStock(new Stock()); // Assurez-vous que Stock est correctement défini
        SummerEat.setOrders(new ArrayList<Order>());
        SummerEat.setClean(true);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuer = true;
            while (continuer) {
                System.out.println("Quel écran souhaitez vous afficher?");
                System.out.println("1- Ecran prise de commande");
                System.out.println("2- Ecran cuisine");
                System.out.println("3- Ecran bar");
                System.out.println("4- Ecran Monitoring");
                System.out.println("5- Assigner une table");
                System.out.println("6- Quitter");
                int choixEcran = lireChoix(scanner);

                switch (choixEcran) {
                    case 1:
                        gererPriseCommande(SummerEat, scanner);
                        break;
                    case 2:
                        // Logique pour l'écran de cuisine
                        System.out.println("Affichage de l'écran de cuisine");
                        ecranCuisine(SummerEat, scanner);
                        break;
                    case 3:
                        // Logique pour l'écran de bar
                        System.out.println("Affichage de l'écran de bar");
                        ecranBar(SummerEat, scanner);
                        break;
                    case 4:
                        // Logique pour l'écran de monitoring
                        gererEcranMonitoring(SummerEat, scanner);
                        // scanner.close();
                        break;
                    case 5:
                        Table.assignerTable(SummerEat, scanner);
                        break;
                    case 6:
                        System.out.println("Fermeture du programme.");
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "Une erreur est survenue lors de la lecture de votre choix. Assurez-vous d'entrer un nombre.");
        }

    }

    private static void ecranCuisine(Restaurant restaurant, Scanner scanner) {
        System.out.println("Ecran Cuisine - Commandes de plats en cours :");
        List<Order> commandes = restaurant.getOrders().stream()
                .filter(c -> !c.isPret() && !c.getPlats().isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < commandes.size(); i++) {
            System.out.println((i + 1) + " - Commande pour la table " + commandes.get(i).getTableNumber());
        }

        System.out.println("Choisissez une commande à préparer (entrez le numéro) :");
        int choixCommande = lireChoix(scanner) - 1;

        if (choixCommande >= 0 && choixCommande < commandes.size()) {
            Order commande = commandes.get(choixCommande);

            Cuisinier cuisinier = selectCuisinier(restaurant, scanner); // Sélectionner un cuisinier
            if (cuisinier != null) {
                cuisinier.preparePlats(commande); // Appeler preparePlats sur l'instance de Cuisinier
                commande.setPret(true); // Optionnel: Marquer la commande comme prête
            }
        } else {
            System.out.println("Choix de commande invalide.");
        }
    }

    private static Cuisinier selectCuisinier(Restaurant restaurant, Scanner scanner) {
        List<Cuisinier> cuisiniers = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Cuisinier)
                .map(e -> (Cuisinier) e)
                .collect(Collectors.toList());

        if (cuisiniers.isEmpty()) {
            System.out.println("Aucun cuisinier disponible.");
            return null;
        }

        System.out.println("Choisissez un cuisinier :");
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

    private static void ecranBar(Restaurant restaurant, Scanner scanner) {
        System.out.println("Ecran Bar - Commandes de boissons en cours :");
        List<Order> commandes = restaurant.getOrders().stream()
                .filter(c -> !c.isPret() && !c.getBoissons().isEmpty())
                .collect(Collectors.toList());

        for (int i = 0; i < commandes.size(); i++) {
            System.out.println((i + 1) + " - Commande pour la table " + commandes.get(i).getTableNumber());
        }

        System.out.println("Choisissez une commande à préparer (entrez le numéro) :");
        int choixCommande = lireChoix(scanner) - 1;

        if (choixCommande >= 0 && choixCommande < commandes.size()) {
            Order commande = commandes.get(choixCommande);
            // Vous pouvez ajouter ici une sélection de barman si nécessaire
            Barman barman = selectBarman(restaurant, scanner); // Méthode à implémenter
            barman.prepareBoissons(commande); // Logique de préparation des boissons
            commande.setPret(true); // Marquer la commande comme prête
        } else {
            System.out.println("Choix de commande invalide.");
        }
    }

    private static Barman selectBarman(Restaurant restaurant, Scanner scanner) {
        List<Barman> barmans = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Barman)
                .map(e -> (Barman) e)
                .collect(Collectors.toList());

        if (barmans.isEmpty()) {
            System.out.println("Aucun barman disponible.");
            return null;
        }

        System.out.println("Choisissez un barman :");
        for (int i = 0; i < barmans.size(); i++) {
            System.out.println((i + 1) + " - " + barmans.get(i).getName());
        }

        int choix = lireChoix(scanner);
        if (choix < 1 || choix > barmans.size()) {
            System.out.println("Choix invalide.");
            return null;
        }

        return barmans.get(choix - 1);
    }

    private static void gererPriseCommande(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("Ecran Prise de commande");
            System.out.println("1- Afficher le menu");
            System.out.println("2- Sélectionner un serveur pour prendre commande");
            System.out.println("3- ");
            System.out.println("4- Revenir au menu principal");

            int choixCommande = lireChoix(scanner);

            switch (choixCommande) {
                case 1:
                    // Créer un menu à partir de MenuData
                    Menu menu = defMenu.createMenu();
                    // Afficher le menu
                    menu.afficherMenu();
                    break;
                case 2:
                    selectionnerServeurEtPrendreCommande(restaurant, scanner);
                    break;
                case 3:
                    // Autres options si nécessaire
                    break;
                case 4:
                    continuer = false; // Sort de la boucle, retour au menu principal
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
            }
        }
    }

    private static void selectionnerServeurEtPrendreCommande(Restaurant restaurant, Scanner scanner) {
        // Affichage et sélection des serveurs
        System.out.println("Sélectionnez un serveur :");
        List<Serveur> serveurs = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Serveur)
                .map(e -> (Serveur) e)
                .collect(Collectors.toList());

        if (serveurs.isEmpty()) {
            System.out.println("Aucun serveur disponible.");
            return;
        }

        for (int i = 0; i < serveurs.size(); i++) {
            System.out.println((i + 1) + " - " + serveurs.get(i).getName());
        }

        int choixServeur = lireChoix(scanner);
        if (choixServeur < 1 || choixServeur > serveurs.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Serveur serveurChoisi = serveurs.get(choixServeur - 1);
        System.out.println("Serveur choisi : " + serveurChoisi.getName());
        System.out.println("Tables assignées : " + serveurChoisi.getTablesAssignees());

        // Choix de la table
        System.out.println("Choisissez une table pour prendre la commande:");
        int choixTable = lireChoix(scanner);

        // Vérifier si la table choisie est dans la liste des tables assignées au
        // serveur
        if (!serveurChoisi.getTablesAssignees().contains(choixTable)) {
            System.out.println("Cette table n'est pas assignée au serveur sélectionné.");
            return;
        }

        // Afficher le menu
        Menu menu = defMenu.createMenu();
        menu.afficherMenu();

        // Créer une nouvelle commande
        Order commande = new Order(choixTable);

        // Boucle pour ajouter des plats et des boissons à la commande
        boolean prendreCommande = true;
        while (prendreCommande) {
            System.out.println("Ajouter à la commande:");
            System.out.println("1 - Plat");
            System.out.println("2 - Boisson");
            System.out.println("3 - Terminer la commande");
            int choixCommande = lireChoix(scanner);

            switch (choixCommande) {
                case 1:
                    // Afficher la liste des plats
                    System.out.println("Liste des plats disponibles :");
                    for (int i = 0; i < menu.getPlats().size(); i++) {
                        Plat plat = menu.getPlats().get(i);
                        System.out.println((i + 1) + " - " + plat.getName() + " : " + plat.getPrix() + " euros");
                    }

                    System.out.println("Choisissez un plat à ajouter à la commande (entrez le numéro) :");
                    int choixPlat = lireChoix(scanner);

                    if (choixPlat < 1 || choixPlat > menu.getPlats().size()) {
                        System.out.println("Choix de plat non valide.");
                    } else {
                        Plat platChoisi = menu.getPlats().get(choixPlat - 1);
                        commande.addPlat(platChoisi);
                        System.out.println(platChoisi.getName() + " ajouté à la commande.");
                    }
                    break;

                case 2:
                    // Afficher la liste des boissons
                    System.out.println("Liste des boissons disponibles :");
                    for (int i = 0; i < menu.getBoissons().size(); i++) {
                        Boisson boisson = menu.getBoissons().get(i);
                        System.out.println((i + 1) + " - " + boisson.getNom() + " : " + boisson.getPrix() + " euros");
                    }

                    System.out.println("Choisissez une boisson à ajouter à la commande (entrez le numéro) :");
                    int choixBoisson = lireChoix(scanner);

                    if (choixBoisson < 1 || choixBoisson > menu.getBoissons().size()) {
                        System.out.println("Choix de boisson non valide.");
                    } else {
                        Boisson boissonChoisie = menu.getBoissons().get(choixBoisson - 1);
                        commande.addBoisson(boissonChoisie);
                        System.out.println(boissonChoisie.getNom() + " ajoutée à la commande.");
                    }
                    break;
                case 3:
                    prendreCommande = false;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 3.");
            }
        }

        // Ajouter la commande à la liste des commandes du restaurant
        restaurant.getOrders().add(commande);
        System.out.println("Commande enregistrée pour la table " + choixTable + ".");
        commande.afficherCommande();
    }

    private static void gererEcranMonitoring(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("Ecran Monitoring");
            System.out.println("1- Gérer les employés du jour");
            System.out.println("2- Ajouter un employé");
            System.out.println("3- Supprimer un employé");
            System.out.println("4- Ajouter une table");
            System.out.println("5- Supprimer une table");
            System.out.println("6- Retour au menu principal");

            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Consomme l'entrée non valide
                continue; // Continue la boucle pour demander de nouveau l'entrée
            }

            int choixMonitoring = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer

            switch (choixMonitoring) {
                case 1:
                    Employee.gererEmployes(restaurant, scanner);
                    break;
                case 2:
                    Employee.ajouterEmploye(restaurant, scanner);
                    break;
                case 3:
                    Employee.supprimerEmploye(restaurant, scanner);
                    break;
                case 4:
                    Table.ajouterTable(restaurant, scanner);
                    break;
                case 5:
                    Table.supprimerTable(restaurant, scanner);
                    break;
                case 6:
                    continuer = false; // Sort de la boucle, retour au menu principal
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
            }
        }
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