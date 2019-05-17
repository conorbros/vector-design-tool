package GUI;

import CommandList.CommandList;
import Commands.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryDialog {
    private JList list;
    private JLabel label;
    private JOptionPane optionPane;
    private JButton revertButton;
    private JButton selectedCmdButton;
    private JButton cancelButton;
    private ActionListener revertEvent;
    private ActionListener okEvent;
    private ActionListener cancelEvent;
    private JDialog dialog;

    public HistoryDialog(String message, JList listToDisplay){
        list = listToDisplay;
        label = new JLabel(message);
        createAndDisplayOptionPane();
    }

    public HistoryDialog(String title, String message, JList listToDisplay){
        this(message, listToDisplay);
        dialog.setTitle(title);
    }

    private void createAndDisplayOptionPane(){
        setupButtons();
        JPanel pane = layoutComponents();
        optionPane = new JOptionPane(pane);
        optionPane.setOptions(new Object[] {revertButton, selectedCmdButton, cancelButton});
        dialog = optionPane.createDialog("undo History");
    }

    private void setupButtons(){
        revertButton = new JButton("Revert file to selected command");
        revertButton.addActionListener(this::handleRevertButtonClick);

        selectedCmdButton = new JButton("Remove selected command");
        selectedCmdButton.addActionListener(this::handleOkButtonClick);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::handleCancelButtonClick);
    }

    private JPanel layoutComponents(){
        centerListElements();
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }

    private void centerListElements(){
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setOnRevert(ActionListener event){
        revertEvent = event;
    }

    public void setOnOk(ActionListener event){
        okEvent = event;
    }

    public void setOnClose(ActionListener event){
        cancelEvent  = event;
    }

    public void handleRevertButtonClick(ActionEvent e) {
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

    public void show(){
        dialog.setVisible(true);
    }

    private void hide(){
        dialog.setVisible(false);
    }

    public Object getSelectedItem(){
        return list.getSelectedValue();
    }
}

