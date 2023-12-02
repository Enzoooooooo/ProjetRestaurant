import java.util.ArrayList;
import java.util.Scanner;

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
                System.out.println("5- Quitter");
                int choixEcran = lireChoix(scanner);

                switch (choixEcran) {
                    case 1:
                        gererPriseCommande(SummerEat, scanner);
                        break;
                    case 2:
                        // Logique pour l'écran de cuisine
                        System.out.println("Affichage de l'écran de cuisine");
                        // Ajoutez le code pour cet écran ici
                        break;
                    case 3:
                        // Logique pour l'écran de bar
                        System.out.println("Affichage de l'écran de bar");
                        // Ajoutez le code pour cet écran ici
                        break;
                    case 4:
                        // Logique pour l'écran de monitoring
                        gererEcranMonitoring(SummerEat, scanner);
                        // scanner.close();
                        break;
                    case 5:
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

    private static void gererPriseCommande(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("Ecran Prise de commande");
            System.out.println("1- Afficher le menu");
            System.out.println("2- Prendre Commande");
            System.out.println("3- ");
            System.out.println("4- Revenir menu principal");

            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Consomme l'entrée non valide
                continue; // Continue la boucle pour demander de nouveau l'entrée
            }

            int choixCommande = scanner.nextInt();
            scanner.nextLine(); // Nettoie le buffer

            switch (choixCommande) {
                case 1:
                    // Créer un menu à partir de MenuData
                    Menu menu = defMenu.createMenu();
                    // Afficher le menu
                    menu.afficherMenu();
                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    continuer = false; // Sort de la boucle, retour au menu principal
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
            }
        }
    }

    private static void gererEcranMonitoring(Restaurant restaurant, Scanner scanner) {
        boolean continuer = true;

        while (continuer) {
            System.out.println("Ecran Monitoring");
            System.out.println("1- Gérer les employés du jour");
            System.out.println("2- Ajouter un employé");
            System.out.println("3- Supprimer un employé");
            System.out.println("4- Retour au menu principal");

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
                    continuer = false; // Sort de la boucle, retour au menu principal
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
            }
        }
    }

    private static int lireChoix(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next(); // Consomme l'entrée non valide
        }
        int choix = scanner.nextInt();
        scanner.nextLine(); // Nettoie le buffer après la lecture d'un int
        return choix;
    }

}