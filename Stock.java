import java.util.List;


class Stock {
    private List<Aliment> aliments;
    private List<Boisson> boissons;

    public void addIngredient(Ingredient ingredient) {
        if (ingredient instanceof Aliment) {
            aliments.add((Aliment) ingredient);
        } else if (ingredient instanceof Boisson) {
            boissons.add((Boisson) ingredient);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        if (ingredient instanceof Aliment) {
            aliments.remove((Aliment) ingredient);
        } else if (ingredient instanceof Boisson) {
            boissons.remove((Boisson) ingredient);
        }
    }

    public Ingredient findIngredientByName(String name) {
        for (Aliment aliment : aliments) {
            if (aliment.getName().equals(name)) {
                return aliment;
            }
        }
        for (Boisson boisson : boissons) {
            if (boisson.getName().equals(name)) {
                return boisson;
            }
        }
        return null;
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
