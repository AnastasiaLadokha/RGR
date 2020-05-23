package view;

import data.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Enumeration;

public class MainGui {
    public JFrame frame;
    private JPanel panel1;
    private JTree tree;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JButton storeButton;
    private JButton restoreButton;
    private JButton functionsButton;
    private JButton outputButton;
    private JButton orderButton;


    protected TreeModel getStartModel() throws Exception {
        Hospital hospital = new Hospital("1st", "Ivanov", 5, 4);
        Department department = new Department("Surgery", 5, 15.4f, "Petrov");
        Card card = new Card("Sidorov", LocalDate.of(2020, Month.FEBRUARY, 5), LocalDate.of(2020, Month.MARCH, 5), "Orvi");
        Treatment treatment = new Treatment("ukol", LocalDate.of(2020, Month.FEBRUARY, 6), "Petrov");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(hospital);
        DefaultMutableTreeNode dNode = new DefaultMutableTreeNode(department);
        DefaultMutableTreeNode cNode = new DefaultMutableTreeNode(card);
        DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(treatment);
        root.add(dNode);
        dNode.add(cNode);
        cNode.add(tNode);
        return (new JTree(root).getModel());
    }

    protected void onWindowOpened() {
        try {
            tree.setModel(getStartModel());
            for (int i = 0; i < tree.getRowCount(); i++) {
                tree.expandRow(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DefaultMutableTreeNode getSelectedNode() {
        Object selectNode = tree.getLastSelectedPathComponent();
        if (selectNode == null) {
            JOptionPane.showMessageDialog(tree, "Node wasn't selected",
                    frame.getTitle(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return (DefaultMutableTreeNode) selectNode;
    }

    private void expandAll() {
        for (int i = 0; i < tree.getRowCount(); i++)
            tree.expandRow(i);
    }

    private void selectedNode(DefaultMutableTreeNode node){
        int n = 0;
        DefaultMutableTreeNode root =
                (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration <DefaultMutableTreeNode> enumeration = root.children();
        while (enumeration.hasMoreElements()){
            DefaultMutableTreeNode node1 = enumeration.nextElement();
            if (node1 == node){
                tree.setSelectionRow(n);
                return;
            }
            n++;
        }
    }

    private void onMouseClicked(MouseEvent event){
        if(event.getClickCount() != 3 || event.getButton() != MouseEvent.BUTTON3)
            return;
        DefaultMutableTreeNode node = getSelectedNode();
        if(node == null)
            return;
        AnyData data = (AnyData) node.getUserObject();
        Dlg dlg = data.showDialog(false);
        dlg.dispose();
    }

    public MainGui() {
        initialize();
        onWindowOpened();
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClicked(e);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAdd();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemove();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEdit();
            }
        });
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onStore();
            }
        });
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRestore();
            }
        });
        functionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count();
            }
        });
        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output();
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order();
            }
        });
    }

    private void onRestore() {
        FileDialog fileDialog = new FileDialog(frame);
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        String directory = fileDialog.getDirectory();
        String fileN = fileDialog.getFile();
        if (directory == null || fileN == null)
            return;
        String fileName = directory + fileN;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            TreeModel model = (TreeModel) inputStream.readObject();
            tree.setModel(model);
            inputStream.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(tree, "Deserializing error",
                    "Restoring data", JOptionPane.ERROR_MESSAGE);
            return;
        }
        expandAll();
    }

    private void onStore() {
        if (tree.getModel() == null)
            return;
        JFileChooser fileChooser = new JFileChooser("Серіалізація моделі дерева");
        fileChooser.showSaveDialog(frame);
        try {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tree.getModel());
            objectOutputStream.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(tree, "Error in opening file ",
                    "Saving data", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tree.setModel((new JTree()).getModel());
    }

    private void onEdit() {
        DefaultMutableTreeNode node = getSelectedNode();
        if (node == null)
            return;
        AnyData data = (AnyData) node.getUserObject();
        Dlg dlg = data.showDialog(true);
        Object object;
        try {
            object = dlg.createObject();
        }catch (Exception e){
            JOptionPane.showMessageDialog(tree, e.getMessage(), frame.getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        dlg.dispose();
        if (object == null)
            return;
        node.setUserObject(object);
        tree.updateUI();
    }

    private void onRemove() {
        DefaultMutableTreeNode node = getSelectedNode();
        if(node == null)
            return;
        node.removeFromParent();
        tree.setSelectionPath(null);
        tree.updateUI();
    }

    protected void onAdd(){
        DefaultMutableTreeNode parent = getSelectedNode();
        if (parent == null)
            return;
        AnyData parentData = (AnyData) parent.getUserObject();
        Dlg dlg = parentData.showSonDialog();
        if (dlg == null)
            return;
        Object object;
        try{
            object = dlg.createObject();
        }catch (Exception e){
            JOptionPane.showMessageDialog(tree, e.getMessage(),
                    frame.getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        dlg.dispose();
        if (object == null)
            return;
        DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(object);
        parent.add(newSon);
        tree.updateUI();
        expandAll();
    }

    private void order(){
        DefaultMutableTreeNode node = getSelectedNode();
        if (node == null)
            return;
        String order = "";
        Enumeration<DefaultMutableTreeNode> enumeration = node.postorderEnumeration();
        while (enumeration.hasMoreElements()){
            DefaultMutableTreeNode currentNode = enumeration.nextElement();
            Object data = currentNode.getUserObject();
            if (!(data instanceof Treatment))
                continue;
            order += ((Treatment)data).toString();
            order += "\n";
        }
        JOptionPane.showMessageDialog(tree, order, "Order", JOptionPane.DEFAULT_OPTION);
        System.out.println(order);
    }

    private void count(){
        int count;
        String result = "";
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> enmDep = root.children();
        while (enmDep.hasMoreElements()){
            count = 0;
            DefaultMutableTreeNode dep = enmDep.nextElement();
            Enumeration<DefaultMutableTreeNode> enmCard = dep.children();
            while (enmCard.hasMoreElements()){
                DefaultMutableTreeNode card = enmCard.nextElement();
                count++;
            }
            result += dep + " has " + count + " patient(s)\n";
        }
        JOptionPane.showMessageDialog(tree, result, "Count", JOptionPane.DEFAULT_OPTION);
    }

    private void output(){
        DefaultMutableTreeNode node = getSelectedNode();
            if (node == null)
                return;
            int maxDate = 0;
            DefaultMutableTreeNode oldest = null;
            Enumeration<DefaultMutableTreeNode> enumeration = node.postorderEnumeration();
            while (enumeration.hasMoreElements()){
                DefaultMutableTreeNode currentNode = enumeration.nextElement();
                Object data = currentNode.getUserObject();
                if(!(data instanceof Card))
                    continue;
                int date = ((Card)data).getDate();
                if (date > maxDate){
                    maxDate = date;
                    oldest = currentNode;
                }
            }
            System.out.println(oldest);
            selectedNode(oldest);
        ((AnyData)oldest.getUserObject()).showDialog(false);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(200, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel1);
        frame.setTitle("Hospital");
    }
}
