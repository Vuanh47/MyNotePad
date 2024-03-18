package MyNotePad;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;


public class MyNotePadView extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextArea textArea;
	public JLabel lblNewLabel;
	public MyNotePadModel model = new MyNotePadModel();


	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public MyNotePadView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Font font = new Font("Arial", Font.PLAIN, 16);
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.EAST);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Thiết lập thanh cuộn dọc
        textArea = new JTextArea();
        contentPane.add(textArea, BorderLayout.CENTER);
        textArea.setFont(font);
        
        lblNewLabel = new JLabel("Address");
        lblNewLabel.setPreferredSize(new Dimension(10, 50));
        contentPane.add(lblNewLabel, BorderLayout.SOUTH);

        ActionListener actionListener = (ActionListener) new MyNotepadController(this);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem mntmNewMenuItem = new JMenuItem("New", KeyEvent.VK_N);
        mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
    	fileMenu.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(actionListener);
        setJMenuBar(menuBar);
        
        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);	
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
    	
        openMenuItem.addActionListener(actionListener);
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
    	saveMenuItem.addActionListener(actionListener);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);
        

		
		
	}

	
	

}
