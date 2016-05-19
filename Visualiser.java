package sda;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Visualiser extends JFrame {
	
	List<String> content = new ArrayList<String>();

    class JButtonFluent extends JButton {
        public JButtonFluent addActionListenerFluent(ActionListener actionListener) {
            super.addActionListener(actionListener);
            return this;
        }

        public JButtonFluent setLabelFluent(String label) {
            super.setLabel(label);
            return this;
        }
    }
    
	public Visualiser(String title) throws HeadlessException {
	    setVisible(true);
	    setSize(400, 400);
	    setTitle(title);
	    setLocation(150, 150);
	    setPreferredSize(new Dimension(310, 75));
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new FlowLayout());
        JButtonFluent button = new JButtonFluent();
        add(new JButtonFluent().setLabelFluent("Task 1").addActionListenerFluent(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	System.out.println(123);
                } catch (Throwable ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }
        }));
        
        addListView();
	}

	private void addListView() {
		final DefaultListModel listModel = new DefaultListModel();
		try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						try {
							System.out.println(new Date().toString());
							listModel.clear();
							for (String string : content) {								
								listModel.addElement(string);
							}
							Thread.sleep((long) 1e3);
							revalidate();
							repaint();

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		} catch (Exception e) {
			for (int i = 0; i < 10; i++) {
				listModel.addElement(Integer.toString(i));
			}
		}
		JList list = new JList(listModel); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);

		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 350));

		add(listScroller);
		
	}
	static Visualiser vis;
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				vis = new Visualiser("Calculator using stack");
			}
		}).start();
		
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vis.content.add(new Date().toString());
		}
	}

}
