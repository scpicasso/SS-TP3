package src;

import java.util.*;

public class CollisionSystem{
	
	ArrayList<Particle> particles;
	ArrayList<Event> events;

	public CollisionSystem(List<Particle> particles) {
		this.particles = particles;
		this.events = new ArrayList<Event>();
	}

	public void findNextEventAllParticles() {
		for(Particle p : particles) {
			double tx = p.xCollision();
			double ty = p.yCollision();
			double tp = 10000000;
			Particle collided = null;
			for(Particle j : particles) {
				if(tp > p.particleCollision(j)) {
					tp = p.particleCollision(j);
					collided = j;
				}
			}
			if(tx < ty && tx < tp && tx!=-1) {
				events.add(new Event(null, p, tx));
			}
			else if(ty < tx && ty < tp && ty!=-1) {
				events.add(new Event(p, null, ty));
			}
			else {
				events.add(new Event(p, collide, tp));
			}
		}
		Collections.sort(events);
		return;
	}
}