/**
 * @author <Le Ngoc Hieu - s3927205>
 */

import java.time.LocalDate;


public class InsuranceCard {
    private String cardNumber;
    private String cardHolder;
    private String policyOwner;
    private LocalDate expirationDate;

    public InsuranceCard(String _cardNumber, String _cardHolder, String _policyOwner, LocalDate _expirationDate) {
        this.cardNumber = _cardNumber;
        this.cardHolder = _cardHolder;
        this.policyOwner = _policyOwner;
        this.expirationDate = _expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void displayInfo(){
        System.out.println("Card number: " + getCardNumber());
        System.out.println("Card holder: " + getCardHolder());
        System.out.println("Policy owner: " + getPolicyOwner());
        System.out.println("Expiration date: "+getExpirationDate());
    }
}
