package com.journalpublication.view;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.journalpublication.model.Journal;

public class JournalPubFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605549884833310068L;
	private JList<Journal> jList;
	private JButton btnView;
	private JButton btnclose;
	private JScrollPane jScrollPane;
	
	private DefaultListModel<Journal> listModel;

	public JournalPubFrame() {
		this("Journal Publication");
	}

	public JournalPubFrame(String title) {
		super(title);

		initComponents();
	}

	private void initComponents() {
		// setup frame
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// setup list
		this.listModel = new DefaultListModel<Journal>();
    	this.jList = new JList<Journal>(this.listModel);
    	this.jList.setSelectedIndex(0);
    	this.jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	this.jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
				javax.swing.JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
			}
		});
		
    	this.btnView = new JButton("View");
		btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	javax.swing.JOptionPane.showMessageDialog(null, "viewing pdf file");
            }
        });
		
		this.btnclose = new JButton("Close");
		btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	closeBtnActionPerformed(evt);
            }
        });
		
		this.jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(this.jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(this.btnView)
                        .addComponent(this.btnclose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(this.btnView)
                .addComponent(this.btnclose)
                .addGap(20, 20, 20))
        );

        this.pack();
	}
	
    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        this.exit();
    }
    
    public void exit() {
    	// exit application
    	dispose();
    }
    
    public void reloadJournalList(ArrayList<Journal> journals) {
    	
    	this.listModel.clear();
    	
    	if (journals != null) {
        	for (Journal journal : journals) {
        		this.listModel.addElement(journal);
        	}    		
    	}    	
    }
}
