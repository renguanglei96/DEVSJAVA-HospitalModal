package HospitalModel;
import GenCol.DEVSQueue;
import GenCol.entity;
import genDevs.modeling.content;
import genDevs.modeling.message;
import simView.ViewableAtomic;
public class Transducer extends ViewableAtomic {
	String IN = "IN", OUT = "OUT";
	String ACTIVE = "ACTIVE", OFF = "OFF", ON = "ON", BUSY = "BUSY";
	int totalPatients=0;
	entity message = null;
	public Transducer() {
		this("Transducer");
	}

	public Transducer(String name) {
		super(name);
		addInport(IN);
		addOutport(OUT);
	}
	
	public void initialize() {
		holdIn(ACTIVE, 100);
	}
	
	public void deltext(double e, message x) {
		Continue(e);
		for (int i = 0; i < x.size(); i++) {
			if (messageOnPort(x, IN, i)) {
				message = x.getValOnPort(IN, i);
				totalPatients++;
			}
		}
	}
	
	public void deltint() {
		holdIn(ACTIVE, 100);
	}
	
	public message out() {
		message m = new message();
		if (phaseIs(ACTIVE)) {
			m.add(makeContent(OUT, new entity(Integer.toString(totalPatients))));
		}
		return m;
	}
	
}
