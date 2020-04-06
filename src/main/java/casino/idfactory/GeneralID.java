package casino.idfactory;

public class GeneralID  {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public GeneralID(String id){
        setId(id);
    }
}
