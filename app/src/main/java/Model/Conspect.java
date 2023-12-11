package Model;

public class Conspect {

    private int id;
    private String ConspectName;

    public Conspect(){

    }
    public Conspect(int id, String conspectName) {
        this.id = id;
        ConspectName = conspectName;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getConspectName() {
        return ConspectName;
    }

    public void setConspectName(String conspectName) {
        ConspectName = conspectName;
    }
}
