package tech.woloszyn.Elotech.domain.usecase.helper;

import tech.woloszyn.Elotech.domain.entities.Contato;

public class ContatoHelper {

    public static boolean validaContato(Contato contato) throws Exception {
        if(EmailHelper.isValid(contato.getEmail())){
            PropertiesHelper.haveAllProperties(contato);
            return true;
        } else {
            throw new Exception("Email não é válido");
        }
    }

}
