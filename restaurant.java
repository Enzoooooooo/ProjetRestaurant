import java.util.List;
import java.util.Scanner;

public class restaurant{

	private List<Employee> Employees;
	private List<Order> Orders;
	private List<Table> Tables;
	private Stock stock;


    /**
     * @return List<Employee> return the Employees
     */
    public List<Employee> getEmployees() {
        return Employees;
    }

    /**
     * @param Employees the Employees to set
     */
    public void setEmployees(List<Employee> Employees) {
        this.Employees = Employees;
    }

    /**
     * @return List<Order> return the Orders
     */
    public List<Order> getOrders() {
        return Orders;
    }

    /**
     * @param Orders the Orders to set
     */
    public void setOrders(List<Order> Orders) {
        this.Orders = Orders;
    }

    /**
     * @return List<Table> return the Tables
     */
    public List<Table> getTables() {
        return Tables;
    }

    /**
     * @param Tables the Tables to set
     */
    public void setTables(List<Table> Tables) {
        this.Tables = Tables;
    }

    /**
     * @return Stock return the stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Stock stock) {
        this.stock = stock;
    }

}