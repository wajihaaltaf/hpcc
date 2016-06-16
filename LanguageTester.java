package languages;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * This class shows how text that is entered by the user into GUI text fields 
 * and contains non-Latin-1 characters (such as Arabic, Farsi, Korean, etc.) 
 * should be handled when saved to and read from a file.
 * 
 * @author Natasha Lloyd
 */
public class LanguageTester extends JFrame {

	/**
	 * Initializes the GUI. To see how reading and writing are done, look at the
	 * read() and write() methods below.
	 */
    private static void init() {
        // use the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        // instantiate the controlling class
        LanguageTester frame = new LanguageTester();
        frame.setTitle("Language Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // add components to it
        JPanel contentPane = (JPanel) frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        contentPane.setBorder(
        		BorderFactory.createCompoundBorder(
        				BorderFactory.createEmptyBorder(10, 10, 10, 10),
        				BorderFactory.createTitledBorder(" Language Tester ")));
        
        // instructions
        JTextArea instructions = new JTextArea();
        instructions.setEditable(false);
        instructions.setOpaque(false);
        instructions.setFont(new JLabel().getFont());
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setText(
        		"Enter text in the input text field. To change languages, " +
        		"use the Windows language toolbar while the focus is inside " +
        		"the text field.\n\n" +
        		"The output text field shows what the Java program is " +
        		"receiving from the input text field.\n\n" +
        		"You can save the input to a file, which will use UTF-8 " +
        		"encoding. You can also read in UTF-8 files that were " +
        		"created with this tool or with other applications.");
        instructions.setPreferredSize(new Dimension(350, 130));
        instructions.setMaximumSize(new Dimension(350, 130));
        contentPane.add(instructions);
        
        // user input text box:
        JLabel inputLabel = new JLabel("Input:");
        contentPane.add(inputLabel);
        final JTextField inputText = new JTextField();
        inputText.setPreferredSize(new Dimension(300, 20));
        inputText.setMaximumSize(new Dimension(300, 20));
        contentPane.add(inputText);
        
        // java output text box:
        JLabel outputLabel = new JLabel("Output:");
        contentPane.add(outputLabel);
        final JTextField outputText = new JTextField();
        outputText.setEditable(false);
        outputText.setPreferredSize(new Dimension(300, 20));
        outputText.setMaximumSize(new Dimension(300, 20));
        contentPane.add(outputText);
        
        // save button
        final JButton saveButton = new JButton("Save input to text file");
        saveButton.setPreferredSize(new Dimension(150, 23));
        contentPane.add(saveButton);
        
        // open button
        final JButton openButton = new JButton("Read input from text file");
        openButton.setPreferredSize(new Dimension(150, 23));
        contentPane.add(openButton);
        
        // clear button
        final JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(150, 23));
        contentPane.add(clearButton);
        
        // layout the components
        layout.putConstraint(
        		SpringLayout.WEST, instructions,
                5,
                SpringLayout.WEST, contentPane);
        layout.putConstraint(
        		SpringLayout.NORTH, instructions,
                5,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(
        		SpringLayout.WEST, inputLabel,
                0,
                SpringLayout.WEST, instructions);
        layout.putConstraint(
        		SpringLayout.NORTH, inputLabel,
                20,
                SpringLayout.SOUTH, instructions);
        layout.putConstraint(
        		SpringLayout.WEST, inputText,
                20,
                SpringLayout.EAST, inputLabel);
        layout.putConstraint(
        		SpringLayout.NORTH, inputText,
                0,
                SpringLayout.NORTH, inputLabel);
        layout.putConstraint(
        		SpringLayout.WEST, outputLabel,
                0,
                SpringLayout.WEST, inputLabel);
        layout.putConstraint(
        		SpringLayout.NORTH, outputLabel,
                20,
                SpringLayout.SOUTH, inputLabel);
        layout.putConstraint(
        		SpringLayout.WEST, outputText,
                0,
                SpringLayout.WEST, inputText);
        layout.putConstraint(
        		SpringLayout.NORTH, outputText,
                0,
                SpringLayout.NORTH, outputLabel);
        layout.putConstraint(
        		SpringLayout.EAST, clearButton,
                0,
                SpringLayout.EAST, inputText);
        layout.putConstraint(
        		SpringLayout.NORTH, clearButton,
                20,
                SpringLayout.SOUTH, outputLabel);
        layout.putConstraint(
        		SpringLayout.EAST, saveButton,
                0,
                SpringLayout.EAST, inputText);
        layout.putConstraint(
        		SpringLayout.NORTH, saveButton,
                5,
                SpringLayout.SOUTH, clearButton);
        layout.putConstraint(
        		SpringLayout.EAST, openButton,
        		0,
        		SpringLayout.EAST, inputText);
        layout.putConstraint(
        		SpringLayout.NORTH, openButton,
        		5,
        		SpringLayout.SOUTH, saveButton);
        layout.putConstraint(
        		SpringLayout.EAST, contentPane,
                5,
                SpringLayout.EAST, outputText);
        layout.putConstraint(
        		SpringLayout.SOUTH, contentPane,
                5,
                SpringLayout.SOUTH, openButton);
        
        // setup the listeners
        inputText.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent event) {
				outputText.setText(inputText.getText());
			}        	
        });
        clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				inputText.setText("");
			}
        });
        saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				int response =
					chooser.showSaveDialog(saveButton.getTopLevelAncestor());
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						write(file, inputText.getText());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(
								saveButton.getTopLevelAncestor(), 
								e.getMessage());
					}
				}
			}
        });
        openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				int response =
					chooser.showOpenDialog(openButton.getTopLevelAncestor());
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						inputText.setText(read(file));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(
								saveButton.getTopLevelAncestor(), 
								e.getMessage());
					}
				}
			}
        });
        
        // display the window
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Shows how to write to a file so that all characters are preserved.
     * @param file The output file.
     * @param text The text to be written out (a Unicode string).
     * @throws Exception if something goes wrong while writing to file.
     */
    public static void write(File file, String text) throws Exception {
    	FileOutputStream writer = new FileOutputStream(file);
		// NOTE: using OutputStreamWriter so we can specify UTF-8 encoding. 
    	// Using the default encoding does not write out special characters 
    	// correctly.
    	OutputStreamWriter out = new OutputStreamWriter(writer, "utf-8");
		out.write(text);
		out.close();
		writer.close();
    }
    
    /**
     * Shows how to read in a UTF-8 file and put its contents into a Unicode
     * string.
     * @param file The input file.
     * @return A string of the contents from the input file.
     * @throws Exception if something goes wrong while reading from file.
     */
    public static String read(File file) throws Exception {
    	FileInputStream reader = new FileInputStream(file);
		// NOTE: using InputStreamReader so that we can specify UTF-8 encoding.
    	// This seems to be the only way to get special characters correctly 
    	// from file.
    	BufferedReader in = 
			new BufferedReader(new InputStreamReader(reader, "utf-8"));
		String fromFile = new String();
		String line;
		while ((line = in.readLine()) != null) 
			fromFile += line;
		in.close();
		reader.close();
		return fromFile;
    }
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
            }
        });
    }
    
}
