import java.util.List;


class Stock {
    private List<Aliment> aliments;
    private List<Boisson> boissons;

    public void addIngredient() {
        // Implémenter la logique
    }

    public void removeIngredient() {
        // Implémenter la logique
    }

    /**
     * @return List<Aliment> return the aliments
     */
    public List<Aliment> getAliments() {
        return aliments;
    }

    /**
     * @param aliments the aliments to set
     */
    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    /**
     * @return List<Boisson> return the boissons
     */
    public List<Boisson> getBoissons() {
        return boissons;
    }

    /**
     * @param boissons the boissons to set
     */
    public void setBoissons(List<Boisson> boissons) {
        this.boissons = boissons;
    }

}
