package data;

import view.Dlg;

import java.time.LocalDate;
import java.time.Period;

public class Card extends AnyData{
    LocalDate dateIn;
    LocalDate dateOut;
    String diagnosis;

    @Override
    public Dlg showDialog(boolean b) {
        DlgCard card = new DlgCard(this);
        card.setEditable(b);
        card.setVisible(true);
        return card;
    }

    @Override
    public Dlg showSonDialog() {
        DlgTreatment treatment = new DlgTreatment();
        treatment.setVisible(true);
        return treatment;
    }

    public int getDate(){
        return Period.between(dateIn, LocalDate.now()).getDays();
    }

    public Card(String name, LocalDate dateIn, LocalDate dateOut, String diagnosis){
        this.name = name;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.diagnosis = diagnosis;
    }
}
