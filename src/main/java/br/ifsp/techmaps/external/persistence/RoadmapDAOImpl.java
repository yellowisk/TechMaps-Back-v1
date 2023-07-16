package br.ifsp.techmaps.external.persistence;

import br.ifsp.techmaps.domain.entities.roadmap.Roadmap;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapLanguage;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapStatus;
import br.ifsp.techmaps.domain.entities.roadmap.RoadmapType;
import br.ifsp.techmaps.usecases.roadmap.gateway.RoadmapDAO;
import br.ifsp.techmaps.external.persistence.util.JsonUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class RoadmapDAOImpl implements RoadmapDAO {

    private final JdbcTemplate jdbcTemplate;
    private final JsonUtil jsonUtil;

    @Value("${queries.sql.roadmap-dao.exists.roadmap-by-id}")
    private String existsRoadmapQuery;

    @Value("${queries.sql.roadmap-dao.insert.roadmap}")
    private String insertRoadmapQuery;

    @Value("${queries.sql.roadmap-dao.select.roadmap-by-id}")
    private String selectRoadmapByIdQuery;

    @Value("${queries.sql.roadmap-dao.update.complete-roadmap}")
    private String updateRoadmapStatusAndCommitCounterQuery;

    public RoadmapDAOImpl(JdbcTemplate jdbcTemplate, JsonUtil jsonUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtil = jsonUtil;
    }

    @Override
    public Boolean RoadmapExists(UUID roadmapId) {
        return jdbcTemplate.queryForObject(existsRoadmapQuery, Boolean.class, roadmapId);
    }

    @Override
    public Roadmap saveRoadmap(Roadmap roadmap) {

        UUID roadmapId = UUID.randomUUID();

        jdbcTemplate.update(insertRoadmapQuery, roadmapId, roadmap.getTitle(), roadmap.getType().name(),
                roadmap.getRoadmapStatus().name(), roadmap.getRoadmapLanguage().name(),
                Timestamp.valueOf(roadmap.getStartTime()), roadmap.getFinishTime(),
                roadmap.getRoadmapCommits(), roadmap.getDashboardId());

        return roadmap.getNewInstanceWithOnlyId(roadmapId);
    }

    @Override
    public List<Roadmap> findAll(UUID roadmapId) {
        return null;
    }

    @Override
    public Optional<Roadmap> findRoadmapById(UUID roadmapId) {
        try {
            Roadmap roadmap = jdbcTemplate.queryForObject(selectRoadmapByIdQuery,
                    this::mapperRoadmapFromRs, roadmapId);

            if (Objects.isNull(roadmap)) {
                throw new IllegalStateException();
            }

            return Optional.of(roadmap);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Roadmap> findAllRoadmapStagesByRoadmapId(UUID roadmapId) {
        return null;
    }

    @Override
    public Roadmap findRoadmapStageByStageId(UUID roadmapId) {
        return null;
    }

    @Override
    public Roadmap updateRoadmap(Roadmap roadmap) {
        jdbcTemplate.update(updateRoadmapStatusAndCommitCounterQuery, roadmap.getRoadmapStatus().name(),
                roadmap.getRoadmapCommits(), Timestamp.valueOf(LocalDateTime.now()),
                roadmap.getRoadmapId());
        return roadmap;
    }

    public Roadmap mapperRoadmapFromRs(ResultSet rs, int rowNum) throws SQLException {
        UUID id = (UUID) rs.getObject("id");
        String description = rs.getString("title");
        RoadmapType type = RoadmapType.valueOf(rs.getString("type"));
        RoadmapStatus status = RoadmapStatus.valueOf(rs.getString("status"));
        RoadmapLanguage language = RoadmapLanguage.valueOf(rs.getString("lang"));
        Timestamp startTime = rs.getTimestamp("start_time");
        int commit_counter = rs.getInt("commit_counter");
        UUID dashboardId = (UUID) rs.getObject("dashboard_id");

        return Roadmap.createWithoutStageAndFinishTime(id, description, type, status, language,
                startTime.toLocalDateTime(), commit_counter, dashboardId);
    }

}
