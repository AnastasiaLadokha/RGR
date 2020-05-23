package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Батьківський діалог Dlg - загальні функції для усіх діалогів
 */


public abstract class Dlg extends JDialog {
    protected JPanel contentPane = new JPanel();
    protected JButton buttonOK;
    protected JButton buttonCancel;
    protected JTextField textFieldName;
    private JLabel lblName;
    protected boolean ok;

    /*
     * Абстрактний метод createObject для повернення відповідних об’єктів у класах-спадкоємцях
     */
    public abstract Object createObject() throws Exception;

    /*
     * Метод для визначення можливості редагувати дані у діалогах
     */

    public void setEditable(boolean b) {
        buttonOK.setVisible(b);
        if (b)
            buttonCancel.setText("Cancel");
        else {
            buttonCancel.setText("Exit");
            for (Component component : contentPane.getComponents())
                component.setEnabled(b);
        }
    }

    public Dlg() {
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
        ok = true;
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        ok = false;
        setVisible(false);
        dispose();
    }
}
