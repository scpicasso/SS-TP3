import java.util.*;

public class CollisionSystem {
	
	private double length;
	private double height;
	List<Particle> particles;
	List<Event> events;
	double current_time;
	double gapSize;

	public CollisionSystem(List<Particle> particles, double length, double height, double gap) {
		this.particles = particles;
		this.events = new ArrayList<Event>();
		this.current_time = 0;
		this.height = height;
		this.length = length;
		this.gapSize = gap;
	}

	public double getTime() {
		return current_time;
	}

	public void findEventsForParticle(Particle p) {
		events.add(new Event(null, p, p.xCollision(length, gapSize) + current_time));
		events.add(new Event(p, null, p.yCollision(height) + current_time));
		for(Particle j : particles) {
			events.add(new Event(p, j, p.timeToCollision(j) + current_time));
		}
		return;
	}

	public void findEventsForAllParticles() {
		for(Particle p : particles) {
			findEventsForParticle(p);
		}
		Collections.sort(events);
		return;
	}

	public void nextEvent() {
		if(events.size() == 0) {
			return;
		}
		Event e = events.get(0);
		while(e.wasSuperveningEvent()) {
			events.remove(0);
			e = events.get(0);
		}

		putEventInAction(e);
		events.remove(0);
		Collections.sort(events);
		return;
	}

	public void putEventInAction(Event e) {
		Particle a = e.getParticle1();
		Particle b = e.getParticle2();
		double new_time = e.getTime();
		if(a == null && b == null) {
			return;
		}
		moveParticles(new_time - current_time);
		current_time = new_time;
		if(a == null && b != null) {
			b.bounceBackX();
			b.addCollision();
			findEventsForParticle(b);
		}
		else if(a != null && b == null) {
			a.bounceBackY();
			a.addCollision();
			findEventsForParticle(a);
		}
		else {
			a.bounce(b);
			a.addCollision();
			b.addCollision();
			findEventsForParticle(a);
			findEventsForParticle(b);
		}
		return;
	}

	public void moveParticles(double time) {
		for(Particle p : particles) {
			double displacement_x = p.getVelocityX()*time;
			double displacement_y = p.getVelocityY()*time;
			p.setX(p.getX() + displacement_x);
			p.setY(p.getY() + displacement_y);
		}
		return;
	}

}