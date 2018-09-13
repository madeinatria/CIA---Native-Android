package atria.communities.cia;

public class ReportModel {

    String id;
    String title;
    String content;
    String timestamp;
    String update;

    public ReportModel(String id, String title,String content, String timestamp) {
        this.id = id;
        this.title = title;
        this.content =content;
        this.timestamp = timestamp;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}