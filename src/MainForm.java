import javax.swing.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class MainForm {
    public static void main(String[] args) {
        new MainForm();
    }
    
    public MainForm(){
        // create a frame and set its title and size
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tax Calculator");
        frame.setSize(500,500);
        
        // create panel with GridBagLayout and add to frame
        JPanel panel=new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel,BorderLayout.NORTH);
        
        GridBagConstraints gbc=new GridBagConstraints();
        // create a heading label
        JLabel headingLabel=new JLabel("Welcome to Tax Calculator");
        headingLabel.setFont(new Font("Times", Font.BOLD, 16));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets=new Insets(10,20,20,20);
        
        // add heading label to panel
        panel.add(headingLabel,gbc);
        
        try{  
            Class.forName("com.mysql.jdbc.Driver");
            // create mysql connection 
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonDB","root","root");  
            Statement statement=connection.createStatement();  
            
            // determine the list of persons from DB
            ResultSet resultSet=statement.executeQuery("select * from person");  
             
        
            // add person ID label and combo box
            JPanel personIDPanel = new JPanel();
            JLabel personIDLabel = new JLabel("Person ID: ");
            JComboBox personIDCombo = new JComboBox();
            while(resultSet.next()){
                personIDCombo.addItem(resultSet.getInt(1));
            }            
            personIDCombo.setEditable(false);        
            personIDCombo.setPreferredSize(new Dimension(200,25));            
            personIDPanel.add(personIDLabel);
            personIDPanel.add(personIDCombo);
            gbc.gridx=0;
            gbc.gridy=1;
            gbc.insets=new Insets(5,10,10,10);
            panel.add(personIDPanel,gbc);
        
            // add clear and calculate button
            JPanel buttonsPanel1 = new JPanel();            
            
            JButton clearButton=new JButton("Clear");
            clearButton.setPreferredSize(new Dimension(125, 30));
            
            JButton calculateTaxButton=new JButton("Calculate Tax");
            calculateTaxButton.setPreferredSize(new Dimension(125, 30));
        
            buttonsPanel1.add(clearButton);
            buttonsPanel1.add(calculateTaxButton);
        
            gbc.gridx=0;
            gbc.gridy=2;
            panel.add(buttonsPanel1,gbc);
        
            JPanel buttonsPanel2 = new JPanel();
            // add add person and update person button
            JButton addPersonButton=new JButton("Add Person");
            addPersonButton.setPreferredSize(new Dimension(125, 30));
        
            JButton updatePersonButton=new JButton("Update Person");
            updatePersonButton.setPreferredSize(new Dimension(125, 30));
        
            buttonsPanel2.add(addPersonButton);
            buttonsPanel2.add(updatePersonButton);
        
            gbc.gridx=0;
            gbc.gridy=3;
            panel.add(buttonsPanel2,gbc);
        
        
            // add result label
            JLabel resultLabel=new JLabel("");
            headingLabel.setFont(new Font("Times", Font.BOLD, 14));
            gbc.gridx=0;
            gbc.gridy=4;
            panel.add(resultLabel,gbc);        
        
            // action to be performed on clicking clear button
            clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // clear the result label and personIDCombo
                    personIDCombo.setSelectedIndex(-1);
                    resultLabel.setText("");
                }
            });
        
            // action to be performed on clicking calculate button
            calculateTaxButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // if personIDCombo selected index atleast 0
                    if(personIDCombo.getSelectedIndex()>=0){
                        try {  
                            // select the person with particular ID
                            ResultSet resultSet=statement.executeQuery("select * from person where personID="+personIDCombo.getSelectedItem().toString());
                            while(resultSet.next()){
                                // create person
                                Person person=new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5));
                                // set result for label
                                resultLabel.setText("<html> <b> Person Details </b> <p>"
                                                    + person.toString()
                                                    + "</p>"
                                                    +"</html>");
                            }
                        } catch (SQLException ex) {
                            // display error message
                            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        // display error message
                        JOptionPane.showMessageDialog(panel, "Select the person first", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            });
        
            // action to be performed on clicking add person button
            addPersonButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // open add person form and close current form
                    new AddPersonForm();
                    frame.dispose();
                }
            });
        
            // action to be performed on clicking update button
            updatePersonButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // open update person form and close current form
                    new UpdatePersonForm();
                    frame.dispose();
                }
            });
        }
        catch(SQLException ex){  
            // display error message
            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (ClassNotFoundException ex) { 
            // display error message
            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // display the frame
        frame.setVisible(true);
    }
}    

