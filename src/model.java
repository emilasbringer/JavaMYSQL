import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class model {
    private ArrayList<meep> meepArrayList;
    private JList list;
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private Font listFont = new Font("Helvetica", Font.BOLD, 15);
    private boolean editing = false;

    public model(DatabaseConnector dbc) {
        meepArrayList = new ArrayList<>();
        updateViewList(dbc);
    }

    public void switchAddEditMode(JButton addUpdateButton) {
        if (editing) {
            addUpdateButton.setText("Add meep");
            editing = false;
        }
        else {
            addUpdateButton.setText("Update meep");
            editing = true;
        }
    }

    public void addMeepObjectToArrayList(meep meepObject) {
        meepArrayList.add(new meep(meepObject));
    }

    public void updateViewList(DatabaseConnector dbc) {
        meepArrayList.clear();
        for (int i = 0; i < dbc.getDatabaseContent().size(); i++) {
            addMeepObjectToArrayList((meep) dbc.getDatabaseContent().get(i));
        }
        listModel.clear();
        for (meep meep : meepArrayList) {
            listModel.addElement(meep.toString());
        }
        if (list == null) {
            list = new JList(listModel);
            list.setFixedCellWidth(40);
            list.setFixedCellHeight(40);
            list.setFont(listFont);
            list.setSelectionBackground(Color.GRAY);
        } else {
            list.setModel(listModel);
        }
    }


    public void clearListModel() {listModel.clear();}

    public JList getList() {return list;}

    public int randomNumber(int min, int max) {return (int) Math.floor(Math.random()*((max+1)-min)+min);}

    public long getDateInEpoch(String string) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {date = df.parse(string);} catch (ParseException e) {e.printStackTrace();}
        long epoch = date.getTime();
        return epoch;
    }

    public meep getMeepFromArrayListByID(int id) {return meepArrayList.get(id);}

    public boolean getEditing() {return editing;}

    public void setEditing(boolean editing) {this.editing = editing;}
}
