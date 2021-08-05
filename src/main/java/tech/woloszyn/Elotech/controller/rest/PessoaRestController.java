package tech.woloszyn.Elotech.controller.rest;

import tech.woloszyn.Elotech.domain.usecase.service.PessoaService;
import tech.woloszyn.Elotech.domain.entities.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@RestController
public class PessoaRestController {
    @Autowired
    private PessoaService service;

    @CrossOrigin(origins = "http://elotech.woloszyn.tech")
    @GetMapping("/getPessoas")
    @Transactional
    public List<Pessoa> getPessoas() throws Exception{
        List clients = service.findAll();
        return clients;
    }

    @CrossOrigin(origins = "http://elotech.woloszyn.tech")
    @GetMapping("/getPessoas/Paginate/{startPagination}/{endPagination}")
    public List<Pessoa> paginate(@PathVariable("startPagination") int strtPagination, @PathVariable("endPagination") int endPagination ) throws Exception {
        List<Pessoa> pessoas = service.paginate(strtPagination, endPagination);
        return pessoas;
    }

    /**
     * Espera receber um array em Base64 como contato
     * @param pessoa
     * @param contatos
     * @return
     * @throws Exception
     */
    @CrossOrigin(origins = "http://elotech.woloszyn.tech")
    @PostMapping("/createPessoa")
    public ResponseEntity create(Pessoa pessoa, @RequestParam("contatos") String contatos) throws Exception {
        try {
            String arr = new String(Base64.getDecoder().decode(contatos));
            service.addContatoJson(pessoa, arr);
            if(Objects.isNull(pessoa.getId()) || pessoa.getId() == 0)  {
                Pessoa returnSavePessoa = service.save(pessoa);
                return new ResponseEntity<Pessoa>(returnSavePessoa, HttpStatus.OK);
            }
            return new ResponseEntity("Para criar não se deve informar o id", HttpStatus.PERMANENT_REDIRECT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://elotech.woloszyn.tech")
    @PutMapping("/updatePessoa")
    public ResponseEntity update(Pessoa pessoa, @RequestParam("contatos") String contatos) throws Exception {
        try {
            String arr = new String(Base64.getDecoder().decode(contatos));
            service.addContatoJson(pessoa, arr);
            if(!Objects.isNull(pessoa.getId())) {
                Pessoa returnSavePessoa = service.save(pessoa);
                return new ResponseEntity<Pessoa>(returnSavePessoa, HttpStatus.OK);
            }
            return new ResponseEntity("Para editar é necessário informar o id", HttpStatus.PERMANENT_REDIRECT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://elotech.woloszyn.tech")
    @DeleteMapping("/deletePessoa/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            service.deleteById(Long.parseLong(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}