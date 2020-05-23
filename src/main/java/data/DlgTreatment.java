package data;

import view.Dlg;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class DlgTreatment extends Dlg {
    private JTextField textFieldDate = new JTextField();
    private JTextField textFieldSurname = new JTextField();
    private JLabel lblName = new JLabel();
    private JLabel lblDate = new JLabel();
    private JLabel lblSurname = new JLabel();

    public DlgTreatment() {
        setTitle("Treatment Dialog");
        setBounds(100, 100, 400, 400);

        GridLayout gridLayout = new GridLayout(4, 2);
        gridLayout.setVgap(20);
        contentPane.setLayout(gridLayout);

        lblName.setPreferredSize(new Dimension(100, 30));
        lblName.setText("Name of treatment");
        lblName.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblDate.setPreferredSize(new Dimension(100, 30));
        lblDate.setText("Date of treatment");
        lblDate.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblSurname.setPreferredSize(new Dimension(100, 30));
        lblSurname.setText("Doctor's surname");
        lblSurname.setFont(new Font("Fire code", Font.PLAIN, 14));

        textFieldName.setPreferredSize(new Dimension(250, 30));
        textFieldName.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldDate.setPreferredSize(new Dimension(250,30));
        textFieldDate.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldSurname.setPreferredSize(new Dimension(250, 30));
        textFieldSurname.setFont(new Font("Fire code", Font.PLAIN, 14));

        buttonOK.setText("Ok");
        buttonCancel.setText("Cancel");

        contentPane.setLayout(gridLayout);
        contentPane.add(lblName);
        contentPane.add(textFieldName);
        contentPane.add(lblDate);
        contentPane.add(textFieldDate);
        contentPane.add(lblSurname);
        contentPane.add(textFieldSurname);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);
        setContentPane(contentPane);
        setModal(true);
    }

    @Override
    public Object createObject() throws Exception {
        if(!ok) return null;
        String name = textFieldName.getText();
        LocalDate date = LocalDate.parse(textFieldDate.getText());
        String surname = textFieldSurname.getText();
        return new Treatment(name, date, surname);
    }

    public DlgTreatment(Object data){
        this();
        Treatment treatment = (Treatment) data;
        textFieldName.setText(treatment.name);
        textFieldDate.setText(String.valueOf(treatment.dateOfTreat));
        textFieldSurname.setText(treatment.surname);
    }

}
