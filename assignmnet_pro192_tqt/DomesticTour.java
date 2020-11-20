package assignmnet_pro192_tqt;

import java.sql.Date;

public class DomesticTour extends Tour {

    private static final long serialVersionUID = 1L;
    private double tourGuideTip;

    public DomesticTour() {
    }
    
    public DomesticTour(String tourCode, String tourTile, 
            double tourPrice, boolean tourTransport, 
            Date startDate, Date endDate, double tourGuideTip){
        super (tourCode, tourTile, tourPrice, tourTransport, startDate, endDate);
        this.tourGuideTip = tourGuideTip;
    }
    
    public double getTourGuideTip() {
        return tourGuideTip;
    }

    public void setTourGuideTip(double tourGuideTip) {
        this.tourGuideTip = tourGuideTip;
    }
    
    public long numberOfDate(){
        return (getEndDate().getTime() - getStartDate().getTime())/1000 / 60 / 60 / 24;
    }
    @Override
    public double tourCharge(){
        return surcharge() + getTourPrice();
    }
    
    @Override
    public double surcharge(){
        return tourGuideTip*numberOfDate();
    }
    
    @Override
    public String toString(){
        return super.toString() + ", TourGuideTip = " + tourGuideTip  + "]\n"; 
    }
}
