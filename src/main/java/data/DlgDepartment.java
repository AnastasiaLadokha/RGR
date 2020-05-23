package data;

import view.Dlg;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DlgDepartment extends Dlg {
    private JTextField textFieldCount = new JTextField();
    private JTextField textFieldPrice = new JTextField();
    private JTextField textFieldLead = new JTextField();
    private JLabel lblName = new JLabel();
    private JLabel lblCount = new JLabel();
    private JLabel lblPrice = new JLabel();
    private JLabel lblLead = new JLabel();

    @Override
    public Object createObject() throws Exception {
        if(!ok) return null;
        String name = textFieldName.getText();
        int count = Integer.parseInt(textFieldCount.getText());
        float price = Float.parseFloat(textFieldPrice.getText());
        String lead = textFieldLead.getText();
        return new Department(name, count, price, lead);
    }

    public DlgDepartment(Object data){
        this();
        Department department = (Department) data;
        textFieldName.setText(department.name);
        textFieldCount.setText(String.valueOf(department.countOfRooms));
        textFieldPrice.setText(String.valueOf(department.pricePerNight));
        textFieldLead.setText(department.leadName);
    }

    public DlgDepartment() {
        setTitle("Department Dialog");
        setBounds(100, 100, 400, 400);

        GridLayout gridLayout = new GridLayout(5, 2);
        gridLayout.setVgap(20);
        contentPane.setLayout(gridLayout);

        lblName.setPreferredSize(new Dimension(100, 30));
        lblName.setText("Name of department");
        lblName.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblCount.setPreferredSize(new Dimension(100, 30));
        lblCount.setText("Count of rooms");
        lblCount.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblPrice.setPreferredSize(new Dimension(100, 30));
        lblPrice.setText("Price per night");
        lblPrice.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblLead.setText("Lead of department");
        lblLead.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblLead.setPreferredSize(new Dimension(50, 30));

        textFieldName.setPreferredSize(new Dimension(250, 30));
        textFieldName.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldCount.setPreferredSize(new Dimension(250,30));
        textFieldCount.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldPrice.setPreferredSize(new Dimension(250, 30));
        textFieldPrice.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldLead.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldLead.setPreferredSize(new Dimension(50, 30));

        buttonOK.setText("Ok");
        buttonCancel.setText("Cancel");

        contentPane.setLayout(gridLayout);
        contentPane.add(lblName);
        contentPane.add(textFieldName);
        contentPane.add(lblCount);
        contentPane.add(textFieldCount);
        contentPane.add(lblPrice);
        contentPane.add(textFieldPrice);
        contentPane.add(lblLead);
        contentPane.add(textFieldLead);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);
        setContentPane(contentPane);
        setModal(true);
    }

}
