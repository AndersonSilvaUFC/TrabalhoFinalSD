package model;

public class Message {
    private int type;
    private int id;
    private String objectRef;
    private String methodId;
    private String args;

    public Message() {
    }

    public Message(int type, int id, String objectRef, String methodId, String arguments) {
        this.type = type;
        this.id = id;
        this.objectRef = objectRef;
        this.methodId = methodId;
        this.args = arguments;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectRef() {
        return objectRef;
    }

    public void setObjectRef(String objectRef) {
        this.objectRef= objectRef;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
