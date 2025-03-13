import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LibraryManagement{
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private JFrame frame;
    private JTextField bookTitleField, authorField;
    private JTextArea displayArea;
    
    public LibraryManagement(){
        frame = new JFrame("Library Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        bookTitleField = new JTextField(20);
        authorField = new JTextField(20);
        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton deleteButton = new JButton("Delete Book");
        displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        frame.add(new JLabel("Book Title:"));
        frame.add(bookTitleField);
        frame.add(new JLabel("Author:"));
        frame.add(authorField);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(deleteButton);
        frame.add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> addBook());
        viewButton.addActionListener(e -> viewBooks());
        deleteButton.addActionListener(e -> deleteBook());

        frame.setVisible(true);
    }

    private void addBook() {
        String title = bookTitleField.getText();
        String author = authorField.getText();
        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both title and author.");
            return;
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO books (title, author) VALUES (?, ?)");) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Book added successfully.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void viewBooks() {
        displayArea.setText("");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books");) {
            while (rs.next()) {
                displayArea.append(rs.getInt("id") + ". " + rs.getString("title") + " by " + rs.getString("author") + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void deleteBook() {
        String bookId = JOptionPane.showInputDialog(frame, "Enter Book ID to delete:");
        if (bookId == null || bookId.trim().isEmpty()) {
            return;
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");) {
            stmt.setInt(1, Integer.parseInt(bookId));
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(frame, "Book deleted successfully.");
                viewBooks();
            } else {
                JOptionPane.showMessageDialog(frame, "No book found with given ID.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagement ::new);
    }
}
