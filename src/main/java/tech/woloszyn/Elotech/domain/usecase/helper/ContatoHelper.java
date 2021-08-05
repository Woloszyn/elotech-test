package tech.woloszyn.Elotech.domain.usecase.helper;

import tech.woloszyn.Elotech.domain.entities.Contato;

public class ContatoHelper {

    public static boolean validaContato(Contato contato) throws Exception {
        EmailHelper.isValid(contato.getEmail());
        PropertiesHelper.haveAllProperties(contato);
        return true;
    }

}
