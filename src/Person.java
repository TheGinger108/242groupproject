import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

// Person class is used to used to store the details of person
public class Person {
    // attributes of class
    private int ID;
    private String name;
    private double salary;
    private String state;
    private String type;

    // constructor of class
    public Person(int ID, String name, double salary, String state, String type) {
        this.ID = ID;
        this.name = name;
        this.salary = salary;
        this.state = state;
        this.type = type;
    }

    // getter for person ID
    public int getID() {
        return ID;
    }

    // setter for person ID
    public void setID(int ID) {
        this.ID = ID;
    }

    // getter for person name
    public String getName() {
        return name;
    }

    // setter for person name
    public void setName(String name) {
        this.name = name;
    }

    // getter for person salary
    public double getSalary() {
        return salary;
    }

    // setter for person salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // getter for person state
    public String getState() {
        return state;
    }

    // setter for person state
    public void setState(String state) {
        this.state = state;
    }

    // getter for person type
    public String getType() {
        return type;
    }

    // setter for person type
    public void setType(String type) {
        this.type = type;
    }
    
    // this function is used to calculate the federal tax
    private double getFederalTax(){
        int[] categoryRate={10,12,22,24,32,35,37};
        int category=0;
        // determine the category to which the person income belongs to
        if(type.equals("Unmarried Individuals")){
            if(salary>510300){
                category=6;
            }
            else if(salary>204100){
                category=5;
            }
            else if(salary>160725){
                category=4;
            }
            else if(salary>84200){
                category=3;
            }
            else if(salary>39475){
                category=2;
            }
            else if(salary>9700){
                category=1;
            }
        }
        else if(type.equals("Married Individuals")){
            if(salary>612350){
                category=6;
            }
            else if(salary>408200){
                category=5;
            }
            else if(salary>321450){
                category=4;
            }
            else if(salary>168400){
                category=3;
            }
            else if(salary>78950){
                category=2;
            }
            else if(salary>19400){
                category=1;
            }
        }
        else{
            if(salary>510300){
                category=6;
            }
            else if(salary>204100){
                category=5;
            }
            else if(salary>160700){
                category=4;
            }
            else if(salary>84200){
                category=3;
            }
            else if(salary>52850){
                category=2;
            }
            else if(salary>13850){
                category=1;
            }
        }
        // determine and return federal tax
        double federalTax=(categoryRate[category]*salary)/100.0;
        
        return federalTax;
    }
    
    // this function is used to calculate state tax
    public double getStateTax(){
        // store state name and its corresponding percentage
        Map<String,Double> stateTaxRate=new HashMap<>();
        stateTaxRate.put("Alabama", 4.00);
        stateTaxRate.put("Alaska", 0.00);
        stateTaxRate.put("Arizona", 5.60);
        stateTaxRate.put("Arkansas", 6.50);
        stateTaxRate.put("California", 7.25);
        stateTaxRate.put("Colorado", 2.90);
        stateTaxRate.put("Connecticut", 6.35);
        stateTaxRate.put("Delaware", 0.00 );
        stateTaxRate.put("Florida", 6.00);
        stateTaxRate.put("Georgia", 4.00);
        stateTaxRate.put("Hawaii", 4.00);
        stateTaxRate.put("Idaho", 6.00);
        stateTaxRate.put("Illinois", 6.25);
        stateTaxRate.put("Indiana", 7.00);
        stateTaxRate.put("Iowa", 6.00);
        stateTaxRate.put("Kansas", 6.50);
        stateTaxRate.put("Kentucky", 6.00);
        stateTaxRate.put("Louisiana", 4.45);
        stateTaxRate.put("Maine", 5.50);
        stateTaxRate.put("Maryland", 6.00);
        stateTaxRate.put("Massachusetts", 6.25);
        stateTaxRate.put("Michigan", 6.00);
        stateTaxRate.put("Minnesota", 6.88);
        stateTaxRate.put("Mississippi", 7.00);
        stateTaxRate.put("Missouri", 4.23);
        stateTaxRate.put("Montana", 0.00 );
        stateTaxRate.put("Nebraska", 5.50);
        stateTaxRate.put("Nevada", 6.85);
        stateTaxRate.put("New Hampshire", 0.00);
        stateTaxRate.put("New Jersey", 6.63);
        stateTaxRate.put("New Mexico", 5.13);
        stateTaxRate.put("New York", 4.00);
        stateTaxRate.put("North Carolina", 4.75);
        stateTaxRate.put("North Dakota", 5.00);
        stateTaxRate.put("Ohio", 5.75);
        stateTaxRate.put("Oklahoma", 4.50);
        stateTaxRate.put("Oregon", 0.00);
        stateTaxRate.put("Pennsylvania", 6.00);
        stateTaxRate.put("Rhode Island", 7.00);
        stateTaxRate.put("South Carolina", 6.00);
        stateTaxRate.put("South Dakota", 4.50);
        stateTaxRate.put("Tennessee", 7.00);
        stateTaxRate.put("Texas", 6.25);
        stateTaxRate.put("Utah", 5.95);
        stateTaxRate.put("Vermont", 6.00);
        stateTaxRate.put("Virginia", 5.30);
        stateTaxRate.put("Washington", 6.50);
        stateTaxRate.put("West Virginia", 6.00);
        stateTaxRate.put("Wisconsin", 4.95);
        stateTaxRate.put("Wyoming", 4.00);

        // determine and return the state tax
        return (stateTaxRate.get(state)*salary)/100.0;
    }
    
    // this function is used to return the details of the person as string
    @Override
    public String toString(){
        DecimalFormat moneyFormat=new DecimalFormat("###,###.##");
        double federalTax=getFederalTax();
        double stateTax=getStateTax();
        double totalTax=federalTax+stateTax;
        return "Person ID: "+ID+ "<br/> " 
               + "Person Name: "+name+ "<br/> " 
               + "Person State: "+state+ "<br/> " 
               + "Person Type: "+type+ "<br/> " 
               + "Person Salary: $"+salary+ "<br/> " 
               + "Federal Tax: $"+moneyFormat.format(federalTax)+ "<br/> " 
               + "State Tax: $"+moneyFormat.format(stateTax)+ "<br/> " 
               + "Total Tax: $"+moneyFormat.format(totalTax)+ "<br/> ";
    }
}
