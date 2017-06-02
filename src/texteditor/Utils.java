/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SirExtreme
 */
public class Utils {

    public Object[] returnFonts() {

        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        ArrayList<String> list = new ArrayList();

        int x = 0;
        for (String f : fonts) {

            if (fonts[x].startsWith("Adobe") || fonts[x].contains("Bold") || fonts[x].contains("Black") || fonts[x].contains("Italic") || fonts[x].contains("Thin") || fonts[x].contains("Light")) {

            } else {
                list.add(f);
            }

            x++;
        }

        Object workFont[] = list.toArray();

        for (int i = 0; i < list.size(); i++) {
            workFont[i] = list.get(i);

        }

        return workFont;
    }

    public String fileReader(File file) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reader.close();
        }
        return stringBuilder.toString();
    }

    void SaveFile(String content, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
