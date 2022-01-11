import algorithms.Strategy1;
import algorithms.Strategy2;
import algorithms.Strategy3;
import simulator.RandomDataCreator;
import structure.CpuSystem;

public class Test {
    public static void main(String[] args) {

        CpuSystem system = new CpuSystem(50) ; //Liczba procesorow(N)
        RandomDataCreator generator = new RandomDataCreator() ;
        generator.createRandomProcessList(10000, 200, 20, 3, system); //Ilosc cykli | max czas wykonania procesu | max wymagana moc | czestotliwosc zapytan | 

        int border = 10 ; //granica(p)
        int z = 10 ; // ilosc losowan(z)
        int min = 5 ; //minimalna granica(r)

        system.restart() ;
        Strategy1 s1 = new Strategy1() ;
        s1.simulate(system, generator, border, z);
        s1.printRaport() ;

        system.restart();
        Strategy2 s2 = new Strategy2() ;
        s2.simulate(system, generator, border, z);
        s2.printRaport() ;

        system.restart();
        Strategy3 s3 = new Strategy3() ;
        s3.simulate(system, generator, border, z, min);
        s3.printRaport() ;

        

        
    }
}
