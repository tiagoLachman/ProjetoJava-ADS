package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialController {

    public void serializar(String path, Object obj) throws Exception {
        FileOutputStream outFile = new FileOutputStream(path);
        ObjectOutputStream s = new ObjectOutputStream(outFile);
        s.writeObject(obj);
        s.close();
    }

    public Object deserializar(String path) throws Exception {
        FileInputStream inFile;
        ObjectInputStream d;
        try {
            inFile = new FileInputStream(path);
            d = new ObjectInputStream(inFile);
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        }

        Object o = d.readObject();
        d.close();
        return o;
    }
}