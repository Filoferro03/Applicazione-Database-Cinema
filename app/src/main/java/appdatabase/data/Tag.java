package appdatabase.data;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public final class Tag {
    public final String name;

    public Tag (String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        } else if (obj==null) {
            return false;
        } else if (obj instanceof Tag) {
            var t = (Tag) obj;
            return t.name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public final class DAO {
        public static Set<Tag> ofProduct (Connection connection, int productId) {
            var tags = new HashSet<Tag>();
            try (var statement = DAOUtils.prepare(connection, null, productId);
            var resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    var tagName = resultSet.getString("tag_name");
                    var tag = new Tag(tagName);
                    tags.add(tag);
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
            return tags;
        } 
    }
}
