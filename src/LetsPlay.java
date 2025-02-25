import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LetsPlay {
    public static void main(String[] args) {
        RunInstallation.running();

        GameProgress gameProgress1 = new GameProgress(55, 15, 14, 1254.73);
        GameProgress gameProgress2 = new GameProgress(94, 10, 2, 274.63);
        GameProgress gameProgress3 = new GameProgress(100, 30, 100, 4832.24);

        File saveGames1 = new File("C:/Users/lenovo/Games/savegames/save1.dat");
        File saveGames2 = new File("C:/Users/lenovo/Games/savegames/save2.dat");
        File saveGames3 = new File("C:/Users/lenovo/Games/savegames/save3.dat");

        saveGame(saveGames1.getAbsolutePath(), gameProgress1);
        saveGame(saveGames2.getAbsolutePath(), gameProgress2);
        saveGame(saveGames3.getAbsolutePath(), gameProgress3);

        String zippedSaves_zip = "C:/Users/lenovo/Games/savegames/zippedSaves.zip";

        zipFiles(zippedSaves_zip, saveGames1, saveGames2, saveGames3);

        deleteSavedFiles(saveGames1, saveGames2, saveGames3);

        System.out.println("Congratulations! You have been zipped and saved the Game!");
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathZip, File... pathSave) {
        for (File file : pathSave) {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathZip));
                 FileInputStream fis = new FileInputStream(file)) {
                zos.putNextEntry(new ZipEntry(file.getName()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteSavedFiles(File... files) {
        for (File file : files) {
            if (!file.delete()) {
                System.out.println(file.getName() + " - not deleted");
            }
        }
    }
}
