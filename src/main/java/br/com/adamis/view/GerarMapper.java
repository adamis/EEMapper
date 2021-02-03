package br.com.adamis.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import br.com.adamis.ConsoleLog;
import br.com.adamis.controller.ListTableController;
import br.com.adamis.helper.SqliteHelper;
import br.com.adamis.resources.ResourcesImages;
import br.com.adamis.sqlite.entity.BancoDadosEntity;
import br.com.adamis.utils.PersonalItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.ScrollPane;
import javax.swing.JTextField;

public class GerarMapper extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5137220162697221129L;
	private BancoDadosEntity bancoDados;
	private TextField inputProject;
	private JTable table;
	private ConsoleLog consoleLog;
	private JTable table_1,table_2;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public GerarMapper(ConsoleLog consoleLog) {
		
		this.consoleLog = consoleLog;
		
		setFrameIcon(ResourcesImages.gear());
		setTitle("Gerar Mapper");
		setIconifiable(true);
		setOpaque(true);		
		setClosable(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);

		JButton btnWorkspace = new JButton("Projeto");
		btnWorkspace.setPreferredSize(new Dimension(125, 23));
		btnWorkspace.setMaximumSize(new Dimension(150, 23));
		btnWorkspace.setBounds(new Rectangle(10, 20, 174, 34));
		btnWorkspace.setBackground(Color.WHITE);
		btnWorkspace.setBounds(10, 13, 136, 34);
		btnWorkspace.setIcon(ResourcesImages.report());
		getContentPane().add(btnWorkspace);

		btnWorkspace.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setCurrentDirectory(new File("."));
				int returnVal = fc.showOpenDialog(fc);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File dir = fc.getSelectedFile();
					inputProject.setText(dir.getPath() + "");
				}
			}
		});

		inputProject = new TextField();
		inputProject.setPreferredSize(new Dimension(10, 0));
		inputProject.setMaximumSize(new Dimension(10, 10));
		inputProject.setColumns(70);
		inputProject.setBounds(152, 15, 547, 30);
		getContentPane().add(inputProject);
		setPreferredSize(new Dimension(668, 400));
		setSize(new Dimension(1116, 587));
		setAutoscrolls(true);

		this.setBackground(Color.WHITE);

		JComboBox<PersonalItem> comboBox = new JComboBox<PersonalItem>();
		//comboBox.setEnabled(false);

		try {
			Dao<BancoDadosEntity, Integer> bancoDadosDao = DaoManager.createDao(SqliteHelper.connectionSource,
					BancoDadosEntity.class);

			List<BancoDadosEntity> listBancoDados = bancoDadosDao.queryBuilder().orderBy("nome", true).query();

			PersonalItem item = new PersonalItem();
			item.setName("Selecione...");
			item.setValue(null);

			comboBox.addItem(item);

			for (Iterator iterator = listBancoDados.iterator(); iterator.hasNext();) {
				BancoDadosEntity bancoDados = (BancoDadosEntity) iterator.next();
				item = new PersonalItem();
				item.setName(bancoDados.getNome() + " (" + bancoDados.getNameBd() + ")");
				item.setValue(bancoDados);
				comboBox.addItem(item);
			}

		} catch (SQLException e) {
			consoleLog.setText("Erro: " + e.getMessage());
			e.printStackTrace();
		}

		comboBox.addItemListener(new ItemListener() {


			public void itemStateChanged(ItemEvent item) {
				PersonalItem personalItem = (PersonalItem) item.getItem();
				BancoDadosEntity bancoDados = (BancoDadosEntity) personalItem.getValue();

				if (bancoDados != null) {					

					if (bancoDados != null) {
						updateGrid(bancoDados);
					}
				} else {
					DefaultTableModel dtm = new DefaultTableModel();
					table.setModel(dtm);
				}

			}

		});

		comboBox.setBounds(10, 145, 689, 30);
		getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("Banco de Dados");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 123, 386, 14);
		getContentPane().add(lblNewLabel);




		table = new JTable();
		table.setBounds(0, 0, 500, 300);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 186, 689, 269);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		JLabel lblPackageEntity = new JLabel("Package Entity");
		lblPackageEntity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPackageEntity.setBounds(10, 58, 386, 14);
		getContentPane().add(lblPackageEntity);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(715, 11, 10, 535);
		getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Gerar");
		btnNewButton.setBounds(307, 497, 89, 23);
		getContentPane().add(btnNewButton);
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 1, 1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table_1);
		scrollPane_1.setBounds(731, 45, 359, 201);
		getContentPane().add(scrollPane_1);
		
		
		table_2 = new JTable();
		table_2.setBounds(0, 0, 1, 1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(731, 319, 359, 201);
		scrollPane_1_1.setViewportView(table_2);
		getContentPane().add(scrollPane_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Tipos Replace");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(734, 23, 138, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name Table Replace");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(734, 299, 356, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(983, 13, 48, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("+");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1.setBounds(1042, 13, 48, 23);
		getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("-");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_2.setBounds(983, 290, 48, 23);
		getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("+");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1_1.setBounds(1042, 290, 48, 23);
		getContentPane().add(btnNewButton_1_1_1);
		
		textField = new JTextField();
		textField.setBounds(10, 83, 689, 30);
		getContentPane().add(textField);
		textField.setColumns(10);


	}


	private void updateGrid(BancoDadosEntity bancoDadosEntity) {

		ListTableController controller = new ListTableController(bancoDadosEntity, table, true, this.consoleLog, "");
		controller.run();
	}
}
