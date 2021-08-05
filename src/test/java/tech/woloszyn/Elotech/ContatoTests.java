package tech.woloszyn.Elotech;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.woloszyn.Elotech.domain.entities.Pessoa;
import tech.woloszyn.Elotech.domain.usecase.helper.EmailHelper;
import tech.woloszyn.Elotech.domain.usecase.service.PessoaService;

import java.time.LocalDate;

@SpringBootTest
public class ContatoTests {

    @Autowired
    private PessoaService pessoaService;

    @Test
    public void testValidEmail() {
        String validEmail = "eduardowoloszyn@example.com";
        Assertions.assertTrue(EmailHelper.isValid(validEmail));
    }

    @Test
    public void testInvalidEmail() {
        String invalidEmail = "llllllkllkl.com";
        Assertions.assertFalse(EmailHelper.isValid(invalidEmail));
    }

    @Test
    public void testCanAddContato() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("John Boe");
        pessoa.setCpf("04673510070");
        pessoa.setDataNascimento(LocalDate.of(1999, 01, 17));
        String contatos = "[{'nome':'John Coe', 'email':'test@test.com', 'telefone':'+55 54 967886554'}]";
        pessoaService.addContatoJson(pessoa, contatos);
        Assertions.assertEquals("John Coe", pessoa.getContato().get(0).getNome());
    }

}
