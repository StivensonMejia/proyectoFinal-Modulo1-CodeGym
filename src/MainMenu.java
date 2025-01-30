import Exceptions.InvalidOptionExceptions;
import java.util.Scanner;

public class MainMenu {

    public MainMenu() {
    }
    private static final MainMenu mainMenu = new MainMenu();
    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    private final String RESET = "\u001B[0m";
    private final String ROJO = "\u001B[31m";
    private final String VERDE = "\u001B[32m";
    private final String AMARILLO = "\u001B[33m";
    private final String AZUL = "\u001B[34m";
    private final String MORADO = "\u001B[35m";

    private final Scanner userScanner = new Scanner(System.in);
    private final FileManager fileManager = FileManager.getFileManager();
    private final Cipher cipher = Cipher.getCipher();
    private final BruteForce bruteForce = BruteForce.getBruteForce();

    //MENU INICIAL
    public void startMainMenu() {
        System.out.println();
        System.out.println(AMARILLO + "********* MENU PRINCIPAL *********" + RESET);
        System.out.println("----------------------------------");
        System.out.println("Selecione la opcion requerida:");
        System.out.println();
        System.out.println("1: Encriptación");
        System.out.println("2: Desencriptación con llave");
        System.out.println("3: Desencriptación sin llave");
        System.out.println("0: Salir");
        System.out.println();
        mainMenuSelectOption();

    }
    private void mainMenuSelectOption() {
        try {
            System.out.print(AZUL + "Digite su opcion : ");
            if (!userScanner.hasNextInt()) {
                userScanner.next();
                throw new InvalidOptionExceptions("Opcion no valida, Se espera un numero entero.");
            } else {
                int userOption = userScanner.nextInt();
                switch (userOption) {
                    case 1 -> {
                        System.out.println();
                        encryptionMenu();
                    }
                    case 2 -> {
                        System.out.println();
                        decryptionWithKeyMenu();
                    }
                    case 3 -> {
                        System.out.println();
                        bruteForceMenu();
                    }
                    case 0 -> {
                        System.out.println();
                        salir();
                    }
                    default -> {
                        throw new InvalidOptionExceptions("Opcion fuera de rango, Intente de nuevo.");
                    }
                }

            }
        } catch (InvalidOptionExceptions e) {
            System.out.println(ROJO + e.getMessage() + RESET);
            System.out.println();
            mainMenuSelectOption();
        }
    }
    //MENU ENCRIPTAR Y DESENCRIPTAR
    private void encryptionMenu() {
        System.out.println(AMARILLO + "******* MENU ENCRIPTACIÓN ********" + RESET);
        System.out.println("----------------------------------");
        userScanner.nextLine();
        String path = cryptionMenuValidatorPath();
        String text = fileManager.readFile(path);
        int shift = cryptionMenuValidatorKey();
        System.out.println(MORADO + "Ecriptando tu archivo, porfavor espera.");
        System.out.print("1.      ");
        System.out.print("2.      ");
        System.out.print("3.      ");
        System.out.print("4.      ");
        System.out.println("5... ");
        System.out.println();
        System.out.println(VERDE + "Tu archivo fue Ecriptando correctamente en:" + RESET);
        System.out.println("src/tuArchivoEncriptado.txt");
        String encryptedText = cipher.encrypt(text,shift);
        fileManager.writeFile(encryptedText,"src/tuArchivoEncriptado.txt");
        salir();
    }
    private void decryptionWithKeyMenu() {
        System.out.println(AMARILLO + "* MENU DESENCRIPTACIÓN CON LLAVE *" + RESET);
        System.out.println("----------------------------------");
        userScanner.nextLine();
        String path = cryptionMenuValidatorPath();
        String text = fileManager.readFile(path);
        int shift = cryptionMenuValidatorKey();
        System.out.println(MORADO + "desencriptando tu archivo, porfavor espera.");
        System.out.print("1.      ");
        System.out.print("2.      ");
        System.out.print("3.      ");
        System.out.print("4.      ");
        System.out.println("5... ");
        System.out.println();
        System.out.println(VERDE + "Tu archivo fue desencriptando correctamente en:" + RESET);
        System.out.println("src/tuArchivoDesencriptado.txt");
        String encryptedText = cipher.decrypt(text,shift);
        fileManager.writeFile(encryptedText,"src/tuArchivoDesencriptado.txt");
        salir();
    }
    //ADICIONALES PARA ENCRIPTAR, DESENCRIPTAR Y FORZAR
    private String cryptionMenuValidatorPath() {
        System.out.println(AZUL + "Porfavor ingrese la ruta");
        System.out.print("del su archivo : " + RESET);
        String option = userScanner.nextLine();
        try {
            switch (Validator.isFileExists(option)) {
                case 0 -> {
                    System.out.println(VERDE + "El archivo es Valido" + RESET);
                    System.out.println();
                    return option;
                }
                case 1 -> throw new InvalidOptionExceptions("El archivo no tiene una extensión de texto válida.");
                case 2 -> throw new InvalidOptionExceptions("El archivo no existe.");
            }
        } catch (InvalidOptionExceptions e){
            System.out.println(ROJO + e.getMessage() + RESET);
            System.out.println();
            return cryptionMenuValidatorPath();
        }
        throw new IllegalStateException("Error inesperado en cryptionMenuValidatorPath");
    }
    private int cryptionMenuValidatorKey() {
        System.out.print(AZUL + "Porfavor ingrese la clave: " +  RESET);
        int option = 0;
        try {
            if (!userScanner.hasNextInt()) {
                userScanner.next();
                throw new InvalidOptionExceptions("Valor invalido, debe ser un numero entero");
            } else {
                option = userScanner.nextInt();
                if (option <= 0) {
                    throw new InvalidOptionExceptions("El numero debe se mayor a 0");
                }
            }

        } catch (InvalidOptionExceptions e) {
            System.out.println(ROJO + e.getMessage() + RESET);
            System.out.println();
            cryptionMenuValidatorKey();
        }
        return option;
    }
    //MENU FORZAR
    private void bruteForceMenu() {
        System.out.println(AMARILLO + "* MENU DESENCRIPTACIÓN SIN LLAVE *" + RESET);
        System.out.println("----------------------------------");
        userScanner.nextLine();
        String path = cryptionMenuValidatorPath();
        String text = fileManager.readFile(path);
        System.out.println(MORADO + "Desencriptando tu archivo, porfavor espera.");
        System.out.print("1.      ");
        System.out.print("2.      ");
        System.out.print("3.      ");
        System.out.print("4.      ");
        System.out.println("5... ");
        System.out.println();
        System.out.println(VERDE + "Tu archivo fue Desencriptando correctamente en:" + RESET);
        System.out.println("src/tuArchivoDesencriptadoSinLlave.txt");
        System.out.print(VERDE);
        String decryptedText = bruteForce.decryptByBruteForce(text);
        System.out.print(RESET);
        fileManager.writeFile(decryptedText,"src/tuArchivoDesencriptadoSinLlave.txt");
        salir();

    }
    private void salir() {
        System.out.println();
        System.out.println(MORADO + "********* **************** *********");
        System.out.println("********* HASTA LA PROXIMA *********");
        System.out.println("********* **************** *********" + RESET);
    }

}
