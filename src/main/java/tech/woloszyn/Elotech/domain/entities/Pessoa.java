package tech.woloszyn.Elotech.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_pessoa")
public class Pessoa implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Contato> contato;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Contato> getContato() {
        return contato;
    }

    public void addContato(Contato contato) {
        if(this.contato == null) {
            this.contato = new ArrayList<Contato>();
        }
        this.contato.add(contato);
    }

    public void removeContato(Contato contato){
        this.contato.remove(contato);
    }

    public void setContato(List<Contato> contato) {
        this.contato = contato;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}