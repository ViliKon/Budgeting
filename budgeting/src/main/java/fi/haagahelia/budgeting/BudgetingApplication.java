package fi.haagahelia.budgeting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.budgeting.domain.Budget;
import fi.haagahelia.budgeting.domain.BudgetRepository;
import fi.haagahelia.budgeting.domain.Category;
import fi.haagahelia.budgeting.domain.CategoryRepository;

@SpringBootApplication
public class BudgetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetingApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BudgetRepository repository, CategoryRepository catRepository) {
		return (args) -> {

			catRepository.save(new Category("Housing", "Rent"));

			Budget myBudget = new Budget("Monthly Budget", 2000.0, 
			catRepository.findByType("Housing").get(0));

			repository.save(myBudget);
			
		};
	}

}
