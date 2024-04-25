package fi.haagahelia.budgeting.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Budget {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private String name;
    private double amount;
    

    @ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;



    public Budget() {
        
    }

    public Budget(String name, double amount, Category category){
        super();
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static double getTotal(Iterable<Budget> budgets) {
        double total = 0.0;
        for (Budget budget : budgets) {
            total += budget.getAmount();
        }
        return total;
    }

    @Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", amount=" + amount + ", category=" + category;
	}


}
