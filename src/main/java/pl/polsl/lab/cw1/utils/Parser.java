/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.cw1.utils;

/**
 *
 * @author Aleksander Augustyniak
 */
public interface Parser {
    static final String PATH_PARAM = "-p";
    static final String OPERATION_PARAM = "-o";
    
    public static String getPath(final String[] args) {
        return getValueOf(args, PATH_PARAM);
    }
    
    public static String getOperation(final String[] args) {
        return getValueOf(args, OPERATION_PARAM);
    }
    
    private static String getValueOf(final String[] args, final String key) {
        if (args.length < 3) return "";
        
        for (int i = 0; i < args.length; i++) {
            if (key.equals(args[i]))
                return args[i+1] != null ? args[i+1] : "";
        }
        return "";
    }
}
