import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        System.out.println("Total: " + getTotal() + " euros");
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

    // Permet de gérer les prise de commande mais aussi les servir
    public static void gererPriseCommande(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            App.printHeader("Ecran Prise de commande");
            App.printOption("Afficher le menu", 1);
            App.printOption("Sélectionner un serveur pour prendre commande", 2);
            App.printOption("Servir les commandes prêtes", 3);
            App.printOption("Revenir au menu principal", 4);
            System.out.println();

            int choixCommande = App.lireChoix(scanner);

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
                            System.out.println("Desgustation en cours ...");
                            try {
                                Thread.sleep(2000); // 1 seconde pour la simulation
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                System.out.println("Interruption lors de la préparation du plat.");
                            }
                            // Ici commence la logique de paiement après avoir servi la commande
                            System.out.println("Total à payer : " + commandeAServir.getTotal() + " euros");
                            App.printHeader("Mode de paiement");
                            App.printOption("Carte bleue", 1);
                            App.printOption("Espèces", 2);
                            int choixPaiement = App.lireChoix(scanner);
                            switch (choixPaiement) {
                                case 1:
                                    System.out.println("Paiement par carte bleue sélectionné.");
                                    break;
                                case 2:
                                    System.out.println("Paiement en espèces sélectionné.");
                                    break;
                                default:
                                    System.out.println("Choix non valide.");
                                    break;
                            }

                            App.printHeader("Diviser l'addition ?");
                            App.printOption("Non", 1);
                            App.printOption("Oui", 2);
                            int choixDivision = App.lireChoix(scanner);
                            if (choixDivision == 2) {
                                System.out.println("En combien de parts souhaitez-vous diviser l'addition ?");
                                int parts = App.lireChoix(scanner);
                                double montantParPart = commandeAServir.getTotal() / parts;
                                System.out.println(
                                        "l'addition a était payé en : " + parts + " parts de "
                                                + String.format("%.2f", montantParPart) + " euros");
                            } else {
                                System.out.println("Le client a payé l'addition dans son integralité");
                            }
                            Stock.enregistrerFacture(commandeAServir);
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
        App.printHeader("Sélectionnez un serveur :");
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

        int choixServeur = App.lireChoix(scanner);
        if (choixServeur < 1 || choixServeur > serveurs.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Serveur serveurChoisi = serveurs.get(choixServeur - 1);
        System.out.println("Serveur choisi : " + serveurChoisi.getName());
        System.out.println("Tables assignées : " + serveurChoisi.getTablesAssignees());

        // Choix de la table
        App.printHeader("Choisissez une table pour prendre la commande:");

        int choixTable = App.lireChoix(scanner);

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
            App.printHeader("Ajouter à la commande:");
            App.printOption("Plat", 1);
            App.printOption("Boisson", 2);
            App.printOption("Terminer la commande", 3);
            System.out.println();
            int choixCommande = App.lireChoix(scanner);

            switch (choixCommande) {
                case 1:
                    // Afficher la liste des plats
                    App.printHeader("Liste des plats disponibles :");
                    for (int i = 0; i < menu.getPlats().size(); i++) {
                        Plat plat = menu.getPlats().get(i);
                        System.out.println((i + 1) + " - " + plat.getName() + " : " + plat.getPrix() + " euros");
                    }

                    System.out.println("Choisissez un plat à ajouter à la commande (entrez le numéro) :");
                    int choixPlat = App.lireChoix(scanner);

                    if (choixPlat < 1 || choixPlat > menu.getPlats().size()) {
                        System.out.println("Choix de plat non valide.");
                    } else {
                        Plat platChoisi = menu.getPlats().get(choixPlat - 1);
                        if (Stock.verifierEtDeduireIngredients(restaurant.getStock(), platChoisi)) {
                            commande.addPlat(platChoisi);
                            System.out.println(platChoisi.getName() + " ajouté à la commande.");
                            Stock.sauvegarderStock(restaurant.getStock());
                        } else {
                            System.out.println("Ingrédients insuffisants pour ajouter " + platChoisi.getName()
                                    + " à la commande.");
                        }
                    }
                    break;

                case 2:
                    // Afficher la liste des boissons
                    App.printHeader("Liste des boissons disponibles :");
                    for (int i = 0; i < menu.getBoissons().size(); i++) {
                        Boisson boisson = menu.getBoissons().get(i);
                        System.out.println((i + 1) + " - " + boisson.getNom() + " : " + boisson.getPrix() + " euros");
                    }

                    System.out.println("Choisissez une boisson à ajouter à la commande (entrez le numéro) :");
                    int choixBoisson = App.lireChoix(scanner);

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
                            Stock.sauvegarderStock(restaurant.getStock()); // Sauvegarder après chaque commande réussie
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

}
