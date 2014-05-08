package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhewedy on 08/05/14.
 */
public class IOUtil {

    public static void writeLine(String filePath, String line, boolean append){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath, append);
            fos.write((line + "\n").getBytes("utf8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static List<String> readFile(String filePath){

        List<String> list = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }catch (IOException e) {
            System.err.println("file at path: " + filePath + " should exist and have data => " + e.getMessage());
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return list;
    }
}
