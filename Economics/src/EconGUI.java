import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class EconGUI {
	
	double present=1;
	double future=1;
	double annual=1;
	double interest=0;
	double initialValue=0;
	double originalTimePeriod=0;
	double gradientValue=1;
	double gradientPercent=0;
	double baseAmount=0;
	double newTimePeriod=0;
	
	private JFrame frame;
	private JTextField presentTextField;
	private JTextField futureTextField;
	private JTextField annualTextField;
	private JTextField gradientVTextField;
	private JTextField gradientPTextField;
	private JTextField originalTimePeriodTextField;
	private JTextField baseAmountTextField;
	private JTextField newTimeTextField;
	private JTextField interestTextField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EconGUI window = new EconGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EconGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		presentTextField = new JTextField();
		presentTextField.setBounds(144, 19, 177, 26);
		layeredPane.add(presentTextField);
		presentTextField.setColumns(10);
		
		futureTextField = new JTextField();
		futureTextField.setBounds(144, 42, 177, 26);
		layeredPane.add(futureTextField);
		futureTextField.setColumns(10);
		
		annualTextField = new JTextField();
		annualTextField.setBounds(144, 65, 177, 26);
		layeredPane.add(annualTextField);
		annualTextField.setColumns(10);
		
		gradientVTextField = new JTextField();
		gradientVTextField.setBounds(144, 88, 177, 26);
		layeredPane.add(gradientVTextField);
		gradientVTextField.setColumns(10);
		
		JLabel lblPresent = new JLabel("Present Value:");
		lblPresent.setBounds(16, 24, 98, 16);
		layeredPane.add(lblPresent);
		
		JLabel lblFuture = new JLabel("Future Value:");
		lblFuture.setBounds(16, 47, 92, 16);
		layeredPane.add(lblFuture);
		
		JLabel lblNewLabel_2 = new JLabel("Annual Value:");
		lblNewLabel_2.setBounds(16, 70, 92, 16);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gradient Value:");
		lblNewLabel_3.setBounds(16, 93, 116, 16);
		layeredPane.add(lblNewLabel_3);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				futureTextField.setText("");
				presentTextField.setText("");
				annualTextField.setText("");
				gradientVTextField.setText("");
				gradientPTextField.setText("");
				originalTimePeriodTextField.setText("");
				baseAmountTextField.setText("");
				present=1;
				future=1;
				annual=1;
				interest=0;
				initialValue=0;
				originalTimePeriod=0;
				gradientValue=1;
				gradientPercent=0;
				baseAmount=0;
				newTimePeriod=0;
			}
		});
		btnReset.setBounds(75, 243, 117, 29);
		layeredPane.add(btnReset);
		
		JButton btnFindAllPossible = new JButton("Find All");
		btnFindAllPossible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("#.##");
				if(!presentTextField.getText().equals("")) {
					try {
						present=Double.parseDouble(presentTextField.getText());
					}catch(Exception exc) {
						presentTextField.setText("ERROR");
					}
				}if(!futureTextField.getText().equals("")) {
					try {
						future=Double.parseDouble(futureTextField.getText());
					}catch(Exception exc) {
						futureTextField.setText("ERROR");
					}
				}if(!annualTextField.getText().equals("")) {
					try {
						annual=Double.parseDouble(annualTextField.getText());
					}catch(Exception exc) {
						annualTextField.setText("ERROR");
					}
				}if(!gradientVTextField.getText().equals("")) {
					try {
						gradientValue=Double.parseDouble(gradientVTextField.getText());
					}catch(Exception exc) {
						gradientVTextField.setText("ERROR");
					}
				}if(!interestTextField.getText().equals("")) {
					try {
						interest=Double.parseDouble(interestTextField.getText());
					}catch(Exception exc) {
						interestTextField.setText("ERROR");
					}	
				}if(!newTimeTextField.getText().equals("")) {
					try {
						newTimePeriod=Double.parseDouble(newTimeTextField.getText());
					}catch(Exception exc) {
						newTimeTextField.setText("ERROR");
					}	
				}if(!baseAmountTextField.getText().equals("")) {
					try {
						baseAmount=Double.parseDouble(baseAmountTextField.getText());
					}catch(Exception exc) {
						baseAmountTextField.setText("ERROR");
					}	
				}if(!originalTimePeriodTextField.getText().equals("")) {
					try {
						originalTimePeriod=Double.parseDouble(originalTimePeriodTextField.getText());
					}catch(Exception exc) {
						originalTimePeriodTextField.setText("ERROR");
					}	
				}if(!interestTextField.getText().equals("")&&!presentTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&futureTextField.getText().equals("")&&annualTextField.getText().equals("")) {
						future=EconomicsMethods.findFPValue(present, interest, newTimePeriod);
						futureTextField.setText(df.format(future));
						annual=EconomicsMethods.findAPValue(present, interest, newTimePeriod);
						annualTextField.setText(df.format(annual));
				}if(!interestTextField.getText().equals("")&&!futureTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&presentTextField.getText().equals("")&&annualTextField.getText().equals("")) {
						present=EconomicsMethods.findPFValue(future, interest, newTimePeriod);
						presentTextField.setText(df.format(present));
						annual=EconomicsMethods.findAFValue(future, interest, newTimePeriod);
						annualTextField.setText(df.format(annual));
				}if(!interestTextField.getText().equals("")&&!annualTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&futureTextField.getText().equals("")&&presentTextField.getText().equals("")&&originalTimePeriodTextField.getText().equals("")) {
						present=EconomicsMethods.findPAValue(annual, interest, newTimePeriod);
						presentTextField.setText(df.format(present));
						future=EconomicsMethods.findFAValue(annual, interest, newTimePeriod);
						futureTextField.setText(df.format(future));
				}if(!interestTextField.getText().equals("")&&!baseAmountTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&presentTextField.getText().equals("")) {
						present=EconomicsMethods.findPresentUniformValue(gradientValue, baseAmount, interest, newTimePeriod);
						presentTextField.setText(df.format(present));
				}if(!interestTextField.getText().equals("")&&!baseAmountTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&futureTextField.getText().equals("")) {
					future=EconomicsMethods.findFutureUniformValue(gradientValue, baseAmount, interest, newTimePeriod);
					futureTextField.setText(df.format(future));
				}if(!interestTextField.getText().equals("")&&!baseAmountTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&annualTextField.getText().equals("")) {
				annual=EconomicsMethods.findAnnualUniformValue(gradientValue, baseAmount, interest, newTimePeriod);
				annualTextField.setText(df.format(annual));
				}if(!interestTextField.getText().equals("")&&!annualTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&!originalTimePeriodTextField.getText().equals("")&&presentTextField.getText().equals("")) {
						present=EconomicsMethods.findEquivalentPresentShifted(annual, interest, originalTimePeriod, newTimePeriod);//(gradientValue, baseAmount, interest, newTimePeriod);
						presentTextField.setText(df.format(present));
						future=EconomicsMethods.findEquivalentFutureShifted(annual, interest, originalTimePeriod, newTimePeriod);//(gradientValue, baseAmount, interest, newTimePeriod);
						futureTextField.setText(df.format(future));
						annual=EconomicsMethods.findEquivalentAnnualShifted(annual, interest, originalTimePeriod, newTimePeriod);//(gradientValue, baseAmount, interest, newTimePeriod);
						annualTextField.setText(df.format(annual));
				}if(!interestTextField.getText().equals("")&&annualTextField.getText().equals("")&&!newTimeTextField.getText().equals("")&&originalTimePeriodTextField.getText().equals("")&&presentTextField.getText().equals("")&&futureTextField.getText().equals("")&&gradientVTextField.getText().equals("")) {
					interest=EconomicsMethods.calculateEffectiveAny(interest, newTimePeriod);
					interestTextField.setText(String.valueOf(interest));
			}
			}
		});
		//I’ve implemented all the methods that are on the review, except for interest rate. It calculates all original relations as well as finding Future from the Annual value and two time periods. New Time Period is always used and Original is used when you are given more than one in a problem.
		btnFindAllPossible.setBounds(287, 243, 117, 29);
		layeredPane.add(btnFindAllPossible);
		
		JLabel lblGradient = new JLabel("Gradient %:");
		lblGradient.setBounds(16, 116, 92, 16);
		layeredPane.add(lblGradient);
		
		gradientPTextField = new JTextField();
		gradientPTextField.setBounds(144, 111, 177, 26);
		layeredPane.add(gradientPTextField);
		gradientPTextField.setColumns(10);
		
		originalTimePeriodTextField = new JTextField();
		originalTimePeriodTextField.setBounds(144, 157, 177, 26);
		layeredPane.add(originalTimePeriodTextField);
		originalTimePeriodTextField.setColumns(10);
		
		JLabel lblDefaultTime = new JLabel("Original Time:");
		lblDefaultTime.setBounds(16, 162, 98, 16);
		layeredPane.add(lblDefaultTime);
		
		baseAmountTextField = new JTextField();
		baseAmountTextField.setBounds(144, 134, 177, 26);
		layeredPane.add(baseAmountTextField);
		baseAmountTextField.setColumns(10);
		
		JLabel lblBaseAmount = new JLabel("Base Amount:");
		lblBaseAmount.setBounds(16, 139, 98, 16);
		layeredPane.add(lblBaseAmount);
		
		newTimeTextField = new JTextField();
		newTimeTextField.setBounds(144, 180, 177, 26);
		layeredPane.add(newTimeTextField);
		newTimeTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New Time:");
		lblNewLabel.setBounds(16, 185, 98, 16);
		layeredPane.add(lblNewLabel);
		
		interestTextField = new JTextField();
		interestTextField.setBounds(144, 203, 177, 26);
		layeredPane.add(interestTextField);
		interestTextField.setColumns(10);
		
		JLabel lblInterest = new JLabel("Interest:");
		lblInterest.setBounds(16, 208, 92, 16);
		layeredPane.add(lblInterest);

	}
}
