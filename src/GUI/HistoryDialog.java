package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryDialog {
    private JList list;
    private JLabel label;
    private JButton revertButton;
    private JButton selectedCmdButton;
    private JButton cancelButton;
    private ActionListener revertEvent;
    private ActionListener okEvent;
    private ActionListener cancelEvent;
    private JDialog dialog;

    /**
     * Constructs a new History Dialog
     * @param message The message of the dialog window
     * @param listToDisplay The list to display in the dialog window
     */
    HistoryDialog(String message, JList listToDisplay){
        list = listToDisplay;
        label = new JLabel(message);
        createAndDisplayOptionPane();
    }

    /**
     * Creates and displays the option buttons for the dialog window
     */
    private void createAndDisplayOptionPane(){
        setupButtons();
        JPanel pane = layoutComponents();
        JOptionPane optionPane = new JOptionPane(pane);
        optionPane.setOptions(new Object[] {revertButton, selectedCmdButton, cancelButton});
        dialog = optionPane.createDialog("undo History");
    }

    /**
     * Sets up the option buttons for dialog window
     */
    private void setupButtons(){
        revertButton = new JButton("Revert file to selected command");
        revertButton.addActionListener(this::handleRevertButtonClick);

        selectedCmdButton = new JButton("Remove selected command");
        selectedCmdButton.addActionListener(this::handleOkButtonClick);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::handleCancelButtonClick);
    }

    /**
     * Lays out the components on the panel
     * @return A JPanel with the components
     */
    private JPanel layoutComponents(){
        centerListElements();
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }

    /**
     * Centers the list elements in the history list
     */
    private void centerListElements(){
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    void setOnRevert(ActionListener event){
        revertEvent = event;
    }

    void setOnOk(ActionListener event){
        okEvent = event;
    }

    public void setOnClose(ActionListener event){
        cancelEvent  = event;
    }

    private void handleRevertButtonClick(ActionEvent e) {
        if(revertEvent != null){
            revertEvent.actionPerformed(e);
        }
        hide();
    }

    private void handleOkButtonClick(ActionEvent e){
        if(okEvent != null){
            okEvent.actionPerformed(e);
        }
        hide();
    }

    private void handleCancelButtonClick(ActionEvent e){
        if(cancelEvent != null){
            cancelEvent.actionPerformed(e);
        }
        hide();
    }

    void show(){
        dialog.setVisible(true);
    }

    private void hide(){
        dialog.setVisible(false);
    }

    /**
     * Returns the selected list item
     * @return An object of the selected list item
     */
    Object getSelectedItem(){
        return list.getSelectedValue();
    }
}

