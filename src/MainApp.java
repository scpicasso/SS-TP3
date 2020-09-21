import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import tp2.FileParser;
import tp2.LatticeGasSimulator;
import tp2.NodeManager;
import tp2.Particle;

public class MainApp {
	
	private static String input_file = "input.txt";
	private static int size = 200;
	private static int step, stepl;
	private static boolean corte;
	private static double auxValues[];
	private static int valuesIdx = 0;
	
	public static void main(String[] args) throws IOException {
		List<Particle> particles = null;
		FileParser fp = new FileParser();
		
		try {
	    	particles = fp.getParticles(input_file);            
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
				
		CollisionSystem cs = new CollisionSystem(particles, 0.24, 0.09, fp.getGap());
		
		for (int i = 0; i < 2000; i++) {
			cs.findNextEventForAllParticles();
		}		
		
//		for (int i = 0; i < 2000; i++) {
//			writeParticlesToFile(particles, nodes, fp.getN(), size);
//		    nodes = fluidSimulator.getFutureNodes(nodes, size);		        
//		}

		
		
//		try (Writer writer3 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("20001.txt"), "utf-8"))) {
//		
//			corte = false;
//			step = 0;
//			for (int k = 0; k < size; k++) {
//				auxNodes[k] = nodes[k].clone();        
//			}
//			try (Writer writer1 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("fpa4.txt"), "utf-8"))) {
//			try (Writer writer2 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("fpb4.txt"), "utf-8"))) {
//				for (int i = 0; i < 10000; i++) {
//					writeParticleFractionFile(particles, auxNodes, size, writer1, writer2, writer3);
//				    auxNodes = fluidSimulator.getFutureNodes(auxNodes, size);		
//				}
//			} 	
//			}
//
//		
//		}
				
	}
	
	public static void writeParticlesToFile(List<Particle> particles, int n, int size) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
//		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output_t" + step + ".txt"), "utf-8"))) {
//			writer.write(String.valueOf(nodes.length * nodes[0].length) + "\n" + "\n");
//		    		
//		}			    	
//	    step++;
	}
	
	public static void writeParticleFractionFile(List<Particle> particles, short[][] nodes, int size, Writer w1, Writer w2, Writer w3) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
//		double n = 0, a = 0, b = 0, fpa = 0, fpb = 0;	
//
//	    fpa = a/n;
//	    fpb = b/n;
//		w1.write(String.valueOf(step) + "\t" + String.valueOf(fpa) + "\n");
//		w2.write(String.valueOf(step) + "\t" + String.valueOf(fpb) + "\n");
//	    
//		
//		auxValues[valuesIdx++] = fpa;
//		
//		
//		if (valuesIdx == 500)
//			valuesIdx = 0;
//		
//		
//		if (calculateSD(auxValues) < 0.01 && !corte) {
//    		w3.write(String.valueOf(step) + " ");
//			System.out.println(step);	
//			corte = true;
//		}
//
//	    step++;
	}
	
    public static double calculateSD(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

}
