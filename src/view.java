import javax.swing.*;

public class view {
    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel controllPane;
    private JTextField meepTextTextField;
    private JButton addUpdateButton;
    private JButton deleteButton;


    public JPanel getPanel() {
        return panel;
    }

    public JButton getAddUpdateButton() {return addUpdateButton;}

    public JButton getDeleteButton() {return deleteButton;}

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getControllPane() {
        return controllPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
