package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;

import java.util.Vector;

public abstract class BaseObject extends Vector<Object> implements KvmSerializable {
    public static final String NAMESPACE = "http://tempuri.org/encodedTypes";
    public BaseObject() {
        super();
    }
}