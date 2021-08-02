package controller.rest;

import controller.service.PessoaService;
import model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PessoaRestController {
    @Autowired
    private PessoaService service;


    @GetMapping("/getPessoas")
    public List<Pessoa> getPessoas() throws Exception{
        List clients = service.findAll();
        return clients;
    }

    @GetMapping("/getPessoas/Paginate")
    public List<Pessoa> paginate(@RequestParam("strtpagination") int strtPagination, @RequestParam("strtpagination") int endPagination ) throws Exception {
        List<Pessoa> pessoas = service.paginate(strtPagination, endPagination);
        return pessoas;
    }

}
