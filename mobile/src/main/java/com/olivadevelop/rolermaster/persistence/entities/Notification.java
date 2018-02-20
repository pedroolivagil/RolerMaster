package com.olivadevelop.rolermaster.persistence.entities;

import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Id;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.OneToOne;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.Persistence;
import com.olivadevelop.rolermaster.olivaobjectpersistence.annotations.RelatedEntity;
import com.olivadevelop.rolermaster.persistence.pojo.NotificationPriority;
import com.olivadevelop.rolermaster.tools.Tools;
import com.olivadevelop.rolermaster.olivaobjectpersistence.entities._BasicEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Copyright OlivaDevelop 2014-2018
 * Created by Oliva on 26/01/2018.
 */

@Persistence(collectionName = "notification")
public class Notification extends _BasicEntity {

    public static final boolean STATUS_READ = true;
    public static final boolean STATUS_UNREAD = false;

    private boolean status;

    @Id
    private Integer idNotification;

    @OneToOne
    @RelatedEntity(joinColumn = "fromUserId")
    private User fromUser;

    @OneToOne
    @RelatedEntity(joinColumn = "toUserId")
    private User toUser;

    private Date date;
    private String title;
    private String body;
    private NotificationPriority priority;

    public Notification() {
    }

    public Notification(JSONObject json) throws JSONException {
        super(json);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public NotificationPriority getPriority() {
        if (Tools.isNull(priority)) {
            priority = NotificationPriority.NONE;
        }
        return priority;
    }

    public void setPriority(NotificationPriority priority) {
        this.priority = priority;
    }
}
