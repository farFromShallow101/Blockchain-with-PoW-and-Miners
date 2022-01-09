package blockchain;

import java.io.*;

class SerializationUtils {
    /**
     * Serialize the given object to the file
     */
    public static void serialize(Object obj, String fileName) throws IOException {

        BufferedOutputStream bos;
        FileOutputStream fos = new FileOutputStream(fileName);
        bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();

    }

    /**
     * Deserialize to an object from the file
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
