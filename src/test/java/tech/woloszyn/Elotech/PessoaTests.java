package tech.woloszyn.Elotech;

import controller.service.PessoaService;
import model.Pessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PessoaTests {

    @Test
    public void testaCpf() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("04673510070");

    }

}