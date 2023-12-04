import java.util.List;

class Plat {
    private String name;
    private List<Aliment> aliments;
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

}
