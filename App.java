import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        Restaurant SummerEat = new Restaurant();
        SummerEat.chargerEmployes();

        // Initialisation des attributs de Restaurant
        // SummerEat.setEmployees(new ArrayList<Employee>()); renitialise les employées
        // si pas commenté
        // sinon
        SummerEat.setTables(new ArrayList<Table>());
        // Charge mon Stock de stock.txt
        SummerEat.setStock(chargerStock());
        SummerEat.setOrders(new ArrayList<Order>());
        SummerEat.setClean(true);
        try (Scanner scanner = new Scanner(System.in)) {
            if (Employee.gererEmployes(SummerEat, scanner)) {
                Employee.gererEmployes(SummerEat, scanner);
                boolean continuer = true;
                while (continuer) {
                    System.out.println("Quel écran souhaitez vous afficher?");
                    System.out.println("1- Ecran prise de commande");
                    System.out.println("2- Ecran cuisine");
                    System.out.println("3- Ecran bar");
                    System.out.println("4- Ecran Monitoring");
                    System.out.println("5- Assigner une table");
                    System.out.println("6- Quitter");
                    System.out.println("7- Fin de journée");
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
                        case 7:

                            Employee.terminerJournee(SummerEat);
                            continuer = false;
                            break;
                        default:
                            System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
                    }
                }
            } else {
                System.out.println("Le nombre minimum d'employés requis n'a pas été atteint.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
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

                    if (commandes.isEmpty()) {
                        System.out.println(
                                "Il n'y a actuellement aucune commande en attente de préparation des boissons.");
                    } else {
                        System.out.println("Commandes en attente de préparation des boissons:");
                        for (Order commande : commandes) {
                            // Vérifier que la commande n'est pas servie et que les boissons ne sont pas
                            // encore prêtes
                            if (!commande.isServie() && !commande.getBoissonsPretes()) {
                                System.out.println("Commande Numéro: " + commande.getTableNumber() + " | Boissons: "
                                        + commande.getBoissons().toString()); // Affiche le numéro de la commande et les
                                                                              // boissons
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
                            if (commandeApreparer.getPlatsPrets()) {
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
                    List<Order> commandesPretes = restaurant.getOrders().stream()
                            .filter(Order::isPret) // Filtrer pour obtenir seulement les commandes prêtes
                            .collect(Collectors.toList());

                    if (commandesPretes.isEmpty()) {
                        System.out.println("Il n'y a pas de commandes prêtes à être servies.");
                    } else {
                        System.out.println("Commandes prêtes à être servies:");
                        for (Order commande : commandesPretes) {
                            System.out.println("Commande Numéro: " + commande.getTableNumber());
                        }

                        System.out.println("Entrez le numéro de la commande à servir:");
                        int numCommande = scanner.nextInt();

                        // Logique pour trouver la commande spécifique et la marquer comme servie
                        Order commandeAServir = null;
                        for (Order c : commandesPretes) {
                            if (c.getTableNumber() == numCommande) {
                                commandeAServir = c;
                                break;
                            }
                        }

                        if (commandeAServir != null) {
                            commandeAServir.setServie(true); // Marquer la commande comme servie
                            System.out.println("La commande pour la table " + numCommande + " a été servie.");

                            // Supprimer la commande de la liste des commandes prêtes
                            restaurant.getOrders().remove(commandeAServir);

                            // Si nécessaire, mettre à jour la liste des commandes dans l'objet restaurant
                            // Cela dépend de la manière dont votre application gère les commandes
                            // restaurant.setOrders(restaurant.getOrders());

                        } else {
                            System.out.println("Aucune commande trouvée avec le numéro " + numCommande);
                        }

                        System.out.println("Commande servie.");
                    }
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
            if (serveurs.get(i).getIsWorking() == true)
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
                        if (verifierEtDeduireIngredients(restaurant.getStock(), platChoisi)) {
                            commande.addPlat(platChoisi);
                            System.out.println(platChoisi.getName() + " ajouté à la commande.");
                            sauvegarderStock(restaurant.getStock());
                        } else {
                            System.out.println("Ingrédients insuffisants pour ajouter " + platChoisi.getName()
                                    + " à la commande.");
                        }
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
                        Boisson boissonEnStock = restaurant.getStock().getBoissons().stream()
                                .filter(b -> b.getNom().equalsIgnoreCase(boissonChoisie.getNom()))
                                .findFirst()
                                .orElse(null);

                        if (boissonEnStock != null && boissonEnStock.getQuantity() > 0) {
                            boissonEnStock.decreaseQuantity(1); // Supposons que chaque commande utilise une unité de
                                                                // boisson
                            commande.addBoisson(boissonChoisie);
                            System.out.println(boissonChoisie.getNom() + " ajoutée à la commande.");
                            sauvegarderStock(restaurant.getStock()); // Sauvegarder après chaque commande réussie
                        } else {
                            System.out.println(
                                    "Boisson insuffisante pour ajouter " + boissonChoisie.getNom() + " à la commande.");
                        }
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
            System.out.println("6- Gérer le stock");
            System.out.println("7- Retour au menu principal");

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
                    gererStock(restaurant, scanner);
                    break;

                case 7:
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

    // Menu Stock
    private static void gererStock(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;
        while (continuer) {
            System.out.println("Gérer le stock");
            System.out.println("1- Ajouter du stock");
            System.out.println("2- Voir le stock");
            System.out.println("3- Retour");

            int choixStock = lireChoix(scanner);

            switch (choixStock) {
                case 1:
                    ajouterAuStock(restaurant, scanner);
                    break;
                case 2:
                    voirStock(restaurant);
                    break;
                case 3:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 3.");
            }
        }
    }

    // Méthode pour ajouter aux stock
    private static void ajouterAuStock(Restaurant restaurant, Scanner scanner) {
        System.out.println("Voulez-vous ajouter un aliment ou une boisson?");
        System.out.println("1- Aliment");
        System.out.println("2- Boisson");

        int choix = lireChoix(scanner);
        String nom;
        int quantite;

        switch (choix) {
            case 1:
                nom = choisirAliment(scanner);
                if (nom == null)
                    return;
                System.out.println("Entrez la quantité:");
                quantite = lireChoix(scanner);
                // Vérifiez si l'aliment existe déjà
                Aliment alimentExistant = restaurant.getStock().getAliments().stream()
                        .filter(a -> a.getName().equalsIgnoreCase(nom))
                        .findFirst()
                        .orElse(null);
                if (alimentExistant != null) {
                    // Si l'aliment existe déjà, mettez à jour la quantité
                    alimentExistant.increaseQuantity(quantite);
                } else {
                    // Sinon, ajoutez un nouvel aliment
                    Aliment nouvelAliment = new Aliment();
                    nouvelAliment.setName(nom);
                    nouvelAliment.setQuantity(quantite);
                    restaurant.getStock().addAliment(nouvelAliment);
                }
                break;
            case 2:
                nom = choisirBoisson(scanner);
                if (nom == null)
                    return;
                System.out.println("Entrez la quantité:");
                quantite = lireChoix(scanner);
                // Vérifiez si la boisson existe déjà
                Boisson boissonExistante = restaurant.getStock().getBoissons().stream()
                        .filter(b -> b.getNom().equalsIgnoreCase(nom))
                        .findFirst()
                        .orElse(null);
                if (boissonExistante != null) {
                    // Si la boisson existe déjà, mettez à jour la quantité
                    boissonExistante.increaseQuantity(quantite);
                } else {
                    // Sinon, ajoutez une nouvelle boisson
                    Boisson nouvelleBoisson = new Boisson();
                    nouvelleBoisson.setName(nom);
                    nouvelleBoisson.setQuantity(quantite);
                    restaurant.getStock().addBoisson(nouvelleBoisson);
                }
                break;
            default:
                System.out.println("Choix non valide.");
        }

        sauvegarderStock(restaurant.getStock());
    }

    // Méthode pour choisir les aliments valide à ajouter dans mon stock
    private static String choisirAliment(Scanner scanner) {
        System.out.println("Entrez le nom de l'aliment:");
        String nom = scanner.nextLine();
        // Liste des noms d'aliments valides
        List<String> nomsValides = Arrays.asList("Salade", "Tomate", "Potirons", "Champignons", "Pain", "Viande",
                "Pate", "Fromage", "Saucisson");
        if (nomsValides.contains(nom)) {
            return nom;
        } else {
            System.out.println("Nom d'aliment non valide.");
            return null;
        }
    }

    // Méthode pour choisir les boisson valide à ajouter dans mon stock
    private static String choisirBoisson(Scanner scanner) {
        System.out.println("Entrez le nom de la boisson:");
        String nom = scanner.nextLine();
        // Liste des boissons valides
        List<String> nomsValides = Arrays.asList("Jus de fruit", "Biere", "Limonade", "Cidre doux", "Verre d'eau");
        if (nomsValides.contains(nom)) {
            return nom;
        } else {
            System.out.println("Nom de Boisson non valide.");
            return null;
        }
    }

    // Méthode pour sauvegarder le stock
    private static void sauvegarderStock(Stock stock) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stock.txt"))) {
            for (Aliment aliment : stock.getAliments()) {
                writer.write("Aliment: " + aliment.getName() + " | Quantité: " + aliment.getQuantity() + "\n");
            }

            for (Boisson boisson : stock.getBoissons()) {
                writer.write("Boisson: " + boisson.getNom() + " | Quantité: " + boisson.getQuantity() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du stock : " + e.getMessage());
        }
    }

    // Méthode pour afficher le stock
    private static void voirStock(Restaurant restaurant) {
        System.out.println("Stock d'aliments :");
        for (Aliment aliment : restaurant.getStock().getAliments()) {
            System.out.println(aliment);
        }
        System.out.println("Stock de boissons :");
        for (Boisson boisson : restaurant.getStock().getBoissons()) {
            System.out.println(boisson);
        }
    }

    // Méthode pour charger le stock
    private static Stock chargerStock() {
        Stock stock = new Stock();
        try (BufferedReader reader = new BufferedReader(new FileReader("stock.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Aliment:")) {
                    String[] parts = line.split("\\|");
                    String nom = parts[0].split(":")[1].trim();
                    int quantite = Integer.parseInt(parts[1].split(":")[1].trim());
                    Aliment aliment = new Aliment();
                    aliment.setName(nom);
                    aliment.setQuantity(quantite);
                    stock.addAliment(aliment);
                }
                if (line.startsWith("Boisson:")) {
                    String[] parts = line.split("\\|");
                    String nom = parts[0].split(":")[1].trim();
                    int quantite = Integer.parseInt(parts[1].split(":")[1].trim());
                    Boisson boisson = new Boisson();
                    boisson.setName(nom);
                    boisson.setQuantity(quantite);

                    stock.addBoisson(boisson);
                }

            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du stock : " + e.getMessage());
        }
        return stock;
    }

    private static boolean verifierEtDeduireIngredients(Stock stock, Plat plat) {
        for (String ingredientName : plat.getIngredients()) {
            Aliment aliment = stock.getAliments().stream()
                    .filter(a -> a.getName().equalsIgnoreCase(ingredientName))
                    .findFirst()
                    .orElse(null);

            // Vérifier si l'ingrédient est présent et en quantité suffisante
            if (aliment == null || aliment.getQuantity() <= 0) {
                return false; // Ingrédient manquant ou quantité insuffisante
            }
        }

        // Déduire les ingrédients du stock
        for (String ingredientName : plat.getIngredients()) {
            Aliment aliment = stock.getAliments().stream()
                    .filter(a -> a.getName().equalsIgnoreCase(ingredientName))
                    .findFirst()
                    .get();
            aliment.decreaseQuantity(1); // Supposons que chaque plat utilise une unité de chaque ingrédient
        }

        return true;
    }
}