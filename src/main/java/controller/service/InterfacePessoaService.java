package controller.service;

import model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface InterfacePessoaService {

    public List<Pessoa> findAll();

    public Optional<Pessoa> findById(long id);

    public void deleteById(long id);

    public Pessoa save(Pessoa pessoa) throws Exception;

}
