package esolutions.com.barcodehungyenpc.utils;

import java.util.ArrayList;

public class ProgramList {

    //variables
    private ArrayList<String> program = new ArrayList<String>();
    private ArrayList<String> rowOrder = new ArrayList<String>();
    private ArrayList<String> table = new ArrayList<String>();


    //in Setter method default it will return arraylist change that to add

    public ArrayList<String> getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program.add(program);
    }

    public ArrayList<String> getRowOrder() {
        return rowOrder;
    }

    public void setRowOrder(String rowOrder) {
        this.rowOrder.add(rowOrder);
    }

    public ArrayList<String> getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table.add(table);
    }

}