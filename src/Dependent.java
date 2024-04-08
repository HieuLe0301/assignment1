/**
 * @author <Le Ngoc Hieu - s3927205>
 */

import java.util.ArrayList;


public class Dependent extends Customer {


    public Dependent(){}

    public Dependent(String id, String fullName, String insuranceCard, ArrayList<String> claimsIDs){
        super(id,fullName,insuranceCard,claimsIDs);
    }


    public void displayInfo(){
        System.out.println("ID: "+this.id);
        System.out.println("Full Name: "+this.fullName);
        System.out.println("Insurance Card Number: "+this.insuranceCard);
        System.out.println("List of claims: " + this.claimsIDs);
    }

}
