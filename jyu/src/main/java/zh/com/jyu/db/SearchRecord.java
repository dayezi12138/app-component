package zh.com.jyu.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : dayezi
 * data :2019/5/5
 * description:搜索记录
 */
@Entity
public class SearchRecord {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "searchValue")
    private String searchValue;

    @Generated(hash = 755186315)
    public SearchRecord(Long id, String searchValue) {
        this.id = id;
        this.searchValue = searchValue;
    }

    @Generated(hash = 839789598)
    public SearchRecord() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchValue() {
        return this.searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
