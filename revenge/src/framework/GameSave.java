/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * Utility static class that saves and loads values from file. Usefull to store
 * score records and game states.
 * @author aluno
 */
public class GameSave {

    private static RecordStore rs;
    private static String fileName = "game_data";
    private static Vector data = new Vector();

    public void setData(int id, String value) {
        data.insertElementAt(value, id);
    }

    public String getData(int id) {
        return (String) data.elementAt(id);
    }

    public static boolean saveData() {
        try {
            rs = RecordStore.openRecordStore(fileName, true);
            for (int i = 0; i < data.size(); i++) {
                String value = (String) data.elementAt(i);
                rs.addRecord(value.getBytes(), 0, value.getBytes().length);
            }
            rs.closeRecordStore();
            return true;
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean loadData() {
        try {
            data.removeAllElements();
            rs = RecordStore.openRecordStore(fileName, true);
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                data.insertElementAt(new String(re.nextRecord()), 0);
            }
            rs.closeRecordStore();
            return true;
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String FileName) {
        GameSave.fileName = FileName;
    }
}