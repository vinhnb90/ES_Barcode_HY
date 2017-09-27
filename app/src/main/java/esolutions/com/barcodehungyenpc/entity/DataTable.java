package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class DataTable extends Vector<String> implements KvmSerializable {
    /**
     *
     */
    private static final long serialVersionUID = -1166006770093411055L;

    public Vector<DataRow> dataRows;

    public DataTable() {
    }

    public DataTable(String name, Vector<DataRow> dataRows) {
        this.dataRows = dataRows;
    }


    @Override
    public Object getProperty(int arg0) {

        switch (arg0) {
            case 0:
                return dataRows;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        switch (arg0) {
            case 0:
                arg2.type = PropertyInfo.STRING_CLASS;
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
                this.dataRows = (Vector<DataRow>) arg1;
                break;
            default:
                break;
        }
    }

}