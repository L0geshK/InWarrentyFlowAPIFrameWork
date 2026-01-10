package com.Inwarrenty.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import com.Inwarrenty.database.DataBaseManagerWithHikariCP;
import com.Inwarrenty.db.model.MapJobProblemDBModel;

import io.qameta.allure.Step;

public class MapJobProblemDao {
	private static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(MapJobProblemDao.class);

	private static final String PROBLEM_QUERY = """
			SELECT * from map_job_problem where tr_job_head_id=?;
			""";

	private MapJobProblemDao() {

	}

	@Step("Reterving Data from DB for MapJobProblemDBModel")
	public static MapJobProblemDBModel getProblemDetails(int tr_job_head_id) {
		log.info("Entry");

		Connection conn = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		MapJobProblemDBModel mapjobproblemmodel = null;
		try {
			conn = DataBaseManagerWithHikariCP.getConnecction();
			statement = conn.prepareStatement(PROBLEM_QUERY);
			statement.setInt(1, tr_job_head_id);
			result = statement.executeQuery();
			while (result.next()) {

				mapjobproblemmodel = new MapJobProblemDBModel(result.getInt("id"), result.getInt("tr_job_head_id"),
						result.getInt("mst_problem_id"), result.getString("remark"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Exit");
		return mapjobproblemmodel;

	}
}
