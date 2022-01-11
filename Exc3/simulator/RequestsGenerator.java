package simulator ;
import computer.* ;
import java.util.ArrayList;
import java.util.Random;

public class RequestsGenerator {
    private int requestsNumber ;
    private int pagesTypesNumber ;
    private Random r ;
    private ArrayList<PageIdentifier> requestsList ;

    public RequestsGenerator(int requestsNumber, int pagesTypesNumber){
        this.requestsNumber = requestsNumber ;
        this.pagesTypesNumber = pagesTypesNumber ;
        r = new Random() ;
    }

    public ArrayList<PageIdentifier> getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(ArrayList<PageIdentifier> requestsList) {
        this.requestsList = requestsList;
    }

    public void generateRequestsList(){
        ArrayList<PageIdentifier> newRequestsList = new ArrayList<PageIdentifier>() ;
        int range = pagesTypesNumber*pagesTypesNumber*pagesTypesNumber*pagesTypesNumber ;
        for(int i=0; i<requestsNumber; i++){
            double randomNumber = r.nextInt(range) ;
            int id = (int) Math.sqrt(Math.sqrt(randomNumber)) ;
            newRequestsList.add(new PageIdentifier(id)) ;
        }
        setRequestsList(newRequestsList) ;
    }
}
