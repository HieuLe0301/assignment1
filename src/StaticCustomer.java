import java.util.ArrayList;

/**
 * @author <Le Ngoc Hieu - s3927205>
 */
public class StaticCustomer extends Customer {
    private ArrayList<Dependent> dependents = new ArrayList<>();
    public StaticCustomer() {}

    public StaticCustomer(String id, String fullName, String insuranceCard, ArrayList<String> claimsIDs){
        super(id,fullName,insuranceCard,claimsIDs);
    }

}
