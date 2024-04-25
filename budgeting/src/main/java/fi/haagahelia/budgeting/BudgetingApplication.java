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
	public CommandLineRunner demo(BudgetRepository repository, CategoryRepository crepository) {
		return (args) -> {


			crepository.save(new Category("Housing"));
			crepository.save(new Category("Bills"));
			crepository.save(new Category("Entertainment"));
			crepository.save(new Category("Savings"));



			Budget myBudget = new Budget("Rent",800.0, 
			crepository.findByName("Housing").get(0));

			Budget myBudget2 = new Budget("Car",50.0, 
			crepository.findByName("Savings").get(0));

			Budget myBudget3 = new Budget("Phone bill", 25.0, 
			crepository.findByName("Bills").get(0));

			repository.save(myBudget);
			repository.save(myBudget2);
			repository.save(myBudget3);

		};
	}

}
