/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finance.util;

import java.io.Serializable;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Miguel
 */
@ManagedBean
@RequestScoped

public class Criptografia implements Serializable {
    
    
    private static final String METODO_ENCRIPTACAO = "AES";
    public final byte[] CHAVE = {-117, -11, -123, -9, -4, 78, -45, -121, 92, -22, -111, -82, -88, 72, 21, 95, };

    public Criptografia() {
    }
    
    public String encriptar(byte[] key, String value) throws Throwable {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, METODO_ENCRIPTACAO);
            Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());

            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            throw new Throwable("Erro ao criptografar informações " + e.getMessage());
        }
    }
    
     public String decriptar(byte[] key, String encrypted) throws Throwable {
        byte[] decrypted = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, METODO_ENCRIPTACAO);
            byte[] decoded = new BASE64Decoder().decodeBuffer(encrypted);
            Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACAO);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            decrypted = cipher.doFinal(decoded);
        } catch (Exception e) {
            throw new Throwable("Erro ao descriptografar informações " + e.getMessage());
        }
        return new String(decrypted);
    }
}
