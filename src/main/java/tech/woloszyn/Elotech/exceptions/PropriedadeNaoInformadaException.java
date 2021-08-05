package tech.woloszyn.Elotech.exceptions;

public class PropriedadeNaoInformadaException extends Exception{

    public PropriedadeNaoInformadaException(String propriedade) {
        super("Você esqueceu de informar " + propriedade);
    }

}
