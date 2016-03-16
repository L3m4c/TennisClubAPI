package Dto;

public class InformationTicketDto {

    private long id;
    private String title;
    private String content;
    private long authorId;

    public InformationTicketDto() {}

    public InformationTicketDto(long id, String title, String content, long authorId) {
        this.setId(id);
        this.setTitle(title);
        this.setContent(content);
        this.setAuthorId(authorId);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
