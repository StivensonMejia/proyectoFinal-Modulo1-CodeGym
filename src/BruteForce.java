public class BruteForce {

    private BruteForce() {
    }
    private static final BruteForce bruteForce = new BruteForce();
    public static BruteForce getBruteForce() {
        return bruteForce;
    }

    private final String[] connectors = {
            " la ", " el ", " en ", " un ", " una ", " que ", " sin ",
            " mi ", " a ", " y ", " o ", " mas ", " de ", " para ", " por "
    };

    private final Cipher cipher = Cipher.getCipher();
    private final String alphabet = cipher.getAlphabetString();


    public String decryptByBruteForce(String encryptedText) {
        String decryptedTest;
        for(int i = 0; i < alphabet.length(); i++) {
            decryptedTest = cipher.decrypt(encryptedText,i);
            int value = 0;
            for (String x : connectors) {
                if(decryptedTest.contains(x)) {
                    value++;
                }
            }
            if (value > 2) {
                System.out.println("Tu llave es : " + i);
                return decryptedTest;
            }
        }
        return "Fallo el desencriptado,\n" +
                "Version aun en prueba,";
    }
}
