package assignmnet_pro192_tqt;

import java.util.Date;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TourList {

    private ArrayList<Tour> list = new ArrayList<>();
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ArrayList<Tour> getList() {
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    Scanner sc = new Scanner(System.in);

    public void menuAdd() {
        System.out.println("========================================");
        System.out.println("1. Add Domestic Tour");
        System.out.println("2. Add International Tour");
        System.out.println("3. Exit");
        System.out.println("========================================");
        System.out.print("Input choice: ");

    }

    public void addTour() {
        int choice;

        do {
            menuAdd();
            choice = sc.nextInt();

            if (choice != 1 && choice != 2) {
                break;
            }
            Tour tours = null;
            switch (choice) {
                case 1:
                    tours = new DomesticTour();
                    break;
                case 2:
                    tours = new InternationalTour();
                    break;
            }

            String tourCode;
            boolean flag;
            sc.nextLine();
            do {
                flag = false;
                System.out.print("Input tourCode: ");
                tourCode = sc.nextLine();
                if (!checkTourCode(tourCode)) {
                    System.out.println("tourCode is already exist. Try again!");
                    flag = true;
                }
            } while (flag);

            tours.setTourCode(tourCode);

            System.out.print("Input tourTitle: ");
            tours.setTourTitle(sc.nextLine());

            System.out.print("Input tourPrice: ");
            tours.setTourPrice(sc.nextDouble());

            System.out.print("Input tourTransport(true: Aircraft | false: Car): ");
            tours.setTourTransport(sc.nextBoolean());

            System.out.print("Input Start Date(dd/MM/yyyy): ");
            boolean flagDate; //break do while when iput valid day
            do {
                flagDate = false;
                try {
                    tours.setStartDate(sdf.parse(sc.next()));
                } catch (ParseException e) {
                    System.out.println("Incorrect Date Format! Please try again!");
                    flagDate = true;
                }

            } while (flagDate);

            sc.nextLine();
            System.out.print("Input End Date(dd/MM/yyyy): ");
            do {
                flagDate = false;
                try {
                    tours.setEndDate(sdf.parse(sc.nextLine()));
                } catch (ParseException e) {
                    System.out.println("Incorrect Date Format! Please try again!");
                    flagDate = true;
                }

            } while (flagDate);

            switch (choice) {
                case 1:
                    System.out.print("Input tourGuideTip: ");
                    ((DomesticTour) tours).setTourGuideTip(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Input Entry Fee: ");
                    ((InternationalTour) tours).setEntryFee(sc.nextDouble());

                    System.out.print("Input Aviation Tax: ");
                    ((InternationalTour) tours).setAviationTax(sc.nextDouble());
                    break;
            }

            list.add(tours);
        } while (true);
    }

    public ArrayList<Tour> DomesTourList() {
        ArrayList<Tour> m = new ArrayList<>();
        for (Tour v : list) {
            if (v instanceof DomesticTour) {
                m.add(v);
            }
        }
        return m;
    }

    public double averageInterToursCharge() {
        double average = 0;
        int count = 0;
        for (Tour v : list) {
            if (v instanceof InternationalTour) {
                count++;
                average += ((InternationalTour) v).tourCharge();
            }
        }
        if (count == 0) {
            return 0;
        }
        return average / count;
    }

    public void tourMenuSearch() {
        System.out.println("-----------------------");
        System.out.println("1.Search by tour's code");
        System.out.println("2.Search by title");
        System.out.println("3.Search by start date");
        System.out.println("4.Search by transport");
        System.out.println("5.Exit");
        System.out.println("----------------------");
        System.out.println("Input choice: ");
    }

    public void searchTour() {
        int choice;
        do {
            tourMenuSearch();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Input tourCode to search: ");
                    String tourCodeSearch = sc.nextLine();
                    Tour t = searchTourCode(tourCodeSearch);
                    if (t != null) {
                        System.out.println("There is one tour that match your search:");
                        System.out.println(t);
                    } else {
                        System.out.println("tourCode doesn't exist.");
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Input tourTitle to search: ");
                    String tourTitleSearch = sc.nextLine();
                    ArrayList<Tour> arrListTourTitle = searchTourTitle(tourTitleSearch);
                    if (!arrListTourTitle.isEmpty()) {
                        System.out.println("These are tours that match your search:");
                        System.out.println(arrListTourTitle);
                    } else {
                        System.out.println("tourTitle doesn't exist.");
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Input the start day of tours to search: ");
                    Date tourStartDateSearch = null;
                    boolean flag; //break do while when iput valid day
                    do {
                        flag = false;
                        try {
                            tourStartDateSearch = sdf.parse(sc.nextLine());
                        } catch (ParseException e) {
                            e.getMessage();
                            flag = true;
                        }
                    } while (flag);

                    ArrayList<Tour> arrListTourStartDate = searchTourStartDate(tourStartDateSearch);
                    if (arrListTourStartDate.isEmpty()) {
                        System.out.println("The tour with given start day doesn't exist.");
                    } else {
                        System.out.println("These are tours that match your search:");
                        System.out.println(arrListTourStartDate);
                    }
                    break;
                case 4:
                    sc.nextLine();
                    System.out.print("Input (true= aircraft, false= car) to search: ");
                    boolean tourTransportSearch = sc.nextBoolean();
                    ArrayList<Tour> arrListTourTransport = searchTourTransport(tourTransportSearch);
                    if (!arrListTourTransport.isEmpty()) {
                        System.out.println("These are tours that match your search:");
                        System.out.println(arrListTourTransport);
                    } else {
                        System.out.println("tourTransport doesn't exist.");
                    }
                    break;
                default:
                    break;
            }
        } while (choice < 5 && choice > 0);
    }

    public Tour searchTourCode(String tourCodeSearch) {
        for (Tour v : list) {
            if (v.getTourCode().equals(tourCodeSearch)) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Tour> searchTourTitle(String tourTitleSearch) {
        ArrayList<Tour> arrListTourTitle = new ArrayList<>();
        for (Tour v : list) {
            if (v.getTourTitle().equals(tourTitleSearch)) {
                arrListTourTitle.add(v);
            }
        }
        return arrListTourTitle;
    }

    public ArrayList<Tour> searchTourStartDate(Date tourStartDateSearch) {
        ArrayList<Tour> arrListTourStartDate = new ArrayList<>();
        for (Tour v : list) {
            if (v.getStartDate().equals(tourStartDateSearch)) {
                arrListTourStartDate.add(v);
            }
        }
        return arrListTourStartDate;
    }

    public ArrayList<Tour> searchTourTransport(boolean tourTransportSearch) {
        ArrayList<Tour> arrListTourTransport = new ArrayList<>();
        for (Tour v : list) {
            if (v.isTourTransport() == tourTransportSearch) {
                arrListTourTransport.add(v);
            }
        }
        return arrListTourTransport;
    }

    public Tour searchFirstOccurHavingMimTourCharge() {
        int i, flag = 0;
        double minTourCharge = list.get(0).tourCharge();
        for (i = 1; i < list.size(); i++) {
            if (list.get(i).tourCharge() > minTourCharge) {
                minTourCharge = list.get(i).tourCharge();
                flag = i;
            }
        }
        return list.get(flag);
    }

    public void removeTourCode(String tourCode) {
        for (Tour v : list) {
            if (v.getTourCode().equals(tourCode)) {
                list.remove(v);
            }
        }
    }

    public boolean checkTourCode(String tourCode) {
        int i = 0;
        for (Tour v : list) {
            if (v.getTourCode().equals(tourCode)) {
                i++;
            }
        }
        return i == 0; //Code checkTourCode nay nó luôn check lại cái đã có
        //nên i == 0 thì chưa xuất hiện, i != 0 thì đã tồn tại rồi.
    }

    public int removeTourDate(Date date) {
        int i = 0;

        for (Tour v : list) {
            if (v.getStartDate().compareTo(date) <= 0) {
                list.remove(v);
                i++;
            }
        }
        return i;
    }

    public void sortTour() {
        ArrayList<Tour> temp = new ArrayList<>();
        temp.addAll(list);
        System.out.println("Sort in descending order of Tour's price: ");
        Collections.sort(temp, new SortByPrice());
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i));
        }
        System.out.println("Sort in ascending order of tour's title: ");
        Collections.sort(temp, new SortByTitle());
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i));

        }

    }

    public void menuUpdate() {
        System.out.println("========================================");
        System.out.println("What information you want to change?");
        System.out.println("0. Quit");
        System.out.println("1. Tour code");
        System.out.println("2. Tour title");
        System.out.println("3. Tour price");
        System.out.println("4. Tour transport");
        System.out.println("5. Start date");
        System.out.println("6. End date");
        System.out.println("7. Tour guide tip (Domestic tour only)");
        System.out.println("8. Aviation tax (International tour only)");
        System.out.println("9. Entry fee (International tour only)");
        System.out.println("========================================");
        System.out.print("Input choice: ");
    }

    public void updateTour() {
        System.out.print("ID: ");
        String ID = sc.nextLine();
        Tour tour = searchTourCode(ID);
        if (tour == null) {
            System.out.println("Your tourcode doesn't exist!");
        } else {
            boolean isDomestic = (tour instanceof DomesticTour ? true : false);
            int choise;
            do {
                menuUpdate();
                choise = sc.nextInt();
                if (choise == 0) {
                    break;
                }
                switch (choise) {
                    case 1:
                        String tourCode;
                        boolean flag;
                        sc.nextLine();
                        do {
                            flag = false;
                            System.out.print("Input tourCode: ");
                            tourCode = sc.nextLine();
                            if (!checkTourCode(tourCode)) {
                                System.out.println("tourCode is already exist. Try again!");
                                flag = true;
                            }
                        } while (flag);
                        tour.setTourCode(tourCode);
                        break;
                    case 2:
                        System.out.print("Enter new tour title: ");
                        sc.nextLine();
                        tour.setTourTitle(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter new tour price");
                        tour.setTourPrice(sc.nextDouble());
                        break;
                    case 4:
                        System.out.print("Enter new tourTransport(true: Aircraft | false: Car): ");
                        tour.setTourTransport(sc.nextBoolean());
                        break;
                    case 5:
                        System.out.print("Enter new Start Date(dd/MM/yyyy): ");
                        boolean flagDate; //break do while when iput valid day
                        do {
                            flagDate = false;
                            try {
                                tour.setStartDate(sdf.parse(sc.next()));
                            } catch (ParseException e) {
                                System.out.println("Incorrect Date Format! Please try again!");
                                flagDate = true;
                            }

                        } while (flagDate);
                        break;
                    case 6:
                        System.out.print("Enter new End Date(dd/MM/yyyy): ");
                        do {
                            flagDate = false;
                            try {
                                tour.setEndDate(sdf.parse(sc.nextLine()));
                            } catch (ParseException e) {
                                System.out.println("Incorrect Date Format! Please try again!");
                                flagDate = true;
                            }

                        } while (flagDate);
                        break;
                    case 7:
                        if (isDomestic) {
                            System.out.print("Input tourGuideTip: ");
                            ((DomesticTour) tour).setTourGuideTip(sc.nextDouble());
                        } else {
                            System.out.println("This option only for domestic tour");
                        }
                        break;
                    case 8:
                        if (!isDomestic) {
                            System.out.print("Input Entry Fee: ");
                            ((InternationalTour) tour).setEntryFee(sc.nextDouble());
                        } else {
                            System.out.println("This option only for International tour");
                        }
                        break;
                    case 9:
                        if (!isDomestic) {
                            System.out.print("Input Aviation Tax: ");
                            ((InternationalTour) tour).setAviationTax(sc.nextDouble());
                        } else {
                            System.out.println("This option only for International tour");
                        }
                        break;
                    default:
                        System.out.println("Your option is invalid! Try again!");
                }

            } while (choise >= 1 && choise <= 8);
        }
    }

    public void saveFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Tourdata.txt"));
            for (Tour v : list) {
                oos.writeObject(v);
            }
            oos.close();

        } catch (NotSerializableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFile() {
        ArrayList<Tour> inTour = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Tourdata.txt"));
            while (true) {
                Tour tours = null;
                try {
                    tours = (Tour) ois.readObject();
                    System.out.println(tours);

                } catch (Exception e) {
                    System.out.println("Read complete!");
                    break;
                }
                if (tours != null) {
                    inTour.add(tours);
                }
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
