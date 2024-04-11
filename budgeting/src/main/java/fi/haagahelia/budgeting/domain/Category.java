package fi.haagahelia.budgeting.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String type;
    private String item;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Budget> budget;

    public Category() {

    }

    public Category(String type, String item) {
        super();
        this.type = type;
        this.item = item;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<Budget> getBudget() {
        return budget;
    }

    public void setBooks(List<Budget> budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Category [categoryid=" + categoryId + ", type= " + type + ", item=" + item + "]";
    }

}
