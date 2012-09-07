import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class GuiJDBCE extends JPanel
					implements ActionListener {
	
	//fields
	private static final String newLine = "\n";
	JButton runButton;
	static JTextArea log;
	
	//constructor
	public GuiJDBCE() {
		super(new BorderLayout());
		
		//create the output display area
				log = new JTextArea(10,50);
				log.setMargin(new Insets(5,5,5,5));
				log.setEditable(false);
				JScrollPane logScrollPane = new JScrollPane(log);
				
				//create the run button
				runButton = new JButton("Run");
				runButton.addActionListener(this); //this class implements ActionListener
				
				//put the button in a separate panel
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(runButton);
				
				//add the buttonPanel and display area to this panel
				add(buttonPanel, BorderLayout.PAGE_START);
				add(logScrollPane, BorderLayout.CENTER);
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == runButton){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					runButtonAction();
				}
			});
		}
	}
	public void runButtonAction() {
		JDBCExample.main();
	}
	public static void output(String title, String author) {
		log.append("Title: " + title + " Author: " + author + newLine);
	}
	public static void createAndShow() {
		JFrame frame = new JFrame("JDBC Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new GuiJDBCE());
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShow();
			}
		});

	}


}
