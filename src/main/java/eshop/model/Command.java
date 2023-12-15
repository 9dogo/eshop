package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "command")
public class Command {

    @Id
    @Column(name = "command_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "command_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "command_clientid_fk"))
    private Client client;

    @OneToMany(mappedBy = "id.command")
    private List<CommandLine> lines = new ArrayList<>();

    public Command() {
    }

    public Command(Client client) {
        this.client = client;
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
        Command other = (Command) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CommandLine> getLines() {
        return lines;
    }

    public void addCommandLine(CommandLine commandLine)
    {
        lines.add(commandLine);
    }

    public void setLines(List<CommandLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Command [id=" + id + ", date=" + date + ", client=" + client + ", lines=" + lines + "]";
    }
}