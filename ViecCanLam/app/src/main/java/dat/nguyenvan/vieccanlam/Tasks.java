package dat.nguyenvan.vieccanlam;

import java.io.Serializable;
import java.util.HashMap;

public class Tasks implements Serializable {
    String name;
    String date;
    String message;
    String prority;

    public Tasks(String name, String prority, String message, String date) {
        this.name = name;
        this.prority = prority;
        this.message = message;
        this.date = date;
    }

    public Tasks() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrority() {
        return prority;
    }

    public void setPrority(String prority) {
        this.prority = prority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public HashMap <String,String> toFirebaseObject(){
        HashMap<String,String> taskObject = new HashMap<String,String>();
        taskObject.put("name",name);
        taskObject.put("prority",prority);
        taskObject.put("message",message);
        taskObject.put("date",date);
        return taskObject;
    }
}

