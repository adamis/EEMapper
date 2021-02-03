package br.com.adamis.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import br.com.adamis.ConsoleLog;
import br.com.adamis.resources.ResourcesImages;
import br.com.adamis.utils.Statics;


public class Menu extends JFrame {

	private static final long serialVersionUID = -7453321014891785234L;
	private JPanel contentPane;
	private JTree fileTree;
	private JDesktopPane conteudo;
	private ConsoleLog consoleLog;
	private int height = 500;
	private int width = 500;
	private JLabel lblLogo;
	private JLabel dialogAviso;
	private JLabel lblAsk;
	private JLabel lblCopyart;

	public void visible(boolean control) {
		this.setVisible(control);
	}

	/**
	 * Create the frame.
	 */
	public Menu() {

		setTitle("" + Statics.NAME_SYS + " v" + Statics.VERSION + " - " + System.getProperty("os.name"));

		try {
			setDefaultLookAndFeelDecorated(true);			
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		addComponentListener(new ComponentListener() {

			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}


			public void componentResized(ComponentEvent e) {
				height = getHeight();
				width = getWidth();

				if (conteudo != null) {
					conteudo.setBounds(1, 0, width - 5, height - 63);
					lblLogo.setBounds(10, 11, width, height - 71);
					dialogAviso.setBounds(width - 250, 52, 190, 114);
					lblAsk.setBounds(width - 90, 2, 52, 57);
					lblCopyart.setBounds((width / 2) - 80, height - 100, 148, 14);
					conteudo.repaint();
				}

			}


			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}


			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		height = screenSize.height;
		width = screenSize.width;

		setBounds(100, 100, width - 150, height - 100);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		JMenu menuArquivo = new JMenu("Arquivo");
		menuArquivo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuArquivo.setIcon(ResourcesImages.coding());
		menuBar.add(menuArquivo);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem mntmConsole = new JMenuItem("Console");
		mntmConsole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (consoleLog.isClosed()) {
					// System.err.println("true");
					consoleLog = new ConsoleLog();
					consoleLog.setVisible(true);
					consoleLog.moveToFront();
					conteudo.revalidate();
					conteudo.repaint();

				} else {
					// System.err.println("false");
					consoleLog.setVisible(true);
					consoleLog.moveToFront();
				}
			}
		});
		menuArquivo.add(mntmConsole);
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		menuArquivo.add(mntmSair);

		JMenu menu_12_1 = new JMenu("    ");
		menu_12_1.setFocusable(false);
		menu_12_1.setEnabled(false);
		menuBar.add(menu_12_1);

		JMenu mnNewMenu = new JMenu("Gerenciar");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.setIcon(ResourcesImages.database2());
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Banco de Dados");
		mntmNewMenuItem_1.setIcon(null);
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_MASK));

		JMenu menu_12_1_1 = new JMenu("    ");
		menu_12_1_1.setFocusable(false);
		menu_12_1_1.setEnabled(false);
		menuBar.add(menu_12_1_1);

		JMenu mntmNewMenuItem = new JMenu("Run");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem.setIcon(ResourcesImages.gear());		
		menuBar.add(mntmNewMenuItem);

		JMenuItem gerarMapperMenuItem = new JMenuItem("Mapper Entity");
		gerarMapperMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarMapper gerarMapper = new GerarMapper(consoleLog);
				gerarMapper.setVisible(true);
				conteudo.add(gerarMapper);
				conteudo.revalidate();
				conteudo.repaint();
				gerarMapper.moveToFront();				
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Conexao Config");
		mntmNewMenuItem.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Locales Config");
		mntmNewMenuItem.add(mntmNewMenuItem_3);
		mntmNewMenuItem.add(gerarMapperMenuItem);

		JMenuItem runMapperMenuItem = new JMenuItem("Run Mapper");
		runMapperMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CODE
			}
		});
		mntmNewMenuItem.add(runMapperMenuItem);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				BancoDados bancoDados;
				try {

					bancoDados = new BancoDados(consoleLog);
					bancoDados.setVisible(true);
					conteudo.add(bancoDados);
					conteudo.revalidate();
					conteudo.repaint();
					bancoDados.moveToFront();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBounds(new Rectangle(0, 0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		File dir = new File(".");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 2));

		//		fileTree = new JTree();
		//		fileTree.setMaximumSize(new Dimension(150, 64));
		//		fileTree = refreshTree(fileTree, dir);
		//
		//		scrollPane.setViewportView(fileTree);

		// ImageIcon image = new ImageIcon(getClass().getResource("cats.png"));
		// ImageIcon image2 = new ImageIcon(getClass().getResource("faq.png"));
		// ImageIcon image3 = new ImageIcon(getClass().getResource("dialogue-box.png"));
		contentPane.setLayout(null);

		conteudo = new JDesktopPane() {

			private static final long serialVersionUID = -1168799504244402764L;
			ImageIcon icon = ResourcesImages.bg();
			Image image = icon.getImage();

			// Image newimage = image.getScaledInstance(icon.getIconWidth(),
			// icon.getIconHeight(), Image.SCALE_SMOOTH);
			Image newimage = image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (newimage != null) {
					g.drawImage(newimage, 0, 0, this.getWidth(), this.getHeight(), this);
				}
				// g.drawImage(newimage, 0, 0, this);
			}


			public Dimension getPreferredSize() {
				return new Dimension(width, height);
			}

		};

		conteudo.setBounds(1, 0, width, height - 60);
		// conteudo.setPreferredSize(new Dimension(100, 0));
		conteudo.setBorder(null);
		conteudo.setBackground(new Color(255, 255, 255));
		contentPane.add(conteudo);

		JLabel label = new JLabel((Icon) null);
		label.setBackground(Color.WHITE);
		label.setBounds(1181, 177, 52, 57);
		conteudo.add(label);

		lblAsk = new JLabel(ResourcesImages.faq());
		lblAsk.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent arg0) {

				dialogAviso.setVisible(true);
			}


			public void mouseExited(MouseEvent e) {

				dialogAviso.setVisible(false);
			}
		});

		dialogAviso = new JLabel(ResourcesImages.dialogue());
		dialogAviso.setVisible(false);
		dialogAviso.setBounds(1138, 52, 190, 114);
		dialogAviso.setBackground(Color.WHITE);
		conteudo.add(dialogAviso);
		lblAsk.setBackground(Color.WHITE);
		lblAsk.setBounds(1300, 2, 52, 57);
		conteudo.add(lblAsk);

		lblLogo = new JLabel(ResourcesImages.cats());
		lblLogo.setBackground(new Color(255, 255, 255));
		lblLogo.setBounds(10, 11, width + 116, height - 71);
		conteudo.add(lblLogo);

		consoleLog = new ConsoleLog();
		consoleLog.setVisible(false);

		lblCopyart = new JLabel(" Esquadro � 2020 Adamis Starling");
		lblCopyart.setForeground(new Color(255, 255, 255));
		lblCopyart.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblCopyart.setBounds(576, 666, 300, 14);
		conteudo.add(lblCopyart);
		conteudo.add(consoleLog);

		Calendar.getInstance();
		conteudo.revalidate();
		conteudo.repaint();

	}
}

