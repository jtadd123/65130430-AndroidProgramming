package dat.nguyenvan.vieccanlam;

import java.io.Serializable;
import java.util.HashMap;

public class Tasks implements Serializable {
    String name;
    String date;
    String message;
    String priority;

    public Tasks(String name, String priority, String message, String date) {
        this.name = name;
        this.priority = priority;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public HashMap<String, Object> toFirebaseObject() {
        HashMap<String, Object> taskObject = new HashMap<String, Object>();
        taskObject.put("name", name);
        taskObject.put("priority", priority);
        taskObject.put("message", message);
        taskObject.put("date", date);
        return taskObject;
    }
}
