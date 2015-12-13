/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seguranca.mb;

/**
 *
 * @author Avell
 */
public class Teste {

    public static void main(String[] args) {
        String hashed = BCrypt.hashpw("admin", BCrypt.gensalt());

        System.out.println(hashed);
        System.out.println(BCrypt.checkpw("admin", hashed));
        System.out.println(BCrypt.checkpw("adsad", hashed));

    }
}
