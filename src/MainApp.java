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
		double frac_part = 0;

		int flag_counter = 0;
		while (flag_counter < 50) {
			writeParticlesToFile(particles, particles.size(), cs.getTime());
			cs.nextEvent();
			frac_part = calculateFractionParticle(particles, particles.size());
			if( frac_part < 0.55 && frac_part > 0.45 ) {
				flag_counter ++;
			}
			else {
				flag_counter = 0;
			}
		}
				
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
	
	
	public static double calculateFractionParticle(List<Particle> particles, int total_particles) {
		int fp = 0;
		for(Particle p: particles) {
			if(p.getX() >= 0.12) {
				fp ++;
			}
		}
		return fp/(double)(total_particles);
	}

}
