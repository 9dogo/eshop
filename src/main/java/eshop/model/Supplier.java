package eshop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "s_contact_name")),
        @AttributeOverride(name = "mail", column = @Column(name = "s_contact_email")),
        @AttributeOverride(name = "tel", column = @Column(name = "s_contact_tel"))
    })
    private Person contact;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "s_adress_number")),
        @AttributeOverride(name = "street", column = @Column(name = "s_street")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "s_zipCode")),
        @AttributeOverride(name = "city", column = @Column(name = "s_city"))
    })
    private Adress adress;

    // @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

    public Supplier() {
    }

    public Supplier(String name) {
        this.name = name;
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

    public Person getContact() {
        return contact;
    }

    public void setContact(Person contact) {
        this.contact = contact;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
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
        Supplier other = (Supplier) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        String output = "Supplier [id=" + id + ", name=" + name + ", contact=" + contact + ", adress=" + adress + "]";
        // for (Product p : products)
        //     output += p.getName();
        return output;
    }
}