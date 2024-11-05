package atm;

// Lịch sử giao dịch
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Transactions {
    
    private String[][] originalData; // Lưu tất cả Transactions
    private JTable table; // Reference to the JTable to refresh data

    public Transactions(Database database, int userID) {
        JFrame frame = new JFrame(userID + " - Transactions");
        frame.setLayout(new BorderLayout());
        
        // Load transactions từ database
        originalData = TransactionsDatabase.getUserTransactions(database, userID);
        String[] columnNames = {"ID", "Operation", "Amount", "Date", "Time"};
        
        // Tạo một JComboBox
        String[] operators = {"All", "Deposit", "Withdraw", "Transfer In", "Transfer Out"};
        JComboBox<String> operatorComboBox = new JComboBox<>(operators);
        operatorComboBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        operatorComboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Xử lý sự kiện cho JComboBox
        operatorComboBox.addActionListener(e -> {
            String selectedOperator = (String) operatorComboBox.getSelectedItem();
            updateTableData(selectedOperator);
        });

        // Khởi tạo bảng ban đầu 
        table = createJTable(originalData, columnNames);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        sp.setBackground(null);
        sp.getViewport().setBackground(null);
        frame.add(sp, BorderLayout.CENTER);
        
        JLabel title = GUIConstants.jLabel(userID + " - Transactions", 30);
        title.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
        
        // Thêm các phần tử vào Frame
        frame.add(title, BorderLayout.NORTH);
        frame.add(operatorComboBox, BorderLayout.SOUTH);
        
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(GUIConstants.backgroundColor);
        frame.setVisible(true);
    }
    
    //Xử lý giao diện cho bảng
    private JTable createJTable(String[][] data, String[] columnNames) {
        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        table.setRowHeight(40);
        table.setBackground(null);
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("SansSerif", Font.PLAIN, 20));
                if (hasFocus) {
                    setBorder(null);
                }
                if (row % 2 == 0) {
                    setBackground(Color.white);
                } else {
                    setBackground(GUIConstants.foregroundColor);
                }
                return this;
            }
        };
        
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setFont(new Font("SansSerif", Font.BOLD, 20));
                setBackground(GUIConstants.mainColor);
                setForeground(Color.white);
                setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                return this;
            }
        };
        
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2,
                GUIConstants.mainColor));
        table.setBorder(BorderFactory.createMatteBorder(1, 2, 2, 2,
                GUIConstants.mainColor));
        table.setGridColor(GUIConstants.mainColor);
        table.setRowSelectionAllowed(false);
        return table;
    }
    
    //Xử lý sự kiện cho JComboBox
    private void updateTableData(String operator) {
        List<String[]> filteredData = new ArrayList<>();
        
        // "All"
        if (operator.equals("All")) {
            filteredData = List.of(originalData);
        } else {
            // các phương thức khác
            for (String[] transaction : originalData) {
                if (transaction[1].equals(operator)) { 
                    filteredData.add(transaction);
                }
            }
        }
        
        // Xóa dữ liệu cũ trong bảng và thêm dữ liệu lọc mới
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); 
        for (String[] row : filteredData) {
            tableModel.addRow(row); 
        }
    }
}

