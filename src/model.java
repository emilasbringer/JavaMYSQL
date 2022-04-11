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

    public model() {
        meepArrayList = new ArrayList<>();
        initizializeViewList();
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

    public void initizializeViewList() {
        for (meep meep : meepArrayList) {
            listModel.addElement(meep.toString());
        }
        list = new JList(listModel);
        list.setFixedCellWidth(40);
        list.setFixedCellHeight(40);
        list.setFont(listFont);
        list.setSelectionBackground(Color.GRAY);
    }


    public void clearListModel() {
        listModel.clear();
    }

    public JList getList() {
        return list;
    }

    public int randomNumber(int min, int max) {
        return (int) Math.floor(Math.random()*((max+1)-min)+min);
    }

    public long getDateInEpoch(String string) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {date = df.parse(string);} catch (ParseException e) {e.printStackTrace();}
        long epoch = date.getTime();
        return epoch;
    }

    public boolean getEditing() {
        return editing;
    }
}
