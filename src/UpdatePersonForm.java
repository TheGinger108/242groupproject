import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// This class is used to display update person form
public class UpdatePersonForm {
    
    public UpdatePersonForm(){
        // create a frame and set its title and size
        JFrame frame = new JFrame();
        frame.setTitle("Update Person");
        frame.setSize(500,500);
        
        // create panel with GridBagLayout and add to frame
        JPanel panel=new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel,BorderLayout.NORTH);
        
        GridBagConstraints gbc=new GridBagConstraints();
        // create a heading label
        JLabel headingLabel=new JLabel("Update Person");
        headingLabel.setFont(new Font("Times", Font.BOLD, 16));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets=new Insets(10,20,20,20);
        panel.add(headingLabel,gbc);
        
        try{  
            // create connection to database
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonDB","root","root");  
            Statement statement=connection.createStatement();  
            ResultSet resultSet=statement.executeQuery("select * from person");  
             
            // create person ID label and combo box
            JPanel personIDPanel = new JPanel();
            JLabel personIDLabel = new JLabel("Person ID: ");
            JComboBox personIDCombo = new JComboBox();
            // fill entries in personID combo box
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
            
            // add person name label and text field
            JPanel personNamePanel = new JPanel();
            JLabel personNameLabel = new JLabel("Person Name: ");
            JTextField  personNameTextField=new JTextField();
            personNameTextField.setPreferredSize(new Dimension(200,25));
        
            personNamePanel.add(personNameLabel);
            personNamePanel.add(personNameTextField);
            gbc.gridx=0;
            gbc.gridy=2;
            gbc.insets=new Insets(5,10,10,10);
            panel.add(personNamePanel,gbc);
            
            
            // add person salary label and text field
            JPanel personSalaryPanel = new JPanel();
            JLabel personSalaryLabel = new JLabel("Person Salary: ");
            JTextField  personSalaryTextField=new JTextField();
            personSalaryTextField.setPreferredSize(new Dimension(200,25));
        
            personSalaryPanel.add(personSalaryLabel);
            personSalaryPanel.add(personSalaryTextField);
            gbc.gridx=0;
            gbc.gridy=3;        
            panel.add(personSalaryPanel,gbc);
        
        
            // add person state label and combo box
            JPanel personStatePanel = new JPanel();
            JLabel personStateLabel = new JLabel("State Name: ");
            String[] stateNames={"Colorado", "Illinois", "Indiana", "Kentucky", "Massachusetts",
                             "Michigan", "New Hampshire", "North Carolina", "Pennsylvania",
                             "Tennessee", "Utah"};
            JComboBox personStateCombo = new JComboBox(stateNames);
            personStateCombo.setPreferredSize(new Dimension(200,25));
        
            personStatePanel.add(personStateLabel);
            personStatePanel.add(personStateCombo);
            gbc.gridx=0;
            gbc.gridy=4;        
            panel.add(personStatePanel,gbc);
        
            // add person type label and combo box
            JPanel personTypePanel = new JPanel();
            JLabel personTypeLabel = new JLabel("Person Type: ");
            String[] types={"Unmarried Individuals","Married Individuals","Head of Household"};
            JComboBox personTypeCombo = new JComboBox(types);
            personTypeCombo.setPreferredSize(new Dimension(200,25));
            
            personTypePanel.add(personTypeLabel);
            personTypePanel.add(personTypeCombo);
            gbc.gridx=0;
            gbc.gridy=5;        
            panel.add(personTypePanel,gbc);
        
            // add clear, update person and exit button
            JPanel buttonsPanel1 = new JPanel();
            
            JButton clearButton=new JButton("Clear");
            clearButton.setPreferredSize(new Dimension(125, 30));            
         
            JButton updatePersonButton=new JButton("Update Person");
            updatePersonButton.setPreferredSize(new Dimension(125, 30));
        
            JButton exitButton=new JButton("Exit");
            exitButton.setPreferredSize(new Dimension(125, 30));
        
            buttonsPanel1.add(clearButton);
            buttonsPanel1.add(updatePersonButton);
            buttonsPanel1.add(exitButton);
        
            gbc.gridx=0;
            gbc.gridy=6;
            panel.add(buttonsPanel1,gbc);
        
            // action to be performed on clicking clear button
            clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // clear all the entries in the control
                    personIDCombo.setSelectedIndex(-1);
                    personNameTextField.setText("");
                    personSalaryTextField.setText("");
                    personStateCombo.setSelectedIndex(0);
                    personTypeCombo.setSelectedIndex(0);
                }
            });
        
            // action to be performed on clicking exit button
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // open main form and close current form
                    new MainForm();
                    frame.dispose();
                }
            });
            
            // action to be performed on changing the personID in combo box
            personIDCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // check if personIDCombo is selected
                    if(personIDCombo.getSelectedIndex()>=0){
                        try {  
                            // get person details with selected person ID
                            ResultSet resultSet=statement.executeQuery("select * from person where personID="+personIDCombo.getSelectedItem().toString());
                            while(resultSet.next()){
                                // create person object
                                Person person=new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5));
                                // display person details in appropriate field
                                personNameTextField.setText(person.getName());
                                personSalaryTextField.setText(Double.toString(person.getSalary()));
                                personTypeCombo.setSelectedItem(person.getType());
                                personStateCombo.setSelectedItem(person.getState());
                            }
                        } catch (SQLException ex) {
                            // display error message
                            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            
            // action to performed on clicking update person button
            updatePersonButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    // check if all fields entered as valid
                    if(personIDCombo.getSelectedIndex()>=0 && personNameTextField.getText().toString().length()>0 &&
                    personSalaryTextField.getText().toString().length()>0 &&
                    personStateCombo.getSelectedIndex()>=0 && personTypeCombo.getSelectedIndex()>=0){
                        try {  
                            // create connection with mysql database
                            Class.forName("com.mysql.jdbc.Driver");  
                            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonDB","root","root");  
                            Statement statement=connection.createStatement(); 
                            // update details of person
                            int success=statement.executeUpdate("Update Person SET "
                                    +"PersonName = '"+personNameTextField.getText().toString()+"', "
                                    +"PersonSalary="+Double.parseDouble(personSalaryTextField.getText())+", "
                                    +"StateName= '"+personStateCombo.getSelectedItem().toString()+"', "
                                    +"PersonType= '"+personTypeCombo.getSelectedItem().toString()+"' where "
                                    +"PersonID = "+personIDCombo.getSelectedItem().toString());
                            // if update operation is success
                            if(success==1){
                                // display success message
                                JOptionPane.showMessageDialog(panel, "Person information is updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                
                                // clear controls
                                personIDCombo.setSelectedIndex(-1);
                                personNameTextField.setText("");
                                personSalaryTextField.setText("");
                                personStateCombo.setSelectedIndex(0);
                                personTypeCombo.setSelectedIndex(0);
                            }
                            else{
                                // display error message
                                JOptionPane.showMessageDialog(panel, "Cannot update data in database", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } 
                        catch (NumberFormatException ex) {
                            // display error message
                            JOptionPane.showMessageDialog(panel, "Salary must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                        catch (SQLException ex) {
                            // display error message
                            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
                        } 
                        catch (ClassNotFoundException ex) {
                            // display error message
                            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        // display error message
                        JOptionPane.showMessageDialog(panel, "Please fill all details in form", "Error", JOptionPane.ERROR_MESSAGE);
                    }                    
                }
            }); 
            personIDCombo.setSelectedIndex(-1);
        }
        catch (NumberFormatException ex) {
            // display error message
            JOptionPane.showMessageDialog(panel, "Salary must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (SQLException ex) {
            // display error message
            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (ClassNotFoundException ex) {
            // display error message
            JOptionPane.showMessageDialog(panel, "Error in connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        frame.setVisible(true);
    }
}    

