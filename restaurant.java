import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Restaurant {
    public List<Employee> employees;
    public List<Table> tables;
    public Stock stock;
    public List<Order> orders;
    public boolean clean;

    public Restaurant() {
        this.employees = new ArrayList<>(); // Initialisation de la liste
    }

    // Getter pour la liste des employés
    public List<Employee> getEmployees() {
        return employees;
    }

    // Setter pour la liste des employés
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // Getter pour la liste des tables
    public List<Table> getTables() {
        return tables;
    }

    // Setter pour la liste des tables
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    // Getter pour le stock
    public Stock getStock() {
        return stock;
    }

    // Setter pour le stock
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    // Getter pour la liste des commandes
    public List<Order> getOrders() {
        return orders;
    }

    // Setter pour la liste des commandes
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // Getter pour l'état de propreté
    public boolean isClean() {
        return clean;
    }

    // Setter pour l'état de propreté
    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public void sauvegarderEmployes() {
        try (PrintWriter out = new PrintWriter("employes.txt")) {
            for (Employee employe : this.getEmployees()) {
                out.println(employe.getId() + "," + employe.getName() + "," + employe.getSalaire() + ","
                        + employe.getRole() + "," + employe.getIsWorking());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void chargerEmployes() {
        File file = new File("employes.txt");
        if (!file.exists()) {
            System.out.println("Le fichier employes.txt n'existe pas.");
            return;
        }

        System.out.println("Début du chargement des employés...");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Ligne lue : " + line); // Débogage

                // Traitement de la ligne pour créer un objet Employee
                String[] data = line.split(",");
                if (data.length >= 5) { // Assurez-vous qu'il y a suffisamment de données
                    Employee employe;
                    String role = data[3];

                    switch (role) {
                        case "Serveur":
                            employe = new Serveur();
                            break;
                        case "Cuisinier":
                            employe = new Cuisinier();
                            break;
                        case "Barman":
                            employe = new Barman();
                            break;
                        default:
                            employe = new Employee();
                            break;
                    }

                    employe.setId(Integer.parseInt(data[0]));
                    employe.setName(data[1]);
                    employe.setSalaire(Integer.parseInt(data[2]));
                    employe.setRole(role); // Définissez le rôle ici
                    employe.setIsWorking(Boolean.parseBoolean(data[4]));

                    this.getEmployees().add(employe); // Ajouter l'employé à la liste
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
