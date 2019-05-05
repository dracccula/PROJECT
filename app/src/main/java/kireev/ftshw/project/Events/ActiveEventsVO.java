package kireev.ftshw.project.Events;

public class ActiveEventsVO {

    private String title;
    private String dateStart;
    private String dateEnd;
    private String eventTypeName;
    private String eventTypeColor;
    private String customDate;
    private String place;
    private String url;
    private Boolean urlExternal;
    private Boolean displayButton;
    private String urlText;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }


    public String getCustomDate() {
        return customDate;
    }

    public void setCustomDate(String customDate) {
        this.customDate = customDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getUrlExternal() {
        return urlExternal;
    }

    public void setUrlExternal(Boolean urlExternal) {
        this.urlExternal = urlExternal;
    }

    public Boolean getDisplayButton() {
        return displayButton;
    }

    public void setDisplayButton(Boolean displayButton) {
        this.displayButton = displayButton;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeColor() {
        return eventTypeColor;
    }

    public void setEventTypeColor(String eventTypeColor) {
        this.eventTypeColor = eventTypeColor;
    }
}
