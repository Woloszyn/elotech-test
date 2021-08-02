package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "db_contato")
public class Contato {
    private long id;
    private String nome;
    private String telefone;
    private String email;

    @OneToMany
    private Pessoa pessoa;

}