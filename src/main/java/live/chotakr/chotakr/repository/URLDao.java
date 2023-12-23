package live.chotakr.chotakr.repository;

import live.chotakr.chotakr.model.ChotakrRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Slf4j
public class URLDao {

    private static JdbcTemplate jdbcTemplate;

    public URLDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long getNextID() {
        String query = "SELECT nextval('chotakr_url_id')";
        return jdbcTemplate.queryForObject(query, Long.class);
    }

    public void saveUrl(String key, ChotakrRequest chotakrRequest) {

        String query = "INSERT INTO chotakr_url "
                + "(id,validform,validto,url) "
                + "values (?,?,?,?)";
        log.info("Saving: {} at {}", chotakrRequest.url(), key);
        Boolean b = jdbcTemplate.execute(query, new PreparedStatementCallback<Boolean>() {
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {

                ps.setString(1, key);
                ps.setObject(2, chotakrRequest.validFrom());
                ps.setObject(3, chotakrRequest.validTill());
                ps.setString(4, chotakrRequest.url());
                return ps.execute();

            }
        });
    }

    public static String getUrl(Long id) throws Exception {
        String query =  "SELECT url FROM chotakr_url WHERE id=?";
        String strprepStmtArgs = ""+id;
        String url = jdbcTemplate.queryForObject(query, String.class, new Object[] {strprepStmtArgs});
        log.info("Retrieved {} at {}", url ,id);
        if (url == null) {
            throw new Exception("URL at key" + id + " does not exist");
        }
        return url;
    }


}
