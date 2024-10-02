package appdatabase.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public final class ScreeningPreview {
    public final int seatNum;
    public final String title;
    public final Date date;
    public final Time hour;
    public final String location;

    public ScreeningPreview(String title, int seatNum, Date date, Time hour, String location) {
        this.seatNum=seatNum;
        this.title=title;
        this.date=date;
        this.hour=hour;
        this.location=location;
    } 

    public final class DAO {
        public final List<ScreeningPreview> list (Connection connection) {
            var previews = new ArrayList<ScreeningPreview>();
            try (var statement = DAOUtils.prepare(connection, Queries.LIST_SCREENING);
            var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    var seatNum = resultSet.getInt("postiDisponibili");
                    var title = resultSet.getString("titolo");
                    var date = resultSet.getDate("data");
                    var hour = resultSet.getTime("ora");
                    var location = resultSet.getString("nomeSede");
                    var preview = new ScreeningPreview(title, seatNum, date, hour, location);
                    previews.add(preview); 
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            return previews;
        }
    }
}
