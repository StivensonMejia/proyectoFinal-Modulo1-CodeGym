import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    private FileManager(){
    }

    private static final FileManager fileManager = new FileManager();
    private Charset charset = Charset.forName("UTF-8");

    public String readFile(String filePath) {
        String fileRead = "";
        try(FileChannel channel = FileChannel.open(Path.of(filePath), StandardOpenOption.READ)) {
            ByteBuffer buffer =  ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            while (channel.read(buffer) > 0) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    content.append(charset.decode(buffer));
                }
            }
            buffer.clear();
            fileRead = content.toString();
        } catch (Exception e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
        }
        return fileRead;
    }

    public void writeFile(String content, String filePath) {

        try (FileChannel canal = FileChannel.open(Path.of(filePath), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
            canal.write(buffer);
        } catch (Exception e) {
            System.out.println("Error escribiendo el archivo: " + e.getMessage());
        }
    }

    public static FileManager getFileManager() {
        return fileManager;
    }
}
