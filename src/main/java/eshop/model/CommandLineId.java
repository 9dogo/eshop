package eshop.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CommandLineId implements Serializable{

    @ManyToOne
    @JoinColumn(name = "command_line_product", foreignKey = @ForeignKey(name = "command_line_product_fk"))
    private Product product;
    @ManyToOne
    @JoinColumn(name = "command_line_command_id", foreignKey = @ForeignKey(name = "command_line_command_id_fk"))
    private Command command;

    // hashCode and equals use both product and command

    public CommandLineId() {
    }

    public CommandLineId(Product product, Command command) {
        this.product = product;
        this.command = command;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Command getCommand() {
        return command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((command == null) ? 0 : command.hashCode());
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
        CommandLineId other = (CommandLineId) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (command == null) {
            if (other.command != null)
                return false;
        } else if (!command.equals(other.command))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CommandLineId [product=" + product + "]";
    }
}