package esolutions.com.barcodehungyenpc.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;
import java.util.Vector;

public class Group implements KvmSerializable
    {
        String groupid;
        Vector groupMembers;
        public Group(String groupId,Vector groupmembers)
        {
            this.groupid=groupId;
            this.groupMembers=groupmembers;
        }

        public Object getProperty(int i)
        {
            switch(i)
            {
                case 0:
                    return groupid;
                case 1:
                    return groupMembers;
            }
            return null;
        }

        public int getPropertyCount()
        {
            return 2;
        }

        public void setProperty(int i, Object o)
        {
            switch(i)
            {
                case 0:
                    groupid=o.toString(); break;
                case 1:
                    groupMembers=(Vector) o;
                    break;
            }
        }

        @Override
        public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
            switch(i)
            {
                case 0:
                    propertyInfo.type=PropertyInfo.STRING_CLASS;
                    propertyInfo.name="groupid";
                    break;
                case 1:
                    propertyInfo.type=PropertyInfo.VECTOR_CLASS;
                    propertyInfo.name="groupMembers";
                    break;
            }
        }
    }