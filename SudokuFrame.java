import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


 public class SudokuFrame extends JFrame {

 	private boolean checked;
	
	public SudokuFrame() {
		super("Sudoku Solver");
		checked = false;
		JTextArea holdSource = new JTextArea(15,20);
		JTextArea holdResults = new JTextArea(15,20);
		holdSource.setBorder(new TitledBorder("Puzzle"));
		holdResults.setBorder(new TitledBorder("Solution"));

		holdSource.getDocument().addDocumentListener(new DocumentListener() {

			//Fill only insert Update, because when user deletes something its dont need to recompute, after he write new number we compute new solution
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (checked){
					Sudoku sudoku = new Sudoku(holdSource.getText());
					int solutions = sudoku.solve();
					holdResults.setText(sudoku.getSolutionText() + '\n' + "Solutions:  " + solutions + '\n' + "Elapsed:  " +sudoku.getElapsed());
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		// Could do this:
		//setLocationByPlatform(true);
		super.setSize(400,500);
		Panel p = new Panel();
		p.setLayout(new BorderLayout(4,4));
		super.getContentPane().add(p,BorderLayout.CENTER);
		p.add(holdSource,BorderLayout.CENTER);
		p.add(holdResults,BorderLayout.EAST);

		Panel controls = new Panel();
		super.getContentPane().add(controls,BorderLayout.SOUTH);
		controls.setLayout(new BoxLayout(controls,BoxLayout.LINE_AXIS));
		controls.add(Box.createRigidArea(new Dimension(10,0)));

		JButton checkButton = new JButton("Check");
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sudoku sudoku = new Sudoku(holdSource.getText());
				int solutions = sudoku.solve();
				holdResults.setText(sudoku.getSolutionText() + '\n' + "Solutions:  " + solutions + '\n' + "Elapsed:  " +sudoku.getElapsed());
			}
		});


		controls.add(checkButton,BorderLayout.NORTH);
		controls.add(Box.createRigidArea(new Dimension(10,0)));

		JCheckBox autoCheck = new JCheckBox("Auto Check");
		autoCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checked = !checked;
			}
		});

		controls.add(autoCheck,BorderLayout.CENTER);
		controls.add(Box.createRigidArea(new Dimension(0,30)));




		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}


	public static void main(String[] args) {
		// GUI Look And Feel
		// Do this incantation at the start of main() to tell Swing
		// to use the GUI LookAndFeel of the native platform. It's ok
		// to ignore the exception.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ignored) { }

		SudokuFrame frame = new SudokuFrame();
	}

}
