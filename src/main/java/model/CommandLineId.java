package model;

public class CommandLineId {

    private Product product;
    private Command command;

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
    public String toString() {
        return "CommandLineId [product=" + product + "]";
    }
}