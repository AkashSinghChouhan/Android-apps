package training.sati.com.newsreader;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * Created by acer on 10/19/2016.
 */

public class News {
    String headline;
    String summery; String newslink;

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }





    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }


}
