package fi.haagahelia.budgeting.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import fi.haagahelia.budgeting.domain.Budget;
import fi.haagahelia.budgeting.domain.BudgetRepository;
import fi.haagahelia.budgeting.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private CategoryRepository crepository;

    @RequestMapping(value = { "/", "mybudget" })
    public String budget(Model model) {
        Iterable<Budget> budgets = repository.findAll();
        double totalBudget = Budget.getTotal(budgets);
        model.addAttribute("totalBudget", totalBudget);
        model.addAttribute("budget", repository.findAll());
        return "mybudget";
    }

    @GetMapping(value = "/budgets")
    public @ResponseBody List<Budget> budgetListRest() {
        return (List<Budget>) repository.findAll();
    }

    @RequestMapping(value = "/add")
    public String addBudget(Model model) {
        model.addAttribute("budget", new Budget());
        model.addAttribute("category", crepository.findAll());
        return "addbudget";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Budget budget) {
        repository.save(budget);
        System.out.println(budget);
        return "redirect:mybudget";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public String deleteBudget(@PathVariable("id") Long budgetId, Model model) {
        repository.deleteById(budgetId);
        return "redirect:../mybudget";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBudget(@PathVariable("id") Long budgetId, Model model) {
        model.addAttribute("budget", repository.findById(budgetId));
        model.addAttribute("category", crepository.findAll());
        return "editbudget";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}
