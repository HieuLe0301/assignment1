/**
 * @author <Le Ngoc Hieu - s3927205>
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataReader {
    public ArrayList<Dependent> readDependents() {
        ArrayList<Dependent> dependents = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dependents = objectMapper.readValue(new File("src/dependents.json"), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Dependent.class));
        } catch (IOException e) {
            System.out.println("Got an I/O message: "+ e.getMessage());
            e.printStackTrace();
        }
        return dependents;
    }

    public ArrayList<PolicyHolder> readPolicyHolders() {
        ArrayList<PolicyHolder> policyHolders = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            policyHolders = objectMapper.readValue(new File("src/policyholders.json"), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, PolicyHolder.class));
        } catch (IOException e) {
            System.out.println("Got an I/O message: "+ e.getMessage());
            e.printStackTrace();
        }
        return policyHolders;
    }

    public ArrayList<InsuranceCard> readInsuranceCards(){

        ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ArrayList<String[]> insuranceCardsAllString = new ArrayList<>();
            insuranceCardsAllString = objectMapper.readValue(new File("src/insuranceCards.json"),objectMapper.getTypeFactory().constructType(new TypeReference<List<String[]>>() {}));
            for (String[] card : insuranceCardsAllString) {
                insuranceCards.add(new InsuranceCard(card[0],card[1],card[2], LocalDate.parse(card[3])));
            }
        } catch (IOException e) {
            System.out.println("Got an I/O message: "+ e.getMessage());
            e.printStackTrace();
        }
        return insuranceCards;
    }

    public ArrayList<ArrayList<String>> readClaimsAsListsOfStrings(){
        ArrayList<ArrayList<String>> allStringClaims = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            allStringClaims = objectMapper.readValue(new File("src/claims.json"), objectMapper.getTypeFactory().constructType(new TypeReference<List<List<String>>>() {}));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return allStringClaims;
    }

    public ArrayList<Claim> readClaims(){
        ArrayList<Claim> claims = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ArrayList<String[]> allStringClaims = objectMapper.readValue(new File("src/claims.json"),objectMapper.getTypeFactory().constructType(new TypeReference<List<String[]>>() {}));

            for (String[] c : allStringClaims){
                ArrayList<String> documents = new ArrayList<>(Arrays.asList(c[0],c[3],c[5]));
                ArrayList<String> receiverBankInfo = new ArrayList<>(Arrays.asList(c[8],c[9],c[10]));
                claims.add(new Claim(c[0],
                        LocalDate.parse(c[1]),
                        c[2],
                        c[3],
                        LocalDate.parse(c[4]),
                        documents,
                        Double.parseDouble(c[6]),
                        c[7],
                        receiverBankInfo));
            }
        } catch (IOException e){
            System.out.println("Got an I/O message: "+ e.getMessage());
            e.printStackTrace();
        }

        return claims;
    }

    public ArrayList<Claim> readOnesAllClaims (String cid) {
        ArrayList<Claim> onesClaims = new ArrayList<>();
        ArrayList<Claim> claims = this.readClaims();

        for (Claim claim : claims) {
            if (claim.getInsuredPerson().equals(cid)){
                onesClaims.add(claim);
            }
        }
        return onesClaims;
    }

    public Claim readOnesOneClaim(String claimID) {
        Claim neededClaim = new Claim();
        ArrayList<Claim> claims = this.readClaims();
        for (Claim claim : claims) {
            if (claim.getId().equals(claimID)) {
                neededClaim = claim;
            }
        }
        return neededClaim;
    }


    public String getLastClaimID(){
        ArrayList<Claim> claims = this.readClaims();
        return claims.get(claims.size()-1).getId();
    }
}
