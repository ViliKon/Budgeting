package fi.haagahelia.budgeting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fi.haagahelia.budgeting.domain.Budget;
import fi.haagahelia.budgeting.domain.BudgetRepository;
import fi.haagahelia.budgeting.domain.Category;
import fi.haagahelia.budgeting.domain.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller 
public class BudgetController {

    @Autowired
    private BudgetRepository repository;

    @Autowired
    private CategoryRepository catRepository;

    @RequestMapping(value = {"/", "mybudget"})
    public String budget(Model model) {
        model.addAttribute("budget", repository.findAll());
        return "mybudget";
    }

    @GetMapping(value = "/budgets")
    public @ResponseBody List<Budget> budgetListRest() {
        return (List<Budget>) repository.findAll();
    }

    @RequestMapping(value = "/addbudget")
    public String addBudget(Model model) {
        model.addAttribute("budget", new Budget());
        return "addbudget";
    }

    @RequestMapping(value = "/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @GetMapping(value = "/deletebudget/{id}")
    public String deleteBudget(@PathVariable("id") Long budgetId, Model model) {
        repository.deleteById(budgetId);
        return "redirect:../mybudget";
    }

    @GetMapping(value = "/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
        catRepository.deleteById(categoryId);
        return "redirect:../mybudget";
    }

    @GetMapping(value = "/edit/{id}")
    public String editBudget(@PathVariable("id") Long budgetId, Model model) {
        model.addAttribute("budget", repository.findById(budgetId));
        model.addAttribute("category", catRepository.findAll());
        return "editbudget";

    }
    


    
}
