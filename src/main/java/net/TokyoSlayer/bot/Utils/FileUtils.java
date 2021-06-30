package net.TokyoSlayer.bot.Utils;

import net.TokyoSlayer.bot.Main;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;


public class FileUtils {
    public static void createFile(File file) throws IOException {

        if (!file.exists()) {
            if(file.getParentFile().mkdirs()){
                if(file.createNewFile()){
                    System.out.println("[API] Create " + file.getName());
                }
            }
        }
    }

    public static void copyApache(InputStream source, String destination) throws IOException{
        File file = new File(destination);
        createFile(file);
        final FileOutputStream out = new FileOutputStream(destination);
        IOUtils.copy(source,out);
    }

    public static String loadContent(File file) {

        if (file.exists()) {

            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8))) {


                final StringBuilder text = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                }

                return text.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public File buildFile(String fileName) throws IOException {
        String pathName = null;
        try {
            pathName = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()+ File.separator+fileName+".json";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathName != null;
        File file = new File(pathName);
        if(!file.exists()) {
            copyApache(Main.class.getClassLoader().getResourceAsStream(fileName + ".json"), pathName);
        }
        return file;
    }

}