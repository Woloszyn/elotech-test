package tech.woloszyn.Elotech.domain.usecase.service;

import org.json.JSONArray;
import org.json.JSONObject;
import tech.woloszyn.Elotech.domain.entities.Contato;
import tech.woloszyn.Elotech.domain.usecase.helper.BirthdayHelper;
import tech.woloszyn.Elotech.domain.usecase.helper.ContatoHelper;
import tech.woloszyn.Elotech.domain.usecase.helper.PropertiesHelper;
import tech.woloszyn.Elotech.domain.usecase.repository.PessoaRepository;
import tech.woloszyn.Elotech.exceptions.BirthdayException;
import tech.woloszyn.Elotech.exceptions.CpfException;
import tech.woloszyn.Elotech.domain.usecase.helper.CpfHelper;
import tech.woloszyn.Elotech.domain.entities.Pessoa;
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
        return repository.findAll(pagination).toList();
    }

    public void addContatoJson(Pessoa pessoa, String contatos) throws Exception {
        JSONArray contactArray = new JSONArray(contatos);
        for (int i = 0; i < contactArray.length(); i++) {
            JSONObject jsonContato = contactArray.getJSONObject(i);
            Contato contato = new Contato(jsonContato.getString("nome"), jsonContato.getString("telefone"), jsonContato.getString("email"));
            contato.setPessoa(pessoa);
            if(ContatoHelper.validaContato(contato)) {
                pessoa.addContato(contato);
            }
        }
    }

    @Override
    public Pessoa save(Pessoa pessoa) throws Exception {
        PropertiesHelper.haveAllProperties(pessoa);
        if(!CpfHelper.isValidCPF(pessoa.getCpf()))  {
            throw new CpfException();
        }
        if(!BirthdayHelper.isValidBirthday(pessoa.getDataNascimento())) {
            throw new BirthdayException();
        }
        return repository.save(pessoa);
    }

}