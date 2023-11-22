public class Employee extends restaurant {
	private String Name;
    private int id;
    private String role;
    private int salaire;

    // Constructeur pour créer un nouvel employé
    public Employee(String Name, int id) {
        this.Name = Name;
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }
}