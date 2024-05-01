import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car{
    private String carid;
    private String carbrand;
    private String model;
    private double priceperdays;
    private boolean isavilable;
    Car(String carid, String carbrand, String model, double priceperdays) {
        this.carid = carid;
        this.carbrand = carbrand;
        this.model = model;
        this.priceperdays = priceperdays;
        this.isavilable =   true;
    }
    public String getcarId(){
        return carid;
    }
    public String getCarbrand(){
        return carbrand;
    }
    public String getModel(){
        return model;
    }
    public double calculateprice(int days){
        double a= priceperdays * days;
        return a;
    }
    public boolean isIsavilable(){
        return isavilable;
    }
    public void rent(){
        isavilable =false;
    }
    public void returncar(){
        isavilable =true;
    }
}
class Customer{
    private String customerid;
    private String name;

    Customer(String customerid, String name) {
        this.customerid = customerid;
        this.name = name;
    }
    public String getCustomerid(){
        return customerid;
    }
    public String getName() {
        return name;
    }



}
class Rental{
    private Car car;
    private Customer customer;
    private int days;

    Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
    public Car getCar() {
        return car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public int getDays() {
        return days;
    }

}
class rentalSystem{
    private List<Car>cars;
    private List<Customer> customers;
    private List<Rental>rental;
    public rentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rental = new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addcustomer(Customer customer){
        customers.add(customer);
    }
    public void rentcar(Car car,Customer customer,int days){
        if(car.isIsavilable()){
            car.rent();
            rental.add(new Rental(car, customer, days));
        }
        else{
            System.out.println("Car is not avilable for rent !!! ");
        }

    }
    public void returncar(Car car){
        Rental rentaltoremove = null;
        for(Rental rental : rental){
            if (rental.getCar() == car) {
                rentaltoremove=rental;
                break;
            }

        }
        if(rentaltoremove != null){
            rental.remove(rentaltoremove);
        }
        else{
            System.out.println("Car not rented ");
        }

    }
    public void menu(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("===== Car Rental System =====");
            System.out.println("1. rent a car ");
            System.out.println("2. return car ");
            System.out.println("3. Exit ");
            System.out.println("Enter what do think : ");
            int choice = sc.nextInt();

            if(choice==1){
                System.out.println("=== Rent a Car ===");
                System.out.println("Enter your Name : ");
                String customername = sc.nextLine();
                System.out.println("\nAvilable cars at this time ");

                for(Car car: cars){
                    if(car.isIsavilable()){
                        System.out.println(" '"+car.getcarId()+" '"+car.getCarbrand()+"' "+car.getModel()+" '");
                    }
                }
                System.out.println("Enter Car Id you want to rent ");
                String carId= sc.nextLine();

                System.out.println("Rental days Enter number of days ");
                String days = sc.nextLine();

                Customer newcustomer = new Customer("cus"+(customers.size()+1),customername);
                addcustomer(newcustomer);

                Car selectedcar = null;
                for(Car car : cars){
                    if(car.getcarId().equals(carId) && car.isIsavilable()){
                        selectedcar = car;
                        break;
                    }
                }
                if(selectedcar!= null){
                    double totalprice = selectedcar.calculateprice(Integer.parseInt(days));
                    System.out.println("=== Rental Information ===");
                    System.out.println("Customer ID: "+ newcustomer.getCustomerid());
                    System.out.println("Cutsomer Name: "+newcustomer.getName());
                    System.out.println("Car: "+selectedcar.getCarbrand()+" "+selectedcar.getModel());
                    System.out.println("Rental Days: "+days);
                    System.out.println("Total Price: $"+totalprice);

                    System.out.println("Confirm rent (Y/N)");

                    String confirm = sc.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        rentcar(selectedcar,newcustomer, Integer.parseInt(days));
                        System.out.println("Car rent Successfully ");
                    }
                    else{
                        System.out.println("Car rent Cancel ");
                    }

                }else{
                    System.out.println("invalid car selection or not avilable car");
                }
            }else if(choice==2){
                System.out.println("=== return a car ===");
                System.out.println("Enter car id ");
                String carid =  sc.nextLine();
                sc.next();
                Car cartoreturn = null;
                for(Car car : cars){
                    if(car.getcarId().equals(carid) && !car.isIsavilable()){
                        cartoreturn = car;
                        break;
                    }
                }
                if(cartoreturn!= null){
                    Customer customer = null;
                    for(Rental rental : rental){
                        if(rental.getCar()== cartoreturn){
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if(customer != null){
                        returncar(cartoreturn);
                        System.out.println("Car returned Successfully by"+ customer.getName());

                    }
                    else{
                        System.out.println("Car was not rented or invalid information");
                    }
                }else{
                    System.out.println("invalid car Id or car is Not Rented");
                }
            } else if (choice ==3) {
                break;
            }else{
                System.out.println("Thank you to using Our Service");
            }

        }
    }
}
public class Main {
    public static void main(String[] args) {
        rentalSystem rent = new rentalSystem();

        Car car1 = new Car("c001","Toyota","camary",60.0);
        Car car2= new Car("c002","Honda","Accord",100);
        Car car3 = new Car("c003","Mahindra","Thar",150);
        Car car4 = new Car("c004","maruti","swift",200);
        rent.addCar(car1);
        rent.addCar(car2);
        rent.addCar(car3);
        rent.addCar(car4);

        rent.menu();

    }
}
