package opinion;

import java.util.LinkedList;

public class Item {
    private String title;
    private String kind;
    private LinkedList<Review> reviewItemList;

    public Item(String title, String kind){
        this.title = title;
        this.kind = kind;
        reviewItemList = new LinkedList<Review>();
    }

    public String getTitle() {
        return title;
    }

    public String getKind() {
        return kind;
    }

    public LinkedList<Review> getReviewItemList() {
        return reviewItemList;
    }
    
    public double getAverageScore() {
        if (reviewItemList.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Review review : reviewItemList) {
            sum += review.getMark();
        }
        return (double) sum / reviewItemList.size();
    }
}
