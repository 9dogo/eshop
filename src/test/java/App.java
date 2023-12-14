import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.JpaConfig;
import dao.DaoCategory;
import dao.DaoClient;
import dao.DaoCommand;
import dao.DaoCommandLine;
import dao.DaoProduct;
import dao.DaoSupplier;
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
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        DaoClient daoClient = ctx.getBean(DaoClient.class);
        Client jerry = new Client("jerry","smith");
        daoClient.insert(jerry);

        Adress jerryAdress = new Adress("1","main street","0001","big city");
        jerry.setAdress(jerryAdress);

        daoClient.update(jerry);
        daoClient.findAll().forEach(System.out::println);

        DaoSupplier daoSupplier = ctx.getBean(DaoSupplier.class);
        Supplier joe = new Supplier("joe");
        daoSupplier.insert(joe);

        Adress joeAdress = new Adress("1","main street","54000","big city");
        joe.setAdress(joeAdress);

        daoSupplier.update(joe);
        daoSupplier.findAll().forEach(System.out::println);

        joe.getProducts().forEach(System.out::println);

    // public Client(String name, String mail, String tel, Long id, String firstName, Title title) {

        DaoCommand daoCommand = ctx.getBean(DaoCommand.class);
        Command command = new Command();

        Product apple = new Product("apple");
        apple.setDescription("very juicy");
        Product banana = new Product("banana");
        banana.setSupplier(joe);

        DaoProduct daoProduct = ctx.getBean(DaoProduct.class);
        daoProduct.insert(apple);
        daoProduct.insert(banana);
        daoProduct.findAll().forEach(System.out::println);

        associateCommandToClient(command, jerry);
        daoCommand.insert(command);
        System.err.println("jerry commands");
        jerry.getCommands().forEach(System.out::println);

        DaoCommandLine daoCommandLine = ctx.getBean(DaoCommandLine.class);
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

        DaoCategory daoCategory = ctx.getBean(DaoCategory.class);
        Category fruits = new Category("fruits");
        Category exoticFruits = new Category("exotic fruits");
        fruits.addProduct(apple);
        apple.setCategory(fruits);  // needed to make the join in DaoCategoryJpaImpl.findByName
        exoticFruits.addProduct(banana);
        exoticFruits.setParent(fruits);
        
        daoCategory.insert(fruits);
        daoCategory.insert(exoticFruits);

        daoProduct.update(apple);

        daoCategory.findAll().forEach((n)->{System.err.println(n.getName());});


        // daoSupplier.delete(joe); // need to delete the product of the supplier first
        System.err.println("ok");
        System.err.println("fruits "+fruits.getProducts());

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

        // System.err.println(daoClient.findByNameContaining("j"));
        // System.err.println(daoSupplier.findByCity("big city"));
        // System.err.println("products of fruits "+daoCategory.findByName("fruits").getProducts());
        // Category categ = daoCategory.findByName("fruits");
        // System.err.println("@@@ fruits @@@ "+categ+" \n products : "+categ.getProducts());
        // System.err.println(daoCommand.findByKey(1l));
        // System.err.println("products of joe "+daoSupplier.findByName("joe").getProducts());
        System.err.println("all suppliers "+daoSupplier.findAll());
        System.err.println("in dept 54 "+daoSupplier.findByDepartement("54"));

        ctx.close();
   }
}