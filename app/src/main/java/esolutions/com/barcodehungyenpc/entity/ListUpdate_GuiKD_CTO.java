package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class ListUpdate_GuiKD_CTO extends Vector<Object> implements KvmSerializable {
    /**
     *
     */
    private static final long serialVersionUID = -1166006770093411055L;

    public Vector<Update_GuiKD_CTO> Update_GuiKD_CTO;

    public ListUpdate_GuiKD_CTO() {
    }

    public ListUpdate_GuiKD_CTO(Vector<Update_GuiKD_CTO> Update_GuiKD_CTO) {
        this.Update_GuiKD_CTO = Update_GuiKD_CTO;
    }


    @Override
    public Object getProperty(int arg0) {

        switch (arg0) {
            case 0:
                return Update_GuiKD_CTO;
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
                arg2.type = PropertyInfo.VECTOR_CLASS;
                arg2.name = "object";
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        switch (arg0) {
            case 0:
                this.Update_GuiKD_CTO = (Vector<Update_GuiKD_CTO>) arg1;
                break;
            default:
                break;
        }
    }

}