package src;

import java.util.*;

public class CollisionSystem {
	
	private double length;
	private double height;
	List<Particle> particles;
	SortedSet<Event> events;
	double current_time;
	double gapSize;

	public CollisionSystem(List<Particle> particles, double length, double height, double gap) {
		this.particles = particles;
		this.events = new TreeSet<>(); 
		this.current_time = 0.0;
		this.height = height;
		this.length = length;
		this.gapSize = gap;
	}

	public double getTime() {
		return current_time;
	}

	public void findEventsForParticle(Particle p) {
		double tx = p.xCollision(length, height, gapSize);
		double ty = p.yCollision(height);

		if(tx >= 0)
			events.add(new Event(null, p, tx));
		if(ty >= 0)
			events.add(new Event(p, null, ty));

		for(Particle j : particles) {
			double tc = p.timeToCollision(j);
			if(tc >= 0)
				events.add(new Event(p, j, tc));
		}
		return;
	}

	public void findEventsForAllParticles() {
		for(Particle p : particles) {
			findEventsForParticle(p);
		}
		return;
	}

	public void nextEvent() {
		if(events.size() == 0) {
			return;
		}

		int counter = 0;
		Event event = events.first();
		while(event.wasSuperveningEvent()) {
			events.remove(event);
			event = events.first();
		}
		putEventInAction(event);
		events.remove(event);
		return;
	}

	public void putEventInAction(Event e) {
		Particle a = e.getParticle1();
		Particle b = e.getParticle2();
		double new_time = e.getTime();
		if(a == null && b == null) {
			return;
		}
		if(a!= null)
			a.addCollision();
		if(b != null)
			b.addCollision();
		moveParticles(new_time);
		current_time = current_time + new_time;
		if(a == null && b != null) {
			b.bounceBackX();
		}
		if(a != null && b == null) {
			a.bounceBackY();
		}
		if(a != null && b!= null) {
			a.bounce(b);

		}
		events.clear(); 
		findEventsForAllParticles();
		return;
	}

	public void moveParticles(double time) {
		for(Particle p : particles) {
			double new_x = p.getVelocityX()*time;
			double new_y = p.getVelocityY()*time;
			if(p.getCounter() == 0)
				p.addTrajectory(new_x, new_y);
			p.setX(p.getX() + new_x);
			p.setY(p.getY() + new_y);
		}
		return;
	}

}