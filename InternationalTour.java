package assignmnet_pro192_tqt;

import java.sql.Date;

public class InternationalTour extends Tour {

    private static final long serialVersionUID = 1L;
    private double aviationTax;
    private double entryFee;

    public InternationalTour() {
    }
    
    
    public InternationalTour(String tourCode, String tourTile, 
            double tourPrice, boolean tourTransport, 
            Date startDate, Date endDate, double aviationTax, double entryFee){
        super (tourCode, tourTile, tourPrice, tourTransport, startDate, endDate);
        this.aviationTax = aviationTax;
        this.entryFee = entryFee;
    }

    public InternationalTour(String tourCode, String tourTile, 
            double tourPrice, boolean tourTransport, 
            Date startDate, Date endDate){
        super (tourCode, tourTile, tourPrice, tourTransport, startDate, endDate);
    }

    public double getAviationTax() {
        return aviationTax;
    }

    public void setAviationTax(double aviationTax) {
        this.aviationTax = aviationTax;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }
    @Override
    public double tourCharge(){
        return surcharge() + getTourPrice();
    }
    
    @Override
    public double surcharge(){
        return aviationTax + entryFee;
    }
   
    @Override
    public String toString(){
        return super.toString() 
                + ", AivationTax = " + aviationTax 
                + ", entryFee = " + entryFee + "]\n";
    }
}
