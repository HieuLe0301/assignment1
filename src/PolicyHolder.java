/**
 * @author <Le Ngoc Hieu - s3927205>
 */
import java.util.ArrayList;
public class PolicyHolder extends Customer{
    private ArrayList<String> dependents;
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getInsuranceCard() {
        return insuranceCard;
    }

    public ArrayList<String> getDependents() {
        return dependents;
    }

    public void displayInfo(){
        System.out.println("ID: "+this.id);
        System.out.println("Full Name: "+this.fullName);
        System.out.println("Insurance Card Number: "+this.insuranceCard);
        System.out.println("List of claims: " + this.claimsIDs);
        System.out.println("Dependents: ");
        int i = 1;
        for (String dependent : dependents){
            System.out.println(i + "/ " + dependent);
            i += 1;
        }
    }

}
