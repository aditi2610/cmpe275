package chapter1;

public class SequenceGenerator {
	
	private String suffix;
	
	private String prefix;
	private int initial;
	private int counter;
	
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setInitial(int initial) {
		this.initial = initial;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public SequenceGenerator() {}
	
	public SequenceGenerator(String prefix, String suffix, int initial) {	
		this.prefix = prefix;
		this.suffix = suffix;
		this.initial = initial;
	
	}
	public synchronized String getSequence() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(prefix);
		buffer.append(initial + counter++);
		buffer.append(suffix);
		return buffer.toString();
	}

}
