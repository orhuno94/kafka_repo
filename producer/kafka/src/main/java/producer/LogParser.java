package producer;

public class LogParser {
    public static String parse(String line){
        Message message = new Message();
        String[] stringArr=line.split("\\s",5);
        message.setDatetime(stringArr[0]+" "+stringArr[1]);
        message.setLevel(stringArr[2]);
        message.setServer(stringArr[3]);
        message.setDetail(stringArr[4]);
        return message.getJson();
    }
}
