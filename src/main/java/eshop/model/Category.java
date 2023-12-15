package model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "category")
    private Set<Product> products =  new HashSet<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Category> subCategory = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "category_parentid_fk"))
    private Category parent;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }

    public Set<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Set<Category> subCategory) {
        this.subCategory = subCategory;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String output = "Category [id=" + id + ", description=" + description + ",  subCategory="
                + subCategory + ", parent=" + (parent==null ? "null": parent.getDescription()) + "]";
        for (Product p : products)
            output += p.getName();
        return output;
    }
}