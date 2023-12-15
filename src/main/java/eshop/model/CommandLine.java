package eshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "command_line")
public class CommandLine {
    
    // @EmbeddedId : make a primary key using an object
    @EmbeddedId
    private CommandLineId id;
    @Column(name = "quantity")
    private int quantity;
    public CommandLine() {
    }

    public CommandLine(int quantity) {
        this.quantity = quantity;
    }

    public CommandLine(CommandLineId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CommandLineId getId() {
        return id;
    }
    public void setId(CommandLineId id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        CommandLine other = (CommandLine) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CommandLine [id=" + id + ", quantity=" + quantity + "]";
    }
}