package esolutions.com.barcodehungyenpc.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;
import java.util.Vector;

public class DataArray extends Vector<DataPlusID> implements KvmSerializable {

    private static final long serialVersionUID = -1166006770093411055L;

    @Override
    public Object getProperty(int index) {
        return this.get(index);
    }    

    @Override
    public int getPropertyCount() {
        return this.size();
        //return dataArray.length;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        info.name = "item";
        info.type = new DataPlusID().getClass();
    }

    @Override
    public void setProperty(int index, Object value) {
        SoapObject soapObject = (SoapObject) value;

        DataPlusID daten = new DataPlusID();
        daten.setProperty(0, soapObject.getProperty("filterDataReal"));
        daten.setProperty(1, soapObject.getProperty("ID_TBL_DIENLUC"));

        this.add(daten);
    }
}