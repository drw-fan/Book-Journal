import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import javax.swing.*;

public class BookJournal {

	// text fields
	private JTextField bookName;
	private JTextField bookAuthor;
	private JTextField dateRead;

	// text areas
	private JTextArea bookSummary;
	private JTextArea bookComments;

	// checkbox
	private JCheckBox readInBC;

	// window frame & content pane
	private JFrame frame;
	private JPanel contentPane;

	// scroll area
	JScrollPane scrollArea;

	// ArrayList to store books; Array of Strings to store book titles; JList for Library tab
	private ArrayList<Book> bookArrayList;
	private String[] bookArray;
	private JList<String> bookList;
	
	// tabbed pane
	private JTabbedPane tabby;


/////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		BookJournal bookJournal = new BookJournal();
		bookJournal.start();
	}

	public void start() {
		frame = new JFrame("Book Journal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) frame.getContentPane();

		makeMenus();
		makeContent();

		frame.pack();
		frame.setVisible(true);
	}

// creates menuBar and adds File, Edit, View & Help menus
	private void makeMenus() {
		JMenuBar menuBar;

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// set up menus
		menuBar.add(makeFileMenu());
		menuBar.add(makeEditMenu());
		menuBar.add(makeViewMenu());
		menuBar.add(makeHelpMenu());
	}

// creates File menu and Open..., New... & Exit items
	private JMenu makeFileMenu() {
		JMenu menu;
		JMenuItem menuItem;

		// set up the File menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		// add Open menu item
		menuItem = new JMenuItem("Open...");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.addActionListener(new OpenMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		menu.add(menuItem);

		// add New File menu item
		menuItem = new JMenuItem("New...");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new NewFileMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		menu.add(menuItem);

		// add Exit menu item
		menu.addSeparator();
		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.addActionListener(new ExitMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		menu.add(menuItem);

		return menu;
	}

// creates Edit menu and Add Book & Delete Book items
	private JMenu makeEditMenu() {
		JMenu menu;
		JMenuItem menuItem;

		// set up the Edit menu
		menu = new JMenu("Edit");
		menu.setMnemonic(KeyEvent.VK_E);

		// add Add Book menu item
		menuItem = new JMenuItem("Add Book");
		menuItem.setMnemonic(KeyEvent.VK_A);
//		menuItem.addActionListener(new AddBookMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		menu.add(menuItem);

		// add Delete Book menu item
		menuItem = new JMenuItem("Delete Book");
		menuItem.setMnemonic(KeyEvent.VK_D);
//		menuItem.addActionListener(new DeleteBookMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Event.CTRL_MASK));
		menu.add(menuItem);

		return menu;
	}

// creates View menu and Next Book & Previous Book items
	private JMenu makeViewMenu() {
		JMenu menu;
		JMenuItem menuItem;

		// set up the View menu
		menu = new JMenu("View");
		menu.setMnemonic(KeyEvent.VK_V);

		// add Next Book menu item
		menuItem = new JMenuItem("Next Book");
		menuItem.addActionListener(new NextMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, Event.ALT_MASK));
		menu.add(menuItem);

		// add Previous Book menu item
		menuItem = new JMenuItem("Previous Book");
		menuItem.addActionListener(new PrevMenuItemListener());
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP, Event.ALT_MASK));
		menu.add(menuItem);

		return menu;
	}

// creates Help menu and About Book Journal item
	private JMenu makeHelpMenu() {
		JMenu menu;
		JMenuItem menuItem;

		// set up the Help menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);

		// add About menu item
		menuItem = new JMenuItem("About Book Journal");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.addActionListener(new AboutMenuItemListener());
		menu.add(menuItem);

		return menu;
	}

// creates content for each tab (Book & Library)
	private void makeContent() {
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		tabby = new JTabbedPane();

// BOOK TAB
		// book name
		JPanel detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.LINE_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
		JLabel nameLabel = new JLabel("Book:  ");
		nameLabel.setFont(new Font("Trebuchet MS", Font.BOLD + Font.ITALIC, 14));
		detailsPanel.add(nameLabel);

		bookName = new JTextField();
		bookName.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bookName.setForeground(Color.BLUE);
		detailsPanel.add(bookName);
		detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(detailsPanel);
		
		// book author
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.LINE_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

		JLabel numLabel = new JLabel("Author:  ");
		numLabel.setFont(new Font("Trebuchet MS", Font.BOLD + Font.ITALIC, 14));
		detailsPanel.add(numLabel);

		bookAuthor = new JTextField();
		bookAuthor.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bookAuthor.setForeground(Color.BLUE);
		detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		detailsPanel.add(bookAuthor);
		panel.add(detailsPanel);

		// date book read
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.LINE_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
		JLabel dateReadLabel = new JLabel("Date Read:  ");
		dateReadLabel.setFont(new Font("Trebuchet MS", Font.BOLD + Font.ITALIC, 14));
		detailsPanel.add(dateReadLabel);

		dateRead = new JTextField(10);
		dateRead.setMaximumSize( dateRead.getPreferredSize() );
		dateRead.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		dateRead.setForeground(Color.BLUE);
		detailsPanel.add(dateRead);

		detailsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// read in Book Club? checkbox
		readInBC = new JCheckBox("Read in BC");
		readInBC.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		detailsPanel.add(readInBC);
		detailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(detailsPanel);
		
		// book summary
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

		JLabel bookSummaryLabel = new JLabel("Summary:");
		bookSummaryLabel.setFont(new Font("Trebuchet MS", Font.BOLD + Font.ITALIC, 14));
		detailsPanel.add(bookSummaryLabel);
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 4)));

		bookSummary = new JTextArea(5, 50);
		bookSummary.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bookSummary.setForeground(Color.BLUE);
		bookSummary.setLineWrap(true);
		bookSummary.setWrapStyleWord(true);

		scrollArea = new JScrollPane(bookSummary);
		scrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollArea.setAlignmentX(Component.LEFT_ALIGNMENT);

		detailsPanel.add(scrollArea);
		panel.add(detailsPanel);
		
		// book comments
		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.PAGE_AXIS));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

		JLabel bookCommentsLabel = new JLabel("My Comments:");
		bookCommentsLabel.setFont(new Font("Trebuchet MS", Font.BOLD + Font.ITALIC, 14));
		detailsPanel.add(bookCommentsLabel);
		detailsPanel.add(Box.createRigidArea(new Dimension(0, 4)));

		bookComments = new JTextArea(5, 50);
		bookComments.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bookComments.setForeground(Color.BLUE);
		bookComments.setLineWrap(true);
		bookComments.setWrapStyleWord(true);

		scrollArea = new JScrollPane(bookComments);
		scrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollArea.setAlignmentX(Component.LEFT_ALIGNMENT);

		detailsPanel.add(scrollArea);
		
		panel.add(detailsPanel);
		tabby.addTab("Book", panel);
		tabby.setMnemonicAt(0, KeyEvent.VK_P);

// LIBRARY TAB
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

		bookList = new JList<String>();  
		bookList.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		bookList.setForeground(Color.BLUE);

		JScrollPane listScroller = new JScrollPane(bookList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		scrollArea = new JScrollPane(bookList);
		scrollArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollArea);
		tabby.addTab("Library", panel);
		tabby.setMnemonicAt(1, KeyEvent.VK_T);

		contentPane.add(tabby);
	}

	private void setBook(Book b) {
		bookName.setText(b.getName());
		bookAuthor.setText(b.getAuthor());
		dateRead.setText(b.getDateRead());
		bookSummary.setText(b.getSummary());
		bookComments.setText(b.getComments());
		readInBC.setSelected(b.isReadInBC());
		
	}
	
	private void setLibrary() {
		bookList.setListData(bookArray);  

		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookList.setSelectedIndex(0);
		bookList.setLayoutOrientation(JList.VERTICAL_WRAP);
		bookList.setVisibleRowCount(10);

	}
	
	
///// INNER CLASSES -- Listeners for menu items
	private class OpenMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String name="";
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(frame);
			File bookFile = fc.getSelectedFile();
			if (bookFile == null)
				return;

			bookArrayList = new ArrayList<Book>();

			try {
				Scanner scannerBookFile = new Scanner(bookFile);

				while (scannerBookFile.hasNext()) {
					name = scannerBookFile.nextLine();
					String author = scannerBookFile.nextLine();
					String dateRead = scannerBookFile.nextLine();
					String summary = scannerBookFile.nextLine();
					String comments = scannerBookFile.nextLine();
					boolean readInBC = Boolean.parseBoolean(scannerBookFile.nextLine());
					bookArrayList.add(new Book(name, author, dateRead, summary, comments, readInBC));
				}

				scannerBookFile.close();

			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame,
						"I/O error in file\n\n" + bookFile.getName() + "\n\nThis program will close",
						"I/O Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}

			Collections.sort(bookArrayList);
			bookArray = new String[bookArrayList.size()];
			
			for (int i=0; i<bookArrayList.size(); i++)
				bookArray[i] = bookArrayList.get(i).getName();
			
			setLibrary();
			setBook(bookArrayList.get(0));
		}
	}

	private class NewFileMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame,
					"New File menu item clicked",
					"New File", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private class ExitMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class NextMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JOptionPane.showMessageDialog(frame,
					"Next Book menu item clicked",
					"Next Book", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private class PrevMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JOptionPane.showMessageDialog(frame,
					"Previous Book menu item clicked",
					"Previous Book", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private class AboutMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(frame,
					"Book Journal\n\nVersion 1.0\n\n" +
					"(c) Copyright Katie Ferencz 2019\nAll rights reserved\n\n"	+
					"Java programming language",
					"About Book Journal", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
