package me.asycc.anonymousonline.common.utils;


import java.io.*;

/**
 * A class used to help serialize
 * and deserialize objects.
 *
 * @author Asyc
 * @since 3/4/2019
 *
 */
public class SerializationUtils {


    /**
     * @param bytes The bytes used to reconstruct the object.
     * @return Returns a reconstructed object from the byte array.
     *
     * @throws IOException Thrown if the {@link ObjectInputStream} could not be initialized
     * or the {@link ObjectInputStream} could not be read from.
     *
     * @throws ClassNotFoundException Thrown when the class of a serialized object could not be found.
     */
    public static Object deserialize(final byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bin);
        Object obj = in.readObject();
        in.close();
        return obj;
    }

    /**
     *
     * @param object The object to serialize.
     * @return Returns Returns the object specified serialized into a byte array.
     * @throws IOException If the {@link ObjectOutputStream} could not be initialized.
     */
    public static byte[] serialize(final Object object) throws IOException{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(object);
        out.flush();
        byte[] bytes = bout.toByteArray();
        out.close();
        return bytes;
    }

}
