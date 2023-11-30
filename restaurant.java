import java.util.List;

class Restaurant {
    public List<Employee> employees;
    public List<Table> tables;
    public Stock stock;
    public List<Order> orders;
    public boolean clean;

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
}
