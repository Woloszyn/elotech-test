package tech.woloszyn.Elotech;

import tech.woloszyn.Elotech.domain.usecase.helper.BirthdayHelper;
import tech.woloszyn.Elotech.domain.usecase.helper.CpfHelper;
import tech.woloszyn.Elotech.domain.entities.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.woloszyn.Elotech.domain.usecase.helper.PropertiesHelper;
import tech.woloszyn.Elotech.exceptions.PropriedadeNaoInformadaException;

import java.time.LocalDate;

@SpringBootTest
public class PessoaTests {

    @Test
    public void testValidCpf() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("04673510070");

        boolean testCpf = CpfHelper.isValidCPF(pessoa.getCpf());
        Assertions.assertTrue(testCpf);
    }

    @Test
    public void testCanHaveWrongCpf() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("0999999999999");

        boolean testCpf = CpfHelper.isValidCPF(pessoa.getCpf());
        Assertions.assertFalse(testCpf);
    }

    @Test
    public void testCanBirthdayAfterNow() {
        // Everybody knows that the world will not exist after 2030 :)
        Assertions.assertFalse(BirthdayHelper.isValidBirthday(LocalDate.of(2050, 12, 31)));
    }

    @Test
    public void testCanForgetToInformProperty() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Eduardo Woloszyn");
        Assertions.assertThrows(PropriedadeNaoInformadaException.class, () -> PropertiesHelper.haveAllProperties(pessoa));
    }

    @Test
    public void testHaveInformAllProperties() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Eduardo Woloszyn");
        pessoa.setCpf("04673510070");
        pessoa.setDataNascimento(LocalDate.of(1999, 01, 17));

        boolean result = PropertiesHelper.haveAllProperties(pessoa);
        Assertions.assertTrue(result);
    }

}