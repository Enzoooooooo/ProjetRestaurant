import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Stock {
    private List<Aliment> aliments = new ArrayList<>();
    private List<Boisson> boissons = new ArrayList<>();

    // ajoute un aliment au stock
    public void addAliment(Aliment aliment) {
        aliments.add(aliment);
    }

    // ajoute une boisson au stock
    public void addBoisson(Boisson boisson) {
        boissons.add(boisson);
    }

    // supprime un aliment au stock
    public void removeAliment(String name) {
        aliments.removeIf(aliment -> aliment.getName().equals(name));
    }

    // supprime une boisson au stock
    public void removeBoisson(String name) {
        boissons.removeIf(boisson -> boisson.getNom().equals(name));
    }

    /**
     * @return List<Aliment> return the aliments
     */
    public List<Aliment> getAliments() {
        return aliments;
    }

    /**
     * @param aliments the aliments to set
     */
    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    /**
     * @return List<Boisson> return the boissons
     */
    public List<Boisson> getBoissons() {
        return boissons;
    }

    /**
     * @param boissons the boissons to set
     */
    public void setBoissons(List<Boisson> boissons) {
        this.boissons = boissons;
    }

    // Menu Stock
    public static void gererStock(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;
        while (continuer) {
            App.printHeader("Gérer le stock");
            App.printOption("Ajouter du stock", 1);
            App.printOption("Voir le stock", 2);
            App.printOption("Retour", 3);
            System.out.println();

            int choixStock = App.lireChoix(scanner);

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
    public static void ajouterAuStock(Restaurant restaurant, Scanner scanner) {
        App.printHeader("Voulez-vous ajouter un aliment ou une boisson ?");
        App.printOption("Aliment", 1);
        App.printOption("Boisson", 2);
        System.out.println();

        int choix = App.lireChoix(scanner);
        String nom;
        int quantite;

        switch (choix) {
            case 1:
                nom = choisirAliment(scanner);
                if (nom == null)
                    return;
                System.out.println("Entrez la quantité:");
                quantite = App.lireChoix(scanner);
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
                quantite = App.lireChoix(scanner);
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
    public static String choisirAliment(Scanner scanner) {
        App.printHeader("Entrez le nom de l'aliment :");
        System.out.println(
                "Listes des Aliments : Salade, Tomate, Potirons, Champignons, Pain, Viande, Pate, Fromage, Saucisson");
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
    public static String choisirBoisson(Scanner scanner) {
        App.printHeader("Entrez le nom de la boisson :");
        System.out.println("Jus de fruit, Biere, Limonade, Cidre doux, Verre d'eau");
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
    public static void sauvegarderStock(Stock stock) {
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
    public static void voirStock(Restaurant restaurant) {
        App.printHeader("Stock d'aliments :");
        for (Aliment aliment : restaurant.getStock().getAliments()) {
            System.out.println(aliment);
        }
        App.printHeader("Stock de boissons:");
        for (Boisson boisson : restaurant.getStock().getBoissons()) {
            System.out.println(boisson);
        }
    }

    // Méthode pour charger le stock
    public static Stock chargerStock() {
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

    public static boolean verifierEtDeduireIngredients(Stock stock, Plat plat) {
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

    public static void verifierStockEtFaireCourses(Restaurant restaurant, Scanner scanner) {
        System.out.println("Vérification des articles à réapprovisionner...");

        Map<String, Integer> articlesAacheter = new HashMap<>();
        for (Aliment aliment : restaurant.getStock().getAliments()) {
            if (aliment.getQuantity() < 50) {
                articlesAacheter.put(aliment.getName(), 50 - aliment.getQuantity());
            }
        }

        for (Boisson boisson : restaurant.getStock().getBoissons()) {
            if (boisson.getQuantity() < 50) {
                articlesAacheter.put(boisson.getNom(), 50 - boisson.getQuantity());
            }
        }

        if (articlesAacheter.isEmpty()) {
            System.out.println("Le stock est déjà suffisant. Aucun achat nécessaire.");
            return;
        }

        System.out.println("Articles à acheter pour réapprovisionner le stock :");
        articlesAacheter.forEach((article, quantite) -> System.out.println(article + " : " + quantite));

        System.out.println("Voulez-vous acheter ces articles ? (oui/non)");
        String reponse = scanner.nextLine();

        if (reponse.equalsIgnoreCase("oui")) {
            for (Map.Entry<String, Integer> article : articlesAacheter.entrySet()) {
                if (restaurant.getStock().getAliments().stream()
                        .anyMatch(a -> a.getName().equalsIgnoreCase(article.getKey()))) {
                    Aliment aliment = restaurant.getStock().getAliments().stream()
                            .filter(a -> a.getName().equalsIgnoreCase(article.getKey())).findFirst().get();
                    aliment.setQuantity(aliment.getQuantity() + article.getValue());
                } else if (restaurant.getStock().getBoissons().stream()
                        .anyMatch(b -> b.getNom().equalsIgnoreCase(article.getKey()))) {
                    Boisson boisson = restaurant.getStock().getBoissons().stream()
                            .filter(b -> b.getNom().equalsIgnoreCase(article.getKey())).findFirst().get();
                    boisson.setQuantity(boisson.getQuantity() + article.getValue());
                }
            }
            sauvegarderStock(restaurant.getStock());
            System.out.println("Achat effectué et stock mis à jour.");
        } else {
            System.out.println("Achat annulé.");
        }
    }

    public static void enregistrerFacture(Order commande) {
        String nomFichier = "factures.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier, true))) { // true pour mode append
            writer.write("Facture pour la table " + commande.getTableNumber() + "\n");
            for (Plat plat : commande.getPlats()) {
                writer.write(plat.getName() + " - " + plat.getPrix() + "€\n");
            }
            for (Boisson boisson : commande.getBoissons()) {
                writer.write(boisson.getNom() + " - " + boisson.getPrix() + "€\n");
            }
            writer.write("Total à payer: " + commande.getTotal() + "€\n");
            writer.write("------------------------------------\n\n");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'écriture de la facture : " + e.getMessage());
        }
    }
}
