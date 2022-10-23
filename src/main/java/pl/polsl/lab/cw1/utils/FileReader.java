/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.cw1.utils;

import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author narxyz
 */
public class FileReader {
//    private File statsFile;
    
    private FileReader() { }
    
    public static boolean readFile(final String path) {
//        statsFile = new File(path);
//        return statsFile != null;
        return new File(path) != null;
    }
}
