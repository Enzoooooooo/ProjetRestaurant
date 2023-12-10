import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

class Employee {
    public String name;
    public int id;
    public int salaire;
    public String role;
    public boolean isWorking;
    public int consecutiveDaysWorked = 0;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter pour le nom
    public String getName() {
        return name;
    }

    // getter pour les jours consécutifss
    public int getConsecutiveDaysWorked() {
        return consecutiveDaysWorked;
    }

    // incrémente un jour de travaille consécutif
    public void incrementConsecutiveDaysWorked() {
        consecutiveDaysWorked++;
    }

    // remet a 0 les jours consécutifs
    public void resetConsecutiveDaysWorked() {
        consecutiveDaysWorked = 0;
    }

    // Setter pour le nom
    public void setName(String name) {
        this.name = name;
    }

    // Getter pour l'ID
    public int getId() {
        return id;
    }

    // Setter pour l'ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter pour le salaire
    public int getSalaire() {
        return salaire;
    }

    // Setter pour le salaire
    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    // Getter pour isWorking
    public boolean getIsWorking() {
        return isWorking;
    }

    // Setter pour isWorking
    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    // Méthode pour changer l'état de travail
    public void work() {
        isWorking = !isWorking;
    }

    // Méthode pour nettoyer le restaurant
    public void nettoyer(Restaurant restaurant) {
        if (restaurant != null) {
            restaurant.clean = !restaurant.clean;
        }
    }

    public static void supprimerEmploye(Restaurant restaurant, Scanner scanner) {
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
                System.out.println((i + 1) + ". " + employe.getName() + " - Role: " + role + " - "
                        + (employe.getIsWorking() ? "Travaille" : "Ne travaille pas"));
            }

            System.out.println("Entrez le numéro de l'employé à virer, ou 0 pour revenir :");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer après un entier

            if (choix == 0) {
                continuerGestion = false; // Permet de sortir de la boucle
            } else if (choix > 0 && choix <= restaurant.getEmployees().size()) {
                restaurant.getEmployees().remove(choix - 1);
                restaurant.sauvegarderEmployes(); // Sauvegarde après la suppression d'un employé
                System.out.println("Employé supprimé avec succès.");
            } else {
                System.out.println("Choix non valide. Veuillez entrer un numéro correct.");
            }
        }

    }

    // fonction pour verifier si il y a assez d'employés au bon poste
    private static boolean verifierConditionEmployes(Restaurant restaurant) {
        long nbServeurs = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Serveur && e.getIsWorking())
                .count();
        long nbCuisiniers = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Cuisinier && e.getIsWorking())
                .count();
        long nbBarmans = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Barman && e.getIsWorking())
                .count();

        if (nbServeurs >= 2 && nbCuisiniers >= 4 && nbBarmans >= 1) {
            return true;
        } else {
            System.out.println("Condition non remplie: 2 serveurs, 4 cuisiniers, et 1 barman requis.");
            return false;
        }
    }

    public static String determinerRoleEmploye(Employee employe) {
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

    // Fonction pour decider de quel employés travaillents
    public static boolean gererEmployes(Restaurant restaurant, Scanner scanner) {
        if (restaurant.getEmployees().isEmpty()) {
            System.out.println("Il n'y a pas d'employés à gérer.");
            return false;
        }

        boolean continuerGestion = true;

        while (continuerGestion) {
            System.out.println("Liste des employés :");
            for (int i = 0; i < restaurant.getEmployees().size(); i++) {
                Employee employe = restaurant.getEmployees().get(i);
                String role = Employee.determinerRoleEmploye(employe);
                if (employe.consecutiveDaysWorked != 3) {
                    System.out.println((i + 1) + ". " + employe.getName() + " - Role: " + role + " - "
                            + (employe.getIsWorking() ? "Travaille   " : "Ne travaille pas  ")
                            + employe.consecutiveDaysWorked + "jours consécutifs");
                } else {
                    System.out.println((i + 1) + ". " + employe.getName() + " - Role: " + role + " - "
                            + "ne peux pas travailler");
                }
            }

            System.out.println(
                    "Entrez le numéro de l'employé pour changer son statut de travail. Appuyer sur 0 pour valider la sélection");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Nettoyer le buffer après un entier

            if (choix == 0) {
                continuerGestion = false;
            } else if (choix > 0 && choix <= restaurant.getEmployees().size()) {
                Employee employe = restaurant.getEmployees().get(choix - 1);

                // Vérifier le nombre de jours consécutifs travaillés
                if (employe.getConsecutiveDaysWorked() < 3) {
                    employe.setIsWorking(!employe.getIsWorking());
                    restaurant.sauvegarderEmployes();
                    System.out.println("Le statut de travail de " + employe.getName() + " a été changé en "
                            + (employe.getIsWorking() ? "Travaille" : "Ne travaille pas"));
                } else {
                    System.out.println("L'employé " + employe.getName()
                            + " a déjà travaillé 3 jours consécutifs et ne peut pas changer de statut aujourd'hui.");
                }
            } else {
                System.out.println("Choix non valide. Veuillez entrer un numéro correct.");
            }
        }
        if (verifierConditionEmployes(restaurant)) {
            return true;
        } else {
            return false;
        }
    }

    // function pour ajouter un employé
    public static void ajouterEmploye(Restaurant restaurant, Scanner scanner) {
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
                nouvelEmploye.setRole("Serveur");
                break;
            case 2: // Cuisinier
                nouvelEmploye = new Cuisinier();
                nouvelEmploye.setRole("Cuisinier");
                break;
            case 3: // Barman
                nouvelEmploye = new Barman();
                nouvelEmploye.setRole("Barman");
                break;
            default:
                System.out.println("Choix de rôle non valide. Création d'un employé par défaut.");
                nouvelEmploye = new Employee();
                nouvelEmploye.setRole("Employé Général");
                break;
        }

        nouvelEmploye.setName(nom);
        nouvelEmploye.setId(id);
        nouvelEmploye.setSalaire(salaire);
        nouvelEmploye.setIsWorking(false); // Par défaut, l'employé n'est pas encore au travail

        restaurant.getEmployees().add(nouvelEmploye);
        restaurant.sauvegarderEmployes(); // Sauvegarde après l'ajout d'un employé
        System.out.println("Employé ajouté avec succès !");

    }

    // bien terminer la journée
    public static void terminerJournee(Restaurant restaurant) {

        for (Employee employe : restaurant.getEmployees()) {
            if (employe.getIsWorking() == false) {
                employe.consecutiveDaysWorked = 0;
            } else {
                employe.incrementConsecutiveDaysWorked();
            }
            employe.setIsWorking(false);
            if (employe.consecutiveDaysWorked == 4) {
                employe.consecutiveDaysWorked = 0;
            }
        }
        System.out.println("Nettoyage du Restaurant en cours ....");
        try {
            Thread.sleep(2000); // 1 seconde pour la simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interruption lors de la préparation du plat.");
        }
        System.out.println("Lavage du sol");
        try {
            Thread.sleep(1000); // 1 seconde pour la simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interruption lors de la préparation du plat.");
        }
        System.out.println("Rangement de la salle et de la cuisine");
        try {
            Thread.sleep(1000); // 1 seconde pour la simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interruption lors de la préparation du plat.");
        }
        System.out.println("Paul a bien nettoyé Summer-Eat !");
        restaurant.setClean(true);
        restaurant.sauvegarderEmployes();
        System.out.println(
                "La journée est terminée. Tous les employés sont maintenant hors service, et le Summer-Eat est nettoyé");
    }

}