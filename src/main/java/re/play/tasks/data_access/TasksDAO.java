package re.play.tasks.data_access;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import re.play.tasks.model.Task;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TasksDAO {

    private JdbcOperations jdbcOperations;
    private final static String INSERT_QUERY = "insert into tasks (body, completed) values (?, ?)";
    private final static String SELECT_ALL_TASKS_QUERY = "select * from tasks";
    private final static String SELECT_ONE_TASK_QUERY = "select * from tasks where id = ?";

    @Inject
    public TasksDAO(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public void addTask(Task task){
        jdbcOperations.update(
                INSERT_QUERY,
                task.getContent(),
                task.getCompleted());
    }

    public Task getTask(int id){
        return jdbcOperations.queryForObject(SELECT_ONE_TASK_QUERY, new TasksRowMapper(), id);
    }

    public List<Task> getAllTasks(){
        return jdbcOperations.query(SELECT_ALL_TASKS_QUERY, new TasksRowMapper());
    }


    private final static class TasksRowMapper implements RowMapper<Task>{

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setContent(resultSet.getString("body"));
            task.setCompleted(resultSet.getBoolean("completed"));
            return task;
        }
    }
}
