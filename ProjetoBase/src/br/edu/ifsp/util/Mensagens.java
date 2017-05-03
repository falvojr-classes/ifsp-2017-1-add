package br.edu.ifsp.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Classe útil responsável por manter as mensagens de negócio e métodos utilitários.
 * 
 * @author falvojr
 */
public final class Mensagens {
    
    public static final String ERRO_BD = "Erro no banco de dados!";
    public static final String ERRO_INESPERADO = "Erro inesperado!";
    public static final String ERRO_EMAIL = "Erro ao validar seu email!";
    
    public static final String USUARIO_SUCESSO_LOGIN = "Credenciais válidas!";
    public static final String USUARIO_ERRO_LOGIN = "Credenciais inválidas!";
    public static final String USUARIO_ERRO_CAMPOS_OBRIGATORIOS = "Preencha Login e Senha!";
    
    public static final String CONTATO_SUCESSO_INSERCAO = "Contato inserido com sucesso!";
    public static final String CONTATO_SUCESSO_ALTERACAO = "";
    public static final String CONTATO_SUCESSO_EXCLUSAO = "";
    public static final String CONTATO_ERRO_CAMPOS_OBRIGATORIOS = "Ao menos um campo deve ser preenchido!";
    
    private Mensagens() {
        super();
    }
    
    public static void erro(Component pai, ExcecaoNegocial erro) {
        if (erro.getCause() != null) {
            erro.getCause().printStackTrace();
            //TODO Substituir por mecanismo de Log.
        }
        JOptionPane.showMessageDialog(pai, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void info(Component pai, String mensagem) {
        JOptionPane.showMessageDialog(pai, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
