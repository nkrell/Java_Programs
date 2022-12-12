

public class Multi_Thread implements Runnable
{
	public static void main(String[] args) 
	{
		Multi_Thread threadM = new Multi_Thread();
		Thread	t1 = new Thread(threadM);
		Thread	t2 = new Thread(threadM);
		Thread	t3 = new Thread(threadM);
		t1.start();
		t2.start();
		t3.start();
	}
}