package com.qsxuan.recentcontests.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Contest数据模型
 *
 * @author qinshaoxuan
 */

@DatabaseTable(tableName = "contests")
public class Contest {
    @DatabaseField(columnName = "id", id = true)
    private String id;
    @DatabaseField(columnName = "oj")
    private String oj;
    @DatabaseField(columnName = "name")
    private String name;
    @SerializedName("start_time")
    @DatabaseField(columnName = "start_time", index = true)
    private String startTime;
    @DatabaseField(columnName = "week")
    private String week;
    @DatabaseField(columnName = "access")
    private String access;
    @DatabaseField(columnName = "link")
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOj() {
        return oj;
    }

    public void setOj(String oj) {
        this.oj = oj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getWeek() {
//        switch (week) {
//            case "MON": return "星期一";
//            case "TUE": return "星期二";
//            case "WED": return "星期三";
//            case "THU": return "星期四";
//            case "FRI": return "星期五";
//            case "SAT": return "星期六";
//            case "SUN": return "星期天";
//        }
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getAccess() {
        if (access.isEmpty()) {
            return "Public";
        }
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "id='" + id + '\'' +
                ", oj='" + oj + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", week='" + week + '\'' +
                ", access='" + access + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
