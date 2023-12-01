import java.util.List;
import java.util.Scanner;

class Employee {
    public String name;
    public int id;
    public int salaire;
    public String role;
    public boolean isWorking;

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    // Getter pour le nom
    public String getName() {
        return name;
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

    public static void supprimerEmploye(Restaurant restaurant, Scanner scanner){
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

    public static void gererEmployes(Restaurant restaurant, Scanner scanner) {
        if (restaurant.getEmployees().isEmpty()) {
            System.out.println("Il n'y a pas d'employés à gérer.");
            return;
        }
    
        boolean continuerGestion = true;
    
        while (continuerGestion) {
            System.out.println("Liste des employés :");
            for (int i = 0; i < restaurant.getEmployees().size(); i++) {
                Employee employe = restaurant.getEmployees().get(i);
                String role = Employee.determinerRoleEmploye(employe);
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
}

