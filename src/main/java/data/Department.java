package data;

import view.Dlg;

public class Department extends AnyData {
    int countOfRooms;
    float pricePerNight;
    String leadName;

    @Override
    public Dlg showDialog(boolean b) {
        DlgDepartment department = new DlgDepartment(this);
        department.setEditable(b);
        department.setVisible(true);
        return department;
    }

    @Override
    public Dlg showSonDialog() {
        DlgCard card = new DlgCard();
        card.setVisible(true);
        return card;
    }

    public Department(String name, int countOfRooms, float pricePerNight, String leadName) {
        this.name = name;
        this.countOfRooms = countOfRooms;
        this.pricePerNight = pricePerNight;
        this.leadName = leadName;
    }
}
