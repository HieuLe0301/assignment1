/**
 * @author <Le Ngoc Hieu - s3927205>
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
public class Main {
    public static void main(String[] args) {
        ArrayList<Dependent> dependents = new ArrayList<>();
        ArrayList<PolicyHolder> policyHolders = new ArrayList<>();
        ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
        ArrayList<Claim> claims = new ArrayList<>();

        // Read dependents, policyholders, insurance card, and claims from JSON files
        DataReader dataReader = new DataReader();
        dependents = dataReader.readDependents();
        policyHolders = dataReader.readPolicyHolders();
        insuranceCards = dataReader.readInsuranceCards();
        claims = dataReader.readClaims();

        //current user variables
        PolicyHolder currentPolicyHolder = new PolicyHolder();
        Dependent currentDependent = new Dependent();

        int k = 0;
        while (true) {
            if (k == 0){
                System.out.println("You are: ");
                System.out.println("1/ Policyholder");
                System.out.println("2/ Dependent");
                System.out.println("X/ Log out\n");

                Scanner scanner1 = new Scanner(System.in);
                String result = scanner1.nextLine();
                if (result.equals("1")){
                    k = 1;
                } else if (result.equals("2")) {
                    k = 2;
                } else if (result.equals("x")) {
                    k = -1;
                } else {
                    System.out.println("result equals nothing");
                    break;
                }
            } else if (k == 1) {                                                //Policyholder menu
                currentDependent = new Dependent();
                System.out.println("Hello PolicyHolder, enter your id: \n");
                Scanner scanner = new Scanner(System.in);
                String enteredID = scanner.nextLine();

                for (PolicyHolder policyHolder : policyHolders) {
                    if (policyHolder.getId().equals(enteredID)) {
                        currentPolicyHolder = policyHolder;
                    }
                }
                System.out.println("This should print out your info, if they're null, re-run the program");
                System.out.println(currentPolicyHolder.getId());
                System.out.println(currentPolicyHolder.getFullName());
                System.out.println(currentPolicyHolder.getInsuranceCard());
                System.out.println(currentPolicyHolder.getClaimsIDs());
                System.out.println(currentPolicyHolder.getDependents());
                k = 5;
            } else if (k == 2) {                                                //Dependent Menu
                currentPolicyHolder = new PolicyHolder();
                System.out.println("Hello Dependent, enter your id: \n");
                Scanner scanner2 = new Scanner(System.in);
                String enteredID = scanner2.nextLine();

                for (Dependent dependent : dependents) {
                    if (dependent.getId().equals(enteredID)) {
                        currentDependent = dependent;
                    }
                }
                System.out.println("This should print out your info, if they're null, re-run the program");
                System.out.println(currentDependent.getId());
                System.out.println(currentDependent.getFullName());
                System.out.println(currentDependent.getInsuranceCard());
                System.out.println(currentDependent.getClaimsIDs());

                k = 3;
            } else if (k == 3) {
                System.out.println("\nWhat do you want to do?: ");
                System.out.println("1/ View info");
                System.out.println("2/ View insurance card");
                System.out.println("3/ Work with claims");
                System.out.println("X/ Log out\n");
                Scanner scanner = new Scanner(System.in);
                String dependentAction = scanner.nextLine();
                if (dependentAction.equals("1")) {
                    currentDependent.displayInfo();
                } else if (dependentAction.equals("2")) {
                    currentDependent.displayInsuranceCard(insuranceCards);
                } else if (dependentAction.equals("3")) {
                    k = 4;
                } else if (dependentAction.equals("x")){
                    k = -1;
                }
            } else if (k == 4) {                                            // Actions with Claim Menu
                Customer customer;
                if (currentPolicyHolder.getId() != null) {
                    customer = (Customer) currentPolicyHolder;
                } else {
                    customer = (Customer) currentDependent;
                }


                System.out.println("\nWhat do you want to do?");
                System.out.println("1/ Add claim");
                System.out.println("2/ Update Claim");
                System.out.println("3/ Delete Claim");
                System.out.println("4/ View One Claim");
                System.out.println("5/ View All Claim");
                System.out.println("X/ Back");

                Scanner scanner4 = new Scanner(System.in);
                String actionWithClaim = scanner4.nextLine();

                if (actionWithClaim.equals("1")) {
                    customer.addOne();
                    System.out.println("New Claim added successfully!");
                } else if (actionWithClaim.equals("2")) {
                    break;
                } else if (actionWithClaim.equals("3")) {
                    Scanner scanner5 = new Scanner(System.in);
                    System.out.println("Enter claim ID you want to delete: ");
                    String claimIDToDelete = scanner5.nextLine();
                    customer.deleteOne(claimIDToDelete);
                    System.out.println("Claim deleted successfully!");
                } else if (actionWithClaim.equals("4")) {
                    Claim claim = customer.getOne();
                    System.out.println("The needed claim");
                    System.out.println("ID: " + claim.getId());
                    System.out.println("Claim Date: " + claim.getClaimDate());
                    System.out.println("Insured Person: " + claim.getInsuredPerson());
                    System.out.println("Card Number: " + claim.getCardNumber());
                    System.out.println("Examination Date: " + claim.getExamDate());
                    System.out.println("Documents: ");
                    System.out.println("     Claim ID: " + claim.getDocuments().get(0));
                    System.out.println("     Card Number: " + claim.getDocuments().get(1));
                    System.out.println("     Document: " + claim.getDocuments().get(2));
                    System.out.println("Claim Amount: " + claim.getClaimAmount());
                    System.out.println("Status: " + claim.getStatus());
                    System.out.println("Receiver Banking Information: ");
                    System.out.println("     Bank: " + claim.getReceiverBankingInfo().get(0));
                    System.out.println("     Name: " + claim.getReceiverBankingInfo().get(1));
                    System.out.println("     Number: " + claim.getReceiverBankingInfo().get(2));
                    System.out.println("\n");

                } else if (actionWithClaim.equals("5")) {
                    System.out.println("These are your claims:");
                    int i = 1;

                    for (Claim claim : customer.getAll()) {
                        System.out.println(i);
                        System.out.println("ID: " + claim.getId());
                        System.out.println("Claim Date: " + claim.getClaimDate());
                        System.out.println("Insured Person: " + claim.getInsuredPerson());
                        System.out.println("Card Number: " + claim.getCardNumber());
                        System.out.println("Examination Date: " + claim.getExamDate());
                        System.out.println("Documents: ");
                        System.out.println("     Claim ID: " + claim.getDocuments().get(0));
                        System.out.println("     Card Number: " + claim.getDocuments().get(1));
                        System.out.println("     Document: " + claim.getDocuments().get(2));
                        System.out.println("Claim Amount: " + claim.getClaimAmount());
                        System.out.println("Status: " + claim.getStatus());
                        System.out.println("Receiver Banking Information: ");
                        System.out.println("     Bank: " + claim.getReceiverBankingInfo().get(0));
                        System.out.println("     Name: " + claim.getReceiverBankingInfo().get(1));
                        System.out.println("     Number: " + claim.getReceiverBankingInfo().get(2));
                        System.out.println("\n");
                        i += 1;
                    }
                } else if (actionWithClaim.equals("x")) {
                    if (currentPolicyHolder.getId() != null) {
                        break;
                    } else if (currentDependent.getId() != null) {
                        k = 3;
                    } else {
                        break;
                    }
                }
            } else if (k == 5) {
                System.out.println("\nWhat do you want to do?: ");
                System.out.println("1/ View info");
                System.out.println("2/ View insurance card");
                System.out.println("3/ Work with claims");
                System.out.println("4/ View dependents");
                System.out.println("X/ Log out\n");
                Scanner scanner = new Scanner(System.in);
                String policyHolderAction = scanner.nextLine();
                if (policyHolderAction.equals("1")) {
                    currentPolicyHolder.displayInfo();
                } else if (policyHolderAction.equals("2")) {
                    currentPolicyHolder.displayInsuranceCard(insuranceCards);
                } else if (policyHolderAction.equals("3")) {
                    k = 4;
                } else if (policyHolderAction.equals("4")){

                } else if (policyHolderAction.equals("x")){
                    k = -1;
                }
            } else if (k == -1) {
                System.out.println("Goodbye");
                break;
            }
        }
    }
}

