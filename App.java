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

        int jour = 1;
        Restaurant SummerEat = new Restaurant();
        SummerEat.chargerEmployes();
        for (Employee employe : SummerEat.getEmployees()) {
            employe.setIsWorking(false);
        }

        SummerEat.setTables(new ArrayList<Table>());
        // Charge mon Stock de stock.txt
        SummerEat.setStock(Stock.chargerStock());
        SummerEat.setOrders(new ArrayList<Order>());
        SummerEat.setClean(true);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean commencerJournee = false;

            while (!commencerJournee) {
                printHeader("Bienvenue dans le système de gestion SummerEat");
                printOption("Commencer la journée - Sélectionner les employés", 1);
                printOption("Faire les courses", 2);
                printOption("Ecran monitoring (Gestion tables + Employés + Stock)", 3);
                printOption("Quitter", 4);
                System.out.println();

                int choixInitial = lireChoix(scanner);

                switch (choixInitial) {
                    case 1:
                        commencerJournee = Employee.gererEmployes(SummerEat, scanner);
                        if (!commencerJournee) {
                            System.out.println("Veuillez réessayer de gérer les employés.");
                        }
                        break;
                    case 2:
                        Stock.verifierStockEtFaireCourses(SummerEat, scanner);
                        break;
                    case 3:
                        Restaurant.gererEcranMonitoring(SummerEat, scanner);
                        break;
                    case 4:
                        System.out.println("Fermeture du programme.");
                        for (Employee employe : SummerEat.getEmployees()) {
                            employe.setIsWorking(false);
                        }
                        System.exit(0); // Sortie immédiate du programme
                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez choisir une option valide.");
                }
            }
            boolean continuer = true;
            while (continuer) {
                System.out.println("------------------------");
                System.out.println("Jour : " + jour);
                System.out.println("------------------------");
                printHeader("Quel écran souhaitez vous afficher?");
                printOption("Assigner une table (Accueil des clients)", 1);
                printOption("Ecran commande (Prise et Service)", 2);
                printOption("Ecran cuisine", 3);
                printOption("Ecran bar", 4);
                printOption("Ecran Monitoring (Gestion tables + Employés + Stock)", 5);
                printOption("Fin de journée", 6);
                System.out.println();
                int choixEcran = lireChoix(scanner);

                switch (choixEcran) {
                    case 1:
                        Table.assignerTable(SummerEat, scanner);
                        break;
                    case 2:
                        Order.gererPriseCommande(SummerEat, scanner);
                        break;
                    case 3:
                        // Logique pour l'écran de cuisine
                        System.out.println("Affichage de l'écran de cuisine");
                        Cuisinier.ecranCuisine(SummerEat, scanner);
                        break;
                    case 4:
                        // Logique pour l'écran de bar
                        System.out.println("Affichage de l'écran de bar");
                        Barman.ecranBar(SummerEat, scanner);
                        break;
                    case 5:
                        // Logique pour l'écran de monitoring
                        Restaurant.gererEcranMonitoring(SummerEat, scanner);
                        break;
                    case 6:

                        Employee.terminerJournee(SummerEat);
                        commencerJournee = false;
                        jour++;
                        while (!commencerJournee) {
                            printHeader("Bienvenue dans le système de gestion SummerEat");
                            printOption("Commencer la journée - Sélectionner les employés", 1);
                            printOption("Faire les courses", 2);
                            printOption("Ecran monitoring", 3);
                            printOption("Quitter", 4);
                            System.out.println();

                            int choixInitial = lireChoix(scanner);

                            switch (choixInitial) {
                                case 1:
                                    commencerJournee = Employee.gererEmployes(SummerEat, scanner);
                                    if (!commencerJournee) {
                                        System.out.println("Veuillez réessayer de gérer les employés.");
                                    }
                                    break;
                                case 2:
                                    // Logique pour faire les courses (à implémenter)
                                    System.out.println("Logique pour faire les courses (à implémenter)");
                                    break;
                                case 3:
                                    Restaurant.gererEcranMonitoring(SummerEat, scanner);
                                    break;
                                case 4:
                                    System.out.println("Fermeture du programme.");
                                    System.exit(0); // Sortie immédiate du programme
                                    break;
                                default:
                                    System.out.println("Choix non valide. Veuillez choisir une option valide.");
                            }
                        }
                        while (Employee.gererEmployes(SummerEat, scanner) == false) {
                            System.out.println("Veuillez réessayer de gérer les employés.");
                        }

                        break;
                    default:
                        System.out.println("Choix non valide. Veuillez choisir une option entre 1 et 4.");
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
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

    public static void printHeader(String title) {
        String separator = new String(new char[50]).replace("\0", "*");
        System.out.println(separator);
        System.out.println("*" + String.format("%-48s", title) + "*");
        System.out.println(separator);
    }

    public static void printOption(String option, int number) {
        System.out.println(String.format("%-3d - %s", number, option));
    }

}