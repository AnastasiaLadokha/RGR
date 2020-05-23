package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DlgHospital1 extends JDialog {
    private JPanel contentPane = new JPanel();
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldHname;
    private JTextField textFieldLname;
    private JTextField textFieldNumb;
    private JTextField textFieldLevel;
    private JLabel lblHname;
    private JLabel lblLname;
    private JLabel lblNum;
    private JLabel lblLevel;
    private JPanel JpanelOk;
    private JPanel JpanelLab;
    private JPanel JpanelLbl1;
    BufferedImage image;


    public DlgHospital1() throws IOException {
        setBounds(100, 100, 100, 100);
        JLabel label = new JLabel(new ImageIcon(Files
                .readAllBytes(Paths
                        .get("D:\\oop\\RGR\\src\\main\\resources\\image\\fon.png"))));
        contentPane.add(label);
        contentPane.add(lblHname);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws IOException {
        DlgHospital1 dialog = new DlgHospital1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
