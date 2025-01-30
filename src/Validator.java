import java.io.File;

public class Validator {

    public Validator() {
    }

    public static boolean isValidKey(int key) {
        if (key <= 0)
            return false;
        else {
            return true;
        }
    }

    public static int isFileExists(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            return 2;
        }

        if (filePath.endsWith(".txt") || filePath.endsWith(".md") || filePath.endsWith(".csv")) {
            return 0;
        } else {
            return 1;
        }
    }
}
