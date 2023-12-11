import java.util.List;

import dao.DaoCategory;
import dao.DaoClient;
import dao.DaoCommand;
import dao.DaoProduct;
import dao.DaoSupplier;
import dao.JpaContext;
import model.Adress;
import model.Category;
import model.Client;
import model.Command;
import model.CommandLine;
import model.CommandLineId;
import model.Product;
import model.Supplier;

public class App {

    public static void associateCommandToClient(Command command, Client client)
    {
        command.setClient(client);
        List<Command> commandsOfClient = client.getCommands();
        commandsOfClient.add(command);
        client.setCommands(commandsOfClient);
    }

    public static void main(String[] args) {
        
        // entite client, fournisseur, produit, commande, categorie
        DaoClient daoClient = JpaContext.getDaoClient() ;
        Client jerry = new Client("smith","jerry");
        daoClient.insert(jerry);

        Adress jerryAdress = new Adress("1","main street","0001","big city");
        jerry.setAdress(jerryAdress);

        daoClient.update(jerry);
        daoClient.findAll().forEach(System.out::println);

        DaoSupplier daoSupplier = JpaContext.getDaoSupplier() ;
        Supplier joe = new Supplier("joe");
        daoSupplier.insert(joe);

        Adress joeAdress = new Adress("1","main street","0001","big city");
        joe.setAdress(joeAdress);

        daoSupplier.update(joe);
        daoSupplier.findAll().forEach(System.out::println);

        joe.getProducts().forEach(System.out::println);

    // public Client(String name, String mail, String tel, Long id, String firstName, Title title) {

        DaoCommand daoCommand = JpaContext.getDaoCommand();
        Command command = new Command();

        CommandLine commandLine0 = new CommandLine(2);
        CommandLine commandLine1 = new CommandLine(5);
        
        Product apple = new Product("apple");
        apple.setDescription("very juicy");
        Product banana = new Product("banana");
        apple.addLine(commandLine0);
        banana.addLine(commandLine1);
        banana.setSupplier(joe);
        commandLine0.setId(new CommandLineId(apple, command));
        commandLine1.setId(new CommandLineId(banana, command));

        command.addCommandLine(commandLine0);
        command.addCommandLine(commandLine1);

        associateCommandToClient(command, jerry);
        daoCommand.insert(command);
        // daoCommand.findAll().forEach(System.out::println);

        System.err.println("jerry commands");
        jerry.getCommands().forEach(System.out::println);

        DaoProduct daoProduct = JpaContext.getDaoProductJpaImpl();
        System.err.println("all products");
        daoProduct.insert(apple);
        daoProduct.insert(banana);
        daoProduct.findAll().forEach(System.out::println);

        DaoCategory daoCategory = JpaContext.getDaoCategory();
        Category fruits = new Category("fruits");
        Category exoticFruits = new Category("exotic fruits");
        exoticFruits.setParent(fruits);
        
        daoCategory.insert(fruits);
        daoCategory.insert(exoticFruits);

        daoCategory.findAll().forEach(System.out::println);

        // daoSupplier.delete(joe); // need to delete the product of the supplier first
        System.err.println("ok");

        // /!\ when overlaoding toString() don't print object in a loop
            // ex : in category : don't print the category parent because its also a category

        // FetchType.EAGER -> eagerly fetch literralty means ~ "aller chercher vigoureusement"

   }
}
