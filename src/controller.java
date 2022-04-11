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
        view = new view();
        model = new model();
        dbc = new DatabaseConnector();

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

        for (int i = 0; i < dbc.getDatabaseContent().size(); i++) {
            model.addMeepObjectToArrayList((meep) dbc.getDatabaseContent().get(i));
        }
        model.initizializeViewList();

        view.getAddUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getEditing()) {
                    model.switchAddEditMode(view.getAddUpdateButton());
                }
            }
        });

        model.getList().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                System.out.println("test");
                if (!event.getValueIsAdjusting()){

                    if (model.getList().getSelectedIndex() == -1) {
                        //No selection, disable fire button.

                    } else {
                        //Selection, enable the fire button.
                        System.out.println("Selected cell");
                    }

                }
            }
        });

    }

    public static void main(String[] args) {
        controller c = new controller();
    }
}