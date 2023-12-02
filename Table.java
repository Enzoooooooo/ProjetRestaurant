import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class Table {
    private int number;
    private int seats;
    private boolean isAvailable;

    /**
     * @return int return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return int return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return boolean return the isAvailable
     */
    public boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void ChangeAvailable(boolean isAvailable) {
        this.isAvailable = !isAvailable;
    }

    public static void supprimerTable(Restaurant restaurant, Scanner scanner) {
        // Afficher toutes les tables avec les serveurs assignés
        Map<Integer, String> tableServeurMap = new HashMap<>();
        for (Employee employe : restaurant.getEmployees()) {
            if (employe instanceof Serveur) {
                Serveur serveur = (Serveur) employe;
                for (int numeroTable : serveur.getTablesAssignees()) {
                    tableServeurMap.put(numeroTable, serveur.getName());
                }
            }
        }

        System.out.println("Tables disponibles :");
        for (Table table : restaurant.getTables()) {
            String serveurNom = tableServeurMap.getOrDefault(table.getNumber(), "Aucun serveur");
            System.out.println("Table " + table.getNumber() + " - Serveur: " + serveurNom);
        }

        // Demander quel numéro de table supprimer
        System.out.println("Entrez le numéro de la table à supprimer :");
        int numeroTable = App.lireChoix(scanner);

        boolean tableTrouvee = false;
        for (Iterator<Table> it = restaurant.getTables().iterator(); it.hasNext();) {
            Table table = it.next();
            if (table.getNumber() == numeroTable) {
                it.remove();
                // Retirer la table de la liste des tables assignées pour le serveur
                tableServeurMap.keySet().removeIf(key -> key.equals(table.getNumber()));
                tableTrouvee = true;
                System.out.println("Table " + numeroTable + " supprimée avec succès.");
                break;
            }
        }

        if (!tableTrouvee) {
            System.out.println("Table non trouvée.");
        }
    }

    public static void assignerTable(Restaurant restaurant, Scanner scanner) {
        // Sélectionner un serveur
        List<Serveur> serveurs = restaurant.getEmployees().stream()
                .filter(e -> e instanceof Serveur)
                .map(e -> (Serveur) e)
                .collect(Collectors.toList());

        if (serveurs.isEmpty()) {
            System.out.println("Aucun serveur disponible.");
            return;
        }

        System.out.println("Choisissez un serveur :");
        for (int i = 0; i < serveurs.size(); i++) {
            System.out.println((i + 1) + " - " + serveurs.get(i).getName());
        }

        int choixServeur = App.lireChoix(scanner);
        if (choixServeur < 1 || choixServeur > serveurs.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Serveur serveurChoisi = serveurs.get(choixServeur - 1);

        // Demander le nombre de personnes
        System.out.println("Entrez le nombre de personnes :");
        int nombrePersonnes = App.lireChoix(scanner);

        // Trouver une table disponible
        Table tableAssignee = null;
        for (Table table : restaurant.getTables()) {
            if (table.getSeats() >= nombrePersonnes && table.isIsAvailable()) {
                tableAssignee = table;
                table.setIsAvailable(false); // Marquer la table comme occupée
                break;
            }
        }

        if (tableAssignee != null) {
            // Associer la table au serveur sélectionné
            serveurChoisi.ajouterTableAssignee(tableAssignee.getNumber());
            System.out.println(
                    "Table " + tableAssignee.getNumber() + " assignée au serveur " + serveurChoisi.getName() + ".");
        } else {
            System.out.println("Aucune table disponible pour le nombre de personnes indiqué.");
        }
    }

    public static void ajouterTable(Restaurant restaurant, Scanner scanner) {
        System.out.println("Entrez le nombre de sièges pour la nouvelle table :");
        int nombreSieges = App.lireChoix(scanner);

        Table nouvelleTable = new Table();
        nouvelleTable.setSeats(nombreSieges);
        nouvelleTable.setIsAvailable(true);

        // Attribuer un numéro unique à la table
        int nouveauNumero = restaurant.getTables().size() + 1;
        nouvelleTable.setNumber(nouveauNumero);

        restaurant.getTables().add(nouvelleTable);
        System.out.println("Table ajoutée avec succès. Numéro de la table: " + nouveauNumero);
    }

}
