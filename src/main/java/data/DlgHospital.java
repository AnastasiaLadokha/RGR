package data;

import view.Dlg;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DlgHospital extends Dlg {
    private JTextField textFieldLname = new JTextField();
    private JTextField textFieldNumb = new JTextField();
    private JTextField textFieldLevel = new JTextField();
    private JLabel lblHname = new JLabel();
    private JLabel lblLname = new JLabel();
    private JLabel lblNum = new JLabel();
    private JLabel lblLevel = new JLabel();

    BufferedImage image;

    public DlgHospital() {
        setTitle("Hospital Dialog");
        setBounds(100, 100, 400, 400);

        GridLayout gridLayout = new GridLayout(5, 2);
        gridLayout.setVgap(20);
        contentPane.setLayout(gridLayout);

        /*JLabel label = null;
        try {
            label = new JLabel(new ImageIcon(Files
                    .readAllBytes(Paths
                            .get("D:\\oop\\RGR\\src\\main\\resources\\image\\fon.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        lblHname.setPreferredSize(new Dimension(100, 30));
        lblHname.setText("Hospital name");
        lblHname.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblLname.setPreferredSize(new Dimension(100, 30));
        lblLname.setText("Lead's name");
        lblLname.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblNum.setPreferredSize(new Dimension(100, 30));
        lblNum.setText("Number of buildings");
        lblNum.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblLevel.setText("Level");
        lblLevel.setFont(new Font("Fire code", Font.PLAIN, 14));
        lblLevel.setPreferredSize(new Dimension(50, 30));

        textFieldName.setPreferredSize(new Dimension(250, 30));
        textFieldName.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldLname.setPreferredSize(new Dimension(250,30));
        textFieldLname.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldNumb.setPreferredSize(new Dimension(250, 30));
        textFieldNumb.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldLevel.setFont(new Font("Fire code", Font.PLAIN, 14));
        textFieldLevel.setPreferredSize(new Dimension(50, 30));

        buttonOK.setText("Ok");
        buttonCancel.setText("Cancel");

        //contentPane.add(label);

        contentPane.setLayout(gridLayout);
        contentPane.add(lblHname);
        contentPane.add(textFieldName);
        contentPane.add(lblLname);
        contentPane.add(textFieldLname);
        contentPane.add(lblNum);
        contentPane.add(textFieldNumb);
        contentPane.add(lblLevel);
        contentPane.add(textFieldLevel);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);
        setContentPane(contentPane);
        setModal(true);
    }

    @Override
    public Object createObject() throws Exception {
        if(!ok) return null;
        String name = textFieldName.getText();
        String lName = textFieldLname.getText();
        int number = Integer.parseInt(textFieldNumb.getText());
        int level = Integer.parseInt(textFieldLevel.getText());
        return new Hospital(name, lName, number, level);
    }

    public DlgHospital(Object data){
        this();
        Hospital hospital = (Hospital) data;
        textFieldName.setText(hospital.name);
        textFieldLname.setText(hospital.headName);
        textFieldNumb.setText(String.valueOf(hospital.numberOfBuildings));
        textFieldLevel.setText(String.valueOf(hospital.level));
    }
}
