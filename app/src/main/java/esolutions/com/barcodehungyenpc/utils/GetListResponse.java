package esolutions.com.barcodehungyenpc.utils;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;
import java.util.Vector;

public class GetListResponse implements KvmSerializable {

    private Vector<SoapObject> datavector = new Vector<SoapObject>();
    private DataArray dataarray = new DataArray();

    @Override
    public Object getProperty(int arg0) {
        //return this.datavector.get(arg0);
        return this.dataarray;
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        info.name = "return";
        info.type = new Vector<DataPlusID>().getClass();
    }

    @Override
    public void setProperty(int index, Object value) {
        this.datavector = (Vector<SoapObject>) value;

        for(int i = 0; i < datavector.size(); i++) {
            dataarray.setProperty(0, datavector.get(i));
        }
    }
}