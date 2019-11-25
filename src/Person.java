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
        stateTaxRate.put("Colorado", 4.63);
        stateTaxRate.put("Illinois", 4.95);
        stateTaxRate.put("Indiana", 3.23);
        stateTaxRate.put("Kentucky", 5.0);
        stateTaxRate.put("Massachusetts", 5.05);
        stateTaxRate.put("Michigan", 4.25);
        stateTaxRate.put("New Hampshire", 5.0);
        stateTaxRate.put("North Carolina", 5.25);
        stateTaxRate.put("Pennsylvania", 3.07);
        stateTaxRate.put("Tennessee", 2.0);
        stateTaxRate.put("Utah", 4.95);

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
