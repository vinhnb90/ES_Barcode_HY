package esolutions.com.barcodehungyenpc.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class DataPlusID implements KvmSerializable
{

    private String data;
    private int ID;

    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
        case 0:
            return data;
        case 1:
            return ID;
        }        
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.STRING_CLASS;
            info.name = "filterDataReal";
            break;
        case 1:
            info.type = PropertyInfo.INTEGER_CLASS;
            info.name = "ID";
            break;
        default:break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch(index)
        {
        case 0:
            data = value.toString();
            break;
        case 1:
            ID = Integer.parseInt(value.toString());
            break;
        default:
            break;
        }
    }

}