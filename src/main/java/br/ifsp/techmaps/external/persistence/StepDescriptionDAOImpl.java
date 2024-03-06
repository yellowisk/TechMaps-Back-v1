package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.step.StepDescription;
import br.ifsp.techmaps.domain.entities.task.TaskBody;
import br.ifsp.techmaps.usecases.step.gateway.StepDescriptionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class StepDescriptionDAOImpl implements StepDescriptionDAO {

    private final JdbcTemplate jdbcTemplate;

    public StepDescriptionDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${queries.sql.step-description-dao.select.step-description-by-task-info}")
    private String selectStepDescriptionByTaskInfoQuery;

    @Override
    public List<StepDescription> getStepDescriptions(TaskBody info) {
        List<StepDescription> descriptions = jdbcTemplate.query(selectStepDescriptionByTaskInfoQuery,
                this::mapperFromRs, info.name());
        return descriptions;
    }

    public StepDescription mapperFromRs(ResultSet rs, int rowNUm) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        TaskBody info = TaskBody.valueOf(rs.getString("info"));
        String description = rs.getString("description");
        String link = rs.getString("link");
        Integer number = rs.getInt("desc_number");
        return StepDescription.createFull(id, info, description, link, number);
    }

}
