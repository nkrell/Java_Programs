package Timer;
//import Timer.Lab5;

public class Timer implements Runnable
{
	private final int threadID;
	private Long startTime;

	public Timer(int threadID)
	{
		this.threadID = threadID;
		this.startTime = System.currentTimeMillis();
	}

	public Long currentTime()
	{
		return((System.currentTimeMillis() - startTime) / 1000);
	}

	public boolean isTimeUp()
	{
		if ((System.currentTimeMillis() - startTime) >= 30000)
		{
			return(true);
		}
		else
		{
			return(false);
		}
	}

	//@Override
	public void run()
	{
		while (!(isTimeUp()))
		{
			System.out.println(currentTime());
			//window.aaTimeUpdate(currentTime);
		}
	}
}