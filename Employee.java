import java.util.List;


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
}

