package sk.stuba.fei.team.local.api;

import java.util.Collection;
import java.util.Date;

public class UpdateWrapper<T> {
    private Collection<T> ids;
    private Date timestamp;

    public UpdateWrapper() {}

    public UpdateWrapper(Collection<T> ids, Date timestamp) {
        this.ids = ids;
        this.timestamp = timestamp;
    }

    public Collection<T> getIds() {
        return ids;
    }

    public void setIds(Collection<T> ids) {
        this.ids = ids;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
