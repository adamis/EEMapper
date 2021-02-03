package br.com.adamis.helper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import br.com.adamis.sqlite.entity.BancoDadosEntity;

public class SqliteHelper {

	public static ConnectionSource connectionSource;

	public static void init() throws SQLException, IOException {

		File dir = new File("bd");
		File file = new File("bd/esquadro.db");
		
		System.err.println(""+file.getAbsolutePath());
		System.err.println(""+file.exists());
		
		
		if (!file.exists()) {
			dir.mkdirs();
			file.createNewFile();
		}

		String databaseUrl = "jdbc:sqlite:" + file.getAbsolutePath();

		// create a connection source to our database
		connectionSource = new JdbcConnectionSource(databaseUrl);

		TableUtils.createTableIfNotExists(connectionSource, BancoDadosEntity.class);

	}

}
