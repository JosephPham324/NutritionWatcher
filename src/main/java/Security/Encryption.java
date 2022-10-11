package Security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Pham Nhat Quang
 */
public class Encryption {
    private static final String ALGO = "AES/CBC/PKCS5Padding";
    private static final byte[] IV= new byte[]{(byte)'p',(byte)'r',(byte)'j',(byte)'3',(byte)'0',(byte)'1',
        (byte)'g',(byte)'r',(byte)'o',(byte)'u',(byte)'p',(byte)'4',(byte)'f',(byte)'a',(byte)'2',(byte)'2',
    };
    
    public static SecretKey getKeyFromPassword(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec)
                .getEncoded(), "AES");
        return secret;
    }

    public static IvParameterSpec generateIv() {
        return new IvParameterSpec(IV);
    }

    public static String encrypt(String input, SecretKey key
            ) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = generateIv();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, SecretKey key
            ) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = generateIv();
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(cipherText));
        return new String(plainText);
    }
    
    public static StringBuffer encryptCC(String text, int s)
    {
        StringBuffer result= new StringBuffer();
 
        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }
    
    public static String generateSpecChar(){
        int max = 47;
        int min = 33;
        String res = "";
        for (int i = 0;i<10;i++){
            int in = ((int)Math.floor(Math.random()*(max-min+1)+min));
            int num= ((int)Math.floor(Math.random()*(9+1)));
            res+=num;
            res+=(char)in;
        }
        min = ((int)Math.floor(Math.random()*(14+1)));
        return res.substring(min, min+7);
    }
    
    public static String generateSalt(String username, String password){
        int max = username.length() + password.length();
        int rand = (int)Math.floor(Math.random()*(max-1+1)+1);
        if (max<10)
            return "Unable to generate salt";
        String salt = encryptCC(password+username, rand).toString();
        salt = salt.substring(0, 6) + generateSpecChar() + salt.substring(6,11);
        System.out.println("Salt: "+ salt);
        return salt;
    }
    
    public static void main(String[] args) {
        String username = "QuangPNCE170036";
        String password = "group4prj301";
        String salt = generateSalt("QuangPNCE170036", "group4prj301");
//        String algorithm = Encryption.getAlgorithm();
        try {
            SecretKey key = getKeyFromPassword("group4prj301", salt);
            IvParameterSpec iv = Encryption.generateIv();
            System.out.println(iv.toString());
            String encryptedPassword = encrypt(password, key);
            
            String decryptedPassword = decrypt(encryptedPassword, key);
            
            System.out.println("Encrypted Password: " + encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}