import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

// This class is used to display add person form
public class AddPersonForm {
    
    public AddPersonForm(){
        // create a frame and set its title and size
        JFrame frame = new JFrame();
        frame.setTitle("Add Person");
        frame.setSize(500,500);
        
        // create panel with GridBagLayout and add to frame
        JPanel panel=new JPanel(new GridBagLayout());
        frame.getContentPane().add(panel,BorderLayout.NORTH);
        
        GridBagConstraints gbc=new GridBagConstraints();
        // create a heading label
        JLabel headingLabel=new JLabel("Add Person");
        headingLabel.setFont(new Font("Times", Font.BOLD, 16));
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets=new Insets(10,20,20,20);
        panel.add(headingLabel,gbc);
        
        // add person name label and text field
        JPanel personNamePanel = new JPanel();
        JLabel personNameLabel = new JLabel("Person Name: ");
        JTextField  personNameTextField=new JTextField();
        personNameTextField.setPreferredSize(new Dimension(200,25));
        
        personNamePanel.add(personNameLabel);
        personNamePanel.add(personNameTextField);
        gbc.gridx=0;
        gbc.gridy=1;
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
        gbc.gridy=2;        
        panel.add(personSalaryPanel,gbc);
        
        // add person state label and text field
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
        gbc.gridy=3;        
        panel.add(personStatePanel,gbc);
        
        // add person type label and text field
        JPanel personTypePanel = new JPanel();
        JLabel personTypeLabel = new JLabel("Person Type: ");
        String[] types={"Unmarried Individuals","Married Individuals","Head of Household"};
        JComboBox personTypeCombo = new JComboBox(types);
        personTypeCombo.setPreferredSize(new Dimension(200,25));
        
        personTypePanel.add(personTypeLabel);
        personTypePanel.add(personTypeCombo);
        gbc.gridx=0;
        gbc.gridy=4;        
        panel.add(personTypePanel,gbc);
        
        
        // add clear, add person and exit button
        JPanel buttonsPanel1 = new JPanel();
        JButton clearButton=new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(125, 30));
            
         
        JButton addPersonButton=new JButton("Add Person");
        addPersonButton.setPreferredSize(new Dimension(125, 30));
        
        JButton exitButton=new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(125, 30));
        
        buttonsPanel1.add(clearButton);
        buttonsPanel1.add(addPersonButton);
        buttonsPanel1.add(exitButton);
        
        gbc.gridx=0;
        gbc.gridy=5;
        panel.add(buttonsPanel1,gbc);
        
        // action to be performed on clicking clear button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // clear all the text fields and combo box
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
        
        // action to be performed on clicking add button
        addPersonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // check if all data entered is valid
                if(personNameTextField.getText().toString().length()>0 &&
                   personSalaryTextField.getText().toString().length()>0 &&
                   personStateCombo.getSelectedIndex()>=0 && personTypeCombo.getSelectedIndex()>=0){
                        try {  
                            // create connection to database
                            Class.forName("com.mysql.jdbc.Driver");  
                            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PersonDB","root","root");  
                            Statement statement=connection.createStatement(); 
                            // insert data into database
                            int success=statement.executeUpdate("Insert into Person(PersonName,PersonSalary,StateName,PersonType) values('"+
                                    personNameTextField.getText().toString()+"', "+Double.parseDouble(personSalaryTextField.getText())+", '"+
                                    personStateCombo.getSelectedItem()+"', '"+ personTypeCombo.getSelectedItem()+"')");
                            if(success==1){
                                // display success message
                                JOptionPane.showMessageDialog(panel, "Person information is added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                
                                // clear the fields
                                personNameTextField.setText("");
                                personSalaryTextField.setText("");
                                personStateCombo.setSelectedIndex(0);
                                personTypeCombo.setSelectedIndex(0);
                            }
                            else{
                                // display error message
                                JOptionPane.showMessageDialog(panel, "Cannot insert data to database", "Error", JOptionPane.ERROR_MESSAGE);
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
        // display the frame
        frame.setVisible(true);
    }
}    

