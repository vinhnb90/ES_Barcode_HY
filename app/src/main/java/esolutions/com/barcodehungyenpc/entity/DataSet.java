package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;
import java.util.Vector;

import static android.R.attr.key;

public class DataSet extends BaseObject {
    private Vector<DataTable> dataset;

    public DataSet(Vector<DataTable> dataset) {
        this.dataset = dataset;
    }

    @Override
    public Object getProperty(int index) {
        switch (index) {
            case 0:
                return dataset;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 1;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {
        switch (index) {
            case 0:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "NewDataSet";
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                dataset = (Vector<DataTable>) value;
                break;
            default:
                break;
        }
    }

}
