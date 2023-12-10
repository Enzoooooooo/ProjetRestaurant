import java.util.List;

class Boisson {
    private String name;
    private int quantity;
    public float prix;

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public String getNom() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    /**
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return name + " (Quantité: " + quantity + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Boisson boisson = (Boisson) obj;
        return name != null ? name.equals(boisson.name) : boisson.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}