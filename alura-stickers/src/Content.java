public class Content {

    private final String title;
    private final String imgUrl;  
    private final int rating;  

    public Content(String title, String imgUrl, int rating) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.rating = rating;
    }

    public Content(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.rating = 0;
    }

    public String getTitle() {
        return title;
    }
    public String getImgUrl() {
        return imgUrl;
    } 
    public int getRating() {
        return rating;
    } 
}
