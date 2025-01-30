import java.nio.charset.StandardCharsets;

public class Cipher {

    private static final Cipher instance = new Cipher();
    private Cipher() {
    }
    public static Cipher getCipher() {
        return instance;
    }

    private String alphabetString = " GAxzHVÍprhMwjBmEqUyoJÑLZFdOKNQntTIYsWcDublñCvgSkXae";
    public String getAlphabetString() {
        return alphabetString;
    }

    public String encrypt(String text, int shift) {

        StringBuilder encryptedText = new StringBuilder();
        shift = shift % alphabetString.length();

        for (char letter : text.toCharArray()) {
            int num = alphabetString.indexOf(letter);
            if (num != -1) {
                int newShift = (num + shift + alphabetString.length()) % alphabetString.length();
                encryptedText.append(alphabetString.charAt(newShift));
            } else {
                encryptedText.append(letter);
            }
        }
        return encryptedText.toString();
    }

    public String decrypt(String encryptedText, int shift) {
        return encrypt(encryptedText,-shift);
    }




}
