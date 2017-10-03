package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by VinhNB on 10/3/2017.
 */

public class Test implements KvmSerializable{

    public String a;
    public String b;

    public Test() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return a;
            case 1:
                return b;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                a = value.toString();
                break;
            case 1:
                b = value.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable hashtable, PropertyInfo info) {
        info.type = PropertyInfo.STRING_CLASS;
        switch (index) {
            case 0:
                info.name = "a";
                break;
            case 1:
                info.name = "b";
                break;
            default:
                break;
        }
    }
}
