package casino.idfactory;

/**
 * Factory for creation of GeneralID objects.
 * creation of the right object is done by specifying the type to create
 * the specified type is case insensitive
 *
 * when the type is not present, null is returned.
 */
public class IDFactory {
  // TODO
    private static long staticID=985620001;
    public static String GenerateID(String objType){
        String id="";
        switch(objType){
            case "PlayerCard":id+="PC";break;
            case "Bet":id+="BT";break;
            case "GamingMachine":id+="GMC";break;
            case "BettingRound":id+="BTR";break;
            default:break;
        }
        long numericID=staticID;
        staticID+=1;
        return id+numericID;
    }
}
