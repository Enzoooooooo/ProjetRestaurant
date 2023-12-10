import java.util.ArrayList;
import java.util.List;

class Plat {
    private String name;
    private List<String> ingredients;
    public float prix;

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param aliments the aliments to set
     */
    public void setAliments(List<Aliment> aliments) {
        // this.aliments = aliments;
    }

    public Plat() {
        this.ingredients = new ArrayList<>(); // Initialiser la liste des ingrédients
    }

    // Getter pour les ingrédients
    public List<String> getIngredients() {
        return ingredients;
    }

    // Setter pour les ingrédients
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // Ajouter un ingrédient
    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

}
