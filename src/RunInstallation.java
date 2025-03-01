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

        File games = new File("C:/Users/lenovo/Games");

        File src = new File(games, "src");
        File res = new File(games, "res");
        File savegames = new File(games, "savegames");
        File temp = new File(games, "temp");

        File main = new File(src, "main");
        File test = new File(src, "test");

        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        makeDirectory(sb, games, src,res, savegames, temp, main, test, drawables, vectors, icons);

        File mainJava = new File(main, "Main.java");
        File utilsJava = new File(main, "Utils.java");
        File tempTxt = new File(temp, "temp.txt");

        makeFile(sb, mainJava, utilsJava, tempTxt);

        try (FileWriter writer = new FileWriter(tempTxt, true)) {
            writer.write(String.valueOf(sb));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(new Date() + ERROR + ex.getMessage());
        }
    }

    private static void makeDirectory(StringBuilder sb, File ... directories) {
        for (File directory : directories) {
            if (directory.mkdir()) {
                sb.append(new Date()).append(INFO).append(directory.getAbsolutePath()).append(DIR_CREATED).append('\n');
            } else {
                sb.append(new Date()).append(WARNING).append(directory.getAbsolutePath()).append(DIR_NOT_CREATED).append('\n');
            }
        }
    }

    private static void makeFile(StringBuilder sb, File ... files) {
        for (File file : files) {
            try {
                if (file.getParentFile().exists()) {
                    if (file.createNewFile()) {
                        sb.append(new Date()).append(INFO).append(file.getAbsolutePath()).append(FILE_CREATED).append('\n');
                    } else {
                        sb.append(new Date()).append(WARNING).append(file.getAbsolutePath()).append(FILE_NOT_CREATED).append('\n');
                    }
                } else {
                    sb.append(new Date()).append(WARNING).append(file.getParentFile().getAbsolutePath()).append(DIR_NOT_CREATED).append('\n');
                }
            } catch (IOException e) {
                sb.append(new Date()).append(ERROR).append(file.getParentFile().getAbsolutePath()).append(FILE_NOT_CREATED).append(e.getMessage()).append('\n');
            }
        }
    }
}
