package Model;

/**
 * Created by achourasiya on 15-03-2018.
 */

public class Rating {
    private String userEmail;
    private String FoodId;
    private String rateValue;
    private String comment;

    public Rating() {
    }

    public Rating(String userEmail, String foodId, String rateValue, String comment) {
        this.userEmail = userEmail;
        FoodId = foodId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFoodId() {
        return FoodId;
    }

    public void setFoodId(String foodId) {
        FoodId = foodId;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
