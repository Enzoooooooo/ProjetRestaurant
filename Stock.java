import java.util.List;
import java.util.ArrayList;


class Stock {
    private List<Aliment> aliments = new ArrayList<>();
    private List<Boisson> boissons = new ArrayList<>();

    public void addAliment(Aliment aliment) {
        aliments.add(aliment);
    }

    public void addBoisson(Boisson boisson) {
        boissons.add(boisson);
    }

    public void removeAliment(String name) {
        aliments.removeIf(aliment -> aliment.getName().equals(name));
    }

    public void removeBoisson(String name) {
        boissons.removeIf(boisson -> boisson.getNom().equals(name));
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
