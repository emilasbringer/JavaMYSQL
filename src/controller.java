import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;


public class controller {
    private model model;
    private view view;
    private DatabaseConnector dbc;

    public controller() {
        dbc = new DatabaseConnector();
        view = new view();
        model = new model(dbc);

        JScrollPane scroll = new JScrollPane(model.getList(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        view.setScrollPane(scroll);

        JFrame frame = new JFrame("Meeper");
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("logo.jpg"))).getImage());
        frame.add(view.getControllPane(), BorderLayout.PAGE_START);
        frame.add(view.getScrollPane(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1600, 500);

        model.updateViewList(dbc);

        view.getAddUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getEditing()) {

                    model.switchAddEditMode(view.getAddUpdateButton());
                }
                else {
                    dbc.insertData(view.getMeepTextTextField().getText());
                    model.updateViewList(dbc);
                }
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dbc.deleteData( model.getMeepFromArrayListByID(model.getList().getSelectedIndex()).getId());
               model.updateViewList(dbc);
            }
        });

        model.getList().addListSelectionListener(new Listener());

    }

    private class Listener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()){

                if (model.getList().getSelectedIndex() == -1) {
                    //No selection, disable fire button.
                    view.getAddUpdateButton().setText("Add");
                    model.setEditing(false);

                } else {
                    //Selection, enable the fire button.
                    view.getAddUpdateButton().setText("Update");
                    model.setEditing(true);
                    System.out.println("Selected cell index = " + model.getList().getSelectedIndex());
                }

            }
        }
    }
    public static void main(String[] args) {controller c = new controller();}
}