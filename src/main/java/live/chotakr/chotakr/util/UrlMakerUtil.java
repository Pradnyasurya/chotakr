package live.chotakr.chotakr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UrlMakerUtil {

    public static final UrlMakerUtil INSTANCE = new UrlMakerUtil();

    public UrlMakerUtil (){
        initializeCharToIndexTable();
        initializeIndexToCharTable();
    }

    private static HashMap<Character, Integer> charToIndexTable;
    private static List<Character> indexToCharTable;



    private void initializeCharToIndexTable() {
        charToIndexTable = new HashMap<>();
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            charToIndexTable.put(c, i);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            charToIndexTable.put(c, i);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            charToIndexTable.put(c, i);
        }
    }

    private void initializeIndexToCharTable() {
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
        indexToCharTable = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            char c = 'a';
            c += i;
            indexToCharTable.add(c);
        }
        for (int i = 26; i < 52; ++i) {
            char c = 'A';
            c += (i-26);
            indexToCharTable.add(c);
        }
        for (int i = 52; i < 62; ++i) {
            char c = '0';
            c += (i - 52);
            indexToCharTable.add(c);
        }
    }

    public static String createUniqueID(Long id) {

        List<Integer> base62Id = base62ToBase10Id(id);
        StringBuilder uniqueId = new StringBuilder();

        for (Integer i: base62Id){
            uniqueId.append(indexToCharTable.get(i));
        }
        return uniqueId.toString();
    }

    private static List<Integer> base62ToBase10Id(Long id) {

        List<Integer> values = new ArrayList<>();

        while(id > 0) {
            int remainder = (int)(id % 62);
            values.addFirst(remainder);
            id /= 62;
        }

        return values;
    }

    public static Long getDictionaryKeyFromUniqueID(String uniqueID) {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        return convertBase62ToBase10ID(base62IDs);
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = charToIndexTable.get(ids.get(i));
            id += (long) (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
}
