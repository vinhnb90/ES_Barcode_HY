package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class a implements KvmSerializable
{
    public ListUpdate_GuiKD_CTO listUpdateGuiKDCTO;

    public a(){}


    public a(ListUpdate_GuiKD_CTO listUpdateGuiKDCTO) {
        this.listUpdateGuiKDCTO = listUpdateGuiKDCTO;
    }


    public Object getProperty(int arg0) {

        switch(arg0)
        {
        case 0:
            return listUpdateGuiKDCTO;
        }

        return null;
    }

    public int getPropertyCount() {

        return 1;
    }
    @Override
    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
        switch(index)
        {
        case 0:
            info.type = PropertyInfo.VECTOR_CLASS;
            info.name = "NewDataSet";
            break;
        default:break;
        }
    }

    public void setProperty(int index, Object value) {
        switch(index)
        {
        case 0:
            this.listUpdateGuiKDCTO = (ListUpdate_GuiKD_CTO)value;
            break;
        default:
            break;
        }
    }

}