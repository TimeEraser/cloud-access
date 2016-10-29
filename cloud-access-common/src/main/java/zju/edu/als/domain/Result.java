package zju.edu.als.domain;

/**
 * Created by zzq on 2016/10/29.
 */
public class Result {
    private boolean success;
    private String message;
    private Object data;

    public Result() {
        this.success = true;
    }

    public Result(Object data) {
        this.success = true;
        this.data = data;
    }

    public Result(boolean success, String message) {
        super();
        this.success = success;
        if (message != null) {
            this.message = message;
        }
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object data) {
        if (data == null) {
            throw new NullPointerException();
        }
        return new Result(data);
    }

    public static Result okMsg(String message) {
        return new Result(true, message);
    }

    public static Result fail(String msg) {
        if (msg == null) {
            msg = "NullPointException";
        }
        return new Result(false, msg);
    }

    public static Result fail(Throwable throwable) {
        String s = throwable.getMessage();
        return fail(s);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message != null) {
            this.message = message;
        }
    }

    public void setAll(boolean success, String message) {
        this.success = success;
        if (message != null) {
            this.message = message;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
