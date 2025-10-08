public class Card extends Calculator{
    public Card(){
        this.cardName = "Dark Magician";
        this.numberOfCopies = 3;
        this.minDesired = 1;
        this.maxDesired = 3;
    }
    public Card(String cardName, int numberOfCopies, int minDesired, int maxDesired){
        if(numberOfCopies < 0 || minDesired < 0 || maxDesired < 0){
            throw new IllegalArgumentException("Invalid negative parameter");
        }
        if(minDesired > maxDesired || minDesired > numberOfCopies || maxDesired > numberOfCopies){
            throw new IllegalArgumentException("Invalid desired parameter");
        }
        this.cardName = cardName;
        this.numberOfCopies = numberOfCopies;
        this.minDesired = minDesired;
        this.maxDesired = maxDesired;
    }
}
