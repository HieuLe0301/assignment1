/**
 * @author <Le Ngoc Hieu - s3927205>
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Customer implements ClaimProcessManager{
    protected String id;
    protected String fullName;
    protected String insuranceCard;
    protected ArrayList<String> claimsIDs;

    public Customer(){}

    public Customer(String id, String fullName, String insuranceCard, ArrayList<String> claimsIDs) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claimsIDs = claimsIDs;
    }



    public String getId() {
        return id;
    }


    public String getFullName() {
        return fullName;
    }


    public String getInsuranceCard() {
        return insuranceCard;
    }


    public ArrayList<String> getClaimsIDs() {
        return claimsIDs;
    }


    public void displayInsuranceCard(ArrayList<InsuranceCard> insuranceCards){
        System.out.println(insuranceCards.get(0).getExpirationDate());
        for (InsuranceCard card : insuranceCards){
            if (card.getCardHolder().equals(this.getId())){
                card.displayInfo();
            }
        }
    }


    @Override
    public void addOne(){
        DataReader datareader = new DataReader();
        String lastClaimID = datareader.getLastClaimID();

        Integer newClaimNumber = Integer.parseInt(lastClaimID.substring(1)) + 1;
        Integer zerosToAdd = 10 - newClaimNumber.toString().length();
        String actualZerosToAdd = "";
        for (int i = 0; i < zerosToAdd; i++) {
            actualZerosToAdd = actualZerosToAdd + "0";
        }
        String newID = "f" + actualZerosToAdd + newClaimNumber.toString();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter claim date in the yyyy-mm-dd format: \n");
        String newClaimDate = scanner.nextLine();

        System.out.println("Enter exam date in the yyyy-mm-dd format: \n");
        String newExamDate = scanner.nextLine();

        System.out.println("Enter the name of the document: \n");
        String newDocument = scanner.nextLine();

        System.out.println("Enter the claim amount: \n");
        String newClaimAmount = scanner.nextLine();

        System.out.println("Enter your bank: \n");
        String newBank = scanner.nextLine();

        System.out.println("Enter your name on the bank card: \n");
        String newName = scanner.nextLine();

        System.out.println("Enter your bank number");
        String newNumber = scanner.nextLine();


        ArrayList<ArrayList<String>> oldClaims = datareader.readClaimsAsListsOfStrings();
        ArrayList<String> newClaimAsListOfStrings = new ArrayList<>();
        newClaimAsListOfStrings.add(newID);
        newClaimAsListOfStrings.add(newClaimDate);
        newClaimAsListOfStrings.add(this.getId());
        newClaimAsListOfStrings.add(this.getInsuranceCard());
        newClaimAsListOfStrings.add(newExamDate);
        newClaimAsListOfStrings.add(newDocument);
        newClaimAsListOfStrings.add(newClaimAmount);
        newClaimAsListOfStrings.add("New");
        newClaimAsListOfStrings.add(newBank);
        newClaimAsListOfStrings.add(newName);
        newClaimAsListOfStrings.add(newNumber);


        oldClaims.add(newClaimAsListOfStrings);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("src/claims.json"),oldClaims);
        } catch (JsonProcessingException e){
            System.out.println("Message from JsonProcessingException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Message from IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(String claimID) {
        DataReader dataReader = new DataReader();
        ArrayList<ArrayList<String>> claimsAsListsOfStrings = dataReader.readClaimsAsListsOfStrings();
        for (ArrayList<String> claim : claimsAsListsOfStrings){
            if (claim.get(0).equals(claimID)){
                claimsAsListsOfStrings.remove(claim);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("src/claims.json"),claimsAsListsOfStrings);
        } catch (JsonProcessingException e){
            System.out.println("Message from JsonProcessingException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Message from IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public Claim getOne(){
        System.out.println("Enter needed ID: ");
        Scanner scanner1 = new Scanner(System.in);
        String claimID =  scanner1.nextLine();
        DataReader dr = new DataReader();
        return dr.readOnesOneClaim(claimID);
    }

    @Override
    public ArrayList<Claim> getAll(){
        DataReader dr = new DataReader();
        return dr.readOnesAllClaims(this.getId());
    }
}
