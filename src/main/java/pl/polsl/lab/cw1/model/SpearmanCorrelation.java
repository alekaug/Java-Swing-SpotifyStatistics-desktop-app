package pl.polsl.lab.cw1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Aleksander Augustyniak
 */
//public class SpearmanRank {
//    private static final String X_COLUMN = "x_i";
//    private static final String Y_COLUMN = "y_i";
//    private static final String X_COEFFICIENT = "r_x";
//    private static final String Y_COEFFICIENT = "r_y";
//    
//    
//    
//    /**
//     * 
//     * @param x
//     * @param y
//     * @return 
//     */
//    public Double SpearmanRank(final Long[] x, final Long[] y) {
//        if (x.length != y.length)
//            return null;
//        
//        Map<Long, Map<String, Long>> table = new HashMap<>(x.length);
//        
//        for (int i = 0; i < x.length; i++) {
//            Map<String, Long> record = new HashMap<>(5);
//            // record.put("id", (long)i);
//            record.put(X_COLUMN, x[i]);
//            record.put(Y_COLUMN, y[i]);
//            
//            table.put((long)i,record);
//        }
//        
//        List<Entry<Long, Map<String, Long>>> entries_x = new ArrayList<>(new HashMap<>(table).entrySet());
//        Collections.sort(entries_x, compare(X_COLUMN));
//        
//        List<Entry<Long, Map<String, Long>>> entries_y = new ArrayList<>(new HashMap<>(table).entrySet());
//        Collections.sort(entries_y, compare(Y_COLUMN));
//        
//        // coefficients' creation
//        for (int i = 0; i < x.length; i++) {
//            Long key_x = entries_x.get(i).getKey();
//            Map<String, Long> entry_x = table.get(key_x);
//            entry_x.put(X_COEFFICIENT, (long)i+1);
//            table.replace(key_x, entry_x);
//            
//            Long key_y = entries_y.get(i).getKey();
//            Map<String, Long> entry_y = table.get(key_y);
//            entry_y.put(Y_COEFFICIENT, (long)i+1);
//            table.replace(key_y, entry_y);
//        }
//
//        // correlation value calculation
//        int tableSize = table.size();
//        double sum = 0.0;
//        for (int i = 0; i < tableSize; i++) {
//            Map<String, Long> map = table.get((long)i);
//            sum += map.get(X_COEFFICIENT) * map.get(Y_COEFFICIENT);
//        }
//        
//        double a1 = 12*sum;
//        a1 /= (tableSize*(tableSize*tableSize-1));
//        double a2 = 3*(tableSize+1);
//        a2 /= tableSize-1;
//        return a1 - a2;
//    }
//    
//    
//    
//    private static Comparator compare(final String key) {
//        return new Comparator<Entry<Long, Map<String, Long>>>() {
//            @Override
//            public int compare(Entry<Long, Map<String, Long>> o1, Entry<Long, Map<String, Long>> o2) {
//                return o1.getValue().get(key).compareTo(o2.getValue().get(key));
//            }
//        };
//    }
//}

public abstract class SpearmanCorrelation {
    private static final String X_COLUMN = "x_i";
    private static final String Y_COLUMN = "y_i";
    private static final String X_COEFFICIENT = "r_x";
    private static final String Y_COEFFICIENT = "r_y";
    
        /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public static Double of(final Long[] x, final Long[] y) {
        if (x.length != y.length)
            return null;
        
        Map<Long, Map<String, Long>> table = new HashMap<>(x.length);
        
        for (int i = 0; i < x.length; i++) {
            Map<String, Long> record = new HashMap<>(5);
            // record.put("id", (long)i);
            record.put(X_COLUMN, x[i]);
            record.put(Y_COLUMN, y[i]);
            
            table.put((long)i,record);
        }
        
        List<Entry<Long, Map<String, Long>>> entries_x = new ArrayList<>(new HashMap<>(table).entrySet());
        Collections.sort(entries_x, compare(X_COLUMN));
        
        List<Entry<Long, Map<String, Long>>> entries_y = new ArrayList<>(new HashMap<>(table).entrySet());
        Collections.sort(entries_y, compare(Y_COLUMN));
        
        // coefficients' creation
        for (int i = 0; i < x.length; i++) {
            Long key_x = entries_x.get(i).getKey();
            Map<String, Long> entry_x = table.get(key_x);
            entry_x.put(X_COEFFICIENT, (long)i+1);
            table.replace(key_x, entry_x);
            
            Long key_y = entries_y.get(i).getKey();
            Map<String, Long> entry_y = table.get(key_y);
            entry_y.put(Y_COEFFICIENT, (long)i+1);
            table.replace(key_y, entry_y);
        }

        // correlation value calculation
        int tableSize = table.size();
        double sum = 0.0;
        for (int i = 0; i < tableSize; i++) {
            Map<String, Long> map = table.get((long)i);
            sum += map.get(X_COEFFICIENT) * map.get(Y_COEFFICIENT);
        }
        
        double a1 = 12*sum;
        a1 /= (tableSize*(tableSize*tableSize-1));
        double a2 = 3*(tableSize+1);
        a2 /= tableSize-1;
        return a1 - a2;
    }
    
    private static Comparator compare(final String key) {
        return new Comparator<Entry<Long, Map<String, Long>>>() {
            @Override
            public int compare(Entry<Long, Map<String, Long>> o1, Entry<Long, Map<String, Long>> o2) {
                return o1.getValue().get(key).compareTo(o2.getValue().get(key));
            }
        };
    }
}
