package top.gjp0609.webtools.controller.dto;

import java.util.Arrays;

public class FaceArgs {

    private Long time;
    private String cid;
    private String image;
    private String[] position;
    private String[] faces;

    @Override
    public String toString() {
        return "FaceArgs{" +
                "time=" + time +
                ", cid='" + cid + '\'' +
                ", image='" + image + '\'' +
                ", position=" + Arrays.toString(position) +
                ", faces=" + Arrays.toString(faces) +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public String[] getFaces() {
        return faces;
    }

    public void setFaces(String[] faces) {
        this.faces = faces;
    }
}
