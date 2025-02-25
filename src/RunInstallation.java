import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class RunInstallation {
    private static final String INFO = " [INFO] ";
    private static final String ERROR = " [ERROR] ";
    private static final String WARNING = " [WARNING] ";
    private static final String DIR_CREATED = " - директория создана.";
    private static final String DIR_NOT_CREATED = " - директория не создана.";
    private static final String FILE_CREATED = " - файл создан.";
    private static final String FILE_NOT_CREATED = " - файл не создан.";

    public static void running () {

        StringBuilder sb = new StringBuilder();

        File homeDirectory = new File("C:/Users/lenovo/Games");
        makeDirectory(sb, homeDirectory);

        File src = new File(homeDirectory, "src");
        File res = new File(homeDirectory, "res");
        File savegames = new File(homeDirectory, "savegames");
        File temp = new File(homeDirectory, "temp");
        makeDirectory(sb, src);
        makeDirectory(sb, res);
        makeDirectory(sb, savegames);
        makeDirectory(sb, temp);

        File main = new File(src, "main");
        File test = new File(src, "test");
        makeDirectory(sb, main);
        makeDirectory(sb, test);

        File main_java = new File(main, "Main.java");
        File utils_java = new File(main, "Utils.java");
        makeFile(sb, main_java);
        makeFile(sb, utils_java);

        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");
        makeDirectory(sb, drawables);
        makeDirectory(sb, vectors);
        makeDirectory(sb, icons);

        File temp_txt = new File(temp, "temp.txt");
        makeFile(sb, temp_txt);

        try (FileWriter writer = new FileWriter(temp_txt, true)) {
            writer.write(String.valueOf(sb));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(new Date() + ERROR + ex.getMessage());
        }

        System.out.println("Congratulations! You have been installed the Game!");
    }

    private static void makeDirectory(StringBuilder sb, File directory) {
        Date current = new Date();
        if (directory.mkdir()) {
            sb.append(current).append(INFO).append(directory.getAbsolutePath()).append(DIR_CREATED).append('\n');
        } else {
            sb.append(current).append(WARNING).append(directory.getAbsolutePath()).append(DIR_NOT_CREATED).append('\n');
        }
    }

    private static void makeFile(StringBuilder sb, File file) {
        Date current = new Date();

        try {
            if (file.getParentFile().exists()) {
                if (file.createNewFile()) {
                    sb.append(current).append(INFO).append(file.getAbsolutePath()).append(FILE_CREATED).append('\n');
                } else {
                    sb.append(current).append(WARNING).append(file.getAbsolutePath()).append(FILE_NOT_CREATED).append('\n');
                }
            } else {
                sb.append(current).append(WARNING).append(file.getParentFile().getAbsolutePath()).append(DIR_NOT_CREATED).append('\n');
            }
        } catch (IOException e) {
            sb.append(current).append(ERROR).append(file.getParentFile().getAbsolutePath()).append(FILE_NOT_CREATED).append(e.getMessage()).append('\n');
        }
    }
}
