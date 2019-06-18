package rezaei.sevice;


import java.util.List;

public class errorResult extends Throwable {
    public String msg;
    public String data;

    public errorResult(String msg, String data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
