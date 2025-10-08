import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private int deckSize;
    private int handSize;
    protected String cardName;
    protected int numberOfCopies;
    protected int minDesired;
    protected int maxDesired;
    private ArrayList<Card> cards =  new ArrayList<>();

    public Calculator(){
        this.deckSize = 40;
        this.handSize = 5;
    }

    public Calculator(int deckSize, int handSize) {
        if(handSize > deckSize){
            throw new IllegalArgumentException("Invalid hand size");
        }
        this.deckSize = deckSize;
        this.handSize = handSize;
    }

    public double nCr(int n, int r){
        double product = 1.0;
        if(n-r < 0){
            return 0;
        }
        else{
            for(int k = 1; k <= r; k++){
                product *= (double) (n - k + 1) /k;
            }
        }
        return product;
    }

    public double calcSingleVar(){
        double sum = 0;
        for(int i = minDesired; i <= maxDesired; i++){
            sum += (double) (nCr(numberOfCopies, i) * nCr(deckSize - numberOfCopies, handSize - i)) / nCr(deckSize, handSize);
        }
        return sum;
    }

    public double calcMultiVar(){
        if(cards.isEmpty()){
            return 0;
        }
        double product = 1.0;
        for(int i = 0; i < cards.size(); i++){
            product *= cards.get(i).calcSingleVar();
        }
        return product;

    }

    public void surveyCard(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter card name: ");
        String cardName = scan.nextLine();
        System.out.print("Enter number of copies: ");
        int numberOfCopies = scan.nextInt();
        System.out.print("Enter minimum number of desired cards: ");
        int minDesired = scan.nextInt();
        System.out.print("Enter maximum number of desired cards: ");
        int maxDesired = scan.nextInt();
        cards.add(new Card(cardName, numberOfCopies, minDesired, maxDesired));
    }
    public static Calculator buildCalc(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter deck size: ");
        int deckSize = scan.nextInt();
        System.out.print("Enter hand size: ");
        int handSize = scan.nextInt();
        return new Calculator(deckSize, handSize);
    }

    public void builder(Calculator calc){
        Scanner scan = new Scanner(System.in);
        if(scan.next().equalsIgnoreCase("Y")){
            calc.surveyCard();
        }
        else if(scan.next().equalsIgnoreCase("N")){

        }
        else{
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public static void main(String[] args) {
        Calculator calc = buildCalc();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.print("Add card? (Y/N): ");
            String input = scan.nextLine().trim();

            if(input.equalsIgnoreCase("Y")){
                calc.surveyCard();
            }
            else if(input.equalsIgnoreCase("N")){
                break;
            }
            else{
                System.out.println("Invalid input!");
            }
        }

        if(calc.cards.isEmpty()){
            System.out.println("No cards found!");
        } else {
            System.out.println("Probability: " + calc.calcMultiVar() * 100 + "%");
        }
    }


}
