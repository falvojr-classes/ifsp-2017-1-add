package br.edu.ifsp.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Classe útil de validacão.
 * 
 * @author falvojr
 */
public final class Validador {

    private Validador() {
        super();
    }
    
    public static void validarEmail(String email) throws AddressException {
        new InternetAddress(email).validate();
    }
}
