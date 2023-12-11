import java.util.List;

import dao.DaoCategory;
import dao.DaoClient;
import dao.DaoCommand;
import dao.DaoCommandLine;
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

        Product apple = new Product("apple");
        apple.setDescription("very juicy");
        Product banana = new Product("banana");
        banana.setSupplier(joe);

        DaoProduct daoProduct = JpaContext.getDaoProductJpaImpl();
        daoProduct.insert(apple);
        daoProduct.insert(banana);
        daoProduct.findAll().forEach(System.out::println);

        associateCommandToClient(command, jerry);
        daoCommand.insert(command);
        System.err.println("jerry commands");
        jerry.getCommands().forEach(System.out::println);

        DaoCommandLine daoCommandLine = JpaContext.getDaoCommandLine();
        CommandLine commandLine0 = new CommandLine( new CommandLineId(apple,command),2);
        CommandLine commandLine1 = new CommandLine( new CommandLineId(banana,command),5);
        System.err.println("commandline hascode "+commandLine0.hashCode()+ " "+commandLine0.getId().hashCode());
        System.err.println("commandLine0 "+commandLine0);
        System.err.println("commandLine0 , commandLineId "+commandLine0.getId());
        System.err.println("commandLine0 , product"+commandLine0.getId().getProduct());
        System.err.println("commandLine0 , command"+commandLine0.getId().getCommand());
        daoCommandLine.insert(commandLine0);
        // daoCommandLine.insert(commandLine1);

        apple.addLine(commandLine0);
        banana.addLine(commandLine1);
        
        command.addCommandLine(commandLine0);
        command.addCommandLine(commandLine1);

        System.err.println("all products");

        DaoCategory daoCategory = JpaContext.getDaoCategory();
        Category fruits = new Category("fruits");
        Category exoticFruits = new Category("exotic fruits");
        exoticFruits.setParent(fruits);
        
        daoCategory.insert(fruits);
        daoCategory.insert(exoticFruits);

        daoCategory.findAll().forEach(System.out::println);

        // daoSupplier.delete(joe); // need to delete the product of the supplier first
        System.err.println("ok");

        // we have a composite key in the class CommandLineId
        // in the hopital project, in Visit, we set a visit_id to not have to deal with a composite key

        // /!\ when overlaoding toString() don't print object in a loop
            // ex : in category : don't print the category parent because its also a category

        // FetchType.EAGER -> eagerly fetch literralty means ~ "aller chercher vigoureusement"

        // to ensure that we are working with the last version of the object in the database, in case several person are working in the database
            // @Version -> see Client.java

        // JPQL : execute queries on java entities, same keywords than SQL (+ some additional keywords)
        // naming convention : queries of type "select" hava to be named like "findByAttribute/Property"
            // if several conditions : findByXXXAndYYY , findByXXXOrYYY
        // main difference with SQL : JPQLL works on objects
        // see DaoClientJpaImpl
   }
}
