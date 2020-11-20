package assignmnet_pro192_tqt;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment_PRO192_TQT {

    public static void showMenu() {
        System.out.println("--------------------------------");
        System.out.println("1.Add new tours to the list");
        System.out.println("2.List all tours");
        System.out.println("3.List all domestic tours in the system");
        System.out.println("4.Compute the average tour charge of all international tours");
        System.out.println("5.Search tour");
        System.out.println("6.Search the first occurrence of the tour having minimum tour charge ");
        System.out.println("7.Remove the tour having given code");
        System.out.println("8.Remove tours with start date after the specified date");
        System.out.println("9.Sort all tours of list by multiple levels in descending order of tour's price and then in ascending order of tour's title");
        System.out.println("10.Update tour information");
        System.out.println("11.Save tours from file");
        System.out.println("12.Load tours from file");
        System.out.println("13.Quit");
        System.out.println("----------------------------------");
        System.out.print("Please enter your choice: ");

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TourList tourlist = new TourList();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int choice;
        do {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    tourlist.addTour();
                    break;
                case 2:
                    ArrayList<Tour> t1 = tourlist.getList();
                    System.out.println("All tours in the system: ");
                    for(Tour v : t1){
                        System.out.println(v);
                    }
                    break;
                case 3:
                {
                    ArrayList<Tour> t2 = tourlist.DomesTourList();
                    System.out.println("All domestic tours in the system: ");
                    
                    for(Tour v : t2){
                        print
                    }
                    break;
                }
                case 4:
                    System.out.println("Average tour charge of all international tours is: " + tourlist.averageInterToursCharge());
                    break;
                case 5:
                    tourlist.searchTour();
                    break;
                case 6:
                    System.out.println("The first occurrence of the tour having minimum tour charge is:");
                    System.out.println(tourlist.searchFirstOccurHavingMimTourCharge());
                    break;
                case 7:
                    System.out.print("Input tourCode to remove: ");
                    String tourCode = sc.nextLine();
                    if (tourlist.checkTourCode(tourCode)) {
                        tourlist.removeTourCode(tourCode);
                        System.out.println("Remove Success!");
                    } else {
                        System.out.println("tourCode doesn't exist.");
                    }
                    break;
                case 8:
                    System.out.println("Input a date to remove all tours'start date after that: ");
                    String date = sc.nextLine(); 
                    boolean flag; //break do while when iput valid day
                    int count = 0;
                    //input date and check if it's valid
                    do{
                        flag = false;
                        try {
                            count = tourlist.removeTourDate(sdf.parse(date));
                        } catch (Exception e) {
                            e.getMessage();
                            flag = true;
                        }
                    } while(flag);
                    
                    if (count > 0) {
                        System.out.println("Remove Success " + count + "tours.");
                    } else {
                        System.out.println("Remove 0 tour.");
                    }
                    break;
                case 9:
                    tourlist.sortTour();
                    break;
                case 10:
                    break;
                case 11: 
                    tourlist.saveFile();
                    break;
                case 12:
                    tourlist.loadFile();
                    break;
                default:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice < 13 && choice > 0);
    }

}
