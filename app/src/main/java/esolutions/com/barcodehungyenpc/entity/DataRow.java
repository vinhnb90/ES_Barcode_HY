package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class DataRow implements KvmSerializable{
    public String name;
    public String value;

    public DataRow(String name, String value) {
        this.name = name;
        this.value = value;
    }


    @Override
    public Object getProperty(int index)
    {
        switch (index) {
            case 0:
                return name;
            case 1:
                return value;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index)
        {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = this.name;
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = this.value;
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                name = value.toString();
                break;
            case 1:
                name = value.toString();
                break;
            default:
                break;
        }
    }
}