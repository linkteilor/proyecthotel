package igu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import data.clientData;
import entities.client;


public class ClientDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    JTextField fromField = new JTextField(" ", 30);
    JTextField toField = new JTextField(30);
    JButton goButton = new JButton("salir");
    JButton addButton = new JButton("añadir");
    JButton delButton = new JButton("eliminar");
    JTable jTable;
    JScrollPane jSP;
    clientData clientData=new clientData();

    public ClientDialog() {
        setSize(500, 500);
        setTitle(" DATOS DEL CLIENTE");
        setLocationRelativeTo(null);

        initForm();
        paintTable();
    }

    private void paintTable() {
         DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
         List<client> lis = clientData.List();
        while (modelo.getRowCount() > 0)
            modelo.removeRow(0);
        for (final client d : lis) {
            modelo.addRow(new Object[] { d.getId(), d.getnombre(), d.getsex() });
        }
    }

    void initForm() {

        jTable = new JTable();
        jTable.setModel(new DefaultTableModel(new Object[][] {
                // { 1, 2 },
                // { 3, 4 }
        }, new String[] { "ID", "nombre", "sex" }));
        jSP = new JScrollPane();
        jSP.setViewportView(jTable);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("nombres y apellidos"));
        add(fromField);
        add(new JLabel(" DNI"));
        add(toField);       
        add(addButton);
        add(delButton);
        add(jSP);
        add(goButton);

        // Manejo de eventos
        final JDialog outer = this;
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // System.out.println(" goButton has press ");
                outer.setVisible(false);
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                addPerson(e);
            }
        });
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                delPerson(e);
            }
        });

    }

    void addPerson(final ActionEvent e) {
        System.out.println(" addButton has press ");
        final client d = new client();
        d.setnombre(fromField.getText());
        d.setsex(toField.getText());
        clientData.create(d);
        paintTable();
    }

    void delPerson(final ActionEvent e) {
        if (jTable.getSelectedRow() != -1) {
            System.out.println(" delButton has press ");
            final int[] row = jTable.getSelectedRows();
            final String ids = jTable.getValueAt(row[0], 0).toString();
            System.out.println("selected: " + ids);
            final int id = Integer.parseInt(ids);
            clientData.delete(id);
            paintTable();
        }
    }
}