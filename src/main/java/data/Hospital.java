package data;

import view.Dlg;

public class Hospital extends AnyData{
    @Override
    public Dlg showDialog(boolean b) {
        DlgHospital hospital = new DlgHospital(this);
        hospital.setEditable(b);
        hospital.setVisible(true);
        return hospital;
    }

    @Override
    public Dlg showSonDialog() {
        DlgDepartment department = new DlgDepartment();
        department.setVisible(true);
        return department;
    }

    String headName;
    int numberOfBuildings;
    int level;

    public Hospital(String name, String headName, int numberOfBuildings, int level) throws Exception{
        if(level < 1 || level > 4)
            throw new Exception("Incorrect level");
        this.name = name;
        this.level = level;
        this.headName = headName;
        this.numberOfBuildings = numberOfBuildings;
    }

}
