package lk.ijse.orm.tm;

public class CourseTM {
    private String code;
    private String name;
    private String type;
    private String duration;

    public CourseTM() {
    }

    public CourseTM(String code, String name, String type, String duration) {
        this.setCode(code);
        this.setName(name);
        this.setType(type);
        this.setDuration(duration);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
