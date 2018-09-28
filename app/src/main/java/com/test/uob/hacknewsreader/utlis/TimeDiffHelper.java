package com.test.uob.hacknewsreader.utlis;

import java.util.Date;

public class TimeDiffHelper {
    private static TimeDiffHelper instance;

    public static TimeDiffHelper getInstance() {
        if (instance == null)
            instance = new TimeDiffHelper();
        return instance;
    }

    public String timeDiffConverter(long time) {
        String timeDiff = "";
        Date now = new Date();
        long diff = now.getTime() - time*1000;
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays > 0) {
            timeDiff = diffDays < 2 ? diffDays + " day ago" : diffDays + " days ago";
        } else if (diffHours > 0) {
            timeDiff = diffHours < 2 ? diffHours + " hour ago" : diffHours + " hours ago";
        } else if (diffMinutes > 0) {
            timeDiff = diffMinutes < 2 ? diffMinutes + " minute ago" : diffMinutes + " minutes ago";
        } else {
            timeDiff = diffSeconds < 2 ? diffSeconds + " second ago" : diffSeconds + " seconds ago";
        }
        return timeDiff;
    }
}
