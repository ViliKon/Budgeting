package fi.haagahelia.budgeting.domain;

import org.springframework.data.repository.CrudRepository;



public interface BudgetRepository extends CrudRepository<Budget, Long> {
    
}