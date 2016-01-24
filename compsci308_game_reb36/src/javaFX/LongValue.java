package javaFX;

public class LongValue
{
    private long value;
    
    public LongValue(long i)
    {
        setValue(i);
    }

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}