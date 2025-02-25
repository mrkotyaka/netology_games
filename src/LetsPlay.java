import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LetsPlay {
    public static void main(String[] args) {
        RunInstallation.running();

        StringBuilder sb = new StringBuilder();

        GameProgress gameProgress1 = new GameProgress(55, 15, 14, 1254.73);
        GameProgress gameProgress2 = new GameProgress(94, 10, 2, 274.63);
        GameProgress gameProgress3 = new GameProgress(100, 30, 100, 4832.24);

        File saveGames1 = new File("C:/Users/lenovo/Games/savegames/save1.dat");
        File saveGames2 = new File("C:/Users/lenovo/Games/savegames/save2.dat");
        File saveGames3 = new File("C:/Users/lenovo/Games/savegames/save3.dat");

        String save1 = saveGames1.getAbsolutePath();
        String save2 = saveGames2.getAbsolutePath();
        String save3 = saveGames3.getAbsolutePath();
        saveGame(save1, gameProgress1);
        saveGame(save2, gameProgress2);
        saveGame(save3, gameProgress3);

        String zippedSaves_zip = "C:/Users/lenovo/Games/savegames/zippedSaves.zip";

//        zipFiles2(zippedSaves_zip,  savegames1, savegames2, savegames3);
        zipFiles(zippedSaves_zip, saveGames1, saveGames2, saveGames3);

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
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathZip))) {
            for (int i = 0; i < pathSave.length; i++) {
                FileInputStream fis = new FileInputStream(pathSave[i]);
                zos.putNextEntry(new ZipEntry(pathSave[i].getName()));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
//    public static void zipFiles2(String pathZip, String ... pathSave) {
//        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathZip))) {
//            for (int i = 0; i < pathSave.length; i++) {
//                FileInputStream fis = new FileInputStream(pathSave[i]);
//                zos.putNextEntry(new ZipEntry("save"+i));
//                byte[] buffer = new byte[fis.available()];
//                fis.read(buffer);
//                zos.write(buffer);
//                zos.closeEntry();
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
