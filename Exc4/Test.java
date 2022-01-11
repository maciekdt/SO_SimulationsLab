import algorithms.EqualAllocation;
import algorithms.ProportionalAllocation;
import computer.Memory;
import symulator.Generator;

public class Test {
    public static void main(String[] args) {
        Generator generator = new Generator() ;
        generator.generate(10, 1000, 100);
        int memorySize = 200 ;

        Memory memory = new Memory(memorySize, generator.getProcessList()) ;
        EqualAllocation equalAllocation = new EqualAllocation() ;
        System.out.print("\nPrzydzial rowny : " + equalAllocation.run(generator.getReqestList(), memory)) ;
        
        memory = new Memory(memorySize, generator.getProcessList()) ;
        ProportionalAllocation proportionalAllocation = new ProportionalAllocation() ;
        System.out.print("\nPrzydzial proporcjonalny : " + proportionalAllocation.run(generator.getReqestList(), memory, generator.getPagesNumber())) ;
        

    }
}
