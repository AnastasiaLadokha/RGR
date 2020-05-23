package data;

import view.Dlg;

import java.time.LocalDate;

public class Treatment extends AnyData{
    LocalDate dateOfTreat;
    String surname;

    @Override
    public Dlg showDialog(boolean b) {
        DlgTreatment treatment = new DlgTreatment(this);
        treatment.setEditable(b);
        treatment.setVisible(true);
        return treatment;
    }

    @Override
    public Dlg showSonDialog() {
        return null;
    }

    public Treatment(String name, LocalDate dateOfTreat, String surname){
        this.name = name;
        this.dateOfTreat = dateOfTreat;
        this.surname = surname;
    }
}
