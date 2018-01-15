package com.xiaoxiao.view.dragon;

/**
 * Created by caixiaoxiao on 1/1/18.
 */

public class NotificationBean {
    public final int TYPE_DRAGON = 1;
    public final int TYPE_NOBLE = 2;

    private int type = TYPE_DRAGON;
    private String from;
    private String to;
    boolean isStealthy;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isStealthy() {
        return isStealthy;
    }

    public void setStealthy(boolean stealthy) {
        isStealthy = stealthy;
    }
}
