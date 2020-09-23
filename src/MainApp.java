package src;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

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
		cs.findEventsForAllParticles();
		
		int flag_counter = 0;
		while (flag_counter < 50) {
			writeParticlesToFile(particles, particles.size(), cs.getTime());
			cs.nextEvent();
			double frac_part = calculateFractionParticle(particles, particles.size());
			System.out.println(frac_part);
			System.out.println("\n" + cs.getTime() + "\n\n");
			if( frac_part < 0.6 && frac_part > 0.4 ) {
				flag_counter ++;
			}
			else {
				flag_counter = 0;
			}

		}
		
		
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
	
	public static void writeParticlesToFile(List<Particle> particles, int n, double time) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("output_t=" + time + ".txt"), "utf-8"))) {
			writer.write(String.valueOf(n) + "\n" + "\n");
			for(Particle p: particles) {
				writer.write(String.valueOf(p.getX()) + " " + String.valueOf(p.getY()) + " " 
					+ String.valueOf(p.getVelocityX()) + " " + String.valueOf(p.getVelocityY()) 
					+ " " + String.valueOf(p.getRadius()) + " " + String.valueOf(p.getMass()) + "\n");
			}
		    		
		}

		return;			    	
	}
	
	public static void writeParticleFractionFile(List<Particle> particles, short[][] nodes, int size, Writer w1, Writer w2, Writer w3) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		

	}
	
	public static double calculateFractionParticle(List<Particle> particles, int total_particles) {
		int fp = 0;
		for(Particle p: particles) {
			if(p.getX() >= 0.12) {
				fp ++;
			}
		}
		return fp/(double)(total_particles);
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
