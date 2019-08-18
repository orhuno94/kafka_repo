package producer;

public class Message {
    String datetime;
    String level;
    String server;
    String detail;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getJson(){
        return "{"+
                "\"datetime\":\""+this.datetime+"\","+
                "\"level\":\""+this.level+"\","+
                "\"server\":\""+this.server+"\","+
                "\"detail\":\""+this.detail+"\""+
                "}";
    }
}
