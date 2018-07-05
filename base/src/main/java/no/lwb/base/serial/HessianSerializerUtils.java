package no.lwb.base.serial;


import com.caucho.hessian.io.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class HessianSerializerUtils {

    private static SerializerFactory serializerFactory = new SerializerFactory();

    private HessianSerializerUtils() {
    }

    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream ops = new ByteArrayOutputStream();
        AbstractHessianOutput out = new HessianSerializerOutput(ops);
        serializerFactory.setAllowNonSerializable(true);
        out.setSerializerFactory(serializerFactory);

        try {
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
           e.printStackTrace();
        }

        return ops.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream ips = new ByteArrayInputStream(bytes);
        AbstractHessianInput in = new HessianSerializerInput(ips);
        in.setSerializerFactory(serializerFactory);
        Object value = null;

        try {
            value = in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value != null ? value : bytes;
    }
}
