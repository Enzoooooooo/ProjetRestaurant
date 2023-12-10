import java.util.List;
import java.util.ArrayList;

class Stock {
    private List<Aliment> aliments = new ArrayList<>();
    private List<Boisson> boissons = new ArrayList<>();


    //ajoute un aliment au stock
    public void addAliment(Aliment aliment) {
        aliments.add(aliment);
    }

    //ajoute une boisson au stock
    public void addBoisson(Boisson boisson) {
        boissons.add(boisson);
    }

    //supprime un aliment au stock
    public void removeAliment(String name) {
        aliments.removeIf(aliment -> aliment.getName().equals(name));
    }

    //supprime une boisson au stock
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
