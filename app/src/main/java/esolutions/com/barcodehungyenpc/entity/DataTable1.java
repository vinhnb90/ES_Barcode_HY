package esolutions.com.barcodehungyenpc.entity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class DataTable1 extends Vector<String> implements KvmSerializable {
    /**
     *
     */
    private static final long serialVersionUID = -1166006770093411055L;

    public String name;
    public ArrayList<Vector<String>> dataRows;

    public DataTable1() {
    }

    public DataTable1(String name, ArrayList<Vector<String>> dataRows) {
        this.name = name;
        this.dataRows = dataRows;
    }


    @Override
    public Object getProperty(int arg0) {

        switch (arg0) {
            case 0:
                return name;
            case 1:
                return dataRows;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        switch (arg0) {
            case 0:
                arg2.type = PropertyInfo.STRING_CLASS;
                arg2.name = this.name;
                break;
            case 1:
                arg2.type = PropertyInfo.VECTOR_CLASS;
                arg2.name = "Table";
//                        this.Update_GuiKD_CTO.toString();
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
                this.dataRows = (ArrayList<Vector<String>>) arg1;
                break;
            default:
                break;
        }
    }

}