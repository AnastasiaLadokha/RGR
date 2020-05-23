package data;

import view.Dlg;

import java.io.Serializable;

public abstract class AnyData implements Serializable {
    protected String name;

    public abstract Dlg showDialog(boolean b);
    public abstract Dlg showSonDialog();

    @Override
    public String toString() {
        return name;
    }
}
