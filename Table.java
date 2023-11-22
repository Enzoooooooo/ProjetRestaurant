public class Table extends restaurant{
    private Employee responsable;
    private int capacity;
    private boolean isAvailable;

    public Employee getResponsable() {
        return responsable;
    }

    public void setResponsable(Employee responsable) {
        this.responsable = responsable;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}