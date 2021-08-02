package exceptions;

public class CpfException extends Exception {

    public CpfException() {
        super("O cpf informado é inválido");
    }

}