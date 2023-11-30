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

    public void getPrix(float prix){
        this.prix = prix;
    }
}