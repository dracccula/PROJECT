package kireev.ftshw.project.Network.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutResponse {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("html")
    @Expose
    private String html;
    @SerializedName("delta")
    @Expose
    private Delta delta;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    public class Attributes {

        @SerializedName("header")
        @Expose
        private Integer header;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("list")
        @Expose
        private String list;
        @SerializedName("blockquote")
        @Expose
        private Boolean blockquote;
        @SerializedName("bold")
        @Expose
        private Boolean bold;
        @SerializedName("color")
        @Expose
        private String color;

        public Integer getHeader() {
            return header;
        }

        public void setHeader(Integer header) {
            this.header = header;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }

        public Boolean getBlockquote() {
            return blockquote;
        }

        public void setBlockquote(Boolean blockquote) {
            this.blockquote = blockquote;
        }

        public Boolean getBold() {
            return bold;
        }

        public void setBold(Boolean bold) {
            this.bold = bold;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

    }

    public class Delta {

        @SerializedName("ops")
        @Expose
        private List<Object> ops = null;

        public List<Object> getOps() {
            return ops;
        }

        public void setOps(List<Object> ops) {
            this.ops = ops;
        }

        public class Ops {

            @SerializedName("insert")
            @Expose
            private String insert;
            @SerializedName("attributes")
            @Expose
            private Attributes attributes;

            public String getInsert() {
                return insert;
            }

            public void setInsert(String insert) {
                this.insert = insert;
            }

            public Attributes getAttributes() {
                return attributes;
            }

            public void setAttributes(Attributes attributes) {
                this.attributes = attributes;
            }

        }
    }
}