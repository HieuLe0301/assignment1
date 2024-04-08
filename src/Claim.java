/**
 * @author <Le Ngoc Hieu - s3927205>
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Claim {
    private String id;
    private LocalDate claimDate;
    private String insuredPerson;
    private String cardNumber;
    private LocalDate examDate;
    private ArrayList<String> documents;
    private double claimAmount;
    private String status;
    private ArrayList<String> receiverBankingInfo;

    public String getId() {
        return id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public ArrayList<String> getDocuments() {
        return documents;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public Claim() {}
    public Claim(String id, LocalDate claimDate, String insuredPerson, String cardNumber, LocalDate examDate, ArrayList<String> documents, double claimAmount, String status, ArrayList<String> receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }
}
