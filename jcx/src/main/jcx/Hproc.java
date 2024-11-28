package jcx;

public abstract class Hproc extends Base {

    public abstract void action();

    public String getState(){
        return "state";
    }

    public String[][] getTableData(){
        return new String[][]{};
    }

}
