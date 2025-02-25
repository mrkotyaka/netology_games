import java.io.*;

public class LetsPlay {
    public static void main(String[] args) {

        // Создание директорий, файлов и лога (Задание 1)
        RunInstallation.running();
        System.out.println("Congratulations! You have been installed the Game!\n");

        // Создание экземпляров класса (Задание 2)
        GameProgress gameProgress1 = new GameProgress(55, 15, 14, 1254.73);
        GameProgress gameProgress2 = new GameProgress(94, 10, 2, 274.63);
        GameProgress gameProgress3 = new GameProgress(100, 30, 100, 4832.24);

        File saveGames1 = new File("C:/Users/lenovo/Games/savegames/save1.dat");
        File saveGames2 = new File("C:/Users/lenovo/Games/savegames/save2.dat");
        File saveGames3 = new File("C:/Users/lenovo/Games/savegames/save3.dat");

        // Сохранение экземпляра класса в файл (Задание 2)
        ZippingGame.saveGame(saveGames1.getAbsolutePath(), gameProgress1);
        ZippingGame.saveGame(saveGames2.getAbsolutePath(), gameProgress2);
        ZippingGame.saveGame(saveGames3.getAbsolutePath(), gameProgress3);

        // Архивирование файлов (Задание 2)
        String zippedSaves_zip = "C:/Users/lenovo/Games/savegames/zippedSaves.zip";
        ZippingGame.zipFiles(zippedSaves_zip, saveGames1, saveGames2, saveGames3);

        // Удаление исходных файлов (Задание 2)
        ZippingGame.deleteSavedFiles(saveGames1, saveGames2, saveGames3);
        System.out.println("Congratulations! You have been zipped and saved the Game!\n");

        // Разархивирование файлов (Задание 3)
        UnzippingGame.openZip(zippedSaves_zip, saveGames3.getParent());

        // Восстановление игрового процесса и вывод в консооль файлов (Задание 3)
        System.out.println(UnzippingGame.openProgress(saveGames1.getAbsolutePath()));
        System.out.println(UnzippingGame.openProgress(saveGames2.getAbsolutePath()));
        System.out.println(UnzippingGame.openProgress(saveGames3.getAbsolutePath()));
    }
}
