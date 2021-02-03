/**
 * 
 */
package br.com.adamis;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.adamis.helper.SqliteHelper;
import br.com.adamis.utils.Styles;
import br.com.adamis.view.Menu;


/**
 * @author Adami
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException, SQLException, IOException {
		UIManager.setLookAndFeel(Styles.style);
		SqliteHelper.init();
		Menu window = new Menu();
		window.setVisible(true);
	}

}
