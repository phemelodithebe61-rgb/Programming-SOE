package za.ac.mattress;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Locale;

public class ProductApp extends JFrame {
    private final JTextField customerField = new JTextField(23);
    private final JTextField priceField = new JTextField(23);
    private final JComboBox<String> productBox = new JComboBox<>(new String[] {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final Path dataFile = Paths.get("data.txt");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 310);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        fileMenu.add(exit);
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem process = new JMenuItem("Process");
        process.addActionListener(e -> processSale());
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> clearForm());
        toolsMenu.add(process);
        toolsMenu.add(clear);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(24, 22, 24, 22));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Customer Name:"), c);
        c.gridx = 1; form.add(customerField, c);
        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Select Product:"), c);
        c.gridx = 1; form.add(productBox, c);
        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Enter Price:"), c);
        c.gridx = 1; form.add(priceField, c);
        JButton button = new JButton("PROCESS");
        button.addActionListener(e -> processSale());
        c.gridx = 1; c.gridy = 3; form.add(button, c);
        add(form, BorderLayout.CENTER);
    }

    private void clearForm() {
        customerField.setText("");
        priceField.setText("");
        productBox.setSelectedIndex(0);
        customerField.requestFocusInWindow();
    }

    private void processSale() {
        String customer = customerField.getText().trim();
        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a customer name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            customerField.requestFocusInWindow(); return;
        }
        double price;
        try { price = Double.parseDouble(priceField.getText().trim()); }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            priceField.requestFocusInWindow(); return;
        }
        ProductCalculations calc;
        try { calc = new ProductCalculations(price); }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE); return;
        }
        String product = (String) productBox.getSelectedItem();
        String details = String.format(Locale.US,
            "Customer Name: %s%nProduct: %s%nVAT: R %.2f%nCOMMISSION: R %.2f%nTOTAL: R %.2f",
            customer, product, calc.GetVat(), calc.GetCommission(), calc.GetTotal());
        String log = "DATA LOG\n" + "***************************************\n" + details
            + "\n***************************************\n\n";
        try {
            Files.write(dataFile, log.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to save data.txt: " + e.getMessage(),
                "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, details, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
