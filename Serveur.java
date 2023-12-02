import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Serveur extends Employee {
    private List<Integer> tablesAssignees;

    public Serveur() {
        this.tablesAssignees = new ArrayList<>();
    }

    public List<Integer> getTablesAssignees() {
        return tablesAssignees;
    }

    public void setTablesAssignees(List<Integer> tablesAssignees) {
        this.tablesAssignees = tablesAssignees;
    }
}
