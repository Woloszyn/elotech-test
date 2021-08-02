package controller.service;

import controller.repository.PessoaRepository;
import exceptions.CpfException;
import helper.CpfHelper;
import model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements InterfacePessoaService {

    @Autowired
    private PessoaRepository repository;

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Pessoa> findById(long id) {
        return repository.findById(id);
    }

    public List<Pessoa> paginate( int pageStrt, int pageEnd ) {
        Pageable pagination = PageRequest.of(pageStrt, pageEnd);
        return (List<Pessoa>) repository.findAll(pagination);
    }

    @Override
    public Pessoa save(Pessoa pessoa) throws Exception {
        if(CpfHelper.isValidCPF(pessoa.getCpf()))  {
            return repository.save(pessoa);
        } else {
            throw new CpfException();
        }
    }
}
