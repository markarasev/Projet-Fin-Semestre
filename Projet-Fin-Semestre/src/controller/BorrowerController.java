package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import config.Model;

import managementsystem.ManagementSystem;
import view.AccountView;
import view.BorrowerView;
import view.BorrowingView;
import view.LoansHistoryView;

public class BorrowerController {

    private String idBorrower;
    private ManagementSystem ms;
    private BorrowerView view;
    private List<String> models;

    public BorrowerController(String idBorrower, ManagementSystem m, BorrowerView v) {
        this.idBorrower = idBorrower;
        ms = m;
        view = v;
        view.setTitle(ms.getUser(idBorrower).getName());
        models = new LinkedList<String>();

        obtainDevicesStrings();

        v.getBorrowButton().addActionListener(new BorrowListener());
        v.getBackButton().addActionListener(new BackListener());
        v.getLoansButton().addActionListener(new LoansListener());
    }
    
    /**
     * The listener of the loans button
     * 
     * @author Hugo
     * 
     */
    public class LoansListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoansHistoryView newView = new LoansHistoryView();
            new LoansHistoryController(idBorrower, ms, newView);
        }
    }

    /**
     * The listener of the borrow button
     * 
     * @author Hugo
     * 
     */
    public class BorrowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<String> selected = new LinkedList<String>();

            for (int i = 0; i < view.getListModel().getSize(); i++) {
                for (int j = 0; j < view.getDevicesList().getSelectedIndices().length; j++) {
                    if (view.getDevicesList().getSelectedIndices()[j] == i) {
                        selected.add(models.get(i));
                    }
                }
            }
            if (selected.isEmpty())
                return;
            BorrowingView v = new BorrowingView();
            new BorrowingController(idBorrower, selected, ms, v);
        }
    }
    
    /**
     * The listener of the back button
     * 
     * @author Hugo
     * 
     */
    public class BackListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AccountView newView = new AccountView();
            new AccountController(ms, newView);
            view.dispose();
        }
    }

    /**
     * Retrieves the ids and labels from the model Adds the labels to the view's
     * list Adds the Ids to the LinkedList
     */
    public void obtainDevicesStrings() {
        models.clear();
        for (String s : ms.getInventory().keySet()) {
            models.add(s);
            view.getListModel().addElement(s);
        }
    }
}
