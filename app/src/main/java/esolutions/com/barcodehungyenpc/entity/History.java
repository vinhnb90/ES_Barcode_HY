package esolutions.com.barcodehungyenpc.entity;

/**
 * Created by VinhNB on 8/8/2017.
 */

public class History {
    private int ID_TBL_HISTORY;
    private int ID_TBL_CTO;
    private String TYPE_TBL_CTO;
    //TYPE_SESSION = 0: lay xuong, =1 gui len OK, = 2 gui len ERROR
    private String TYPE_SESSION;
    private String DATE_SESSION;
    private String TYPE_RESULT;
    private String INFO_SEARCH;
    private String INFO_RESULT;

    public History() {
        ID_TBL_HISTORY = -1;
        ID_TBL_CTO = -1;
    }

    public String getINFO_SEARCH() {
        return INFO_SEARCH;
    }

    public void setINFO_SEARCH(String INFO_SEARCH) {
        this.INFO_SEARCH = INFO_SEARCH;
    }

    public int getID_TBL_HISTORY() {
        return ID_TBL_HISTORY;
    }

    public void setID_TBL_HISTORY(int ID_TBL_HISTORY) {
        this.ID_TBL_HISTORY = ID_TBL_HISTORY;
    }

    public int getID_TBL_CTO() {
        return ID_TBL_CTO;
    }

    public void setID_TBL_CTO(int ID_TBL_CTO) {
        this.ID_TBL_CTO = ID_TBL_CTO;
    }

    public String getTYPE_TBL_CTO() {
        return TYPE_TBL_CTO;
    }

    public void setTYPE_TBL_CTO(String TYPE_TBL_CTO) {
        this.TYPE_TBL_CTO = TYPE_TBL_CTO;
    }

    public String getTYPE_SESSION() {
        return TYPE_SESSION;
    }

    public void setTYPE_SESSION(String TYPE_SESSION) {
        this.TYPE_SESSION = TYPE_SESSION;
    }

    public String getDATE_SESSION() {
        return DATE_SESSION;
    }

    public void setDATE_SESSION(String DATE_SESSION) {
        this.DATE_SESSION = DATE_SESSION;
    }

    public String getTYPE_RESULT() {
        return TYPE_RESULT;
    }

    public void setTYPE_RESULT(String TYPE_RESULT) {
        this.TYPE_RESULT = TYPE_RESULT;
    }

    public String getINFO_RESULT() {
        return INFO_RESULT;
    }

    public void setINFO_RESULT(String INFO_RESULT) {
        this.INFO_RESULT = INFO_RESULT;
    }
}
