import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LetsPlay {
    public static void main(String[] args) {
        RunInstallation.running();

        StringBuilder sb = new StringBuilder();

        GameProgress gameProgress1 = new GameProgress(55, 15, 14, 1254.73);
        GameProgress gameProgress2 = new GameProgress(94, 10, 2, 274.63);
        GameProgress gameProgress3 = new GameProgress(100, 30, 100, 4832.24);

//        File savegames1 = new File("C:/Users/lenovo/Games/savegames/save1.dat");
//        File savegames2 = new File("C:/Users/lenovo/Games/savegames/save2.dat");
//        File savegames3 = new File("C:/Users/lenovo/Games/savegames/save3.dat");
//        RunInstallation.makeFile(sb, savegames1);
//        saveGame(savegames1.getAbsolutePath(), gameProgress1);

        String savegames1 = "C:/Users/lenovo/Games/savegames/save1.dat";
        String savegames2 = "C:/Users/lenovo/Games/savegames/save2.dat";
        String savegames3 = "C:/Users/lenovo/Games/savegames/save3.dat";
        saveGame(savegames1, gameProgress1);
        saveGame(savegames2, gameProgress2);
        saveGame(savegames3, gameProgress3);

        String zip_zip = "C:/Users/lenovo/Games/savegames/zip.zip";

        zipFiles(zip_zip,  savegames1, savegames2, savegames3);

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
    public static void zipFiles(String pathZip, String ... pathSave) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("new_my_dir/zos_zipers.zip"));
             FileInputStream fis = new FileInputStream("new_my_dir/file_zip.txt")) {
            ZipEntry entry = new ZipEntry(Arrays.toString(pathSave));
            zos.putNextEntry(entry);
            byte[] b = new byte[fis.available()];
            if(fis.read(b) != -1) {
                System.out.println("File read");
            }
            zos.write(b);
            zos.closeEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
