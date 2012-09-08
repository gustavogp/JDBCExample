import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
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
			log.append("Running..." + newLine);
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
	public static void output(ArrayList<ArrayList<String>> returnArray) {
		ArrayList<String> titleArray = returnArray.get(0);
		ArrayList<String> authorArray = returnArray.get(1);
		
		for (int i = 0; i < titleArray.size(); i++){
			log.append("Title: " + titleArray.get(i) + " Author: " + authorArray.get(i) + newLine);
		}
		
	}
	public static void errorDriver(String err) {
		log.append("Error loading driver: " + err + newLine);
	}
	public static void errorConnecting(String err) {
		log.append("Error connecting to DB: " + err + newLine);
	}
	public static void connectedMsg() {
		log.append("Connected" + newLine);
	}
	public static void driverLoadedMsg() {
		log.append("Driver Loaded" + newLine);
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
