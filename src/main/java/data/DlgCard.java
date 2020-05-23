package data;

import view.Dlg;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class DlgCard extends Dlg {
    private JTextField textFieldDateIn = new JTextField();
    private JTextField textFieldDateOut = new JTextField();
    private JTextField textFieldDiagnosis = new JTextField();
    private JLabel lblName = new JLabel();
    private JLabel lblDateIn = new JLabel();
    private JLabel lblDateOut = new JLabel();
    private JLabel lblDiagnosis = new JLabel();

    @Override
    public Object createObject() throws Exception {
        if(!ok) return null;
        String name = textFieldName.getText();
        LocalDate dateIn = LocalDate.parse(textFieldDateIn.getText());
        LocalDate dateOut = LocalDate.parse(textFieldDateOut.getText());
        String diagnosis = textFieldDiagnosis.getText();
        return new Card(name, dateIn, dateOut, diagnosis);
    }

    public DlgCard(Object data){
        this();
        Card card = (Card) data;
        textFieldName.setText(card.name);
        textFieldDateIn.setText(String.valueOf(card.dateIn));
        textFieldDateOut.setText(String.valueOf(card.dateOut));
        textFieldDiagnosis.setText(card.diagnosis);
    }

    public DlgCard() {
        setTitle("Card Dialog");
        setBounds(100, 100, 400, 400);

        GridLayout gridLayout = new GridLayout(5, 2);
        gridLayout.setVgap(20);
        contentPane.setLayout(gridLayout);

        lblName.setPreferredSize(new Dimension(100, 30));
        lblName.setText("Patient's name");
        lblName.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblDateIn.setPreferredSize(new Dimension(100, 30));
        lblDateIn.setText("Receipt date");
        lblDateIn.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblDateOut.setPreferredSize(new Dimension(100, 30));
        lblDateOut.setText("Discharge date");
        lblDateOut.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblDiagnosis.setText("Diagnosis");
        lblDiagnosis.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblDiagnosis.setPreferredSize(new Dimension(50, 30));

        textFieldName.setPreferredSize(new Dimension(250, 30));
        textFieldName.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldDateIn.setPreferredSize(new Dimension(250,30));
        textFieldDateIn.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldDateOut.setPreferredSize(new Dimension(250, 30));
        textFieldDateOut.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldDiagnosis.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldDiagnosis.setPreferredSize(new Dimension(50, 30));

        buttonOK.setText("Ok");
        buttonCancel.setText("Cancel");

        contentPane.setLayout(gridLayout);
        contentPane.add(lblName);
        contentPane.add(textFieldName);
        contentPane.add(lblDateIn);
        contentPane.add(textFieldDateIn);
        contentPane.add(lblDateOut);
        contentPane.add(textFieldDateOut);
        contentPane.add(lblDiagnosis);
        contentPane.add(textFieldDiagnosis);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);
        setContentPane(contentPane);
        setModal(true);
    }
}
