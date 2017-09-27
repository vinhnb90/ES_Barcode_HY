package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class FReadStatus implements KvmSerializable {

    String mac;

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return mac;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 1;//because you have 1 parameter
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        switch (arg0) {

            case 0:
                arg2.type = PropertyInfo.STRING_CLASS;//because its type is string
                arg2.name = "arg0";
                break;
            default:
                break;
        }

    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        switch (arg0) {
            case 0:
                mac = (String) arg1;
                break;
            default:
                break;
        }
    }

}
