import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LibrarySystem extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public LibrarySystem() {

        setTitle("Library Management System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table Columns
        String[] columns = {"Book ID", "Book Name", "Author", "Status"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        // Buttons
        JButton addButton = new JButton("Add Book");
        JButton issueButton = new JButton("Issue Book");
        JButton returnButton = new JButton("Return Book");
        JButton deleteButton = new JButton("Delete Book");

        // Panel
        JPanel panel = new JPanel();

        panel.add(addButton);
        panel.add(issueButton);
        panel.add(returnButton);
        panel.add(deleteButton);

        add(pane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Add Book
        addButton.addActionListener((ActionEvent e) -> {
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField authorField = new JTextField();
            Object[] fields = {
                "Book ID:", idField,
                "Book Name:", nameField,
                "Author:", authorField
            };
            int option = JOptionPane.showConfirmDialog(
                    null,
                    fields,
                    "Add Book",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (option == JOptionPane.OK_OPTION) {
                String id = idField.getText();
                String name1 = nameField.getText();
                String author = authorField.getText();
                model.addRow(new Object[]{id, name1, author, "Available"});
            }
        });

        // Issue Book
        issueButton.addActionListener((ActionEvent e) -> {
            int row = table.getSelectedRow();
            
            if (row >= 0) {
                
                String status = model.getValueAt(row, 3).toString();
                
                if (status.equals("Available")) {
                    
                    model.setValueAt("Issued", row, 3);
                    
                    JOptionPane.showMessageDialog(null,
                            "Book Issued Successfully");
                    
                } else {

                    JOptionPane.showMessageDialog(null,
                            "Book already issued");
                }
                
            } else {
                
                JOptionPane.showMessageDialog(null,
                        "Select a book first");
            }
        });

        // Return Book
        returnButton.addActionListener((ActionEvent e) -> {
            int row = table.getSelectedRow();
            
            if (row >= 0) {
                
                model.setValueAt("Available", row, 3);
                
                JOptionPane.showMessageDialog(null,
                        "Book Returned Successfully");
                
            } else {
                
                JOptionPane.showMessageDialog(null,
                        "Select a book first");
            }
        });

        // Delete Book
        deleteButton.addActionListener((ActionEvent e) -> {
            int row = table.getSelectedRow();
            
            if (row >= 0) {
                
                model.removeRow(row);
                
            } else {
                
                JOptionPane.showMessageDialog(null,
                        "Select a book first");
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LibrarySystem().setVisible(true);
        });
    }
}