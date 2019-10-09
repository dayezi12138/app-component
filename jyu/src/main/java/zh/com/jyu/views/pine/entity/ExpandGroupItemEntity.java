package zh.com.jyu.views.pine.entity;


import java.util.List;

/**
 * 每个分组对应的entity
 *
 * @param <G> 标题栏entity
 * @param <S> 子项entity
 */
public class ExpandGroupItemEntity<G, S> implements Comparable<ExpandGroupItemEntity> {

    /**
     * 分组对应的标题栏
     */
    private G mParent;
    /**
     * 分组里面的子项
     */
    private List<S> mChildList;
    /**
     * 分组展开还是收起
     */
    private boolean mExpand;

    public G getParent() {
        return mParent;
    }

    public void setParent(G parent) {
        mParent = parent;
    }

    public List<S> getChildList() {
        return mChildList;
    }

    public void setChildList(List<S> childList) {
        mChildList = childList;
    }

    public boolean isExpand() {
        return mExpand;
    }

    public void setExpand(boolean expand) {
        mExpand = expand;
    }

    @Override
    public int compareTo(ExpandGroupItemEntity entity) {
        String t1 = this.getParent().toString().substring(4, this.getParent().toString().indexOf("-"));
        String t1p = this.getParent().toString().substring(this.getParent().toString().indexOf("-"), this.getParent().toString().length());
        String t2 = entity.getParent().toString().substring(4, this.getParent().toString().indexOf("-"));
        String t2p = entity.getParent().toString().substring(this.getParent().toString().indexOf("-"), this.getParent().toString().length());
        if (Integer.valueOf(t1) - Integer.valueOf(t2) < 0) {
            return -1;
        }
        if (Integer.valueOf(t1) - Integer.valueOf(t2) == 0) {
            return Integer.valueOf(t2p) - Integer.valueOf(t1p);
        }
        return 1;
    }
}
