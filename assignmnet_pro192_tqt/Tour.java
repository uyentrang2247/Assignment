package assignmnet_pro192_tqt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Tour implements ITour, Serializable {

	private static final long serialVersionUID = 1L;
	protected String tourCode;
    protected String tourTitle;
    protected double tourPrice;
    protected boolean tourTransport; // true for aircraft, flase for car
    protected Date startDate;
    protected Date endDate;
    
    public Tour() {
    }

    public Tour(String tourCode, String tourTitle, double tourPrice, boolean tourTransport, Date startDate, Date endDate) {
        this.tourCode = tourCode;
        this.tourTitle = tourTitle;
        this.tourPrice = tourPrice;
        this.tourTransport = tourTransport;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getTourCode() {
        return tourCode;
    }
    
    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public boolean isTourTransport() {
        return tourTransport;
    }

    public void setTourTransport(boolean tourTransport) {
        this.tourTransport = tourTransport;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Tour [" + "Tour Code = " + tourCode 
                + ", Title = " + tourTitle 
                + ", Price = " + tourPrice 
                + ", Transport = " + (tourTransport ? "Aircraft" : "Car")
                + ", Start Date = " + sdf.format(startDate) 
                + ", End Date = " + sdf.format(endDate) ;
    }
    
 

    public abstract double surcharge();
}
