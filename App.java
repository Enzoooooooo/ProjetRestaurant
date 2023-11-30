import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Restaurant SummerEat = new Restaurant();

        // Initialisation des attributs de Restaurant
        SummerEat.setEmployees(new ArrayList<Employee>());
        SummerEat.setTables(new ArrayList<Table>());
        SummerEat.setStock(new Stock());  // Assurez-vous que Stock est correctement défini
        SummerEat.setOrders(new ArrayList<Order>());
        SummerEat.setClean(true);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuer = true;

        while(continuer){
        System.out.println("Quel écran souhaitez vous afficher?");
        System.out.println("1- Ecran prise de commande");
        System.out.println("2- Ecran cuisine");
        System.out.println("3- Ecran bar");
        System.out.println("4- Ecran Monitoring");
        System.out.println("5- Quitter");
            int choixEcran = lireChoix(scanner);

            switch (choixEcran) {
                case 1:
                    // Logique pour l'écran de prise de commande
                    System.out.println("Affichage de l'écran de prise de commande");
                    // Ici, vous pouvez ajouter votre code spécifique pour cet écran
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
                    //scanner.close();
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
            System.out.println("Une erreur est survenue lors de la lecture de votre choix. Assurez-vous d'entrer un nombre.");
        }
    
    }

    private static void supprimerEmploye(Restaurant restaurant, Scanner scanner){
        if (restaurant.getEmployees().isEmpty()) {
            System.out.println("Il n'y a pas d'employés à gérer.");
            return;
        }
    
        boolean continuerGestion = true;
    
        while (continuerGestion) {
            System.out.println("Liste des employés :");
            for (int i = 0; i < restaurant.getEmployees().size(); i++) {
                Employee employe = restaurant.getEmployees().get(i);
                String role = determinerRoleEmploye(employe);
                System.out.println((i + 1) + ". " + employe.getName() + " - Role: " + role + " - " + (employe.getIsWorking() ? "Travaille" : "Ne travaille pas"));
            }
    
            System.out.println("Entrez le numéro de l'employé à virer, ou 0 pour revenir :");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer après un entier
    
            if (choix == 0) {
                continuerGestion = false;  // Permet de sortir de la boucle
            } else if (choix > 0 && choix <= restaurant.getEmployees().size()) {
                restaurant.getEmployees().remove(choix - 1);
                System.out.println("Employé supprimé avec succès.");
            } else {
                System.out.println("Choix non valide. Veuillez entrer un numéro correct.");
            }
        }

    }

    private static void ajouterEmploye(Restaurant restaurant, Scanner scanner) {
    System.out.println("Choisissez le rôle de l'employé :");
    System.out.println("1- Serveur");
    System.out.println("2- Cuisinier");
    System.out.println("3- Barman");
    int choixRole = scanner.nextInt();
    scanner.nextLine(); // Nettoie le buffer après la lecture d'un int    
    System.out.println("Entrez le nom de l'employé :");
    String nom = scanner.next();
    
    System.out.println("Entrez l'ID de l'employé :");
    int id = scanner.nextInt();

    System.out.println("Entrez le salaire de l'employé :");
    int salaire = scanner.nextInt();

    Employee nouvelEmploye = new Employee();

    switch (choixRole) {
        case 1: // Serveur
            nouvelEmploye = new Serveur();
            break;
        case 2: // Cuisinier
            nouvelEmploye = new Cuisinier();
            break;
        case 3: // Barman
            nouvelEmploye = new Barman();
            break;
        default:
            System.out.println("Choix de rôle non valide. Création d'un employé par défaut.");
            nouvelEmploye = new Employee();
            break;
    }

    nouvelEmploye.setName(nom);
    nouvelEmploye.setId(id);
    nouvelEmploye.setSalaire(salaire);
    nouvelEmploye.setIsWorking(false); // Par défaut, l'employé n'est pas encore au travail

    restaurant.getEmployees().add(nouvelEmploye);
    System.out.println("Employé ajouté avec succès !");


    }

    private static void gererEmployes(Restaurant restaurant, Scanner scanner) {
        if (restaurant.getEmployees().isEmpty()) {
            System.out.println("Il n'y a pas d'employés à gérer.");
            return;
        }
    
        boolean continuerGestion = true;
    
        while (continuerGestion) {
            System.out.println("Liste des employés :");
            for (int i = 0; i < restaurant.getEmployees().size(); i++) {
                Employee employe = restaurant.getEmployees().get(i);
                String role = determinerRoleEmploye(employe);
                System.out.println((i + 1) + ". " + employe.getName() + " - Role: " + role + " - " + (employe.getIsWorking() ? "Travaille" : "Ne travaille pas"));
            }
    
            System.out.println("Entrez le numéro de l'employé pour changer son statut de travail, ou 0 pour revenir :");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer après un entier
    
            if (choix == 0) {
                continuerGestion = false;  // Permet de sortir de la boucle
            } else if (choix > 0 && choix <= restaurant.getEmployees().size()) {
                Employee employe = restaurant.getEmployees().get(choix - 1);
                employe.setIsWorking(!employe.getIsWorking());
                System.out.println("Le statut de travail de " + employe.getName() + " a été changé en " + (employe.getIsWorking() ? "Travaille" : "Ne travaille pas"));
            } else {
                System.out.println("Choix non valide. Veuillez entrer un numéro correct.");
            }
        }
    }
    
    private static String determinerRoleEmploye(Employee employe) {
        if (employe instanceof Serveur) {
            return "Serveur";
        } else if (employe instanceof Cuisinier) {
            return "Cuisinier";
        } else if (employe instanceof Barman) {
            return "Barman";
        } else {
            return "Employé Général";
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
                    gererEmployes(restaurant, scanner);
                    break;
                case 2:
                    ajouterEmploye(restaurant, scanner);
                    break;
                case 3:
                    supprimerEmploye(restaurant, scanner);
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