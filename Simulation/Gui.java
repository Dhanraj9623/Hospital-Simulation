import javax.swing.*;
import java.awt.event.*;
public class Gui{
    boolean stopClicked;
    JFrame frame;
    JPanel panel;
    JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11;
    JTextField field1,field2,field3,field4,field5,field6,field7,field8,field9,field10,field11;
    JButton button;
    JProgressBar progressBar;
    public Gui(){
        stopClicked = false;
        
    }        
    JFrame getframe(){
        return frame;
    }
    JPanel getPanel(){
        return panel;
    }
    JLabel getLabel1(){
        return label1;
    }
    JLabel getLabel2(){
        return label2;
    }
    String getTextField1(){
        return field1.getText();
    }
    JTextField getTextField2(){
        return field2;
    }
    JTextField getTextField3(){
        return field3;
    }
    String getTextField6(){
        return field6.getText();
    }
    String getTextField7(){
        return field7.getText();
    }
    String getTextField8(){
        return field8.getText();
    }
    String getTextField9(){
        return field9.getText();
    }
    String getTextField10(){
        return field10.getText();
    }
    String getTextField11(){
        return field11.getText();
    }
    
    JButton getButton(){
        return button;
    }
    JProgressBar getProgressBar(){
        return progressBar;
    }

    void setField1Text(String x){
        field1.setText(x);
    }
    void setField2Text(String x){
        field2.setText(x);
    }
    void setField3Text(String x){
        field3.setText(x);
    }
    void setField4Text(String x){
        field4.setText(x);
    }
    void setField5Text(String x){
        field5.setText(x);
    }
    void setProgressBarValue(int progress){
        progressBar.setValue(progress);
    }

    void create(){
        frame = new JFrame("Hospital Simulator");
        panel = new JPanel();
        panel.setLayout(null);
        label1 = new JLabel("Simulation Satus");
        label2 = new JLabel("Average waiting Time");
        label3 = new JLabel("Overflow Transfers");
        label4 = new JLabel("Total Emergency Visits");
        label5 = new JLabel("Average Occupancy");
        label6 = new JLabel("Patients");
        label7 = new JLabel("Beds"); 
        label8 = new JLabel("Doctors"); 
        label9 = new JLabel("Staff");
        label10 = new JLabel("Waiting Area Capacity");
        label11 = new JLabel("Simulation Duration");

        field1 = new JTextField();
        field2 = new JTextField();
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();
        field6 = new JTextField("100");
        field7 = new JTextField("40");
        field8 = new JTextField("10");
        field9 = new JTextField("20");
        field10 = new JTextField("30");
        field11 = new JTextField("24");

        button = new JButton("RUN");
        progressBar = new JProgressBar(0,100);
        progressBar.setStringPainted(true);


        label1.setBounds(10, 10, 200, 30);
        field1.setBounds(150, 10, 200, 30);
        label2.setBounds(10, 50, 200, 30);
        field2.setBounds(150, 50, 200, 30);
        label3.setBounds(10, 90, 200, 30);
        field3.setBounds(150, 90, 200, 30);
        label4.setBounds(10, 130, 200, 30);
        field4.setBounds(150, 130, 200, 30);
        label5.setBounds(10, 170, 200, 30);
        field5.setBounds(150, 170, 200, 30);
        label6.setBounds(450, 10, 150, 30);
        field6.setBounds(550, 10, 200, 30);
        label7.setBounds(450, 50, 150, 30);
        field7.setBounds(550, 50, 200, 30);
        label8.setBounds(450, 90, 150, 30);
        field8.setBounds(550, 90, 200, 30);
        label9.setBounds(450, 130,150, 30);
        field9.setBounds(550, 130,200, 30);
        label10.setBounds(400, 170, 150, 30);
        field10.setBounds(550, 170, 200, 30);
        label11.setBounds(420, 210, 150, 30);
        field11.setBounds(550, 210, 200, 30);

        button.setBounds(300,400,100,30);
        progressBar.setBounds(420,400,350,30);


        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);
        panel.add(label10);
        panel.add(label11);

        panel.add(field1);
        panel.add(field2);
        panel.add(field3);
        panel.add(field4);
        panel.add(field5);
        panel.add(field6);
        panel.add(field7);
        panel.add(field8);
        panel.add(field9);
        panel.add(field10);
        panel.add(field11);

        panel.add(button);
        panel.add(progressBar);

        frame.add(panel);
        frame.setSize(800,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
