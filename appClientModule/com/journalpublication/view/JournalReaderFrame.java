package com.journalpublication.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * Window for journal reading
 * @author nouval
 *
 */
public final class JournalReaderFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3314418642410357358L;

	public JournalReaderFrame(String title, Image journalAsImage) {
		
		super (title);
		setDefaultCloseOperation (DISPOSE_ON_CLOSE);

		JLabel label = new JLabel (new ImageIcon (journalAsImage));
		label.setVerticalAlignment (JLabel.TOP);

		setContentPane (new JScrollPane (label));

		pack ();
		setVisible (true);
	}
}
