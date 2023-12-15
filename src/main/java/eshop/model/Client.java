package eshop.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DialectOverride.Version;

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
@Table(name = "client")
public class Client extends Person {

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_firstName")
    private String firstName;
    @Column(name = "c_title")
    private Title title;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "number", column = @Column(name = "c_adress_number")),
        @AttributeOverride(name = "street", column = @Column(name = "c_street")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "c_zipCode")),
        @AttributeOverride(name = "city", column = @Column(name = "c_city"))
    })
    private Adress adress;

    @OneToMany(mappedBy = "client")
    private List<Command> commands = new ArrayList<>();

    // @Version : an int or a long (primitive type because the default value is 0)
    // at the insert of the object -> version == 0
    // when calling update, version in the database and version of the java object in memory are compared
        // if not the same, an error occurs -> to avoid this, we change the return type of update -> from void to ObjectType
    // at every update, version of the database is incremented
    @Version(major = 0)
    private int version;

    public Client() {
    }

    public Client(Long id, String firstName, Title title) {
        this.id = id;
        this.firstName = firstName;
        this.title = title;
    }

    public Client(String name, String firstName) {
        super(name);
        this.firstName = firstName;
    }


    public Client(String name, String firstName, Title title) {
        super(name);
        this.firstName = firstName;
        this.title = title;
    }

    public Client(String name, String mail, String tel, String firstName, Title title) {
        super(name, mail, tel);
        this.firstName = firstName;
        this.title = title;
    }

    public Client(String name, Long id, String firstName, Title title) {
        super(name);
        this.id = id;
        this.firstName = firstName;
        this.title = title;
    }

    public Client(String name, String mail, String tel, Long id, String firstName, Title title) {
        super(name, mail, tel);
        this.id = id;
        this.firstName = firstName;
        this.title = title;
    }

    public Client(String firstName, Title title) {
        this.firstName = firstName;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
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
        Client other = (Client) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName + ", title=" + title + ", adress=" + adress + "]";
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public void addCommand(Command command)
    {
        this.commands.add(command);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Client(Long id, String name, String firstName, Title title, Adress adress, List<Command> commands,
            int version) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.title = title;
        this.adress = adress;
        this.commands = commands;
        this.version = version;
    }

    public Client(String name, Long id, String name2, String firstName, Title title, Adress adress,
            List<Command> commands, int version) {
        super(name);
        this.id = id;
        name = name2;
        this.firstName = firstName;
        this.title = title;
        this.adress = adress;
        this.commands = commands;
        this.version = version;
    }

    public Client(String name, String mail, String tel, Long id, String name2, String firstName, Title title,
            Adress adress, List<Command> commands, int version) {
        super(name, mail, tel);
        this.id = id;
        name = name2;
        this.firstName = firstName;
        this.title = title;
        this.adress = adress;
        this.commands = commands;
        this.version = version;
    }
}