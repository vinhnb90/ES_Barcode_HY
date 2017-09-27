package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class DataRow1 extends Vector<String> implements KvmSerializable{
    /**
     *
     */

    public String name;
    public String value;

    public DataRow1() {
    }

    public DataRow1(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return name;
            case 1:
                return value;
        }

        return null;
//        return this.get(arg0);
    }

    @Override
    public int getPropertyCount() {
//        return 2;
        return this.size();
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        switch (arg0) {
            case 0:
                arg2.type = PropertyInfo.STRING_CLASS;
                arg2.name = this.name;
                break;
            case 1:
                arg2.type = PropertyInfo.STRING_CLASS;
                arg2.name = this.value;
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        switch (arg0) {
            case 0:
                this.name = arg1.toString();
                break;
            case 1:
                this.value = arg1.toString();
                break;
            default:
                break;
        }
    }

}